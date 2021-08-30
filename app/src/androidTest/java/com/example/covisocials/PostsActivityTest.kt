package com.example.covisocials
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PostsActivityTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(PostsActivity::class.java)

    @Test
    fun sampleTest(){
        val activityScenario = ActivityScenario.launch(PostsActivity::class.java)
        onView(withId(R.id.postsMain))
                .check(matches(isDisplayed()))
    }

}
