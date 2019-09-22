package com.nearfoodcommunication.order;

import java.util.List;

public class Order {
    private Integer table;
    private Long idProperty;
    private List<OrderLine> orderLine;

    public Order(Integer table, Long idProperty, List<OrderLine> orderLine) {
        this.table = table;
        this.idProperty = idProperty;
        this.orderLine = orderLine;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Long getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Long idProperty) {
        this.idProperty = idProperty;
    }

    public List<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }
}
