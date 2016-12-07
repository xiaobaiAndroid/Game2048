package com.bzf.easygame2048.main;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.main.widget.GameItem;

/**
 * 具体移动算法操作
 * Created by baizhengfu on 2016/11/21.
 * Email：709889312@qq.com
 */
public class MoveArithmetic extends MoveOptions {

    public MoveArithmetic(GameItem[][] gameMatrixs) {
        super(gameMatrixs);
    }

    /**
     * 向下滑动
     */
    @Override
    public void slideBottom() {
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = GameApplication.GAMELINES - 1; j >= 0; j--) {
                int number = mGameMatrixs[j][i].getNumber();
                getMovedValue(number);
            }
            addLastNumber();

            for (int k = 0; k < mValueList.size(); k++) {
                mGameMatrixs[GameApplication.GAMELINES - 1 - k][i].setNumber(mValueList.get(k));
            }
            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                mGameMatrixs[GameApplication.GAMELINES - 1 - m][i].setNumber(0);
            }
            reset();
        }
    }

    /**
     * 向上滑动
     */
    @Override
    public void slideTop() {
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                int number = mGameMatrixs[j][i].getNumber();
                getMovedValue(number);
            }
            addLastNumber();
            for (int k = 0; k < mValueList.size(); k++) {
                mGameMatrixs[k][i].setNumber(mValueList.get(k));
            }
            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                mGameMatrixs[m][i].setNumber(0);
            }
            reset();
        }

    }


    /**
     * 向右滑动
     */
    @Override
    public void slideRight() {
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = GameApplication.GAMELINES - 1; j >= 0; j--) {
                int number = mGameMatrixs[i][j].getNumber();
                getMovedValue(number);
            }
            addLastNumber();

            int valueSize = mValueList.size();
            for (int k = 0; k < valueSize; k++) {
                mGameMatrixs[i][GameApplication.GAMELINES - 1 - k].setNumber(mValueList.get(k));
            }
            //把没有值方块重新赋值为0
            for (int m = valueSize; m < GameApplication.GAMELINES; m++) {
                mGameMatrixs[i][GameApplication.GAMELINES - 1 - m].setNumber(0);
            }
            reset();
        }
    }

    /**
     * 向左滑动
     */
    @Override
    public void slideLeft() {
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                int number = mGameMatrixs[i][j].getNumber();
                getMovedValue(number);
            }
            addLastNumber();
            for (int k = 0; k < mValueList.size(); k++) {
                mGameMatrixs[i][k].setNumber(mValueList.get(k));
            }

            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                mGameMatrixs[i][m].setNumber(0);
            }
            reset();
        }
    }

    /**
     * 调用getMovedValue（）后，会有最后一个小方格的数字没有存到mValueList集合中，所以添加最后一个数到mValueList集合中
     */
    private void addLastNumber() {
        //添加最后一个数
        if (mTempItemNum != EMPTYVALUE) {
            mValueList.add(mTempItemNum);
        }
    }

    /**
     * 获取一行或一列移动后，数字不为0的小方格的集合mValueList
     */
    private void getMovedValue(int number) {
        if (number != 0) {
            if (mTempItemNum == EMPTYVALUE) {
                mTempItemNum = number;
            } else {
                if (mTempItemNum == number) {
                    mValueList.add(mTempItemNum * 2);
                    GameApplication.SCORE += mTempItemNum * 2;
                    mTempItemNum = EMPTYVALUE;
                } else {
                    mValueList.add(mTempItemNum);
                    mTempItemNum = number;
                }
            }
        }
    }

    /**
     * 重置
     */
    private void reset() {
        mTempItemNum = EMPTYVALUE;
        mValueList.clear();
    }

}
