package com.seclass.snappat.modules.welcome;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.seclass.snappat.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest3 {

  @Rule
  public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(
      WelcomeActivity.class);

  @Test
  public void welcomeActivityTest3() {
    ViewInteraction clearEditText = onView(
        allOf(withId(R.id.sms_password),
            childAtPosition(
                childAtPosition(
                    withClassName(is("com.zhy.autolayout.AutoLinearLayout")),
                    5),
                0),
            isDisplayed()));
    clearEditText.perform(replaceText("3"), closeSoftKeyboard());

    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.create_account), withText("登录"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                8),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction clearEditText2 = onView(
        allOf(withId(R.id.sms_password), withText("3"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("com.zhy.autolayout.AutoLinearLayout")),
                    5),
                0),
            isDisplayed()));
    clearEditText2.perform(replaceText("33"));

    ViewInteraction clearEditText3 = onView(
        allOf(withId(R.id.sms_password), withText("33"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("com.zhy.autolayout.AutoLinearLayout")),
                    5),
                0),
            isDisplayed()));
    clearEditText3.perform(closeSoftKeyboard());
  }

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
