package com.bzf.easygame2048.entrance.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bzf.easygame2048.entrance.widget.EntranceActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,EntranceActivity.class));
        finish();
    }


}
