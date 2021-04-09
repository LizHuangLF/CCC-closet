package com.exam.closet_f.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exam.closet_f.R;
import com.exam.closet_f.bean.ItemPic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlbumAdapter extends BaseAdapter {
    private List<ItemPic> picList;
    private Context context;
    private LayoutInflater inflater;

    public AlbumAdapter(Context context, List<ItemPic> picList){
        this.picList = picList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Object getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.item_album,null);
            convertView = inflater.from(context).inflate(R.layout.item_album,null);
            holder = new ViewHolder();
//            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.iv = convertView.findViewById(R.id.item_album_image);

            ItemPic pic = picList.get(position);
            holder.iv.setBackgroundResource(pic.getPic());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        ItemPic pic = picList.get(position);
//        if (pic != null) {
//            holder.iv.setBackgroundResource(pic.getPic());
//            holder.text.setText(provinceBean.getName());
//            holder.text.setBackgroundResource(provinceBean.getColor());
//        }
        return convertView;
    }

    class ViewHolder {
        TextView textView;
        ImageView iv;
    }

}

