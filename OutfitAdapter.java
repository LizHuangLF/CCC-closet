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

public class OutfitAdapter extends BaseAdapter {
    private LayoutInflater mlayout;
    private Context mcontext;

    //https://blog.csdn.net/qq_36243942/article/details/82178388?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161650326016780274194987%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=161650326016780274194987&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-4-82178388.pc_search_result_cache&utm_term=gridview
    //https://blog.csdn.net/lyy666888/article/details/79163632?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522161650326016780274194987%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=161650326016780274194987&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v29-1-79163632.pc_search_result_cache&utm_term=gridview

    public OutfitAdapter(Context context){
        this.mcontext = context;
        mlayout = LayoutInflater.from(context);
    }

    public OutfitAdapter(HeartFragment heartFragment, List<Heart> fitList) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mlayout.inflate(R.layout.item_gridview_outfit,null);
            holder = new ViewHolder();
            holder.iv = convertView.findViewById(R.id.g_iv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


        return null;
    }

    static class ViewHolder{
        public ImageView iv;
        public TextView tvName;
    }
}
