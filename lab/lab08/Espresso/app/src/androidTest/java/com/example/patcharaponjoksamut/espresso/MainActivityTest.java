package com.example.patcharaponjoksamut.espresso;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private ArrayList<String> testName = new ArrayList<>(Arrays.asList(
            "Ying",
            "Somkait",
            "Prayoch",
            "Prayoch"
    ));

    private ArrayList<String> testAge = new ArrayList<>(Arrays.asList(
            "20",
            "80",
            "60",
            "50"
    ));

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCase1() {
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));
    }

    @Test
    public void testCase2() {
        onView(withId(R.id.editTextAge)).perform(replaceText("20"));
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testCase3() {
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withId(R.id.textNotFound)).check(matches(withText("Not Found")));
    }

    @Test
    public void testCase4() {
        onView(withId(R.id.editTextName)).perform(replaceText("Ying"));
        onView(withId(R.id.buttonAdded)).perform(click());
        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testCase5() {

        clearOldData();
        for(int i=0; i<1; i++) {
            addToList(i);
        }

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textName))
                .check(matches(withText("Ying")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textAge))
                .check(matches(withText("20")));
    }

    @Test
    public void testCase6() {

        clearOldData();

        for(int i=0; i<2; i++) {
            addToList(i);
        }

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textName))
                .check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textAge))
                .check(matches(withText("80")));
    }

    @Test
    public void testCase7() {

        clearOldData();

        for(int i=0; i<3; i++) {
            addToList(i);
        }

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textAge))
                .check(matches(withText("60")));
    }

    @Test
    public void testCase8() {
        clearOldData();
        for(int i=0; i<4; i++) {
            addToList(i);
        }

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textAge))
                .check(matches(withText("50")));
    }

    private void clearOldData() {
        onView(withId(R.id.buttonGotoList)).perform(click());
        onView(withId(R.id.button)).perform(click());
        pressBack();
    }

    private void addToList(int index) {
        onView(withId(R.id.editTextName)).perform(replaceText(testName.get(index)), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText(testAge.get(index)), closeSoftKeyboard());
        onView(withId(R.id.buttonAdded)).perform(click());
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
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
