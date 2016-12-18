package com.bzf.easygame2048.main.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bzf.easygame2048.GameApplication;
import com.bzf.easygame2048.R;
import com.bzf.easygame2048.options.OptionsActivity;

public class MainActivity extends AppCompatActivity {
    /**目标分数*/
    private TextView vGoal;
    /**分数*/
    private TextView vScore;
    /**历史最高分数*/
    private TextView vHighScore;

    /**退一步*/
    private Button vRevert;
    /**重新开始*/
    private Button vRestart;
    /**设置*/
    private Button vOptions;

    private  GameLayout vGameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    @Override
    protected void onDestroy() {
        GameApplication.getIns().saveHighScore(vGameLayout.getmScore());
        super.onDestroy();
    }

    private void initListener() {
        vGameLayout.setListenter(new GameLayout.GameListenter(){

            @Override
            public void changeScore(int score) {
                vScore.setText(String.valueOf(score));
            }

            @Override
            public void gameOver() {
                vGameLayout.restart();
                initData();
            }

            @Override
            public void modificationGoal(int goal) {
                vGoal.setText(String.valueOf(goal));
            }
        });

        vRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vGameLayout.revert();
            }
        });

        vRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vGameLayout.restart();
            }
        });

        vOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                if(Build.VERSION.SDK_INT>=16){

//                }
                //                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, "animation_title").toBundle();
//                ActivityCompat.startActivityForResult(MainActivity.this,intent,0,bundle);
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){
            initData();
            vGameLayout.restart();
        }
    }

    private void initData() {
        //获取历史数据，没有历史数据初始化为0
        vGoal.setText(String.valueOf(GameApplication.GOAL));
        vScore.setText("0");
        vHighScore.setText(String.valueOf(GameApplication.HIGHSCROE));
    }

    private void initView() {
        vGoal = (TextView) findViewById(R.id.tv_goal);
        vScore = (TextView) findViewById(R.id.tv_score);
        vHighScore = (TextView) findViewById(R.id.tv_highScore);
        vRevert = (Button) findViewById(R.id.bt_revert);
        vRestart = (Button) findViewById(R.id.bt_restart);
        vOptions = (Button) findViewById(R.id.bt_options);
        vGameLayout = (GameLayout) findViewById(R.id.gl_game_panel);
    }


}
