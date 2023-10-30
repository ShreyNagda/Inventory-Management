package com.example.inventorymanagement;

import com.example.inventorymanagement.database.UserDao;
import com.example.inventorymanagement.models.User;
import com.example.inventorymanagement.utils.ScreenUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    public ComboBox<String> userroleCBox;
    public PasswordField passwordField;
    public TextField usernameField;
    public TextField nameField;
    public PasswordField passwordField2;
    public Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("Admin");
        temp.add("User");
        userroleCBox.setItems(temp);
    }

    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addUser(ActionEvent event){
        if(userroleCBox.getValue() == null){
            errorLabel.setText(userroleCBox.getPromptText());
        }
        if(checkFields(nameField) && checkFields(usernameField) && checkFields(passwordField) && checkFields(passwordField2) && confirmPassword() && userroleCBox.getValue() != null){
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = userroleCBox.getValue();

            User user = new User(name, username, password, role);
            boolean res = UserDao.createUser(user);
            if(res){
                ScreenUtils.showAlertDialog(Alert.AlertType.INFORMATION, "", "User created successfully!");
                closeWindow(event);
            }
        }
    }

    boolean confirmPassword(){
        errorLabel.setText("");
        if(!passwordField.getText().equals(passwordField2.getText())){
            errorLabel.setText("Passwords do not match");
            return false;
        }
        return true;
    }

    boolean checkFields(TextField field){
        errorLabel.setText("");
        if(field.getText().isEmpty() || field.getText() == null){
            errorLabel.setText(field.getPromptText());
            return false;
        }
        return true;
    }
}
