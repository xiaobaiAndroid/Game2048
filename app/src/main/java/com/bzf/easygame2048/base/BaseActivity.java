package com.bzf.easygame2048.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public <T> T getView(int viewId){
        return (T)this.findViewById(viewId);
    }

}
