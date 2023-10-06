package com.example.inventorymanagement;

import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Product;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController {
    public TextField idField;
    public TextField nameField;
    public TextField priceField;
    public TextField stockField;
    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addProduct(ActionEvent event){
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int price = Integer.parseInt(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        Product product = new Product(id, name, price, stock);
        boolean res = ProductDao.addProduct(product);
        System.out.println(res);
        closeWindow(event);
    }
}
