package com.bzf.easygame2048.pk.widget;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.pk.WifiConnectManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 加入热点
 */
public class JoinWifiActivity extends BaseActivity {

    private TextView mTv_message;
    private RecyclerView mRv_WifiList;
    private WifiP2pManager.Channel mChannel;
    private WifiP2pManager mWifiP2pManager;
    private List<WifiP2pDevice> mDeviceList = new ArrayList<WifiP2pDevice>();

    private static final String SSIDNAME = "EASYGAME2048";
    private static final String PASSWORD = "08121027";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_wifi);
        initView();
        scanHotSpot();
    }

    /**
     * 扫描热点
     */
    private void scanHotSpot() {
        WifiConnectManager wifiConnectManager = new WifiConnectManager(this){

            @Override
            public Intent myRegisterReceiver(BroadcastReceiver receiver, IntentFilter filter) {
                return JoinWifiActivity.this.registerReceiver(receiver,filter);
            }

            @Override
            public void myUnregisterReceiver(BroadcastReceiver receiver) {
                JoinWifiActivity.this.unregisterReceiver(receiver);
            }

            @Override
            public void onNotifyWifiConnected() {
                mTv_message.setText("连接热点成功");
            }

            @Override
            public void onNotifyWifiConnectFailed() {
                mTv_message.setText("连接热点失败");
            }
        };
        wifiConnectManager.openWifi();
        wifiConnectManager.addNetwork(wifiConnectManager.createWifiInfo(SSIDNAME,PASSWORD,WifiConnectManager.TYPE_WEP));
    }


    private void initView() {
        mTv_message = getView(R.id.tv_message);
        mRv_WifiList = getView(R.id.rv_wifiList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv_WifiList.setLayoutManager(layoutManager);
        mRv_WifiList.setHasFixedSize(true);
//        mRv_WifiList.setAdapter(new ArrayAdapter<String>());
    }

}
