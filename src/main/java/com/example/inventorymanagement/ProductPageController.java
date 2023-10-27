package com.example.inventorymanagement;

import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.ScreenUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {
    public TableView<Product> productsTable;
    public TableColumn<Product, Integer> idColumn;
    public TableColumn<Product, String> nameColumn;
    public TableColumn<Product, Integer> priceColumn;
    public TableColumn<Product, Integer> stockColumn;

    public ObservableList<Product> products;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableValues();
    }

    void setTableValues(){
        productsTable.setEditable(App.user.getRole().equals("admin"));
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        try {
            products = ProductDao.getProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        productsTable.setItems(products);
    }

    public void editName(TableColumn.CellEditEvent<Product, String> event){
        String newName = event.getNewValue();
        Product product = productsTable.getSelectionModel().getSelectedItem();
        product.setName(newName);
        ProductDao.updateProduct(product);
    }
    public void editPrice(TableColumn.CellEditEvent<Product, Integer> event){
        int price = event.getNewValue();
        Product product = productsTable.getSelectionModel().getSelectedItem();
        product.setPrice(price);
        ProductDao.updateProduct(product);
    }
    public void editStock(TableColumn.CellEditEvent<Product, Integer> event){
        int stock = event.getNewValue();
        Product product = productsTable.getSelectionModel().getSelectedItem();
        product.setStock(stock);
        ProductDao.updateProduct(product);
    }

    public void addProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource() ).getScene().getWindow();
        Stage stage1 = new Stage();
        stage1.setResizable(false);
        stage1.initStyle(stage.getStyle());
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("add_product.fxml")).load());
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.showAndWait();
        setTableValues();
    }
    public void deleteProduct(){
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if(product == null) return;
        boolean res = ProductDao.deleteProduct(product);
        if(res) ScreenUtils.showAlertDialog(Alert.AlertType.INFORMATION, "Successful!",product.getName() + " deleted successful");
        setTableValues();
    }
}
