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

import java.util.List;

public class FoodListAdapter extends ArrayAdapter<Food> {
    int mResource;
    private Context mContext;

    public FoodListAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Integer imgid = getItem(position).getImgid();
        String foodname = getItem(position).getFoodname();
        String fooddescription = getItem(position).getFooddescription();
        String foodprice = getItem(position).getFoodprice();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView ivImgid = convertView.findViewById(R.id.ImageView);
        TextView tvFoodname = convertView.findViewById(R.id.nume);
        TextView tvFooddescription = convertView.findViewById(R.id.descriere);
        TextView tvFoodprice = convertView.findViewById(R.id.pret);

        ivImgid.setImageResource(imgid);
        tvFoodname.setText(foodname);
        tvFooddescription.setText(fooddescription);
        tvFoodprice.setText(foodprice);

        return convertView;
    }
}
