package com.bzf.easygame2048.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具
 * com.bzf.easygame2048.utils
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class NetworkUtils {

    /**
     * 判断是否联网
     * @param context
     * @return
     */
    public static boolean networkIsConnect(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnect = (networkInfo!=null) && networkInfo.isConnectedOrConnecting();
        return isConnect;
    }

    /**
     * 判断当前网络是否处于wifi
     * @param context
     * @return
     */
    public static boolean networkIsWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else{
            return false;
        }
    }
}
