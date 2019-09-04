package com.nearfoodcommunication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;


import com.nearfoodcommunication.order.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    public static final  String DB_NAME="DataBase.db";
    public static final int DB_VER=1;
    public Database(Context context){
        super (context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Name", "Id", "Description", "Price", "Picture", "Quantity"};
        String sqlTable="OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null,null,null,null, null);


        List<Order> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                Order order = new Order();
                order.setFoodName(c.getString(c.getColumnIndex("Name")));
                order.setFoodId(c.getLong(c.getColumnIndex("Id")));
                order.setFoodDescription(c.getString(c.getColumnIndex("Description")));
                order.setFoodPrice(c.getDouble(c.getColumnIndex("Price")));
                order.setFoodPicture(c.getString(c.getColumnIndex("Picture")));
                order.setFoodQuantity(c.getInt(c.getColumnIndex("Quantity")));

                result.add(order);
            }while(c.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(Name,Id,Description,Price,Picture,Quantity) VALUES('%s','%s','%s','%s','%s','%s');",
                order.getFoodName(),
                order.getFoodId(),
                order.getFoodDescription(),
                order.getFoodPrice(),
                order.getFoodPicture(),
                order.getFoodQuantity());
        db.execSQL(query);
    }

    public void cleanCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

    public void removeCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail where Id=%d", order.getFoodId());
        db.execSQL(query);
    }

    public boolean checkExistenceCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Id"};
        String sqlTable="OrderDetail";

        qb.setTables(sqlTable);
        String selection = String.format("Id=%d", order.getFoodId());

        Cursor c = qb.query(db, sqlSelect, selection,null,null,null, null);

        return c.moveToFirst();
    }



}
