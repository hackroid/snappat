package com.sustech.snappat.LoginActivityUITest;

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

import com.sustech.snappat.MainActivity;
import com.sustech.snappat.R;


@RunWith(AndroidJUnit4.class)
public class LoginActivityChangeUITest {

    @Rule
    public IntentsTestRule<LoginActivity> loginActivityActivityTestRule = new IntentsTestRule <>(
            LoginActivity.class);

    @Test
    public void loginSuccessTest() {
        onView(withId(R.id.phonenumber))
                .check(matches((isDisplayed())));

        onView(withId(R.id.verifycode))
                .check(matches((isDisplayed())));
        onView(withId(R.id.sendcode_button))
                .check(matches((isDisplayed())));
        onView(withId(R.id.login_button))
                .check(matches((isDisplayed())));

        onView(withId(R.id.login_button)).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        intending(hasComponent(MainActivity.class.getName()));
    }
}