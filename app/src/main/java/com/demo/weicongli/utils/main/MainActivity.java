package com.demo.weicongli.utils.main;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.demo.weicongli.library.base.BaseActivity;
import com.demo.weicongli.utils.R;
import com.demo.weicongli.utils.view.HuaWeiView;

public class MainActivity extends BaseActivity {
    private RelativeLayout relativeLayout;
    private HuaWeiView huaWeiView;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {
        relativeLayout = findViewById(R.id.main_layout);
        huaWeiView = findViewById(R.id.huawei);
        huaWeiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huaWeiView.changeAngle(200);
            }
        });
        huaWeiView.setOnAngleColorListener(new HuaWeiView.OnAngleColorListener() {
            @Override
            public void colorListener(int red, int green) {
                Color color = new Color();
                int backColor = color.argb(100, red, green, 0);
                relativeLayout.setBackgroundColor(backColor);
            }
        });
    }

    @Override
    protected void initListener() {
    }
}
