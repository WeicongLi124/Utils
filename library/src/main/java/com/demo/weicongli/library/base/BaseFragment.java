package com.demo.weicongli.library.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @author: Frank
 * @time: 2018/3/11 15:16
 * @e-mail: 912220261@qq.com
 * Function:
 */

public abstract class BaseFragment extends Fragment {
    private View view;

    /**
     * 沉浸式开关
     */
    private boolean steep = false;

    public void setSteep(boolean steep) {
        this.steep = steep;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayout(),container,false);
        initParams(view);
        initListener();
        if (steep){
            steep();
        }
        return view;
    }

    /**
     * 设置布局，返回布局ID
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化view
     */
    protected abstract void initParams(View view);

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 收起软键盘
     * parentView需要先设置focusableInTouchMode为true
     * parentView:外围被点击的View
     * @param parentView
     */
    public void hideSoftInput(View parentView){
        //parentView获取焦点
        parentView.requestFocus();
        // 收起键盘
        View view = getActivity().getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 不携带消息直接跳转
     * activity:当前活动
     * nextActivity:跳转到的活动
     * @param activity
     * @param nextActivity
     */
    public void startActivity(Activity activity, Class<? extends Activity> nextActivity){
        activity.startActivity(new Intent(activity,nextActivity));
    }

    /**
     * 携带消息进行跳转
     * activity:当前活动
     * nextActivity:跳转到的活动
     * requestCode:标识的消息
     * @param activity
     * @param nextActivity
     * @param requestCode
     */
    public void startActivity(Activity activity,Class<? extends Activity> nextActivity,int requestCode){
        activity.startActivityForResult(new Intent(activity,nextActivity),requestCode);
    }

    /**
     * 开启沉浸式
     */
    private void steep() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

}
