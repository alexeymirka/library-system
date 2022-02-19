package com.alexsis.client.windowsAlert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


public class ChangeWindow {

    public static ChangeWindow changeWindow = new ChangeWindow();
    public ChangeWindow(){}

    public void openWindow(Button button, String fxmlFile, String title, String username, String role) throws IOException {
        System.out.println(fxmlFile);

//        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
//        Stage stage = new Stage();
//        stage.setTitle("Введите данные!");
//        stage.setScene(new Scene(root));
//        stage.show();

       FXMLLoader loader = new FXMLLoader();
        // FileInputStream fileInputStream = new FileInputStream(new File(fxmlFile));

        loader.setLocation(getClass().getResource(fxmlFile));

        try {
            button.getScene().getWindow().hide();
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();

        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
        System.out.println(fxmlFile);
    }

//        Stage stage = (Stage)button.getScene().getWindow();
//        stage.close();
//
//        Stage primaryStage = new Stage();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource(fxmlFile));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        stage.setTitle(title);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

}