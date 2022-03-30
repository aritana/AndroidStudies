/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.potterverse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @get:Rule
  val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

  private val mockWebServer = MockWebServer()

  @Before
  fun setup() {
    mockWebServer.start(8080)
    IdlingRegistry.getInstance().register(
        OkHttp3IdlingResource.create(
            "okhttp",
            OkHttpProvider.getOkHttpClient()
        )
    )
  }

  @Test
  fun testSuccessfulResponse() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse()
            .setResponseCode(200)
            .setBody(FileReader.readStringFromFile("success_response.json"))
      }
    }
    activityRule.launchActivity(null)

    onView(withId(R.id.progress_bar))
        .check(matches(withEffectiveVisibility(Visibility.GONE)))
    onView(withId(R.id.character_recyclerview))
        .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withId(R.id.textview))
        .check(matches(withEffectiveVisibility(Visibility.GONE)))
  }

  @Test
  fun testFailedResponse() {
    mockWebServer.dispatcher = object : Dispatcher() {
      override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
      }
    }

    activityRule.launchActivity(null)

    onView(withId(R.id.progress_bar))
        .check(matches(withEffectiveVisibility(Visibility.GONE)))
    onView(withId(R.id.character_recyclerview))
        .check(matches(withEffectiveVisibility(Visibility.GONE)))
    onView(withId(R.id.textview))
        .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    onView(withId(R.id.textview))
        .check(matches(withText(R.string.something_went_wrong)))
  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }
}
