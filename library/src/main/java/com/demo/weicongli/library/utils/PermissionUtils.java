package com.demo.weicongli.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.demo.weicongli.library.Interface.PermitToDo;

/**
 * @author: Frank
 * @time: 2018/7/12 18:30
 * @e-mail: 912220261@qq.com
 * Function: 动态获取权限的工具类
 */
public class PermissionUtils {

    private PermitToDo permitToDo;

    public PermissionUtils(PermitToDo permitToDo) {
        this.permitToDo = permitToDo;
    }

    /**
     * 获取权限方法
     * @param context           上下文资源
     * @param permissionType    权限的类型
     */
    public void getPermission(Context context, String permissionType, int requestCode) {
        //Android 6.0 以上，动态获取权限
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkPermission(context, permissionType)) {
                ActivityCompat.requestPermissions((Activity)context, new String[]{permissionType}, requestCode);
            }else {
                permitToDo.permitToDo(permissionType);
            }
        }else {
            permitToDo.permitToDo(permissionType);
        }
    }

    /**
     * 检测权限是否已获得
     * @param context           上下文资源
     * @param permissionType    权限的类型
     * @return
     */
    public boolean checkPermission(Context context, String permissionType) {
        boolean flag = false;
        int checkReadStoragePermission = ContextCompat.checkSelfPermission(context, permissionType);
        if (checkReadStoragePermission == PackageManager.PERMISSION_GRANTED) {
            flag = true;
        }
        return flag;
    }


}
