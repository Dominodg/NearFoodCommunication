package com.nearfoodcommunication.order;

import java.util.List;

public class Order {
    private Integer tableNumber;
    private Long propertyId;
    private List<OrderLine> orderLine;

    public Order(Integer tableNumber, Long propertyId, List<OrderLine> orderLine) {
        this.tableNumber = tableNumber;
        this.propertyId = propertyId;
        this.orderLine = orderLine;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }
}
