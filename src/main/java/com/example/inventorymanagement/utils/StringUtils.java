package com.example.inventorymanagement.utils;

public class StringUtils {
    public static final String dbUrl = "jdbc:mysql://localhost:3306";
    public static final String dbUser= "root";
    public static final String dbPassword = "Shrey@0308";
    public static final String dbName = "inventory";
    public static final String title = "Inventory Management";

    //
    public static final String userLoginQuery = "SELECT * FROM users WHERE username=? AND password=?";
    public static final String createProductQuery = "INSERT INTO products VALUES(?, ?, ?, ?);";
    public static final String selectProductQuery = "SELECT * FROM products;";

    public static final String updateProductQuery = "UPDATE products SET name=?,price=?,stock=? WHERE id=?";
    public static final String deleteProductQuery = "DELETE FROM products WHERE id=?;";
}
