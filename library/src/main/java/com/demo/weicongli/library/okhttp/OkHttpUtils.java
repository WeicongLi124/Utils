package com.demo.weicongli.library.okhttp;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author: WeicongLi
 * @time: 2018/7/16 10:26
 * @email: 912220261@qq.com
 * @Function:
 */
public class OkHttpUtils {
    private volatile static OkHttpUtils mInstance;
    private static OkHttpClient mClient;
    private static Call mCall;

    /**
     * 接收构建后的Builder
     * 里氏替换
     * @param request
     */
    private static OkHttpUtils initBuilder(Request request){
        mCall = mClient.newCall(request);
        return mInstance;
    }

    /**
     * 创建OkHttpClient对象
     * @param client
     */
    private OkHttpUtils(OkHttpClient client) {
        if (client == null){
            mClient = new OkHttpClient();
        }else mClient = client;
    }

    /**
     * DCL方式初始化OkHttpUtils
     * @param client
     * @return
     */
    private static OkHttpUtils initUtils(OkHttpClient client){
        if (mInstance == null){
            synchronized (OkHttpUtils.class){
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(client);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取OkHttpUtils对象实例
     * @return
     */
    public static OkHttpUtils getInstance(){
        return initUtils(null);
    }

    /**
     * 传入Builder
     * @param request
     * @return
     */
    public OkHttpUtils setRequest(Request request){
        return initBuilder(request);
    }

    /**
     * 设置Callback
     * @param callback
     * @return
     */
    public OkHttpUtils callback(Callback callback){
        mCall.enqueue(callback);
        return this;
    }

    /**
     * 获取OkHttpClient实例
     * @return
     */
    public OkHttpClient getOkHttpClientInstance(){
        return mClient;
    }

    /**
     * 获取Call实例
     * @return
     */
    public Call getCallInstance(){
        return mCall;
    }

    /**
     * 设置为post解析
     * @return
     */
    public PostBuilder post(){
        return new PostBuilder();
    }

    /**
     * 设置为get解析
     * @return
     */
    public GetBuilder get(){
        return new GetBuilder();
    }
}
