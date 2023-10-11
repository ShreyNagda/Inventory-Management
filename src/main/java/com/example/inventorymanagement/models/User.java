package com.example.inventorymanagement.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private final String name;
    private final String username;
    private final String password;
    private final String role;

    public User(String name, String username, String password, String role){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.role = rs.getString("role");
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
