package com.example.obvious.Views

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.obvious.R
import com.example.obvious.Views.Adapter.ImageAdapter
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class MainActivityTest{


    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(MainActivity::class.java,true,false)

    @Test
    fun testSample() {
        assertEquals(getRVcount(),26)
        if (getRVcount() > 0) {
            onView(withId(R.id.imagesrv))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ImageAdapter.ImageViewHolder>(0, click()))
        }
    }

    private fun getRVcount(): Int {
        val recyclerView =
            mActivityRule.activity.findViewById<View>(R.id.imagesrv) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

}