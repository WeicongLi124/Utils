package com.demo.weicongli.library.base;

import android.os.Handler;
import android.os.Message;

import com.demo.weicongli.library.Interface.IHandleMessage;

import java.lang.ref.WeakReference;

/**
 * @author: Frank
 * @time: 2018/4/2 21:08
 * @e-mail: 912220261@qq.com
 * Function: 使用handler时，设置activity或fragment的弱引用
 */
public class WeakHandler extends Handler {
    /**
     * 弱引用
     */
    private WeakReference<Object> weakReference;

    /**
     * 消息处理接口
     */
    private IHandleMessage iHandleMessage;

    /**
     * 设置弱引用
     *
     * @param weakReference
     * @param iHandleMessage
     */
    public WeakHandler(WeakReference<Object> weakReference, IHandleMessage iHandleMessage) {
        this.weakReference = weakReference;
        this.iHandleMessage = iHandleMessage;
    }


    @Override
    public void handleMessage(Message msg) {
        if (weakReference.get() != null) {
            iHandleMessage.onMessage(msg);
        }
    }
}
