package com.exam.closet_f.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exam.closet_f.R;
import com.exam.closet_f.bean.Heart;
import com.exam.closet_f.bean.Outfit;
import com.exam.closet_f.fragment.HeartFragment;

import java.util.List;

public class HeartAdapter extends BaseAdapter {
    private List<Heart> list;
    private Context mContext;
    private HeartFragment heartFragment;

    public HeartAdapter(HeartFragment fragment, List<Heart> list, Context mContext){
        heartFragment = fragment;
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView;
        if(convertView == null){
            listItemView = new ListItemView();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listv_heart,null);
            Heart heart = list.get(position);
            listItemView.ivOutfit = convertView.findViewById(R.id.l_iv);
            listItemView.ivHeadpic = convertView.findViewById(R.id.liv_head);
            listItemView.tvName = convertView.findViewById(R.id.ltv_name);
            listItemView.tvHeart = convertView.findViewById(R.id.ltv_heart_number);
            listItemView.detail = convertView.findViewById(R.id.ltv_detail);

            listItemView.ivOutfit.setBackgroundResource(heart.getOutfitPic());
            listItemView.ivHeadpic.setBackgroundResource(heart.getHeadPic());
            listItemView.tvName.setText(heart.getUserName());
            listItemView.tvHeart.setText(heart.getHeartNum());
            listItemView.detail.setText(heart.getDetail());
            convertView.setTag(convertView);
        }
        return convertView;
    }

    private class ListItemView{
        ImageView ivOutfit;
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvHeart;
        TextView detail;
    }
}
