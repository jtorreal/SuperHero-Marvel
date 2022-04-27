package com.jtorreal.superheromarvel.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.jtorreal.superheromarvel.MainActivity
import com.jtorreal.superheromarvel.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SuperHeroListFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun `onClickInItemList`() {

        onView(withId(R.id.rvMain)).check(ViewAssertions.matches(isDisplayed()));

        try {
            Thread.sleep(4000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rvMain))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<SuperHeroAdapterList.ViewHolder>(
                    10,
                    click()
                )
            );
    }

}

