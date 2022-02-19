package com.alexsis.client;

import com.alexsis.client.actions.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/alexsis/client/resources/log_in.fxml"));
        primaryStage.setTitle("Введите данные!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.connectToServer();
        launch(args);
    }
}
