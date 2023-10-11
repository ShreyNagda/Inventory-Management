package com.example.inventorymanagement.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Order {

    private int id;
    private String cName;
    private int pId;
    private int quantity;
    private Date deliveryDate;

    public Order(int id, String cName, int pId, int quantity, Date deliveryDate) {
        this.id = id;
        this.cName = cName;
        this.pId = pId;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
    }
    public Order(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.cName = resultSet.getString("cName");
        this.pId = resultSet.getInt("pId");
        this.quantity = resultSet.getInt("quantity");
        this.deliveryDate = resultSet.getDate("delivery_date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
