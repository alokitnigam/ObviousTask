package com.example.obvious.Views

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.obvious.R
import org.junit.Rule
import org.junit.Test

class ImageDetailsActivityTest{
    @get:Rule
    public var activityRule :ActivityTestRule<ImageDetailsActivity>
                            = ActivityTestRule(ImageDetailsActivity::class.java, true, false)

    @Test
    fun positionIntentNotFound() {
        val intent = Intent()
        intent.putExtra("position", 0)
        activityRule.launchActivity(null)
        onView(withId(R.id.image_title))
            .check(matches(withText("Starburst Galaxy M94 from Hubble")))


    }



}