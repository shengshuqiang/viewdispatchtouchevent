package com.example.shengshuqiang.viewdispatchtouchevent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.shengshuqiang.viewdispatchtouchevent.widgets.ProxyButton;
import com.example.shengshuqiang.viewdispatchtouchevent.widgets.ProxyFrameLayout;
import com.example.shengshuqiang.viewdispatchtouchevent.widgets.ProxyLinearLayout;
import com.example.shengshuqiang.viewdispatchtouchevent.widgets.ProxyScrollView;

/**
 * 1. onInterceptTouchEvent处理滑动拦截
 *      i: 当ACTION_MOVE超过阈值，则拦截，对应ACTION_MOVE转成ACTION_CANCEL最后一次分发给目标子view，结束目标子view事件流
 *      ii: 接下来的MotionEvent分发到自己的onTouchEvent处理
 * 2. onTouchEvent恒返回true（如果不返回true，事件会返回给父容器消费），可以接着处理接下来的MotionEvent
 * Created by shengshuqiang on 2018/1/7.
 */
public class ScrollViewNestButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = this;
        final int padding = 30;

        Button button =new ProxyButton(context);
        button.setBackgroundColor(0xffb3ff66);
        button.setText("按键");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        final FrameLayout frameLayout = new ProxyFrameLayout(context);
        frameLayout.setBackgroundColor(0xff3399ff);
        frameLayout.setPadding(padding, padding, padding, padding);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("SSU", frameLayout.getMeasuredWidth() + "");
            }
        });

        final LinearLayout linearLayout = new ProxyLinearLayout(context);
        linearLayout.setBackgroundColor(0XFFFF66b3);
        linearLayout.setPadding(padding, padding, padding, padding);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("SSU", linearLayout.getMeasuredWidth() + "");
            }
        });

        linearLayout.addView(button, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));

        // 使得scrollview内容区比scrollview高度要大，具备滑动前提
        linearLayout.addView(frameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Helper.getScreenHeight(this)));
        ((ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams()).topMargin = 50;

        ScrollView scrollView = new ProxyScrollView(context);
        scrollView.setBackgroundColor(0XFFFF8000);
        scrollView.setPadding(padding, padding, padding, padding);
        scrollView.addView(linearLayout);

        setContentView(scrollView);
    }
}
