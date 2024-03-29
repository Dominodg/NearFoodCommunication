package com.nearfoodcommunication.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearfoodcommunication.database.Database;
import com.nearfoodcommunication.main.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<OrderLine> {
    int mResource;
    private Context mContext;
    Database db;

    public OrderAdapter(Context context, int resource, List<OrderLine> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        db = new Database(mContext);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final String foodname = getItem(position).getFoodName();
        final String fooddescription = getItem(position).getFoodDescription();
        final Double foodprice = getItem(position).getFoodPrice();
        final Long foodid = getItem(position).getFoodId();
        final String foodpicture = getItem(position).getFoodPicture();
        final Integer foodquantity = getItem(position).getFoodQuantity();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView ivImgid = convertView.findViewById(R.id.ImageView);
        TextView tvFoodname = convertView.findViewById(R.id.nume);
        TextView tvFoodprice = convertView.findViewById(R.id.pret);
        TextView tvFooddescription = convertView.findViewById(R.id.descriere);
        TextView tvFoodquantity = convertView.findViewById(R.id.tvQuantity);
        Button remove = convertView.findViewById(R.id.remove);


        tvFoodname.setText(foodname);
        tvFooddescription.setText(fooddescription);
        tvFoodprice.setText(foodprice.toString());
        tvFoodquantity.setText(foodquantity.toString() + "×");
        Picasso.with(mContext).load(foodpicture).into(ivImgid);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

                db.removeOrderFromCart(foodid);

                addAll(db.getCarts());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}
