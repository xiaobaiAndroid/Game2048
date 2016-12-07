package com.bzf.easygame2048.options.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bzf.easygame2048.R;

/**
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class GameDialog<T> extends Dialog{
    private Context mContext;
    private ListView mListView;

    private T[] mArray;

    private DialogItemListener mListener;

    public GameDialog(Context context,T[] t) {
        this(context, R.style.gameDialogStyle,t);
    }

    public GameDialog(Context context, int themeResId,T[] t) {
        super(context, themeResId);
        this.mContext = context;
        this.mArray = t;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_gamelines, null);
        initView(view);
//        setCanceledOnTouchOutside(false);
        setContentView(view);
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_dialog);
        ArrayAdapter<T> adapter = new ArrayAdapter<T>(mContext,R.layout.item_dialog,R.id.tv_dialog_content,mArray);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mListener.selectItem(position);
            }
        });
    }

    public void  setDialogItemListener(DialogItemListener listener){
       mListener =  listener;
    }

    public interface DialogItemListener{
        public void selectItem(int position);
    }
}
