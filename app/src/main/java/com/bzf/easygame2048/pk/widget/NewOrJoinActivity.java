package com.bzf.easygame2048.pk.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.pk.BluetoothManager;

public class NewOrJoinActivity extends BaseActivity {

    private Button mBt_new;
    private Button mBt_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_join);
        initView();
        initListener();
        initBluetooth();
    }

    private void initBluetooth() {
        new BluetoothManager().openBluetooth();
    }

    private void initListener() {
        mBt_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewOrJoinActivity.this,ServiceActivity.class));
            }
        });

        mBt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewOrJoinActivity.this,ScanBluetoothActivity.class));
            }
        });
    }

    private void initView() {
        mBt_new = getView(R.id.bt_new);
        mBt_join = getView(R.id.bt_join);
    }
}
