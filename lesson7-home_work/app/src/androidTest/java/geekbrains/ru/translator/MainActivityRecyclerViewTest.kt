package geekbrains.ru.translator

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import geekbrains.ru.translator.view.main.MainActivity
import geekbrains.ru.translator.view.main.adapter.MainAdapter
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityRecyclerViewTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @Test
    fun activitySearch_PerformClickAtPosition() {
            scenario.onActivity {
                it.model.getData("Apple",true)
            }
            onView(isRoot()).perform(delay())
            onView(withId(R.id.main_activity_recyclerview))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<MainAdapter.RecyclerItemViewHolder>(
                        0,
                        ViewActions.click()
                    )
                )
        onView(isRoot()).perform(delay())
        onView(withId(R.id.description_textview)).check(matches(withText("яблоко, яблоня")))
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