package com.bzf.easygame2048.entrance.widget;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ScoreSortRecyclerAdapter extends RecyclerView.Adapter<ScoreSortRecyclerAdapter.ScoreSortViewHolder>{

    private List<User> mList;

    public ScoreSortRecyclerAdapter(List<User> list) {
        this.mList =list;
    }

    /**
     * 将布局转化成View并传递给RecyclerView封装好的ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ScoreSortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scoresort, parent, false);
        return new ScoreSortViewHolder(view);
    }


    /**
     * 建立ViewHolder中视图与数据的关联
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ScoreSortViewHolder holder, int position) {
        setData(holder,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * 装配数据
     * @param holder
     * @param position
     */
    private void setData(ScoreSortViewHolder holder, int position) {
        User user = mList.get(position);
        if(user!=null){
            holder.sortNumber.setText("NO."+(position+1));
            holder.score.setText(String.valueOf(user.getHightScore()));
            String name = user.getName();
            if(name!=null){
                holder.name.setText(name);
            }
            String headPath = user.getHeadPath();
            if(TextUtils.isEmpty(headPath)){
                holder.head.setImageResource(R.drawable.default_head);
            }else{
                //加载网络图片
            }

        }
    }

    public class ScoreSortViewHolder extends RecyclerView.ViewHolder{

        TextView sortNumber;
        ImageView head;
        TextView name;
        TextView score;

        public ScoreSortViewHolder(View itemView) {
            super(itemView);
            sortNumber = (TextView) itemView.findViewById(R.id.tv_sortNumber);
            head = (ImageView) itemView.findViewById(R.id.iv_head);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            score = (TextView) itemView.findViewById(R.id.tv_highScore);
        }
    };
}
