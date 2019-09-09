package com.nearfoodcommunication.menu.adapter;

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
import com.nearfoodcommunication.menu.model.Food;
import com.nearfoodcommunication.order.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodListAdapter extends ArrayAdapter<Food> {
    int mResource;
    private Context mContext;
    Database db;

    public FoodListAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        db = new Database(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        final String foodpicture = getItem(position).getFoodPicture();
        final String foodname = getItem(position).getFoodName();
        final String fooddescription = getItem(position).getFoodDescription();
        final Double foodprice = getItem(position).getFoodPrice();
        final Long foodId = getItem(position).getFoodId();


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView ivImgid = convertView.findViewById(R.id.ImageView);
        TextView tvFoodname = convertView.findViewById(R.id.nume);
        TextView tvFooddescription = convertView.findViewById(R.id.descriere);
        TextView tvFoodprice = convertView.findViewById(R.id.pret);
        Button addtocart = convertView.findViewById(R.id.addtocart);

        Picasso.with(mContext).load(foodpicture).into(ivImgid);
        tvFoodname.setText(foodname);
        tvFooddescription.setText(fooddescription);
        tvFoodprice.setText(foodprice.toString());


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Order existingOrder = db.getOrderByFoodId(foodId);

                if (existingOrder != null) {
                    db.removeOrderFromCart(existingOrder.getFoodId());

                    existingOrder.setFoodQuantity(existingOrder.getFoodQuantity() + 1);

                    db.addToCart(existingOrder);
                } else {

                    Order order = new Order();
                    order.setFoodName(foodname);
                    order.setFoodId(foodId);
                    order.setFoodDescription(fooddescription);
                    order.setFoodPrice(foodprice);
                    order.setFoodPicture(foodpicture);
                    order.setFoodQuantity(1);

                    db.addToCart(order);
                }
            }
        });

        return convertView;
    }
}
