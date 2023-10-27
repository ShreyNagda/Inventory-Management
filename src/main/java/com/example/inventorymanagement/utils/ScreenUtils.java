package com.example.inventorymanagement.utils;

import javafx.scene.control.Alert;

public class ScreenUtils {
    public static final int height = 400;
    public static final int width = 600;

    public static  final String primaryColor = "50c878";

    public static void showAlertDialog(Alert.AlertType type, String title, String message){
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
    }
}
