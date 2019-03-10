package com.demo.weicongli.library.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author: Frank
 * @time: 2018/3/11 15:22
 * @e-mail: 912220261@qq.com
 * Function:
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 传入文字
     */
    public static void showText(Context context, String text, int gravity, int code) {
        if (toast == null) {
            toast = Toast.makeText(context, text, code);
            toast.setGravity(gravity, 0, 0);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新创建
            toast.setText(text);
        }
        toast.show();
    }

    /**
     * 传入资源文件
     */
    public static void showText(Context context, int resId, int gravity, int code) {
        if (toast == null) {
            toast = new Toast(context);
            toast.setText(resId);
            toast.setDuration(code);
            toast.setGravity(gravity, 0, 0);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新创建
            toast.setText(resId);
        }
        toast.show();
    }

    /**
     * 传入图片
     */
    public static void showImg(Context context, int resImg, int gravity, int code) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(resImg);
        if (toast == null) {
            toast = new Toast(context);
            toast.setView(imageView);
            toast.setDuration(code);
            toast.setGravity(gravity, 0, 0);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新创建
            toast.setView(imageView);
        }
        toast.show();
    }
}
