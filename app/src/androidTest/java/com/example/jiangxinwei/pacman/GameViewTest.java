package com.example.jiangxinwei.pacman;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GameViewTest {

    @Rule
    public ActivityTestRule<GameActivity> mActivityTestRule =
            new ActivityTestRule<>(GameActivity.class, true, true);

    @Test
    public void DrawingTest(){
        onView(withId(R.id.pacmanView)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonUp)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonDown)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonLeft)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonRight)).check(matches(isDisplayed()));
    }

    @Test
    public void MovingTest(){

        GameActivity thisOne = mActivityTestRule.getActivity();
//        Pos p_pos_old = thisOne.getPacmanView().game.getPlayer().pos;
        onView(withId(R.id.buttonRight)).perform(click()).check(matches(withText("R")));
//        Pos p_pos_new = thisOne.getPacmanView().game.getPlayer().pos
        onView(withId(R.id.buttonRight)).perform(click()).check(matches(withText("R")));
        onView(withId(R.id.buttonDown)).perform(click()).check(matches(withText("D")));
        onView(withId(R.id.buttonLeft)).perform(click()).check(matches(withText("L")));
        onView(withId(R.id.buttonUp)).perform(click()).check(matches(withText("U")));

        int count = 0;
        Random rand = new Random();
        while (count<100){
            int next = rand.nextInt(4);
            switch (next){
                case 0:
                    onView(withId(R.id.buttonLeft)).perform(click()).check(matches(withText("L")));
                    break;
                case 1:
                    onView(withId(R.id.buttonRight)).perform(click()).check(matches(withText("R")));
                    break;
                case 2:
                    onView(withId(R.id.buttonUp)).perform(click()).check(matches(withText("U")));
                    break;
                case 3:
                    onView(withId(R.id.buttonDown)).perform(click()).check(matches(withText("D")));
                    break;
            }
        }

    }

}
