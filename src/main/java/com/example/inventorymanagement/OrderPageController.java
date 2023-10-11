package com.example.inventorymanagement;

import com.example.inventorymanagement.database.OrderDao;
import com.example.inventorymanagement.models.Order;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class OrderPageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderDao.addOrder(new Order(3, "Harsh", 101, 20, Date.valueOf("2023-11-10")));
        try {
            System.out.println(OrderDao.getOrders());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
