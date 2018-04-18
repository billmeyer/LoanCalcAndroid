package io.billmeyer.loancalc;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

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

//@LargeTest
//@RunWith(AndroidJUnit4.class)
public class MainActivityTest
{

//    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

//    @Test
    public void mainActivityTest()
    {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.etLoanAmount), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 0), 1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("25000"), closeSoftKeyboard());

        ViewInteraction editText = onView(allOf(withId(R.id.etLoanAmount), withText("25000"),
                childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 0), 1), isDisplayed()));
        editText.check(matches(withText("25000")));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.etEditInterest), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 1), 1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("3.42"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(allOf(withId(R.id.etEditInterest), withText("3.42"),
                childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 1), 1), isDisplayed()));
        editText2.check(matches(withText("3.42")));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.etSalesTax), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 1), 4), isDisplayed()));
        appCompatEditText3.perform(replaceText("8"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.etSalesTax), withText("8"), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 1), 4),
                        isDisplayed()));
        editText3.check(matches(withText("8")));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.etTerm), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 2), 1), isDisplayed()));
        appCompatEditText4.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.etTerm), withText("60"), childAtPosition(childAtPosition(withId(R.id.llLoanInputs), 2), 1),
                        isDisplayed()));
        editText4.check(matches(withText("60")));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.etDownPayment), childAtPosition(childAtPosition(withId(R.id.llReductions), 1), 1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("500"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(allOf(withId(R.id.etDownPayment), withText("500"),
                childAtPosition(childAtPosition(withId(R.id.llReductions), 1), 1), isDisplayed()));
        editText5.check(matches(withText("500")));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.etTradeIn), childAtPosition(childAtPosition(withId(R.id.llReductions), 2), 1), isDisplayed()));
        appCompatEditText6.perform(replaceText("7500"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.etTradeIn), withText("7500"), childAtPosition(childAtPosition(withId(R.id.llReductions), 2), 1),
                        isDisplayed()));
        editText6.check(matches(withText("7500")));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.etFees), childAtPosition(childAtPosition(withId(R.id.llReductions), 3), 1), isDisplayed()));
        appCompatEditText7.perform(replaceText("300"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.etFees), withText("300"), childAtPosition(childAtPosition(withId(R.id.llReductions), 3), 1),
                        isDisplayed()));
        editText7.check(matches(withText("300")));

        pressBack();

        ViewInteraction appCompatButton = onView(allOf(withId(R.id.btnCalculate), withText("CALCULATE"),
                childAtPosition(childAtPosition(withId(R.id.fragment_container), 0), 3), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(allOf(withId(R.id.tvLoanTotal), withText("$20,370.97"), childAtPosition(
                allOf(withId(R.id.llLoanOutputs),
                        childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class), 0)), 0),
                isDisplayed()));
        textView.check(matches(withText("$20,370.97")));

        ViewInteraction textView2 = onView(allOf(withId(R.id.tvMonthlyPaymentVal), withText("$339.52"),
                childAtPosition(allOf(withId(R.id.ll1), childAtPosition(withId(R.id.llLoanOutputs), 1)), 1), isDisplayed()));
        textView2.check(matches(withText("$339.52")));

        ViewInteraction textView3 = onView(allOf(withId(R.id.tvLoanInterestVal), withText("$1,670.97"),
                childAtPosition(allOf(withId(R.id.ll2), childAtPosition(withId(R.id.llLoanOutputs), 2)), 1), isDisplayed()));
        textView3.check(matches(withText("$1,670.97")));

        ViewInteraction textView4 = onView(allOf(withId(R.id.tvLoanTotalCostVal), withText("$28,370.97"),
                childAtPosition(allOf(withId(R.id.ll3), childAtPosition(withId(R.id.llLoanOutputs), 3)), 1), isDisplayed()));
        textView4.check(matches(withText("$28,370.97")));

    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position)
    {

        return new TypeSafeMatcher<View>()
        {
            @Override
            public void describeTo(Description description)
            {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view)
            {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
                        ((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
