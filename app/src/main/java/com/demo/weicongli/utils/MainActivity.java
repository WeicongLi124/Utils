package com.demo.weicongli.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.weicongli.library.okhttp.OkCallback;
import com.demo.weicongli.library.okhttp.OkHttpUtils;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpUtils.getInstance()
                .post()
                .url("https://508cst.gcu.edu.cn/tentcooTools3.0/"+"api/v1/auth/login_comfirm")
                .addParam("username","13030271735")
                .addEncryptedParam("password","tentcoo123")
                .build()
                .callback(new OkCallback() {
                    @Override
                    public void onFailure(Call call, String errorMsg) {
                        Log.i(TAG, "onFailure: "+errorMsg);
                    }

                    @Override
                    public void onResponse(Call call, String response) {
                        Log.i(TAG, "onResponse: "+response);
                    }
                });
        OkHttpUtils.getInstance()
                .get()
                .url("https://508cst.gcu.edu.cn/tentcooTools3.0/"+"api/v1/meet/get_allMeet")
                .header("Authorization","Bearer "+"eLEubCLWlnY5oZdowKsB9VerwaNnklZb")
                .build()
                .callback(new OkCallback() {
                    @Override
                    public void onFailure(Call call, String errorMsg) {
                        Log.i(TAG, "onFailure: "+errorMsg);
                    }

                    @Override
                    public void onResponse(Call call, String response) {
                        Log.i(TAG, "onResponse: "+response);
                    }
                });
    }
}
