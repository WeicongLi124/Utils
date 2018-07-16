package com.demo.weicongli.library.okhttp;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 15:43
 * @email: 912220261@qq.com
 * @Function:
 */
abstract class HttpRequestBuilder<T extends HttpRequestBuilder> {
    Gson gson;
    Map<Object,Object> parameters;
    Request.Builder requestBuilder;
    static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public T url(URL url){
        requestBuilder.url(url);
        return (T) this;
    }

    public T url(HttpUrl url){
        requestBuilder.url(url);
        return (T) this;
    }

    public T url(String url){
        requestBuilder.url(url);
        return (T) this;
    }

    public T header(String headerName, String headerValue){
        requestBuilder.header(headerName,headerValue);
        return (T) this;
    }

    public T addHeader(String name, String value){
        requestBuilder.addHeader(name,value);
        return (T) this;
    }

    public T addParameters(Object key, Object value){
        if (parameters == null){
            parameters = new HashMap<>();
        }
        this.parameters.put(key,value);
        return (T) this;
    }

    public abstract OkHttpUtils build();

}
