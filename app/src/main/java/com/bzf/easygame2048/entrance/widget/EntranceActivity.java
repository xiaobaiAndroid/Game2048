package com.bzf.easygame2048.entrance.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.main.widget.MainActivity;
import com.bzf.easygame2048.pk.widget.NewOrJoinActivity;

public class EntranceActivity extends BaseActivity {
    private Button mB_tourist;
    private Button mB_pk;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        initView();
        initListener();
    }



    private void initListener() {

        mB_tourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mB_pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EntranceActivity.this, NewOrJoinActivity.class));
            }
        });

    }

    private void initView() {
        mB_tourist = getView(R.id.bt_tourist);
        mB_pk = getView(R.id.bt_pk);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitAppDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitAppDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(this)
                    .setMessage("确定要退出游戏吗?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDialog.dismiss();
                            GameApplication.getIns().exitApp(EntranceActivity.this);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mDialog.dismiss();
                        }
                    })
                    .create();
        }
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //退出应用
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

