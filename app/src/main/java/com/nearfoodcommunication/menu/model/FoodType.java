package com.nearfoodcommunication.menu.model;

import java.io.Serializable;
import java.util.List;

public class FoodType implements Serializable {
    private String foodPicture;
    private String foodName;
    private long foodId;
    private Integer idProperty;
    private List<Food> foodItems;


    public FoodType(long foodId, String foodName, String foodPicture, Integer idProperty, List<Food> foodItems) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPicture = foodPicture;
        this.idProperty = idProperty;
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

    public Integer getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Integer idProperty) {
        this.idProperty = idProperty;
    }
}
