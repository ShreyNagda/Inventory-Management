package com.example.inventorymanagement.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private final int id;
    private String name;
    private int price, stock;

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }




    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.price = rs.getInt("price");
        this.stock = rs.getInt("stock");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
