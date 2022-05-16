package com.raywenderlich.android.emojicalculator

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.raywenderlich.android.emojicalculator.ScreenRobot.Companion.withRobot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest() {

    @get:Rule //https://developer.android.com/training/testing/instrumented-tests/androidx-test-libraries/rules
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {

        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.inputAmount))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenOkayButtonIsPressedAndAmountIsFilledTipIsSet() {
        ActivityScenario.launch(MainActivity::class.java)

        withRobot(CalculatorScreenRobot::class.java)
            .inputCheckAmountAndSelectOkayButton("11")
            .verifyTipIsCorrect("1.98")
    }

    class CalculatorScreenRobot : ScreenRobot<CalculatorScreenRobot>() {

        fun verifyTipIsCorrect(tip: String): CalculatorScreenRobot {
            return checkViewHasText(R.id.textTip, tip)
        }

        fun inputCheckAmountAndSelectOkayButton(input: String):
            CalculatorScreenRobot {
            return enterTextIntoView(R.id.inputAmount, input)
                .clickOkOnView(R.id.buttonOkay)
        }
    }
}