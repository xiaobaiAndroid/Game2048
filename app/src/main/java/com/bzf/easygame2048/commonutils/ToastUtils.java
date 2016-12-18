package com.bzf.easygame2048.commonutils;

import android.content.Context;
import android.widget.Toast;

/**
 * com.bzf.easygame2048.utils
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class ToastUtils {

    /**
     * @param context
     * @param message
     */
    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
