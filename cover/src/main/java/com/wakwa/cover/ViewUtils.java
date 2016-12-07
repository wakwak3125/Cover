package com.wakwa.cover;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by ryo on 2016/12/07.
 */

public class ViewUtils {

    public static <T extends View> T toGone(T v) {
        v.setVisibility(View.GONE);
        return v;
    }

    public static <T extends View> T toVisible(T v) {
        v.setVisibility(View.VISIBLE);
        return v;
    }

    public static <T extends View> T toInvisible(T v) {
        v.setVisibility(View.INVISIBLE);
        return v;
    }

    public static <T extends View> T fadeIn(final T v, long duration) {
        v.setAlpha(0f);
        v.animate().alpha(1f).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                toVisible(v);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return v;
    }

    public static <T extends View> T fadeOut(final T v, long duration, @Nullable final OnAnimationFinishListener listener) {
        v.animate().alpha(0f).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (listener != null) {
                    listener.onAnimationEnd(v);
                } else {
                    toGone(v);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return v;
    }

    public interface OnAnimationFinishListener {

        void onAnimationEnd(View view);
    }
}
