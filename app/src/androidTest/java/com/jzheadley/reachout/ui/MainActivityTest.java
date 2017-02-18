package com.jzheadley.reachout.ui;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jzheadley.reachout.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        onView(allOf(withId(R.id.test1), withText("borrower"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.submit_new_restaurant), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.three_button_loan_amount), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.three_button_repayment_amount), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.three_button_loan_length), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.three_button_money_making), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.three_button_loan_purchase), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.three_button_how_money_help), isDisplayed())).perform(click());


        // Submit the form
        onView(allOf(withId(R.id.btn_proposal_submit), withText("Submit"),
            withParent(allOf(withId(R.id.activity_proposal_creation),
                withParent(withId(android.R.id.content)))),
            isDisplayed())).perform(click());

    }

}
