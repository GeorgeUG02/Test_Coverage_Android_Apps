package geekbrains.ru.translator


import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import geekbrains.ru.translator.view.main.SearchDialogFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchDialogFragmentTest {
    private lateinit var scenario: FragmentScenario<SearchDialogFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer(Bundle(), R.style.AppTheme)
    }
    @Test
    fun fragment_testSearchButton() {
        val scenario = launchFragmentInContainer<SearchDialogFragment>(Bundle(), R.style.AppTheme)
        //Возможность менять стейт Фрагмента
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = ViewAssertions.matches(withText("Search"))
        Espresso.onView(withId(R.id.search_button_textview)).check(assertion)
    }
    @Test
    fun fragment_testSearch(){
        scenario.onFragment{
            val editText= it.activity?.findViewById<EditText>(R.id.search_edit_text)
            val searchButton=it.activity?.findViewById<TextView>(R.id.search_button_textview)
            editText?.setText("Hello")
            searchButton?.performClick()
        }
    }
}