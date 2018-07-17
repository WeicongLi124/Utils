package com.demo.weicongli.library.okhttp;

import com.google.gson.Gson;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 15:43
 * @email: 912220261@qq.com
 * @Function:
 */
abstract class HttpRequestBuilder<T extends HttpRequestBuilder> {
    String url;
    Gson gson;
    Map<Object,Object> parameters;
    Request.Builder requestBuilder;
    static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public T url(String url){
        this.url = url;
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

    public T addParam(Object key, Object value){
        if (parameters == null){
            parameters = new HashMap<>();
        }
        this.parameters.put(key,value);
        return (T) this;
    }

    public T removeHeader(String name){
        requestBuilder.removeHeader(name);
        return (T) this;
    }

    public T patch(RequestBody requestBody){
        requestBuilder.patch(requestBody);
        return (T) this;
    }

    public T tag(Object tag){
        requestBuilder.tag(tag);
        return (T) this;
    }

    public T delete(RequestBody requestBody){
        requestBuilder.delete(requestBody);
        return (T) this;
    }

    public abstract OkHttpUtils build();

}
