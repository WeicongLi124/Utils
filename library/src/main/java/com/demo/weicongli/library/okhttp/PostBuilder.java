package com.demo.weicongli.library.okhttp;

import com.demo.weicongli.library.utils.EncryptionUtils;
import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 13:48
 * @email: 912220261@qq.com
 * @Function:
 */
public class PostBuilder extends HttpRequestBuilder<PostBuilder> {

    PostBuilder(){
        requestBuilder = new Request.Builder();
    }

    /**
     * 添加加密参数
     * @param key
     * @param value
     * @return
     */
    public PostBuilder addEncryptedParam(String key, String value){
        parameters.put(key,EncryptionUtils.getSHA1(EncryptionUtils.getMD5(value)));
        return this;
    }

    @Override
    public OkHttpUtils build() {
        if (gson == null){
            gson = new Gson();
        }
        requestBuilder.post(RequestBody.create(JSON_TYPE,gson.toJson(parameters)));
        return OkHttpUtils.getInstance().setRequest(requestBuilder.build());
    }

}
