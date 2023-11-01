package com.example.inventorymanagement.database;

import com.example.inventorymanagement.utils.ScreenUtils;
import com.example.inventorymanagement.utils.StringUtils;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DbConnection {
    Connection connectionLink;

    public Connection getConnection() {
        try{
            connectionLink = DriverManager.getConnection(StringUtils.dbUrl + "/" + StringUtils.dbName, StringUtils.dbUser, StringUtils.dbPassword);
        }catch (SQLException e){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return connectionLink;
    }

    public void initDatabase(File inputFile) {
        try {
            Connection connection = DriverManager.getConnection(StringUtils.dbUrl, StringUtils.dbUser, StringUtils.dbPassword);
            String delimiter = ";";

            // Create scanner
            Scanner scanner;
            try {
                scanner = new Scanner(inputFile).useDelimiter(delimiter);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                return;
            }

            // Loop through the SQL file statements
            Statement currentStatement = null;
            while(scanner.hasNext()) {
                // Get statement
                String rawStatement = scanner.next() + delimiter;
                System.out.println(rawStatement);
                try {
                    // Execute statement
                    currentStatement = connection.createStatement();
                    currentStatement.execute(rawStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    // Release resources
                    if (currentStatement != null) {
                        try {
                            currentStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentStatement = null;
                }
            }
            scanner.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
