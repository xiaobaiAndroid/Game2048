package com.bzf.easygame2048.main;

import com.bzf.easygame2048.main.widget.GameItem;
import com.bzf.easygame2048.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动算法抽象类
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public abstract class MoveOptions {

    /**用-1表示未赋值*/
    protected static final int EMPTYVALUE = -1;

    /**作比较的值，和下一个小方格的值相等则需要合并*/
    protected int mTempItemNum = EMPTYVALUE;

    /**
     * 保存item的排列
     */
    protected GameItem[][] mGameMatrixs;

    /**值得集合,保存一行或一列不为0的值*/
    protected List<Integer> mValueList = new ArrayList<Integer>();

    public MoveOptions(GameItem[][] gameMatrixs){
        mGameMatrixs = gameMatrixs;
    }

    /**移动*/
    public void move(int offsetX,int offsetY){
        int slideDis = (int) (DisplayUtils.getDensity()*10);
        if(Math.abs(offsetX)>Math.abs(offsetY)){//左右方向滑动
            if(offsetX>slideDis){
                slideRight();
            }else if(offsetX<-slideDis){
                slideLeft();
            }
        }else{//上下方向滑动
            if(offsetY>slideDis){
                slideBottom();
            }else if(offsetY<-slideDis){
                slideTop();
            }
        }
    }

    public abstract void slideLeft();
    public abstract  void slideRight();
    public abstract void slideTop();
    public abstract void slideBottom();

}
