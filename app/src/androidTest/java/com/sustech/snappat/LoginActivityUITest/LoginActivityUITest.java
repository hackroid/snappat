package com.sustech.snappat.LoginActivityUITest;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
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

import com.sustech.snappat.R;

import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import java.util.Collection;

import static androidx.test.runner.lifecycle.Stage.RESUMED;


@RunWith(AndroidJUnit4.class)
public class LoginActivityUITest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(
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

//        try {
//            Thread.sleep(700);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        onView(withText("Login Sucessfully")).inRoot(withDecorView(not(loginActivityActivityTestRule.getActivity().getWindow().getDecorView())))
//                .check(matches(isDisplayed()));
    }

    @Test
    public void loginFaildTest() {
        onView(withId(R.id.phonenumber))
                .check(matches((isDisplayed())));

        onView(withId(R.id.verifycode))
                .check(matches((isDisplayed())));
        onView(withId(R.id.sendcode_button))
                .check(matches((isDisplayed())));
        onView(withId(R.id.login_button))
                .check(matches((isDisplayed())));
        onView(withId(R.id.verifycode)).perform(setTextInTextView(""));
        onView(withId(R.id.login_button)).perform(click());
        onView(withText("Please wait for verify code and Input right code")).inRoot(withDecorView(not(loginActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginWrongFormatTest() {
        onView(withId(R.id.phonenumber))
                .check(matches((isDisplayed())));

        onView(withId(R.id.verifycode))
                .check(matches((isDisplayed())));
        onView(withId(R.id.sendcode_button))
                .check(matches((isDisplayed())));
        onView(withId(R.id.login_button))
                .check(matches((isDisplayed())));
        onView(withId(R.id.phonenumber)).perform(setTextInTextView("135305197"));
        onView(withId(R.id.sendcode_button)).perform(click());
        onView(withText("Wrong Phone Number!")).inRoot(withDecorView(not(loginActivityActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }


    Activity currentActivity = null;
    public Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities =
                        ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity = (Activity)resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }

    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

}
