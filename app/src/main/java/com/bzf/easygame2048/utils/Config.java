package com.bzf.easygame2048.utils;

/**
 *配置类
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class Config {

    public  static Integer[] mGameLinesArray;

    public static Integer[] mGameGoalArray;

    /**初始化方格数列表和目标列表*/
    public static void init(){
        mGameLinesArray = new Integer[]{4,5,6};
        mGameGoalArray = new Integer[]{512,1024,2048,4096};
    }

}
