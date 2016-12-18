package com.bzf.easygame2048.commonutils;

import android.util.TypedValue;

import com.bzf.easygame2048.GameApplication;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class DimensionUtils {

    /**dp转px*/
    public static float dpTopx(float value){
      return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value, GameApplication.getIns().getResources().getDisplayMetrics());
    }

    /**sp转px*/
    public static float spToPx(float value){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, GameApplication.getIns().getResources().getDisplayMetrics());
    }
}
