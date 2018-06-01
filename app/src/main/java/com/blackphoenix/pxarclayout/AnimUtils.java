package com.blackphoenix.pxarclayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praba on 3/1/2018.
 */
public class AnimUtils {

    public static final String ROTATION = "rotation";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";




    private AnimUtils() {
        //No instances.
    }

    public static PropertyValuesHolder rotation(float... values) {
        return PropertyValuesHolder.ofFloat(ROTATION, values);
    }

    public static PropertyValuesHolder translationX(float... values) {
        return PropertyValuesHolder.ofFloat(TRANSLATION_X, values);
    }

    public static PropertyValuesHolder translationY(float... values) {
        return PropertyValuesHolder.ofFloat(TRANSLATION_Y, values);
    }

    public static PropertyValuesHolder scaleX(float... values) {
        return PropertyValuesHolder.ofFloat(SCALE_X, values);
    }

    public static PropertyValuesHolder scaleY(float... values) {
        return PropertyValuesHolder.ofFloat(SCALE_Y, values);
    }

    public static void showMenu(ArcLayout arcLayout, float x, float y, ArcMenuAnimator arcMenuAnimator) {
        showMenu(null,arcLayout,x,y,arcMenuAnimator);
    }

    public static void hideMenu(ArcLayout arcLayout, float x, float y, ArcMenuAnimator arcMenuAnimator) {
        hideMenu(null,arcLayout,x,y,arcMenuAnimator);
    }

    public static void showMenu(ArcLayout arcLayout, View fab, ArcMenuAnimator arcMenuAnimator) {
        showMenu(null,arcLayout,fab.getX(),fab.getY(),arcMenuAnimator);
    }

    public static void hideMenu(View menuLayout, ArcLayout arcLayout, View fab, ArcMenuAnimator arcMenuAnimator) {
        hideMenu(menuLayout,arcLayout,fab.getX(),fab.getY(),arcMenuAnimator);
    }

    public static void showMenu(View menuLayout, ArcLayout arcLayout, View fab, ArcMenuAnimator arcMenuAnimator) {
        showMenu(menuLayout,arcLayout,fab.getX(),fab.getY(),arcMenuAnimator);
    }

    public static void hideMenu(ArcLayout arcLayout, View fab, ArcMenuAnimator arcMenuAnimator) {
        hideMenu(null,arcLayout,fab.getX(),fab.getY(),arcMenuAnimator);
    }

//    @SuppressWarnings("NewApi")
//public static void showMenu(View menuLayout, ArcLayout arcLayout, View fab) {
    public static void showMenu(View menuLayout, ArcLayout arcLayout, float x, float y, ArcMenuAnimator arcMenuAnimator) {

        if(menuLayout!=null) {
            menuLayout.setVisibility(View.VISIBLE);
        }

        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {

            switch (arcMenuAnimator.getAnimType()) {
                case ArcMenuAnimator.ANIM_TYPE.ROTATION_TRANSLATE:
                    animList.add(createShowItemAnimator(arcLayout.getChildAt(i), x, y));
                    break;
                case ArcMenuAnimator.ANIM_TYPE.FADE:
                    animList.add(createShowItemAnimatorFadeIn(arcLayout.getChildAt(i)));
                    break;
                default:
                    animList.add(createShowItemAnimator(arcLayout.getChildAt(i), x, y));
            }
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(arcMenuAnimator.getAnimShowDuration());
        animSet.setInterpolator(new OvershootInterpolator());

        switch (arcMenuAnimator.getAnimPlayMode()){
            case ArcMenuAnimator.ANIM_PLAY_MODE.TOGETHER:
                animSet.playTogether(animList);
                break;
            case ArcMenuAnimator.ANIM_PLAY_MODE.SEQUENTIAL:
                animSet.playSequentially(animList);
                break;
            default:
                animSet.playTogether(animList);
        }

        animSet.start();
    }

 //   @SuppressWarnings("NewApi")
// public static void hideMenu(final View menuLayout, ArcLayout arcLayout, View fab)
    public static void hideMenu(final View menuLayout, ArcLayout arcLayout, float x, float y, ArcMenuAnimator arcMenuAnimator) {

        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            switch (arcMenuAnimator.getAnimType()) {
                case ArcMenuAnimator.ANIM_TYPE.ROTATION_TRANSLATE:
                    animList.add(createHideItemAnimator(arcLayout.getChildAt(i), x,y));
                    break;
                case ArcMenuAnimator.ANIM_TYPE.FADE:
                    animList.add(createHideItemAnimatorFadeOut(arcLayout.getChildAt(i)));
                    break;
                default:
                    animList.add(createHideItemAnimator(arcLayout.getChildAt(i), x,y));
            }

        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(arcMenuAnimator.getAnimHideDuration());
        animSet.setInterpolator(new AnticipateInterpolator());
        switch (arcMenuAnimator.getAnimPlayMode()){
            case ArcMenuAnimator.ANIM_PLAY_MODE.TOGETHER:
                animSet.playTogether(animList);
                break;
            case ArcMenuAnimator.ANIM_PLAY_MODE.SEQUENTIAL:
                animSet.playSequentially(animList);
                break;
            default:
                animSet.playTogether(animList);
        }
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(menuLayout!=null) {
                    menuLayout.setVisibility(View.INVISIBLE);
                }

            }
        });
        animSet.start();
    }

    public static Animator createShowItemAnimator(View item, View fab) {
        return createShowItemAnimator(item,fab.getX(),fab.getY());
    }

    public static Animator createHideItemAnimator(final View item, View fab) {
        return createHideItemAnimator(item,fab.getX(),fab.getY());
    }

    public static Animator createShowItemAnimator(final View item, float x, float y) {

        float dx = x - item.getX();
        float dy = y - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimUtils.rotation(0f, 720f),
                AnimUtils.translationX(dx, 0f),
                AnimUtils.translationY(dy, 0f)
        );


        /*anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                item.setVisibility(View.VISIBLE);
            }
        });*/

        return anim;
    }

    public static Animator createHideItemAnimator(final View item, float x, float y) {
        float dx = x - item.getX();
        float dy = y - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimUtils.rotation(720f, 0f),
                AnimUtils.translationX(0f, dx),
                AnimUtils.translationY(0f, dy)

        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

     /*   anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setVisibility(View.INVISIBLE);
            }
        });*/

        return anim;
    }

    public static Animator createShowItemAnimatorFadeIn(final View item) {

        Animator anim = ObjectAnimator.ofFloat(item, View.ALPHA, 0f, 1f);
        //  anim.setDuration(1000);
        /*anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //  item.setVisibility(View.VISIBLE);
            }
        });*/

        return anim;
    }

    public static Animator createHideItemAnimatorFadeOut(final View item) {
        Animator anim = ObjectAnimator.ofFloat(item, View.ALPHA, 1f, 0f);
        // anim.setDuration(1000);
        /*anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //  item.setVisibility(View.INVISIBLE);
            }
        });*/
        return anim;
    }
}
