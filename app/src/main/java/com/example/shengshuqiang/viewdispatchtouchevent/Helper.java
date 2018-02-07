package com.example.shengshuqiang.viewdispatchtouchevent;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shengshuqiang on 2018/1/7.
 */
public class Helper {
    public static final String TAG = "Helper";
    public static final int NEST_CHAR_COUNT = 5;
    public static int onMeasureNestLevel = 0;
    public static int onLayoutNestLevel = 0;
    public static int onDispatchTouchEventNestLevel = 0;

    public static void log(Object obj) {
        Log.e(TAG, String.valueOf(obj));
    }

    public static void logPreOnMeasure(View view, int widthMeasureSpec, int heightMeasureSpec) {
        logPre("OnMeasure", view, onMeasureNestLevel++, "#pre onMeasure: widthMeasureSpec=" + View.MeasureSpec.toString(widthMeasureSpec) + ", heightMeasureSpec=" + View.MeasureSpec.toString(heightMeasureSpec), true);
    }

    public static void logPostOnMeasure(View view, int widthMeasureSpec, int heightMeasureSpec) {
        logPost("OnMeasure", view, --onMeasureNestLevel, "#post onMeasure: measuredWidth=" + view.getMeasuredWidth() + ", measuredHeight=" + view.getMeasuredHeight(), true);
    }

    public static void logPreOnLayout(View view, boolean changed, int left, int top, int right, int bottom) {
        logPre("OnLayout", view, onLayoutNestLevel++, "#pre onLayout: changed=" + changed + ", (" + left + ", " + top + ", " + right + ", " + bottom + ")", true);
    }

    public static void logPostOnLayout(View view, boolean changed, int left, int top, int right, int bottom) {
        logPost("OnLayout", view, --onLayoutNestLevel, "#post onLayout: width=" + view.getWidth() + ", height=" + view.getHeight(), true);
    }

    public static void logPreDispatchTouchEvent(View view, MotionEvent event) {
        logPre("DispatchTouchEvent", view, onDispatchTouchEventNestLevel++, "#pre dispatchTouchEvent: event=" + MotionEvent.actionToString(event.getAction()), true);
    }

    public static void logPostDispatchTouchEvent(View view, MotionEvent event, boolean handled) {
        logPost("DispatchTouchEvent", view, --onDispatchTouchEventNestLevel, "#post dispatchTouchEvent: event=" + MotionEvent.actionToString(event.getAction()) + ", handled=" + handled, true);
    }

    public static void logPreOnInterceptTouchEvent(View view, MotionEvent event) {
        logPre("OnInterceptTouchEvent", view, onDispatchTouchEventNestLevel, "#pre onInterceptTouchEvent: event=" + MotionEvent.actionToString(event.getAction()), false);
    }

    public static void logPostOnInterceptTouchEvent(View view, MotionEvent event, boolean handled) {
        logPost("OnInterceptTouchEvent", view, onDispatchTouchEventNestLevel, "#post onInterceptTouchEvent: event=" + MotionEvent.actionToString(event.getAction()) + ", handled=" + handled, false);
    }

    public static void logPreOnTouchEvent(View view, MotionEvent event) {
        logPre("OnTouchEvent", view, onDispatchTouchEventNestLevel, "#pre onTouchEvent: event=" + MotionEvent.actionToString(event.getAction()), false);
    }

    public static void logPostOnTouchEvent(View view, MotionEvent event, boolean handled) {
        logPost("OnTouchEvent", view, onDispatchTouchEventNestLevel, "#post onTouchEvent: event=" + MotionEvent.actionToString(event.getAction()) + ", handled=" + handled, false);
    }

    private static String getViewSimpleName(View view) {
        return view.getClass().getSimpleName();
    }

    private static void logPre(String title, View view, int nestLevel, String msg, boolean newLine) {
        Helper.log(getPreSeparateStr(title, nestLevel, newLine) + getViewSimpleName(view) + msg);
    }

    private static void logPost(String title, View view, int nestLevel,  String msg, boolean newLine) {
        Helper.log(getViewSimpleName(view) + msg + getPostSeparateStr(title, nestLevel, newLine));
    }

    private static String getPreSeparateStr(String title, int nestLevel, boolean newLine) {
        // "\n>>>=title=>\n"
        // ">>>=title=>\n"
        StringBuilder stringBuilder = new StringBuilder();

        if (newLine && nestLevel == 0) {
            stringBuilder.append("\n");
        }

        stringBuilder.append(">>>");

        StringBuilder nestStringBuilder = new StringBuilder();
        int level = nestLevel;
        while (level++ < NEST_CHAR_COUNT) {
            nestStringBuilder.append("=");
        }
        String nestString = nestStringBuilder.toString();
        stringBuilder.append(nestString);

        stringBuilder.append(title);

        stringBuilder.append(nestString);

        stringBuilder.append(">\n");

        return stringBuilder.toString();
    }

    private static String getPostSeparateStr(String title, int nestLevel, boolean newLine) {
        // "\n<=title=<<<\n"
        // "\n<=title=<<<"
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n<");

        StringBuilder nestStringBuilder = new StringBuilder();
        int level = nestLevel;
        while (level++ < NEST_CHAR_COUNT) {
            nestStringBuilder.append("=");
        }
        String nestString = nestStringBuilder.toString();
        stringBuilder.append(nestString);

        stringBuilder.append(title);

        stringBuilder.append(nestString);

        stringBuilder.append("<<<");

        if (newLine && nestLevel == 0) {
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public static int getScreenHeight(Activity activity) {
        if (activity != null) {
            Point outSize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(outSize);
            return outSize.y;
        }

        return 0;
    }
}
