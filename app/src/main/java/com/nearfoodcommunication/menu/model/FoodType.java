package com.nearfoodcommunication.menu.model;

import java.io.Serializable;
import java.util.List;

public class FoodType implements Serializable {
    private String foodPicture;
    private String foodName;
    private long foodId;
    private List<Food> foodItems;


    public FoodType(String foodPicture, String foodName, long foodId, List<Food> foodItems) {
        this.foodPicture = foodPicture;
        this.foodName = foodName;
        this.foodId = foodId;
        this.foodItems = foodItems;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public List<Food> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<Food> foodItems) {
        this.foodItems = foodItems;
    }
}
