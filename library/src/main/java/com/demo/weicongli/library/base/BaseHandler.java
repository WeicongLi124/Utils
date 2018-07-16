package com.demo.weicongli.library.base;

import android.os.Handler;
import android.os.Message;

import com.demo.weicongli.library.Interface.HandleMessage;

import java.lang.ref.WeakReference;

/**
 * @author: Frank
 * @time: 2018/4/2 21:08
 * @e-mail: 912220261@qq.com
 * Function: 使用handler时，设置activity或fragment的弱引用
 */
public class BaseHandler extends Handler {
    /**
     * 弱引用
     */
    private WeakReference<Object> weakReference;

    /**
     * 消息处理接口
     */
    private HandleMessage handlerInterface;

    /**
     * 设置弱引用
     * @param weakReference
     * @param handleMessage
     */
    public BaseHandler(WeakReference<Object> weakReference,HandleMessage handleMessage){
        this.weakReference = weakReference;
        this.handlerInterface = handleMessage;
    }


    @Override
    public void handleMessage(Message msg) {
       handlerInterface.onMessage(msg);
    }
}
