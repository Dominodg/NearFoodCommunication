package com.nearfoodcommunication.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.nearfoodcommunication.order.OrderLine;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    public static final String DB_NAME = "DataBase.db";
    public static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<OrderLine> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name", "Id", "Description", "Price", "Picture", "Quantity"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<OrderLine> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                OrderLine orderLine = extractOrderFromCursor(c);

                result.add(orderLine);
            } while (c.moveToNext());
        }

        return result;
    }

    private OrderLine extractOrderFromCursor(Cursor c) {

        OrderLine orderLine = new OrderLine();
        orderLine.setFoodName(c.getString(c.getColumnIndex("Name")));
        orderLine.setFoodId(c.getLong(c.getColumnIndex("Id")));
        orderLine.setFoodDescription(c.getString(c.getColumnIndex("Description")));
        orderLine.setFoodPrice(c.getDouble(c.getColumnIndex("Price")));
        orderLine.setFoodPicture(c.getString(c.getColumnIndex("Picture")));
        orderLine.setFoodQuantity(c.getInt(c.getColumnIndex("Quantity")));

        return orderLine;
    }

    public void addToCart(OrderLine orderLine) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(Name,Id,Description,Price,Picture,Quantity) VALUES('%s','%s','%s','%s','%s','%s');",
                orderLine.getFoodName(),
                orderLine.getFoodId(),
                orderLine.getFoodDescription(),
                orderLine.getFoodPrice(),
                orderLine.getFoodPicture(),
                orderLine.getFoodQuantity());
        db.execSQL(query);
    }

    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

    public void removeOrderFromCart(Long foodId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail where Id=%d", foodId);
        db.execSQL(query);
    }

    public OrderLine getOrderByFoodId(Long foodId) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Name", "Id", "Description", "Price", "Picture", "Quantity"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        String selection = String.format("Id=%d", foodId);

        Cursor c = qb.query(db, sqlSelect, selection, null, null, null, null);

        OrderLine orderLine = null;

        if (c.moveToFirst()) {
            orderLine = extractOrderFromCursor(c);
        }
        return orderLine;
    }


}
