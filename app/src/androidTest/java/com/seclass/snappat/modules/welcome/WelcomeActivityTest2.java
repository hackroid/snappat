//package com.seclass.snappat.modules.welcome;
//
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
//import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.TextView;
//import androidx.test.espresso.UiController;
//import androidx.test.espresso.ViewAction;
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
//import com.seclass.snappat.R;
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//
//import static org.hamcrest.Matchers.not;
//
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class WelcomeActivityTest2 {
//
//  @Rule
//  public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(
//      WelcomeActivity.class);
//
//  @Test
//  public void loginFaild() {
//    onView(withId(R.id.mobile_phone)).perform(setTextInTextView("13530519766"));
//    onView(withId(R.id.sms_password)).perform(setTextInTextView("3355"));
//    onView(withId(R.id.create_account)).perform(click());
//    onView(withText("Invalid Code")).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
//        .check(matches(isDisplayed()));
//  }
//
//  public void loginFaild2() {
//    onView(withId(R.id.mobile_phone)).perform(setTextInTextView("135305197"));
//    onView(withId(R.id.sms_password)).perform(setTextInTextView("135305197"));
//    onView(withId(R.id.create_account)).perform(click());
//    onView(withText("手机格式错误")).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
//        .check(matches(isDisplayed()));
//  }
//
//  public static ViewAction setTextInTextView(final String value) {
//    return new ViewAction() {
//      @SuppressWarnings("unchecked")
//      @Override
//      public Matcher<View> getConstraints() {
//        return allOf(isDisplayed(), isAssignableFrom(TextView.class));
//      }
//
//      @Override
//      public void perform(UiController uiController, View view) {
//        ((TextView) view).setText(value);
//      }
//
//      @Override
//      public String getDescription() {
//        return "replace text";
//      }
//    };
//  }
//}