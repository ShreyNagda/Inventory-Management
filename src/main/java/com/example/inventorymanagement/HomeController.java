package com.example.inventorymanagement;

import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.inventorymanagement.App.user;

public class HomeController implements Initializable {
    public Label usernameLabel;
    public AnchorPane home, productPane;
    public Button homeBtn;
    public Button productBtn;
    public Button orderBtn;
    public AnchorPane ordersPane;
    @FXML
    private TableView<Product> productTable;
    public TableColumn<Product, Integer> productId, productPrice, productStock;
    public TableColumn<Product, String> productName;

    public ArrayList<Product> products;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            products = ProductDao.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        home.setVisible(true);
        productPane.setVisible(false);
        ordersPane.setVisible(false);
        usernameLabel.setText("Hello, " + user.getName());
        productTable.setEditable(true);
        setTableValues();

    }

    void setTableValues() {
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

//        productId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productStock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productName.setCellFactory(TextFieldTableCell.forTableColumn());
        for (Product p:products) {
            productTable.getItems().add(p);
        }

    }
    public void homeClick() {
        home.setVisible(true);
        productPane.setVisible(false);
        ordersPane.setVisible(false);
    }
    public void productClick() {
        home.setVisible(false);
        productPane.setVisible(true);
        ordersPane.setVisible(false);
    }
    public void orderClick(){
        home.setVisible(false);
        productPane.setVisible(false);
        ordersPane.setVisible(true);
    }
    public void editName(TableColumn.CellEditEvent<Product, String> event){
        String newName = event.getNewValue();
        Product product = productTable.getSelectionModel().getSelectedItem();
        product.setName(newName);
        ProductDao.updateProduct(product);
    }
    public void editPrice(TableColumn.CellEditEvent<Product, Integer> event){
        int price = event.getNewValue();
        Product product = productTable.getSelectionModel().getSelectedItem();
        product.setPrice(price);
        ProductDao.updateProduct(product);
    }
    public void editStock(TableColumn.CellEditEvent<Product, Integer> event){
        int stock = event.getNewValue();
        Product product = productTable.getSelectionModel().getSelectedItem();
        product.setStock(stock);
        ProductDao.updateProduct(product);
    }
    public void deleteSelectedProduct(){
        Product product = productTable.getSelectionModel().getSelectedItem();

    }
}
