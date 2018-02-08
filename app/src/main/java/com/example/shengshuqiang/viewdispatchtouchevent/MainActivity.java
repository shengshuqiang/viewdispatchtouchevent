package com.example.shengshuqiang.viewdispatchtouchevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

/**
 * Created by shengshuqiang on 2018/1/7.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gotoActivity();
    }

    private void gotoActivity() {
        Intent intent = new Intent(this, ParentInterceptTouchEventActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gotoActivity();
        }

        return super.onTouchEvent(event);
    }
}
