package com.bzf.easygame2048.pk.widget;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bzf.easygame2048.R;

import java.util.List;

/**
 * com.bzf.easygame2048.pk.widget
 * Created by baizhengfu
 * Email：709889312@qq.com
 */
public class BluetoothMessageAdapter extends RecyclerView.Adapter<BluetoothMessageAdapter.BluetoothHolder>{
    private List<BluetoothDevice> mList;
    private BluetoothOnItemClick mListener;

    public BluetoothMessageAdapter(List<BluetoothDevice> list){
        mList = list;
    }

    public void setOnItemClickListener(BluetoothOnItemClick listener){
        this.mListener = listener;
    }

    @Override
    public BluetoothHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bluetooth_layout,null);
        return new BluetoothHolder(view);
    }

    @Override
    public void onBindViewHolder(BluetoothHolder holder, int position) {
        BluetoothDevice bluetoothDevice = mList.get(position);
        holder.mTV_BluetoothMessage.setText(bluetoothDevice.getName()+":"+bluetoothDevice.getAddress());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

      class BluetoothHolder extends RecyclerView.ViewHolder{
        TextView mTV_BluetoothMessage;

        public BluetoothHolder(View itemView) {
            super(itemView);
            mTV_BluetoothMessage = (TextView) itemView.findViewById(R.id.tv_bluetoothMessage);
            mTV_BluetoothMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(view,getPosition());
                }
            });
        }
    };

    public interface BluetoothOnItemClick{

       void onItemClick(View view,int position);
    }
}
