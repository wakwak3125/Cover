package com.wakwa.cover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ryo on 2016/12/07.
 */

public class Cover {

    private static final long DEFAULT_DURATION_SHOW = 500;
    private static final long DEFAULT_DURATION_DISMISS = 300;

    @SuppressLint("StaticFieldLeak")
    @Nullable
    private static Cover cover;
    private Context context;
    private View view;
    private Activity activity;

    private Cover(Context context) {
        this.context = context;
    }

    public static Cover with(Context context) {
        if (context == null) {
            throw new NullPointerException("");
        }
        cover = new Cover(context);
        return cover;
    }

    public Cover put(@LayoutRes int resId) {
        view = View.inflate(context, resId, null);
        view.setClickable(true);
        return cover;
    }

    public Cover put(View view) {
        this.view = view;
        this.view.setClickable(false);
        return cover;
    }

    public void on(Activity activity, final OnCoverClickListener listener, long duration) {
        this.activity = activity;
        ((ViewGroup) activity.getWindow().getDecorView()).addView(ViewUtils.fadeIn(view, duration));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCoverViewClickListener(view, cover);
            }
        });
    }

    public void on(Activity activity, final OnCoverClickListener listener) {
        this.on(activity, listener, DEFAULT_DURATION_SHOW);
    }

    public void dismiss(long duration) {
        ViewUtils.fadeOut(view, duration, new ViewUtils.OnAnimationFinishListener() {
            @Override
            public void onAnimationEnd(View view) {
                ((ViewGroup) activity.getWindow().getDecorView()).removeView(view);
            }
        });
    }

    public void dismiss() {
        this.dismiss(DEFAULT_DURATION_DISMISS);
    }

    public interface OnCoverClickListener {

        void onCoverViewClickListener(View view, Cover cover);
    }
}
