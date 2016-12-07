package com.bzf.easygame2048.pk.widget;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.pk.WifiCreateManager;

/**
 * 创建新的连接
 */
public class NewWifiActivity extends BaseActivity {


    private static final String SSIDNAME = "EASYGAME2048";
    private static final String PASSWORD = "08121027";

    private TextView mTv_promptMessage;
    private WifiManager mWifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wifi);
        initView();
        createHotspot();
    }

    /**
     * 创建热点
     */
    private void createHotspot() {
        WifiCreateManager wifiCreateManager = new WifiCreateManager(this);
        wifiCreateManager.startWifiAp(SSIDNAME,PASSWORD);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initView() {
        mTv_promptMessage = getView(R.id.tv_promptMessge);
    }


    /**
     * 获取热点的配置类
     * @return
     */
    public WifiConfiguration getWifiConfig() {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID=SSIDNAME;
        wifiConfiguration.preSharedKey = PASSWORD;

//        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
//        wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//        wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
//        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//        wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//        wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//        wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//        wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        return wifiConfiguration;
    }
}
