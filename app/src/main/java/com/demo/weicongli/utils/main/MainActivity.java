package com.demo.weicongli.utils.main;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.demo.weicongli.library.base.BaseActivity;
import com.demo.weicongli.utils.R;

public class MainActivity extends BaseActivity {
    private Button button;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams() {
        button = findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.weiconglee.faketieba.MYACTION", Uri.parse("info"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initListener() {
    }
}
