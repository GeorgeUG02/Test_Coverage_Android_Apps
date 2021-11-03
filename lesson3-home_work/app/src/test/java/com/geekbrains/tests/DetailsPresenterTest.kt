package com.geekbrains.tests

import android.os.Build
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.DetailsActivity
import org.junit.After
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsPresenterTest {
    private lateinit var presenter: DetailsPresenter
    private lateinit var scenario: ActivityScenario<DetailsActivity>
    @Before
    fun setUp(){
        presenter= DetailsPresenter()
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        scenario.onActivity {
            presenter.onAttach(it)
        }
    }
    @After
    fun close(){
        scenario.onActivity {
            presenter.onDetach(it)
        }
        scenario.close()
    }
    @Test
    fun setCounter_Test(){
        presenter.setCounter(42)
        presenter.onIncrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: 43", totalCountTextView.text)
        }
    }
    @Test
    fun onIncrement_Test(){
        presenter.onIncrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: 1", totalCountTextView.text)
        }
    }
    @Test
    fun onDecrement_Test(){
        presenter.onDecrement()
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            assertEquals("Number of results: -1", totalCountTextView.text)
        }
    }
}