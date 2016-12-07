package com.bzf.easygame2048.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bzf.easygame2048.GameApplication;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class DisplayUtils {

    /**获取屏幕的宽*/
    public static int getDisplayWidth(){
        DisplayMetrics displayMetrics = getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**获取手机屏幕的密度*/
    public static float getDensity(){
        DisplayMetrics displayMetrics = getDisplayMetrics();
        return displayMetrics.density;
    }

    @NonNull
    private static DisplayMetrics getDisplayMetrics() {
        WindowManager wm = (WindowManager) GameApplication.getIns().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }


}
