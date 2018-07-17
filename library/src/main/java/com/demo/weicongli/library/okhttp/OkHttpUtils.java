package com.demo.weicongli.library.okhttp;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 10:26
 * @email: 912220261@qq.com
 * @Function:
 */
public class OkHttpUtils {
    private final OkHttpClient client = new OkHttpClient();
    private final Request.Builder requestBuilder;

    public OkHttpUtils(Request.Builder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public OkHttpUtils callback(Callback callback){
        client.newCall(requestBuilder.build()).enqueue(callback);
        return this;
    }

    public Call getCall(){
        return client.newCall(requestBuilder.build());
    }

    public Request.Builder getRequestBuilder(){
        return requestBuilder;
    }



    public static class Builder {

        /**
         * 设置为post解析
         * @return
         */
        public PostRequestBuilder postRequest(){
            return new PostRequestBuilder();
        }

        /**
         * 设置为get解析
         * @return
         */
        public GetRequestBuilder getRequest(){
            return new GetRequestBuilder();
        }
    }
}
