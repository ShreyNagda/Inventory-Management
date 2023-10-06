package com.example.inventorymanagement.database;

import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection connectionLink;

    public Connection getConnection() {
        try{
            connectionLink = DriverManager.getConnection(StringUtils.dbUrl+"/"+StringUtils.dbName, StringUtils.dbUser, StringUtils.dbPassword);
        }catch (SQLException e){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return connectionLink;
    }
}
