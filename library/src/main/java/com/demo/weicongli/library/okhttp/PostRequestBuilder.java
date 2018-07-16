package com.demo.weicongli.library.okhttp;

import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 13:48
 * @email: 912220261@qq.com
 * @Function:
 */
public class PostRequestBuilder extends HttpRequestBuilder<PostRequestBuilder> {

    PostRequestBuilder(){
        requestBuilder = new Request.Builder();
    }

    public PostRequestBuilder removeHeader(String name){
        requestBuilder.removeHeader(name);
        return this;
    }

    public PostRequestBuilder head(){
        requestBuilder.head();
        return this;
    }

    public PostRequestBuilder patch(RequestBody requestBody){
        requestBuilder.patch(requestBody);
        return this;
    }

    public PostRequestBuilder tag(Object tag){
        requestBuilder.tag(tag);
        return this;
    }

    public PostRequestBuilder delete(RequestBody requestBody){
        requestBuilder.delete(requestBody);
        return this;
    }

    @Override
    public OkHttpUtils build() {
        if (gson == null){
            gson = new Gson();
        }
        requestBuilder.post(RequestBody.create(JSON_TYPE,gson.toJson(parameters)));
        return new OkHttpUtils(requestBuilder);
    }

}
