package com.example.inventorymanagement.database;

import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.models.User;
import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public static boolean createUser(User user){
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(StringUtils.createUserQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<User> getUsers(){
        ObservableList<User> temp = FXCollections.observableArrayList();
        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(StringUtils.getUsersQuery);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                User user = new User(rs);
                temp.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    public static void updateUser(User user){
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.updateUserQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.execute();
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
    }

    public static boolean deleteUser(User user){
        boolean res = false;
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement(StringUtils.deleteUserQuery);
            pstm.setString(1, user.getUsername());
            res = pstm.execute();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
