package com.bzf.easygame2048.pk.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.base.BaseActivity;
import com.bzf.easygame2048.commonutils.TimeUtils;
import com.bzf.easygame2048.commonutils.ToastUtils;
import com.bzf.easygame2048.main.widget.GameLayout;
import com.bzf.easygame2048.pk.presenter.BluetoothPresenter;
import com.bzf.easygame2048.pk.view.IBluetoothView;

/**
 * com.bzf.easygame2048.pk.widget
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public  class BluetoothActivity extends BaseActivity implements IBluetoothView {

    protected TextView mTv_rivalName;
    protected TextView mTv_rivalScore;
    protected TextView mTv_selfName;
    protected TextView mTv_selfScore;
    protected GameLayout mGameLayout;
    protected BluetoothPresenter mPresenter;
    protected TextView mTv_state;
    /**
     * 5分钟
     */
    protected int mTime = 60;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        mPresenter = new BluetoothPresenter(this);
        mPresenter.initBluetooth();
        initView();
        initListener();
        init();
    }

    protected void init() {};

    @Override
    protected void onDestroy() {
        mPresenter.closeBluetoothIO();
        super.onDestroy();
    }

    protected void initView() {
        mTv_rivalName = getView(R.id.tv_rivalName);
        mTv_rivalScore = getView(R.id.tv_rivalScore);
        mTv_selfName = getView(R.id.tv_selfName);
        mTv_selfScore = getView(R.id.tv_selfScore);
        mGameLayout = getView(R.id.gl_game_panel);
        mTv_state = getView(R.id.tv_state);
        mGameLayout.setVisibility(View.INVISIBLE);
    }

    protected void initListener() {
        mGameLayout.setListenter(new GameLayout.GameListenter() {

            @Override
            public void changeScore(int score) {
                mTv_selfScore.setText(String.valueOf(score));
                mPresenter.writeData(score);
            }

            @Override
            public void gameOver() {

            }

            @Override
            public void modificationGoal(int goal) {

            }
        });
    }

    @Override
    public void readData(String data) {
        if(!TextUtils.isEmpty(data)){
            mTv_rivalScore.setText(data);
        }
    }

    @Override
    public void connectSuccess() {
        mTv_state.setText("连接成功");
        mTv_rivalScore.setText("0");
        mGameLayout.setVisibility(View.VISIBLE);
        timekeepingAndStartGame();
    }

    /**
     * 开始游戏并计时
     */
    private void timekeepingAndStartGame() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mTime, 0);
        valueAnimator.setDuration(mTime*1000);
        valueAnimator.setTarget(mTv_state);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                String time = TimeUtils.secondToMinute(value);
//                mTv_state.setText(time);
                mTv_state.setText(String.valueOf(value));
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mTv_state.setText("游戏开始");
                mGameLayout.restart();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mTv_state.setText("游戏结束");
                //判断谁赢了，弹出对话框。
                showResultDialog(judgeLossOrSuccess());
            }
        });
        valueAnimator.start();
    }

    private void showResultDialog(boolean isWin) {
        String message = "";
        if(isWin){
            message = "恭喜,你赢了";
        }else{
            message="很遗憾,你输了";
        }
         AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        BluetoothActivity.this.onBackPressed();
                    }
                })
                .create();
        alertDialog.show();
    }

    /**
     * 根据分数判断自己是赢是输， true 赢  false 输
     * @return
     */
    private boolean judgeLossOrSuccess() {
        String rivalScore = mTv_rivalScore.getText().toString();
        String selfScore = mTv_selfScore.getText().toString();
       return Integer.valueOf(rivalScore)<Integer.valueOf(selfScore);
    }

    @Override
    public void connectFail() {
        ToastUtils.showToast(getApplicationContext(),"连接失败");
    }

    @Override
    public void error(String errorMsg) {
        ToastUtils.showToast(getApplicationContext(),errorMsg);
    }

    @Override
    public void setNickName(String name) {
        mTv_rivalName.setText(name);
    }

    @Override
    public void connectStop() {
        new AlertDialog.Builder(this)
                .setMessage("连接中断，对方已离开游戏")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        BluetoothActivity.super.onBackPressed();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
