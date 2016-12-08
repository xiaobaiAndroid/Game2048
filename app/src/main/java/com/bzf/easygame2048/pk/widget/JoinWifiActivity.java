package com.bzf.easygame2048.pk.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.utils.LogTool;

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
    private WifiManager mWifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_wifi);
        initView();
        startWifi();
        registerWifiScanReceiver();
        scanHotSpot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mWifiScanReceiver);
    }

    private void registerWifiScanReceiver() {
        registerReceiver(mWifiScanReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    private void startWifi() {
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if(!mWifiManager.isWifiEnabled()){
            if(mWifiManager.getWifiState()!=WifiManager.WIFI_STATE_ENABLED){
                mWifiManager.setWifiEnabled(true);
            }
        }
    }

    private BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> scanResults = mWifiManager.getScanResults();
            LogTool.i("bzf","scanResults.size="+scanResults.size()+"----"+scanResults.toString());

        }
    };

    /**
     * 扫描热点
     */
    private void scanHotSpot() {
        mWifiManager.startScan();
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
