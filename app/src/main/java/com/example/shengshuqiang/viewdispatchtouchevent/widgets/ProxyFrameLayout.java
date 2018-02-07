package com.example.shengshuqiang.viewdispatchtouchevent.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.shengshuqiang.viewdispatchtouchevent.Helper;

/**
 * Created by shengshuqiang on 2018/1/7.
 */
public class ProxyFrameLayout extends FrameLayout {
    public ProxyFrameLayout(Context context) {
        super(context);
    }

    public ProxyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Helper.logPreOnMeasure(this, widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Helper.logPostOnMeasure(this, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Helper.logPreOnLayout(this, changed, l, t, r, b);

        super.onLayout(changed, l, t, r, b);

        Helper.logPostOnLayout(this, changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Helper.logPreDispatchTouchEvent(this, event);

        boolean handled = super.dispatchTouchEvent(event);

        Helper.logPostDispatchTouchEvent(this, event, handled);

        return handled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Helper.logPreOnInterceptTouchEvent(this, event);

        boolean handled = super.onInterceptTouchEvent(event);

        Helper.logPostOnInterceptTouchEvent(this, event, handled);

        return handled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Helper.logPreOnTouchEvent(this, event);

        boolean handled = super.onTouchEvent(event);

        Helper.logPostOnTouchEvent(this, event, handled);

        return handled;
    }

}
