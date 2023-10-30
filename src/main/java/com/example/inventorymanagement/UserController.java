package com.example.inventorymanagement;

import com.example.inventorymanagement.database.UserDao;
import com.example.inventorymanagement.models.User;
import com.example.inventorymanagement.utils.ScreenUtils;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public TableView<User> usersTable;
    public TableColumn<User, String> nameColumn;
    public TableColumn<User, String> usernameColumn;
    public TableColumn<User, String> passwordColumn;
    public TableColumn<User, String> roleColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInitialTable();
    }

    void setInitialTable(){
        usersTable.setEditable(App.user.getRole().equalsIgnoreCase("admin"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        setTableValues();
    }

    public void addUserClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource() ).getScene().getWindow();
        Stage stage1 = new Stage();
        stage1.setResizable(false);
        stage1.initStyle(stage.getStyle());
        Scene scene = new Scene(new FXMLLoader(getClass().getResource("add_user.fxml")).load());
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.setScene(scene);
        stage1.showAndWait();
        setTableValues();
    }

    void setTableValues(){
        usersTable.setItems(UserDao.getUsers());
    }

    public void editName(TableColumn.CellEditEvent<User, String> event){
        String newName = event.getNewValue();
        User user = usersTable.getSelectionModel().getSelectedItem();
        user.setName(newName);
        UserDao.updateUser(user);
    }

    public void editPassword(TableColumn.CellEditEvent<User, String> event){
        String newPass = event.getNewValue();
        User user = usersTable.getSelectionModel().getSelectedItem();
        user.setPassword(newPass);
        UserDao.updateUser(user);
    }
    public void editRole(TableColumn.CellEditEvent<User, String> event){
        String newRole = event.getNewValue();
        User user = usersTable.getSelectionModel().getSelectedItem();
        user.setRole(newRole);
        UserDao.updateUser(user);
    }

    public void deleteUser(ActionEvent event){
        User user = usersTable.getSelectionModel().getSelectedItem();
        if(user == null) return;
        boolean res = UserDao.deleteUser(user);
        if(res) ScreenUtils.showAlertDialog(Alert.AlertType.INFORMATION, "Successful!", user.getName() + " deleted successful");
        setTableValues();
    }
}
