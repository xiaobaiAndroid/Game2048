package com.bzf.easygame2048.pk.widget;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.pk.BluetoothManager;
import com.bzf.easygame2048.commonutils.LogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建新的连接
 */
public class ScanBluetoothActivity extends BaseActivity {


    private TextView mTv_promptMessage;
    private WifiManager mWifiManager;
    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mList;

    private BluetoothMessageAdapter mBluetoothMessageAdapter;
    private RecyclerView mRv_bluetooth;
    private BluetoothSocket mBluetoothSocket;


    private static final int DISCOVERY_REQUEST = 0x1;
    /**
     * 搜索蓝牙广播事件
     */
    private static final String START_ACTION = BluetoothAdapter.ACTION_DISCOVERY_STARTED;

    /**
     * 搜索蓝牙广播结束事件
     */
    private static final String END_ACTION = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;

    /**
     * 找到蓝牙设备事件
     */
    private static final String FIND_ACTION = BluetoothDevice.ACTION_FOUND;

    private BluetoothManager mBluetoothManager;
    private TextView mTv_promptMessage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanbluetooth);
        initView();
        initBluetooth();
        initData();
    }

    private void initBluetooth() {
        mBluetoothManager = new BluetoothManager();
        //监听扫描到的设备
        registerReceiver(mBluetoothReceiver, new IntentFilter(START_ACTION));
        registerReceiver(mBluetoothReceiver, new IntentFilter(END_ACTION));
        registerReceiver(mBluetoothReceiver, new IntentFilter(FIND_ACTION));
        //开始扫描
        mBluetoothManager.startScanBluetooth();

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mBluetoothReceiver);
        super.onDestroy();
    }

    private void initData() {
        mList = new ArrayList<BluetoothDevice>();
        mBluetoothMessageAdapter = new BluetoothMessageAdapter(mList);
        mRv_bluetooth.setAdapter(mBluetoothMessageAdapter);
        mBluetoothMessageAdapter.setOnItemClickListener(new BluetoothMessageAdapter.BluetoothOnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                BluetoothDevice bluetoothDevice = mList.get(position);
                Intent intent = new Intent(ScanBluetoothActivity.this, ClientActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("BluetoothDevice",bluetoothDevice);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initView() {
        mTv_promptMessage = getView(R.id.tv_promptMessge);
        mRv_bluetooth = getView(R.id.rv_Bluetooth);
        mTv_promptMessage = getView(R.id.tv_promptMessge);

        mRv_bluetooth.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv_bluetooth.setLayoutManager(linearLayoutManager);
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (START_ACTION == action) {
                LogTool.i("bzf", "蓝牙搜索开始");
            } else if (FIND_ACTION == action) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name = device.getName();
                LogTool.i("bzf", "搜索到设备：" + device.getName());
//                if(name.contains(BluetoothManager.SIGNNAME)){
                    mTv_promptMessage.setVisibility(View.GONE);
                    mList.add(device);
                    mBluetoothMessageAdapter.notifyDataSetChanged();
//                }
            } else if (END_ACTION == action) {
                LogTool.i("bzf", "蓝牙搜索结束");
            }
        }
    };

}
