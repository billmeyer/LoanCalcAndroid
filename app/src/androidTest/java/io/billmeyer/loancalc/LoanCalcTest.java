package io.billmeyer.loancalc;

import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import io.billmeyer.loancalc.model.Loan;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by bmeyer on 4/17/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoanCalcTest
{
    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void calcLoanViaUI()
    {
        onView(withId(R.id.etLoanAmount)).perform(typeText("25000"), closeSoftKeyboard());
        onView(withId(R.id.etEditInterest)).perform(typeText("3.42"), closeSoftKeyboard());
        onView(withId(R.id.etSalesTax)).perform(typeText("8"), closeSoftKeyboard());
        onView(withId(R.id.etTerm)).perform(typeText("60"), closeSoftKeyboard());
        onView(withId(R.id.etDownPayment)).perform(typeText("500"), closeSoftKeyboard());
        onView(withId(R.id.etTradeIn)).perform(typeText("7500"), closeSoftKeyboard());
        onView(withId(R.id.etFees)).perform(typeText("300"), closeSoftKeyboard());

        takeScreenshot("BeforeCalc");
        onView(withId(R.id.btnCalculate)).perform(click());
        takeScreenshot("AfterCalc");

        onView(withId(R.id.tvLoanTotal)).check(matches(withText("$20,370.97")));
        onView(withId(R.id.tvMonthlyPaymentVal)).check(matches(withText("$339.52")));
        onView(withId(R.id.tvLoanInterestVal)).check(matches(withText("$1,670.97")));
        onView(withId(R.id.tvLoanTotalCostVal)).check(matches(withText("$28,370.97")));
    }

    @Test
    public void calcLoanViaAPI()
    {
        Loan loan = new Loan(25000.0D, 5, 3.42D, 500.0D, 7500.0D, 8.0D, 300.0D);

        Assert.assertEquals(loan.getTotalLoanPayments(), 20370.9651780305D);
        Assert.assertEquals(loan.getMonthlyPayment(), 339.51608630050833D);
        Assert.assertEquals(loan.getTotalLoanInterest(), 1670.9651780304994D);
        Assert.assertEquals(loan.getTotalCost(), 28370.9651780305D);
    }

    @Test
    public void simpleTest()
    {
        Assert.assertEquals(5 + 5, 10);
    }

    @Rule public TestRule watcher = new TestWatcher()
    {
        @Override
        protected void failed(Throwable e, Description description)
        {
            takeScreenshot(description);
//            // Save to external storage (usually /sdcard/screenshots)
//            File path = new File(
//                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenshots/" + InstrumentationRegistry
//                            .getTargetContext().getPackageName());
//            if (!path.exists())
//            {
//                path.mkdirs();
//            }
//
//            // Take advantage of UiAutomator screenshot method
//            android.support.test.uiautomator.UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//            String filename = description.getClassName() + "-" + description.getMethodName() + ".png";
//            device.takeScreenshot(new File(path, filename));
        }
    };

    protected void takeScreenshot(Description description)
    {
        takeScreenshot(description.getClassName() + "-" + description.getMethodName());
    }

    protected void takeScreenshot(String description)
    {
        // Save to external storage (usually /sdcard/screenshots)

        String strPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenshots/" + InstrumentationRegistry
                .getTargetContext().getPackageName();

        Log.i("LoanCalcTest", "Screenshot Path: " + strPath);

        File path = new File(strPath);
        if (!path.exists())
        {
            path.mkdirs();
        }

        // Take advantage of UiAutomator screenshot method
        android.support.test.uiautomator.UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        String filename = description + ".png";
        device.takeScreenshot(new File(path, filename));
    }

//    public static void screenshot(String screenshotName) throws Exception {
//        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
//        File e = getFilesDirectory(context, Locale.getDefault());
//        String screenshotFileName = System.currentTimeMillis() + "_" + screenshotName + ".png";
//        File screenshotFile = new File(e, screenshotFileName);
//
//        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(screenshotFile);
//    }
}
