package com.demo.weicongli.utils.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.demo.weicongli.utils.R;

/**
 * @author: WeicongLi
 * @time: 2018/7/28 15:34
 * @email: 912220261@qq.com
 * @Function:
 */
public class WeeklyDialog extends Dialog implements RadioGroup.OnCheckedChangeListener {
    private Context context;
    private LayoutInflater layoutInflater;
    private RadioGroup extentRg;
    private RadioGroup typeRg;
    private RadioButton meExtentRbtn;
    private RadioButton groupExtentRbtn;
    private RadioButton allExtentRbtn;
    private RadioButton allTypeRbtn;
    private RadioButton partTypeRbtn;
    private String TAG = "WeeklyDialog";

    public WeeklyDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_selector_dialog);
        initView();
        initListener();
        initWindow();
    }

    private void initView(){
        extentRg = findViewById(R.id.weekly_extent_rg);
        typeRg = findViewById(R.id.weekly_type_rg);
       /* meExtentRbtn = findViewById(R.id.weekly_extent_me_rbtn);
        groupExtentRbtn = findViewById(R.id.weekly_extent_group_rbtn);
        allExtentRbtn = findViewById(R.id.weekly_extent_all_rbtn);
        allTypeRbtn = findViewById(R.id.weekly_type_all_rbtn);
        partTypeRbtn = findViewById(R.id.weekly_type_part_rbtn);*/
    }

    private void initListener(){
        extentRg.setOnCheckedChangeListener(this);

        typeRg.setOnCheckedChangeListener(this);
    }

    private void initWindow(){
        Window dialogWindow = getWindow();
        if (dialogWindow != null){
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.gravity = Gravity.TOP;
            dialogWindow.setAttributes(layoutParams);
            dialogWindow.setWindowAnimations(R.style.WeeklyDialogAnim);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.weekly_extent_me_rbtn:
                Log.i(TAG, "onCheckedChanged: 1");
                break;
            case R.id.weekly_extent_group_rbtn:
                Log.i(TAG, "onCheckedChanged: 2");
                break;
            case R.id.weekly_extent_all_rbtn:
                Log.i(TAG, "onCheckedChanged: 3");
                break;
            case R.id.weekly_type_all_rbtn:
                Log.i(TAG, "onCheckedChanged: 4");
                break;
            case R.id.weekly_type_part_rbtn:
                Log.i(TAG, "onCheckedChanged: 5");
                break;
        }
    }
}
