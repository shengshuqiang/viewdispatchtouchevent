package com.example.shengshuqiang.viewdispatchtouchevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.shengshuqiang.viewdispatchtouchevent.widgets.ProxyFrameLayout;

public class ParentInterceptTouchEventActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox interceptActionDownCheckBox;
    private CheckBox interceptActionMoveCheckBox;
    private CheckBox interceptActionUPCheckBox;
    private CheckBox parentHandleCheckBox;
    private CheckBox childHandleCheckBox;
    private ProxyFrameLayout parentView;
    private ProxyFrameLayout childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_intercept_touch_event_activity);

        interceptActionDownCheckBox = (CheckBox) findViewById(R.id.checkbox_down);
        interceptActionMoveCheckBox = (CheckBox) findViewById(R.id.checkbox_move);
        interceptActionUPCheckBox = (CheckBox) findViewById(R.id.checkbox_up);

        interceptActionDownCheckBox.setOnCheckedChangeListener(this);
        interceptActionMoveCheckBox.setOnCheckedChangeListener(this);
        interceptActionUPCheckBox.setOnCheckedChangeListener(this);

        parentHandleCheckBox = (CheckBox) findViewById(R.id.checkbox_parent);
        childHandleCheckBox = (CheckBox) findViewById(R.id.checkbox_child);

        parentHandleCheckBox.setOnCheckedChangeListener(this);
        childHandleCheckBox.setOnCheckedChangeListener(this);

        parentView = (ProxyFrameLayout) findViewById(R.id.parent);
        childView = (ProxyFrameLayout) findViewById(R.id.child);

        parentView.setTag("Parent");
        childView.setTag("Child");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int interceptTouchEventAction;
        if (interceptActionDownCheckBox.isChecked()) {
            interceptTouchEventAction = MotionEvent.ACTION_DOWN;
        } else if (interceptActionMoveCheckBox.isChecked()) {
            interceptTouchEventAction = MotionEvent.ACTION_MOVE;
        } else if (interceptActionUPCheckBox.isChecked()) {
            interceptTouchEventAction = MotionEvent.ACTION_UP;
        } else {
            interceptTouchEventAction = -1;
        }
        parentView.setInterceptTouchEventAction(interceptTouchEventAction);

        parentView.setIshandleEvent(parentHandleCheckBox.isChecked());

        childView.setIshandleEvent(childHandleCheckBox.isChecked());
    }
}
