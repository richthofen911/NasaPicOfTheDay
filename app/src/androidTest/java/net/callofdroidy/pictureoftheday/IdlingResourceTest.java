package net.callofdroidy.pictureoftheday;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.callofdroidy.pictureoftheday.view.ActivityMain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IdlingResourceTest {
    @Rule
    public ActivityTestRule rule = new ActivityTestRule<>(ActivityMain.class, true);

    @Before
    public void registerIdlingResource(){
        Espresso.registerIdlingResources(((ActivityMain)rule.getActivity()).simpleIdlingResource);
    }

    @Test
    public void testShowPicTitle(){
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
    }
}
