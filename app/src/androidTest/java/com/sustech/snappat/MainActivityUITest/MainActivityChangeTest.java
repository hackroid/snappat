package com.sustech.snappat.MainActivityUITest;


import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import static android.support.test.InstrumentationRegistry.getTargetContext;

import static androidx.test.espresso.intent.Intents.intended;

import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import androidx.test.espresso.intent.rule.IntentsTestRule;


import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import com.sustech.snappat.CameraActivity.CameraActivity;
import com.sustech.snappat.MainActivity;
import com.sustech.snappat.R;

@RunWith(AndroidJUnit4.class)
public class MainActivityChangeTest {
    @Rule
    public IntentsTestRule<MainActivity> loginActivityActivityTestRule = new IntentsTestRule <>(
            MainActivity.class);

    @Test
    public void loginSuccessTest() {
        onView(withText("CameraButton")).perform(click());
        intending(hasComponent(CameraActivity.class.getName()));
    }
}
