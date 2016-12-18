package com.bzf.easygame2048.pk.widget;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.bzf.easygame2048.commonutils.ToastUtils;

public class ClientActivity extends BluetoothActivity{

    @Override
    protected void init() {
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        BluetoothDevice bluetoothDevice = intent.getParcelableExtra("BluetoothDevice");
        if(bluetoothDevice!=null){
            setNickName(bluetoothDevice.getName());
            mTv_selfName.setText(mPresenter.getName());
            mTv_selfScore.setText("0");
            mPresenter.connectToservice(bluetoothDevice);
        }else{
            ToastUtils.showToast(getApplicationContext(),"未找到蓝牙设备");
            super.onBackPressed();
        }
    }

}
