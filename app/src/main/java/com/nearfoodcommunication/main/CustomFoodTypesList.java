package com.nearfoodcommunication.main;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomFoodTypesList extends ArrayAdapter<String> {
    private Integer[] imgid;
    private String[] foodname;
    private Activity context;

    public CustomFoodTypesList(Activity context, Integer[] imgid, String[] foodname){
        super(context, R.layout.menu_layout, foodname);

        this.context=context;
        this.foodname=foodname;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder = null;
        if(r==null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.menu_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder =(ViewHolder) r.getTag();
        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw.setText(foodname[position]);

        return r;
    }

    class ViewHolder{
        TextView tvw;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw=(TextView) v.findViewById(R.id.TextView);
            ivw= (ImageView) v.findViewById(R.id.ImageView);

        }
    }
}
