package com.bzf.easygame2048.pk;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bzf.easygame2048.commonutils.LogTool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * com.bzf.easygame2048.pk
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class BluetoothManager {
    private static final String TAG = BluetoothManager.class.getName();

    public static final UUID GAMEUUID = UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865");
    /**
     * 游戏中断或停止
     */
    public static final int STOPGAME = 4;
    private   BluetoothAdapter mBluetoothAdapter;

    /**
     * 连接成功
     */
    public static final int CONNECT_SUCCESS = 1;
    /**
     * 读取消息
     */
    public static final int READMESSAGE = 2;

    /**
     * 客户端昵称
     */
    public static final int NICKNAME = 3;

    /**
     * 标记，只有包含这个标记的蓝牙设备才会显示出来
     */
    public static final String SIGNNAME = "Game2048";

    /**
     * 用于传输对方昵称
     */
    public static final String RIVAL_NAME = SIGNNAME;


    public BluetoothManager(){
        mBluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();

    }

    /**
     * 打开蓝牙
     */
    public  void openBluetooth(){
        if(!mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.enable();
        }
    }

    /**
     * 关闭蓝牙
     */
    public void closeBluetooth(){
        if(mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.disable();
        }
    }

    /**
     * 开始扫描设备
     */
    public void startScanBluetooth(){
        if(!mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.startDiscovery();
        }
    }

    /**
     * 取消扫描
     */
    public void stopScanBluetooth(){
        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
        }
    }

    public String getDeviceName(){
        return mBluetoothAdapter.getName();
    }

    public BluetoothServerSocket getServiceSocket(String name, UUID uuid) throws IOException {
        return mBluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord(name, uuid);
    }

    /**
     * 读取数据
     * @param inputStream
     * @param handler
     * @throws IOException
     */
    public void readData(InputStream inputStream,Handler handler) {
        Message message = null;
        Bundle bundle = null;
        byte[] buff = new byte[1024];
        int len = -1;
        try {
            while((len =inputStream.read(buff))!=-1){
                String data = new String(buff,0,len,"utf-8");
                LogTool.i(TAG,"read data="+data);
                message = new Message();
                bundle = new Bundle();
                if(data.contains(RIVAL_NAME)){
                    String[] strs = data.split(":");
                    message.what = NICKNAME;
                    bundle.putString("data",strs[0]);
                }else{
                    message.what = READMESSAGE;
                    bundle.putString("data",data);
                }
                message.setData(bundle);
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            handler.sendEmptyMessage(STOPGAME);
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            LogTool.i("bluetoothManager","read is close");
        }
    }

    /**
     * 把分数输出到客户端
     * @param outputStream
     * @param text
     */
    public void writeData(final OutputStream outputStream, final String text) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.write(text.getBytes("utf-8"));
                    outputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void setBluetoothName(String name) {
        mBluetoothAdapter.setName(name);
    }
}
