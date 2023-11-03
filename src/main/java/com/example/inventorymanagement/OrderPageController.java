package com.example.inventorymanagement;

import com.example.inventorymanagement.database.OrderDao;
import com.example.inventorymanagement.database.ProductDao;
import com.example.inventorymanagement.models.Order;
import com.example.inventorymanagement.models.Product;
import com.example.inventorymanagement.utils.ScreenUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class OrderPageController implements Initializable {


    public TableColumn<Order, Integer> idColumn;
    public TableColumn<Order, String> cNameColumn;
    public TableColumn<Order, Integer> pIdColumn;
    public TableColumn<Order, Integer> quantityColumn;
    public TableColumn<Order, Integer> amountColumn;
    public TableColumn<Order, Date> dateColumn;
    public TableView<Order> ordersTable;
    ObservableList<Order> orders;

    void setTableValues(){
        ordersTable.setEditable(false);
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pIdColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        try {
            orders = OrderDao.getOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ordersTable.setItems(orders);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTableValues();
    }

    public void addOrder(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource() ).getScene().getWindow();
        Stage stage1 = new Stage();
        stage1.setResizable(false);
        stage1.initStyle(stage.getStyle());
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("add_order.fxml")).load());
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.showAndWait();
        setTableValues();
    }
    public void deleteProduct(){
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        if(order == null){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", "Please select an order");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete order number " + order.getId() + "?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            boolean res = OrderDao.deleteOrder(order);
            if(res) ScreenUtils.showAlertDialog(Alert.AlertType.INFORMATION, "Successful!",order.getId() + " deleted successfully!");
            setTableValues();
        }
    }
}
