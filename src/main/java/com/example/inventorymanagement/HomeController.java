package com.example.inventorymanagement;

import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.ScreenUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.inventorymanagement.App.user;

public class HomeController implements Initializable {
    public Label nameLabel;
    public BorderPane bp;
    public AnchorPane ap;
    public TableColumn<Product, Integer> idColumn;
    public TableColumn<Product, Integer> stockColumn;
    public TableColumn<Product, String> nameColumn;
    public TableView<Product> leastStockedTable;
    ObservableList<Product> leastStockedProducts;

    public TableColumn<Product, Integer> idColumn2;
    public TableColumn<Product, Integer> stockColumn2;
    public TableColumn<Product, String> nameColumn2;
    public TableView<Product> mostStockedTable;
    ObservableList<Product> mostStockedProducts;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText("Hello, " + user.getName());
        getLeastStockedProducts();
        setInitialTableValues();
    }
    void getLeastStockedProducts(){

    }
    void setInitialTableValues() {
        leastStockedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

        mostStockedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        idColumn2.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        stockColumn2.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        nameColumn2.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        setTableValues();
    }
    void setTableValues() {
        leastStockedProducts = ProductDao.getLeastStockedProducts();
        mostStockedProducts = ProductDao.getMaxStockedProducts();
        leastStockedTable.setItems(leastStockedProducts);
        mostStockedTable.setItems(mostStockedProducts);
    }
    public void homeClick(){
        bp.setCenter(ap);
        setTableValues();
    }
    public void productClick(){
        loadPage("product");
    }
    public void orderClick(){
        loadPage("order");
    }
    public void logoutClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout");
        Optional <ButtonType> res =  alert.showAndWait();
        ButtonType button = res.orElse(ButtonType.CANCEL);
        if(button == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), ScreenUtils.width, ScreenUtils.height);
            Stage root = (Stage)((Node) event.getSource()).getScene().getWindow();
            root.setScene(scene);
        }else{
            alert.close();
        }

    }

    void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(root);
    }
}
