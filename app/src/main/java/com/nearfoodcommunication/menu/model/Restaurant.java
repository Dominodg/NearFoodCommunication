package com.nearfoodcommunication.menu.model;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable {
    private String propertyName;
    private Long propertyId;
    private String propertyAdress;
    private Integer noTables;
    private List<FoodType> foodCategories;

    public Restaurant() {}

    public Restaurant(String propertyName, Long propertyId, String propertyAdress, Integer noTables, List<FoodType> foodCategories) {
        this.propertyName = propertyName;
        this.propertyId = propertyId;
        this.propertyAdress = propertyAdress;
        this.noTables = noTables;
        this.foodCategories = foodCategories;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyAdress() {
        return propertyAdress;
    }

    public void setPropertyAdress(String propertyAdress) {
        this.propertyAdress = propertyAdress;
    }

    public Integer getNoTables() {
        return noTables;
    }

    public void setNoTables(Integer noTables) {
        this.noTables = noTables;
    }

    public List<FoodType> getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(List<FoodType> foodCategories) {
        this.foodCategories = foodCategories;
    }
}
