package com.raywenderlich.android.emojicalculator

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.raywenderlich.android.emojicalculator.ScreenRobot.Companion.withRobot
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*This tells JUnit to run the tests inside
of AndroidJUnit4 instead of the runner built into JUnit.
AndroidJUnit4 is a test runner that
runs JUnit style tests on Android devices.
When used in Espresso tests, it controls launching
the app and running UI tests.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }
    //Before you run your test, you should turn off animations on your testing device.
    //Window animation scale.
    //Transition animation scale.
    //Animator duration scale.

    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        // 1
        ActivityScenario.launch(MainActivity::class.java)

        // 2
        onView(withId(R.id.inputAmount))
            // 3
            .check(matches(isDisplayed()))
    }

    @Test
    fun onLaunchOkayButtonIsDisplayed() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withText(R.string.okay))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenOkayButtonIsPressedAndAmountIsEmptyTipIsEmpty() {
        ActivityScenario.launch(MainActivity::class.java)

        // 1
        onView(withId(R.id.buttonOkay))
            .perform(click())

        // 2
        onView(allOf(withId(R.id.textTip), withText("")))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenOkayButtonIsPressedAndAmountIsFilledTipIsSet() {
        ActivityScenario.launch(MainActivity::class.java)

        withRobot(CalculatorScreenRobot::class.java)
            .inputCheckAmountAndSelectOkayButton("11")
            .verifyTipIsCorrect("1.98")

    }

    @Test
    fun whenOkayButtonIsPressedAndRoundSwitchIsSelectedAmountIsCorrect() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.switchRound))
            .perform(click())
        onView(withId(R.id.inputAmount))
            .perform(typeText("11"))
        onView(withId(R.id.buttonOkay))
            .perform(click())

        onView(withId(R.id.textTip))
            .check(matches(withText("2.00")))
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