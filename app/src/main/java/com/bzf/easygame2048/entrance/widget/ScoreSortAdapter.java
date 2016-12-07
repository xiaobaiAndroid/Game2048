package com.bzf.easygame2048.entrance.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bzf.easygame2048.R;
import com.bzf.easygame2048.bean.User;

import java.util.List;

/**
 * 榜单适配器
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class ScoreSortAdapter extends BaseAdapter {

    private Context mContext;
    private List<User> mList;

    public ScoreSortAdapter(Context context, List<User> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (contentView == null) {
            holder = new ViewHolder();
            contentView = LayoutInflater.from(mContext).inflate(R.layout.item_scoresort, null);
            holder.sortNumber = (TextView) contentView.findViewById(R.id.tv_sortNumber);
            holder.head = (ImageView) contentView.findViewById(R.id.iv_head);
            holder.name = (TextView) contentView.findViewById(R.id.tv_name);
            holder.score = (TextView) contentView.findViewById(R.id.tv_highScore);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        setData(holder, position);
        return contentView;
    }


    /**
     * @param holder
     * @param position
     */
    private void setData(ViewHolder holder, int position) {
        User user = mList.get(position);
        if (user != null) {
            holder.sortNumber.setText("NO." + (position + 1));
            holder.score.setText(String.valueOf(user.getHightScore()));
            String name = user.getName();
            if (name != null) {
                holder.name.setText(name);
            }
            String headPath = user.getHeadPath();
            if (TextUtils.isEmpty(headPath)) {
                holder.head.setImageResource(R.drawable.default_head);
            } else {
                //加载网络图片
            }

        }
    }


    private class ViewHolder {
        TextView sortNumber;
        ImageView head;
        TextView name;
        TextView score;
    }

    ;
}
