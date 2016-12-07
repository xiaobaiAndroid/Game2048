package com.bzf.easygame2048.file;

import android.content.Context;

import com.bzf.easygame2048.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * com.bzf.easygame2048.file
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class FileUtils {

    public static boolean writeData(Context context){
        return true;
    }

    public static String readFile(Context context,String fileName) throws Exception {
        InputStream in = context.getResources().openRawResource(R.raw.test);
        InputStreamReader inputStreamReader =  new InputStreamReader(in, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line =  bufferedReader.readLine())!=null){
            sb.append(line);
        }
        bufferedReader.close();
        return sb.toString();
    }
}
