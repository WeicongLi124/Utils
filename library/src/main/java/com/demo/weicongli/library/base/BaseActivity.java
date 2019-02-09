package com.demo.weicongli.library.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @author: Frank
 * @time: 2018/3/11 15:00
 * @e-mail: 912220261@qq.com
 * Function:
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 沉浸式开关
     */
    protected boolean openSteep = false;
    /**
     * 设置固定页面方向
     */
    protected int requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initParams();
        initListener();
        setRequestedOrientation(requestedOrientation);
        if (openSteep) {
            unlockSteep();
        }
    }

    /**
     * 设置布局，返回布局ID
     *
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化view
     */
    protected abstract void initParams();

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 收起软键盘
     * parentView需要先设置focusableInTouchMode为true
     *
     * @param parentView 外围被点击的View
     */
    public void hideKeyBoard(View parentView) {
        //parentView获取焦点
        parentView.requestFocus();
        //收起键盘
        View view = this.getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 不携带消息直接跳转
     *
     * @param activity     当前活动
     * @param nextActivity 跳转到的活动
     */
    public void start(Activity activity, Class<? extends Activity> nextActivity) {
        activity.startActivity(new Intent(activity, nextActivity));
    }

    /**
     * 携带消息进行跳转
     *
     * @param activity     当前活动
     * @param nextActivity 跳转到的活动
     * @param requestCode  标识的消息
     */
    public void start(Activity activity, Class<? extends Activity> nextActivity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, nextActivity), requestCode);
    }

    /**
     * 开启沉浸式
     */
    private void unlockSteep() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
}
