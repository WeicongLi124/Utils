package com.demo.weicongli.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.library.jianjunhuang.okhttputils.okhttputils.OkHttpUtils;
import com.library.jianjunhuang.okhttputils.okhttputils.callback.ResultCallback;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpUtils.getInstance()
                .getAsy()
                .build()
                .execute(new ResultCallback() {
                    @Override
                    public void onError(Call call, int code, Exception e) {

                    }

                    @Override
                    public void onResponse(String response, int code) throws IOException, JSONException {

                    }
                });
    }
}
