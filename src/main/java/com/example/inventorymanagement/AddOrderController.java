package com.example.inventorymanagement;

import com.example.inventorymanagement.database.OrderDao;
import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Order;
import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddOrderController implements Initializable {
    public ComboBox<HashMap<Integer, String>> cBoxProducts;

    ObservableList<Product> productList;
    ObservableList<HashMap<Integer, String>> cBoxProductList = FXCollections.observableArrayList();
    public TextField quantityField;
    public TextField cNameField;

    public DatePicker datePicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            productList = ProductDao.getProducts();
            for (Product p: productList) {
                HashMap<Integer, String> temp = new HashMap<>();
                temp.put(p.getId(), p.getName());
                cBoxProductList.add(temp);
            }
            cBoxProducts.setItems(cBoxProductList);
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
    }

    int getProductId(){
        if(cBoxProducts.getValue() == null){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", "Select a product!");
            return -1;
        }else return cBoxProducts.getValue().keySet().stream().toList().get(0);
    }

    boolean validateInputs(TextField field){
        if(field.getText().isEmpty() || field.getText() == null){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "",field.getPromptText());
            return false;
        }
        return true;
    }

    public void addOrder(ActionEvent event){
        int pId = getProductId();
        String cName = "";
        int quantity = 0;
        Date date = null;
        if(validateInputs(cNameField) && validateInputs(quantityField) && datePicker.getValue() != null){
            date = Date.valueOf(datePicker.getValue().toString());
            cName = cNameField.getText();
            quantity = Integer.parseInt(quantityField.getText());
            Order order = new Order(cName, pId, quantity, date);
            OrderDao.addOrder(order);
            closeWindow(event);
        }


    }
    

    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
