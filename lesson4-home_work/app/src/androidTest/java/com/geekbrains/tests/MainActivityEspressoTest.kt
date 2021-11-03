package com.geekbrains.tests

import android.view.View
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }
    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }
    @Test
    fun searchEditText_NotNull() {
        scenario.onActivity {
            val searchEditTextView = it.findViewById<TextView>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditTextView)
        }
    }
    @Test
    fun toDetailsButton_NotNull() {
        scenario.onActivity {
            val toDetailsButtonView = it.findViewById<TextView>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsButtonView)
        }
    }
    @Test
    fun activityButton_HasText() {
        val assertion = matches(withText("to details"))
        onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }
    @Test
    fun activityEditText_IsCompletelyDisplayed() {
        onView(withId(R.id.searchEditText)).check(matches(isCompletelyDisplayed()))
    }
    @Test
    fun activityButton_IsCompletelyDisplayed() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(isCompletelyDisplayed()))
    }
    @Test
    fun activityButtonAndEditText_AreEffectiveVisible() {
        onView(withId(R.id.toDetailsActivityButton)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.searchEditText)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())
            onView(isRoot()).perform(delay())
            onView(withId(R.id.totalCountTextView)).check(matches(withText(R.string.number_of_repositories)))
    }

    private fun delay(): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $2 seconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(2000)
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
