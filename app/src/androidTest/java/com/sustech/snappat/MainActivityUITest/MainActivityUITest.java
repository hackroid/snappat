package com.sustech.snappat.MainActivityUITest;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.rule.ActivityTestRule;


import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import com.sustech.snappat.MainActivity;
import com.sustech.snappat.R;

import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import java.util.Collection;

import static androidx.test.runner.lifecycle.Stage.RESUMED;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void basicTest() {
//        onView(withId(R.id.navigation))
//                .check(matches((isDisplayed())));

//        onView(withId(R.id.message))
//                .check(matches((isDisplayed())));
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.login_button), withText("Login in"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(android.R.id.content),
//                                        0),
//                                3),
//                        isDisplayed()));
//        appCompatButton2.perform(click());

//        onView(withId(R.id.login_button))
//                .check(matches((isDisplayed())));
//
//        onView(withId(R.id.login_button)).perform(click());

        onView(withText("CameraButton")).check(matches((isDisplayed())));

        onView(withId(R.id.navigation_dashboard)).perform(click());

        onView(withId(R.id.message)).check(matches(withText(R.string.title_dashboard)));

        onView(withId(R.id.navigation_home)).perform(click());

        onView(withId(R.id.message)).check(matches(withText(R.string.title_home)));

        onView(withId(R.id.navigation_notifications)).perform(click());

        onView(withId(R.id.message)).check(matches(withText(R.string.title_notifications)));
    }
}
