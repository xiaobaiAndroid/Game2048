package com.bzf.easygame2048.pk.view;

/**
 * com.bzf.easygame2048.pk.view
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public interface IBluetoothView{
    /**
     * 读取数据
     */
    void readData(String data);

    /**
     * 连接成功
     */
    void connectSuccess();

    /**
     * 连接失败
     */
    void connectFail();

    /**
     * 发生错误
     * @param errorMsg
     */
    void error(String errorMsg);

    /**
     *设置队友的昵称
     * @param name
     */
    void setNickName(String name);

    /**
     * 连接中断
     */
    void connectStop();
}
