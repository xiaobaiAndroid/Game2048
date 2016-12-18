package com.bzf.easygame2048.pk.presenter;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bzf.easygame2048.commonutils.LogTool;
import com.bzf.easygame2048.pk.BluetoothManager;
import com.bzf.easygame2048.pk.view.IBluetoothView;

import java.io.IOException;

/**
 * com.bzf.easygame2048.pk.presenter
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class BluetoothPresenter {

    private IBluetoothView mView;
    private BluetoothManager mBluetoothManager;
    private BluetoothSocket mSocket;

    public BluetoothPresenter(IBluetoothView view) {
        this.mView = view;
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            switch (msg.what){
                case BluetoothManager.CONNECT_SUCCESS:
                    LogTool.i("presenter","连接成功");
                    mView.connectSuccess();
                break;
                case BluetoothManager.READMESSAGE:
                    LogTool.i("presenter",data.getString("data"));
                    mView.readData(data.getString("data"));
                break;
               case BluetoothManager.NICKNAME:
                   LogTool.i("presenter",data.getString("data"));
                   mView.setNickName(data.getString("data"));
                break;
               case BluetoothManager.STOPGAME:
                   if(mView!=null){
                       mView.connectStop();
                   }
                   break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 初始化蓝牙即打开蓝牙
     */
    public void initBluetooth() {
        mBluetoothManager = new BluetoothManager();
        mBluetoothManager.openBluetooth();
    }

    /**
     * 创建服务端，等待客户端连接
     */
    public void createBluetoothService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String name = mBluetoothManager.getDeviceName() + BluetoothManager.SIGNNAME;
                    BluetoothServerSocket mServiceSocket = mBluetoothManager.getServiceSocket(name, BluetoothManager.GAMEUUID);
                    mSocket = mServiceSocket.accept();
                    mHandler.sendEmptyMessage(BluetoothManager.CONNECT_SUCCESS);
                    mBluetoothManager.readData(mSocket.getInputStream(),mHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                    closeBluetoothIO();
                }

            }
        }).start();
    }

    /**
     * 连接服务端
     * @param device
     */
    public void connectToservice(final BluetoothDevice device){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSocket = device.createInsecureRfcommSocketToServiceRecord(BluetoothManager.GAMEUUID);
                    mBluetoothManager.stopScanBluetooth();
                    //开始连接,阻塞，直到服务器连接
                    mSocket.connect();
                    String text = mBluetoothManager.getDeviceName() +":"+BluetoothManager.RIVAL_NAME;
                    mHandler.sendEmptyMessage(BluetoothManager.CONNECT_SUCCESS);
                    mBluetoothManager.writeData(mSocket.getOutputStream(), text);
                    mBluetoothManager.readData(mSocket.getInputStream(),mHandler);
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.error(e.getMessage());
                }
            }
        }).start();
    }

    /**
     * 关闭流
     */
    public void closeBluetoothIO() {
        try {
            if (mSocket != null) {
                mSocket.close();
            }
            mView = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return mBluetoothManager.getDeviceName();
    }

    /**
     * 输出
     * @param score
     */
    public void writeData(final int score) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mBluetoothManager.writeData(mSocket.getOutputStream(), String.valueOf(score));
                } catch (IOException e) {
                    e.printStackTrace();
                    mView.error(e.getMessage());
                }
            }
        }).start();

    }

}
