package com.example.inventorymanagement.database;

import com.example.inventorymanagement.models.User;
import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User loginUser(String username, String password){
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.userLoginQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new User(rs);
            }
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return null;
    }
}
