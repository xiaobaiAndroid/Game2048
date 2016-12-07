package com.bzf.easygame2048.options;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.R;
import com.bzf.easygame2048.options.widget.GameDialog;
import com.bzf.easygame2048.utils.Config;

/**设置页面*/
public class OptionsActivity extends AppCompatActivity {
    private Button vGameLines;
    private Button vGoal;
    private Button vConfirm;
    private Button vBack;
    private Button vContact;

    private Integer[] mGameLinesArray ;
    private Integer[] mGameGoalArray ;
    private GameDialog mGameLinesDialog;
    private GameDialog mGoalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        vGameLines.setText(String.valueOf(GameApplication.GAMELINES));
        vGoal.setText(String.valueOf(GameApplication.GOAL));
        mGameLinesArray =  Config.mGameLinesArray;
        mGameGoalArray = Config.mGameGoalArray;
    }

    private void initListener() {
        vGameLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGameLines();
            }


        });
        vGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGoal();
            }
        });
        vBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        vConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1,new Intent());
                finish();
            }
        });

        vContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void selectGoal() {
        if(mGoalDialog==null){
            mGoalDialog = new GameDialog(this, mGameGoalArray);
        }
        mGoalDialog.setDialogItemListener(new GameDialog.DialogItemListener() {
            @Override
            public void selectItem(int position) {
                Integer value = mGameGoalArray[position];
                GameApplication.GOAL = value;
                vGoal.setText(String.valueOf(value));
                mGoalDialog.dismiss();
            }
        });
        mGoalDialog.show();
    }

    private void selectGameLines() {
        if(mGameLinesDialog==null){
            mGameLinesDialog = new GameDialog(this, mGameLinesArray);
        }
        mGameLinesDialog.setDialogItemListener(new GameDialog.DialogItemListener() {
            @Override
            public void selectItem(int position) {
                Integer value = mGameLinesArray[position];
                vGameLines.setText(String.valueOf(value));
                GameApplication.GAMELINES = value;
                mGameLinesDialog.dismiss();
            }
        });
        mGameLinesDialog.show();
    }


    private void initView() {
        vGameLines = (Button) findViewById(R.id.bt_gameLines);
         vGoal =  (Button) findViewById(R.id.bt_goal);
        vBack = (Button) findViewById(R.id.bt_back);
        vConfirm = (Button) findViewById(R.id.bt_confirm);
        vContact = (Button) findViewById(R.id.bt_contact);
    }
}
