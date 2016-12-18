package com.bzf.easygame2048.main.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.GridLayout;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.main.MoveArithmetic;
import com.bzf.easygame2048.main.MoveOptions;
import com.bzf.easygame2048.commonutils.Config;
import com.bzf.easygame2048.commonutils.DisplayUtils;
import com.bzf.easygame2048.commonutils.LogTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class GameLayout extends GridLayout {
    /**
     * 保存item的排列
     */
    private GameItem[][] mGameMatrixs;

    /**
     * 剩余空白item的集合
     */
    private List<Point> mBlanks;

    private int mDownX;
    private int mDownY;

    /**
     * 保存历史分数的集合
     */
    private List<Integer> mHistoryScoreList;

    /**
     * 保存历史方块位置的集合
     */
    private List<int[][]> mHistoryItemsList;

    private GameListenter mListenter;

    private MoveOptions mMoveOption;

    private AlertDialog mAlertDialog;

    /**
     * 分数
     */
    private int mScore = 0;


    public GameLayout(Context context) {
        super(context);
        initGameMatrix();
    }

    public GameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameMatrix();
    }

    private void initGameMatrix() {
        removeAllViews();
        mBlanks = new ArrayList<Point>();
        mHistoryScoreList = new ArrayList<Integer>();
        mHistoryItemsList = new ArrayList<int[][]>();
        mGameMatrixs = new GameItem[GameApplication.GAMELINES][GameApplication.GAMELINES];
        mMoveOption = new MoveArithmetic(this);

        setRowCount(GameApplication.GAMELINES);
        setColumnCount(GameApplication.GAMELINES);
        initGameView(getCardSize());
        addRandomNum();
        addRandomNum();
    }

    public void setListenter(GameListenter listener) {
        mListenter = listener;
    }

    /*在剩余的空位上生成随机的一个数（2或4）*/
    private void addRandomNum() {
        getBlanks();
        Point point = mBlanks.get(getPosition());
        int num = getNum();
        mGameMatrixs[point.x][point.y].setNumber(num);
        animCreat(mGameMatrixs[point.x][point.y]);
    }

    /**
     * 生成随机数
     * @return
     */
    private int getNum() {
        return Math.random() > 0.4f ? 2 : 4;
    }

    /**
     * 生成随机位置
     * @return
     */
    private int getPosition() {
        return (int) (Math.random() * mBlanks.size());
    }

    /**
     * 开启生成随机小方块的动画
     * @param gameItem
     */
    private void animCreat(GameItem gameItem) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1f, 0.1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        gameItem.setAnimation(null);
        gameItem.getvTvNum().startAnimation(scaleAnimation);
    }

    private int getCardSize() {
        return DisplayUtils.getDisplayWidth() / GameApplication.GAMELINES;
    }

    private void initGameView(int cardSize) {
        removeAllViews();
        GameItem gameItem;
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                gameItem = new GameItem(getContext(), 0);
                mGameMatrixs[i][j] = gameItem;
                addView(gameItem, cardSize, cardSize);
                mBlanks.add(new Point(i, j));
//                mHistoryItems[i][j] = 0;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                saveHistory();
                mDownX = x;
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                mMoveOption.move(x - mDownX, y - mDownY);
                if (isMove()) {
                    addRandomNum();
                    mListenter.changeScore(mScore);
                }
                int state = getOptionState();
                Log.i("bzf", "state=" + state);
                if (state == 0) {
                    GameApplication.getIns().saveHighScore(mScore);
                    showGameOverDialog();
                } else if (state == 2) {
                    GameApplication.getIns().saveHighScore(mScore);
                    showfinishGoalDialog();
                }
                break;
        }
        return true;
    }

    private void showfinishGoalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("达到目标,是否继续挑战");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                for(int i = 0; i< Config.mGameGoalArray.length-1; i++){
                    if(Config.mGameGoalArray[i]==GameApplication.GOAL){
                        GameApplication.GOAL = Config.mGameGoalArray[i+1];
                        break;
                    }
                }
                LogTool.i("bzf", "showfinishGoalDialog--"+GameApplication.GOAL);
                mListenter.modificationGoal(GameApplication.GOAL);
                mAlertDialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAlertDialog.dismiss();
                restart();
            }
        });
        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
    }

    private void showGameOverDialog() {
        GameApplication.getIns().saveHighScore(mScore);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("游戏结束,未达到目标");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAlertDialog.dismiss();
                mListenter.gameOver();
            }
        });
        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);
        mAlertDialog.show();
    }

    /**
     * 保存上一步操作
     */
    private void saveHistory() {
        int[][] mHistoryItems = new int[GameApplication.GAMELINES][GameApplication.GAMELINES];
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                mHistoryItems[i][j] = mGameMatrixs[i][j].getNumber();
            }
        }
        mHistoryItemsList.add(mHistoryItems);
        mHistoryScoreList.add(mScore);
    }

    /**
     * 判断游戏该执行怎么样的操作
     * @return
     */
    private int getOptionState() {
        int state = 1;
        getBlanks();
        if (isFinishGoal()) {
            state = 2;
        } else if (mBlanks.size() == 0) {
            if (!isHasMergeNum()) {
                state = 0;
            }
        }
        return state;
    }

    /**
     * 判断是否完成目标
     * @return
     */
    private boolean isFinishGoal() {
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                if (mGameMatrixs[i][j].getNumber() >= GameApplication.GOAL) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否还有可以合并的数字
     * @return
     */
    private boolean isHasMergeNum() {
        for (int i = 0; i < GameApplication.GAMELINES - 1; i++) {
            for (int j = 0; j < GameApplication.GAMELINES - 1; j++) {
                if (mGameMatrixs[i][j].getNumber() == mGameMatrixs[i][j + 1].getNumber()) {
                    return true;
                }
                if (mGameMatrixs[i][j].getNumber() == mGameMatrixs[i + 1][j].getNumber()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取剩余空白数量
     */
    public void getBlanks() {
        mBlanks.clear();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                if (mGameMatrixs[i][j].getNumber() == 0) {
                    mBlanks.add(new Point(i, j));
                }
            }
        }
    }

    /**
     * 根据每个方格数字书否发生变化，判断是否移动
     * @return
     */
    public boolean isMove() {
        int[][] historyItems = getLastSaveMatrix();
        for (int i = 0; i < GameApplication.GAMELINES; i++) {
            for (int j = 0; j < GameApplication.GAMELINES; j++) {
                if (mGameMatrixs[i][j].getNumber() != historyItems[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从历史矩阵集合中，获取最后一次保存的矩阵
     * @return
     */
    private int[][] getLastSaveMatrix() {
        return  mHistoryItemsList.get(mHistoryItemsList.size() - 1);
    }


    /**
     * 退一步
     */
    public void revert() {
        int[][] historyItems = null;
        int historyScore = 0;
        if (mHistoryItemsList.size() > 0) {
            historyItems = mHistoryItemsList.remove(mHistoryItemsList.size() - 1);
        }
        if (mHistoryScoreList.size() > 0) {
            historyScore = mHistoryScoreList.remove(mHistoryScoreList.size() - 1);
        }
        if (historyItems != null) {
            for (int i = 0; i < GameApplication.GAMELINES; i++) {
                for (int j = 0; j < GameApplication.GAMELINES; j++) {
                    mGameMatrixs[i][j].setNumber(historyItems[i][j]);
                    mScore = historyScore;
                    mListenter.changeScore(mScore);
                }
            }
        }
    }

    /**
     * 重新开始
     */
    public void restart() {
        initGameMatrix();
        mScore = 0;
        mListenter.changeScore(mScore);
    }

    public GameItem[][] getmGameMatrixs() {
        return mGameMatrixs;
    }

    public void setmGameMatrixs(GameItem[][] mGameMatrixs) {
        this.mGameMatrixs = mGameMatrixs;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    /**
     * 修改分数的接口
     */
    public interface GameListenter {

        public void changeScore(int score);

        public void gameOver();

        public void modificationGoal(int goal);
    }
}
