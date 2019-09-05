package com.nearfoodcommunication.menu.model;

import java.io.Serializable;

public class Food implements Serializable {

    private Long foodId;
    private String foodName;
    private Double foodPrice;
    private String foodDescription;
    private Double foodWeight;
    private String foodPicture;

    public Food(Long foodId, String foodName,Double foodPrice , String foodDescription,Double foodWeight, String foodPicture) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodDescription = foodDescription;
        this.foodWeight = foodWeight;
        this.foodPicture = foodPicture;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }

    public Double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(Double foodWeight) {
        this.foodWeight = foodWeight;
    }
}
