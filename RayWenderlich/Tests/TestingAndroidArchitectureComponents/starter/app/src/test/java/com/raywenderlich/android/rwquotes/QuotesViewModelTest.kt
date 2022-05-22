/*
 * Copyright (c) 2022 Razeware LLC
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

package com.raywenderlich.android.rwquotes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.raywenderlich.android.rwquotes.data.Quote
import com.raywenderlich.android.rwquotes.data.QuotesRepositoryImpl
import com.raywenderlich.android.rwquotes.data.RWQuotesDatabase
import com.raywenderlich.android.rwquotes.ui.viewmodel.QuotesViewModel
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.IOException


// 1
@RunWith(AndroidJUnit4::class)
class QuotesViewModelTest {

  // 2
  @Mock
  private lateinit var viewModel: QuotesViewModel

  @Mock
  private lateinit var isLoadingLiveData: LiveData<Boolean>

  @Mock
  private lateinit var observer: Observer<in Boolean>

  // 3:Define the InstantTaskExecutorRule to run any executors synchronously
  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  // 4
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    //rovides ability to retrieve the current application Context in tests.
    viewModel = spy(QuotesViewModel(ApplicationProvider.getApplicationContext(),
        QuotesRepositoryImpl(ApplicationProvider.getApplicationContext())))
    isLoadingLiveData = viewModel.dataLoading
  }

  // Your tests go here ...
  /**
   * Testing *onChanged()* method for [LiveData]
   *
   */
  @Test
  fun `Verify livedata values changes on event`() {
    assertNotNull(viewModel.getAllQuotes())
    viewModel.dataLoading.observeForever(observer)
    verify(observer).onChanged(false)
    viewModel.getAllQuotes()
    verify(observer).onChanged(true)
  }

  /**
   * Test asserting values for [LiveData] items on [QuotesViewModel] to insert [Quote]
   *
   */
  @Test
  fun `Assert loading values are correct fetching quotes`() {
    // 1
    val testQuote = Quote(id = 1, text = "Hello World!", author = "Ray Wenderlich",
        date = "27/12/1998")
    // 2
    var isLoading = isLoadingLiveData.value
    // 3
    assertNotNull(isLoading)
    // 4
    isLoading?.let { assertTrue(it) }
    // 5
    viewModel.insertQuote(testQuote)
    // 6
    isLoading = isLoadingLiveData.value
    assertNotNull(isLoading)
    isLoading?.let { assertFalse(it) }
  }

  /**
   * Test asserting values for [LiveData] items on [QuotesViewModel] to delete [Quote]
   *
   */

  //https://stackoverflow.com/questions/52533878/mockito-error-in-spring-boot-tests-after-migrating-to-jdk-11/52537324#52537324
  @Test
  fun `Assert loading values are correct deleting quote`() {
    // 1
    val testQuote = Quote(id = 1, text = "Hello World!", author = "Ray Wenderlich",
        date = "27/12/1998")
    // 2
    var isLoading = isLoadingLiveData.value
    // 3
    assertNotNull(isLoading)
    // 4
    isLoading?.let { assertTrue(it) }
    // 5
    viewModel.delete(testQuote)
    // 6
    isLoading = isLoadingLiveData.value
    assertNotNull(isLoading)
    isLoading?.let { assertFalse(it) }
  }

  /**
   * Test asserting values for [LiveData] items on [QuotesViewModel] to update [Quote]
   *
   */
  @Test
  fun `Assert loading values are correct updating quote`() {
    // 1
    val testQuote = Quote(id = 1, text = "Hello World!", author = "Ray Wenderlich",
        date = "27/12/1998")
    // 2
    var isLoading = isLoadingLiveData.value
    // 3
    assertNotNull(isLoading)
    // 4
    isLoading?.let { assertTrue(it) }
    // 5
    viewModel.updateQuote(testQuote)
    // 6
    isLoading = isLoadingLiveData.value
    assertNotNull(isLoading)
    isLoading?.let { assertFalse(it) }
  }

  @RunWith(AndroidJUnit4::class)
  abstract class DatabaseTest {
    protected lateinit var appDatabase: RWQuotesDatabase

    @Before
    fun initDb() {
      appDatabase = Room.inMemoryDatabaseBuilder(
          ApplicationProvider.getApplicationContext(),
          RWQuotesDatabase::class.java)
          .allowMainThreadQueries()
          .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
      appDatabase.close()
    }
  }






}
