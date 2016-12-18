package com.bzf.easygame2048.main.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bzf.easygame2048.commonutils.DimensionUtils;

/**
 * 小方块
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class GameItem extends FrameLayout {

    /**
     * item要显示的数
     */
    private int mCardNumber;

    private TextView vTvNum;


    public GameItem(Context context, int cardShowNum) {
        super(context);
        this.mCardNumber = cardShowNum;
        initCardItem();
    }

    private void initCardItem() {
        setBackgroundColor(Color.GRAY);
        vTvNum = new TextView(getContext());
        setNumber(mCardNumber);
        //根据选择的模式修改字体
        vTvNum.setTextSize(DimensionUtils.spToPx(12.5f));
        vTvNum.getPaint().setFakeBoldText(true);//设置为粗体
        vTvNum.setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        int margin = (int) DimensionUtils.dpTopx(1.5f);
        layoutParams.setMargins(margin, margin, margin, margin);
        addView(vTvNum, layoutParams);
    }

    public int getNumber() {
        return mCardNumber;
    }

    public void setNumber(int num) {
        this.mCardNumber = num;
        if (num == 0) {
            vTvNum.setText("");
        } else {
            vTvNum.setText(String.valueOf(num));
        }
        setItemBackground(num);
    }

    /**
     * 设置小方块的颜色
     * @param num
     */
    private void setItemBackground(int num) {
        switch (num) {
            case 0:
                vTvNum.setBackgroundColor(0x00000000);
                break;
            case 2:
                vTvNum.setBackgroundColor(0xffeee5db);
                break;
            case 4:
                vTvNum.setBackgroundColor(0xffeee0ca);
                break;
            case 8:
                vTvNum.setBackgroundColor(0xfff2c17a);
                break;
            case 16:
                vTvNum.setBackgroundColor(0xfff59667);
                break;
            case 32:
                vTvNum.setBackgroundColor(0xfff68c6f);
                break;
            case 64:
                vTvNum.setBackgroundColor(0xfff66e3c);
                break;
            case 128:
                vTvNum.setBackgroundColor(0xffedcf74);
                break;
            case 256:
                vTvNum.setBackgroundColor(0xffedcc64);
                break;
            case 512:
                vTvNum.setBackgroundColor(0xffedc854);
                break;
            case 1024:
                vTvNum.setBackgroundColor(0xffedc54f);
                break;
            case 2048:
                vTvNum.setBackgroundColor(0xffedc32e);
                break;
            default:
                vTvNum.setBackgroundColor(0xff3c4a34);
                break;
        }
    }

    public TextView getvTvNum() {
        return vTvNum;
    }

    public void setvTvNum(TextView vTvNum) {
        this.vTvNum = vTvNum;
    }
}
