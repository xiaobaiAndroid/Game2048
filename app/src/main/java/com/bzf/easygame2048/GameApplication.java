package com.bzf.easygame2048;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.bzf.easygame2048.entrance.widget.EntranceActivity;
import com.bzf.easygame2048.utils.Config;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class GameApplication extends Application{

    private static GameApplication ins;

    /**目标分数*/
    public static int GOAL;

    public static int HIGHSCROE;

    /**分数*/
    public static int SCORE = 0;

    /**模式：4X4 5X5 6X6*/
    public static int GAMELINES;

    @Override
    public void onCreate() {
        super.onCreate();
        ins  = this;
        initConfig();
    }

    private void initConfig() {
       SharedPreferences sp =  getSharedPreferences("config", Context.MODE_PRIVATE);
        HIGHSCROE =  sp.getInt("highScore",0);
        Config.init();
        GAMELINES = Config.mGameLinesArray[0];
        GOAL = Config.mGameGoalArray[0];
    }


    public static GameApplication getIns(){
        return ins;
    }

    /**保存最高的成绩*/
    public void saveHighScore() {
        if(SCORE>HIGHSCROE){
            HIGHSCROE = SCORE;
            SharedPreferences sp = getSharedPreferences("config",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("highScore",SCORE);
            editor.commit();
        }
    }

    /**退出应用，将主Activity设置为SingleTask,重新主Activity的onNewIntent()方法来退出应用*/
    public void exitApp(Activity activity){
        activity.startActivity(new Intent(activity,EntranceActivity.class));
    }

}
