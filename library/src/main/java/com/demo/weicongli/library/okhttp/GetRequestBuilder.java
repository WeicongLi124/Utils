package com.demo.weicongli.library.okhttp;

import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 13:55
 * @email: 912220261@qq.com
 * @Function:
 */
public class GetRequestBuilder extends HttpRequestBuilder<GetRequestBuilder> {

    GetRequestBuilder() {
        requestBuilder = new Request.Builder();
    }

    /**
     * url附加参数
     * @param str
     * @return
     */
    public GetRequestBuilder appendUrl(String str){
        url += str;
        return this;
    }

    @Override
    public OkHttpUtils build() {
        if (gson == null){
            gson = new Gson();
        }else requestBuilder.put(RequestBody.create(JSON_TYPE,gson.toJson(parameters)));
        return new OkHttpUtils(requestBuilder);
    }
}
