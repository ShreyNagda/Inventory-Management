package com.example.inventorymanagement.database;

import com.example.inventorymanagement.utils.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection connectionLink;

    public Connection getConnection() {
        try{
            System.out.println("URL -> " + StringUtils.dbUrl+"/"+StringUtils.dbName);
            connectionLink = DriverManager.getConnection(StringUtils.dbUrl+"/"+StringUtils.dbName, StringUtils.dbUser, StringUtils.dbPassword);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connectionLink;
    }
}
