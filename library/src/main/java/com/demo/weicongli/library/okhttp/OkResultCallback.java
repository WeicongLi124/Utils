package com.demo.weicongli.library.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 12:53
 * @email: 912220261@qq.com
 * @Function:
 */
public abstract class OkResultCallback implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {
        onFailure(call,e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        onResponse(call,response.body().string());
    }

    public abstract void onFailure(Call call, String errorMsg);
    public abstract void onResponse(Call call, String response);
}
