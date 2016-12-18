package com.bzf.easygame2048.pk.widget;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * 服务端
 */
public class ServiceActivity extends BluetoothActivity{

    private static final int DISCOVERY_REQUEST = 0x1;

    @Override
    protected void init() {
        initData();
        showRequestBluetoothDialog();
    }

    private void initData() {
        mTv_selfName.setText(mPresenter.getName());
        mTv_selfScore.setText("0");
        mTv_state.setText("等待连接。。");

    }

    /**
     * 提示用户允许请求蓝牙的行为。
     */
    private void showRequestBluetoothDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("这个功能需要打开蓝牙和设置蓝牙必须可被扫描，请允许")
                .setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //设置设备可被发现
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE), DISCOVERY_REQUEST);
                    }
                })
                .create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DISCOVERY_REQUEST) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ServiceActivity.this, "请设置蓝牙为可见状态", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
            } else {
                mPresenter.createBluetoothService();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
