package com.demo.weicongli.library.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by WeicongLi
 * date: 2018/7/15
 * time: 15:12
 * email: 912220261@qq.com
 */
public class CloseUtils {
    /**
     * close掉继承该接口的类
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
