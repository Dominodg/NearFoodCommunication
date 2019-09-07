package com.nearfoodcommunication.menu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearfoodcommunication.main.R;
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.menu.model.FoodType;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodTypeListAdapter extends ArrayAdapter<FoodType> {
    private Context mContext;
    int mResource;

    public FoodTypeListAdapter(Context context, int resource, List<FoodType> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imgid = getItem(position).getFoodPicture();
        String foodname = getItem(position).getFoodName();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView ivImgid = (ImageView) convertView.findViewById(R.id.ImageView);
        TextView tvFoodname = (TextView) convertView.findViewById(R.id.TextView);

        Picasso.with(mContext).load(imgid).into(ivImgid);
        tvFoodname.setText(foodname);

        return convertView;
    }

}
