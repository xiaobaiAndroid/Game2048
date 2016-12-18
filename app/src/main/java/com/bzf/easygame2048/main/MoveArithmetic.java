package com.bzf.easygame2048.main;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.main.widget.GameItem;
import com.bzf.easygame2048.main.widget.GameLayout;

/**
 * 具体移动算法操作
 * Created by baizhengfu on 2016/11/21.
 * Email：709889312@qq.com
 */
public class MoveArithmetic extends MoveOptions {

    public MoveArithmetic(GameLayout gamelayout) {
        super(gamelayout);
    }

    /**
     * 向下滑动
     */
    @Override
    public void slideBottom() {
        GameItem[][] gameItems = mGameLayout.getmGameMatrixs();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = GameApplication.GAMELINES - 1; j >= 0; j--) {

                int number = gameItems[j][i].getNumber();
                getMovedValue(number);
            }
            addLastNumber();

            for (int k = 0; k < mValueList.size(); k++) {
                gameItems[GameApplication.GAMELINES - 1 - k][i].setNumber(mValueList.get(k));
            }
            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                gameItems[GameApplication.GAMELINES - 1 - m][i].setNumber(0);
            }
            reset();
        }
    }

    /**
     * 向上滑动
     */
    @Override
    public void slideTop() {
        GameItem[][] gameItems = mGameLayout.getmGameMatrixs();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                int number = gameItems[j][i].getNumber();
                getMovedValue(number);
            }
            addLastNumber();
            for (int k = 0; k < mValueList.size(); k++) {
                gameItems[k][i].setNumber(mValueList.get(k));
            }
            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                gameItems[m][i].setNumber(0);
            }
            reset();
        }

    }


    /**
     * 向右滑动
     */
    @Override
    public void slideRight() {
        GameItem[][] gameItems = mGameLayout.getmGameMatrixs();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = GameApplication.GAMELINES - 1; j >= 0; j--) {
                int number = gameItems[i][j].getNumber();
                getMovedValue(number);
            }
            addLastNumber();

            int valueSize = mValueList.size();
            for (int k = 0; k < valueSize; k++) {
                gameItems[i][GameApplication.GAMELINES - 1 - k].setNumber(mValueList.get(k));
            }
            //把没有值方块重新赋值为0
            for (int m = valueSize; m < GameApplication.GAMELINES; m++) {
                gameItems[i][GameApplication.GAMELINES - 1 - m].setNumber(0);
            }
            reset();
        }
    }

    /**
     * 向左滑动
     */
    @Override
    public void slideLeft() {
        GameItem[][] gameItems = mGameLayout.getmGameMatrixs();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                int number = gameItems[i][j].getNumber();
                getMovedValue(number);
            }
            addLastNumber();
            for (int k = 0; k < mValueList.size(); k++) {
                gameItems[i][k].setNumber(mValueList.get(k));
            }

            for (int m = mValueList.size(); m < GameApplication.GAMELINES; m++) {
                gameItems[i][m].setNumber(0);
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
                    int score = mGameLayout.getmScore();
                    score += mTempItemNum * 2;
                    mGameLayout.setmScore(score);
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
