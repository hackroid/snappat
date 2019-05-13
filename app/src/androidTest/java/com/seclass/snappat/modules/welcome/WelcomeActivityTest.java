package com.seclass.snappat.modules.welcome;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
public class WelcomeActivityTest {

  @Rule
  public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(
      WelcomeActivity.class);

  @Test
  public void welcomeActivityTest() {
    ViewInteraction clearEditText = onView(
        allOf(withId(R.id.mobile_phone), withText("13028871392"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    clearEditText.perform(replaceText("1302887139"));

    ViewInteraction clearEditText2 = onView(
        allOf(withId(R.id.mobile_phone), withText("1302887139"),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    clearEditText2.perform(closeSoftKeyboard());

    ViewInteraction clearEditText3 = onView(
        allOf(withId(R.id.mobile_phone),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    clearEditText3.perform(click());

    ViewInteraction clearEditText4 = onView(
        allOf(withId(R.id.mobile_phone),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    clearEditText4.perform(click());

    ViewInteraction clearEditText5 = onView(
        allOf(withId(R.id.mobile_phone),
            childAtPosition(
                childAtPosition(
                    withId(android.R.id.content),
                    0),
                2),
            isDisplayed()));
    clearEditText5.perform(click());
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
