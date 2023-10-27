package com.example.inventorymanagement.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Order {

    private int id;
    private String name;
    private int pid;
    private int price;
    private int quantity;
    private int amount;
    private Date deliveryDate;


    public Order(String cName, int pId, int quantity, Date deliveryDate) {
        this.name = cName;
        this.pid = pId;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
    }
    public Order(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("cName");
        this.pid = resultSet.getInt("pId");
        this.price = resultSet.getInt("price");
        this.quantity = resultSet.getInt("quantity");
        this.deliveryDate = resultSet.getDate("delivery_date");
        this.amount = resultSet.getInt("amount");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
