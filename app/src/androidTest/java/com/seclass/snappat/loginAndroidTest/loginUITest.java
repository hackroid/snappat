//package com.seclass.snappat.loginAndroidTest;
//
//import androidx.test.rule.ActivityTestRule;
//import androidx.test.runner.AndroidJUnit4;
//
//import com.seclass.snappat.R;
//import com.seclass.snappat.modules.login.LoginActivity;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.intent.Intents.intended;
//
//import static androidx.test.espresso.intent.Intents.intending;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//
//import androidx.test.espresso.intent.rule.IntentsTestRule;
//
//
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//import static org.hamcrest.Matchers.allOf;
//import static org.hamcrest.Matchers.not;
//
//
//
//
//
//@RunWith(AndroidJUnit4.class)
//public class loginUITest {
//  @Rule
//  public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(
//      LoginActivity.class);
//
//  @Test
//  public void loginSuccessTest() {
//
//    System.out.println("");
//    System.out.println("##########################");
//    onView(withId(R.id.mobile_phone))
//        .check(matches((isDisplayed())));
//
//    onView(withId(R.id.sms_password))
//        .check(matches((isDisplayed())));
//    onView(withId(R.id.get_code))
//        .check(matches((isDisplayed())));
//    onView(withId(R.id.check_box))
//        .check(matches((isDisplayed())));
//    onView(withId(R.id.create_account))
//        .check(matches((isDisplayed())));
//
//    onView(withId(R.id.create_account)).perform(click());
//
////        try {
////            Thread.sleep(700);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        onView(withText("Login Sucessfully")).inRoot(withDecorView(not(loginActivityActivityTestRule.getActivity().getWindow().getDecorView())))
////                .check(matches(isDisplayed()));
//  }
//
//}
