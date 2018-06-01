package com.blackphoenix.pxarclayout;

/**
 * Created by Praba on 6/1/2018.
 */

public class ArcMenuAnimator {


    public static class ANIM_TYPE {
        public static final int ROTATION_TRANSLATE = 1;
        public static final int FADE = 2;
    }

    public static class ANIM_PLAY_MODE {
        public static final int TOGETHER = 1;
        public static final int SEQUENTIAL = 2;
    }

    public static int DEFAULT_ANIM_DURATION = 400;

    private int animType;
    private int animPlayMode;
    private int animShowDuration;
    private int animHideDuration;

    public ArcMenuAnimator(){

    }

    public int getAnimType() {
        return animType;
    }

    public void setAnimType(int animType) {
        this.animType = animType;
    }

    public int getAnimPlayMode() {
        return animPlayMode;
    }

    public void setAnimPlayMode(int animPlayMode) {
        this.animPlayMode = animPlayMode;
    }

    public int getAnimShowDuration() {
        return animShowDuration;
    }

    public void setAnimShowDuration(int animShowDuration) {
        this.animShowDuration = animShowDuration;
    }

    public int getAnimHideDuration() {
        return animHideDuration;
    }

    public void setAnimHideDuration(int animHideDuration) {
        this.animHideDuration = animHideDuration;
    }
}
