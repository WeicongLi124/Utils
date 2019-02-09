package com.demo.weicongli.library.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * @author: WeicongLi
 * @time: 2019/1/19 21:00
 * @email: 912220261@qq.com
 * @Function:
 */
public class ViewUtils {
    /**
     * dp转换为像素
     *
     * @param dp
     * @return
     */
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}
