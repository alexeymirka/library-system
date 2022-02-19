package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class AddNewPersonAdminController {

    @FXML
    private Button addNewPersonButton;

    @FXML
    private Button backButton;

    @FXML
    private RadioButton rb_black;

    @FXML
    private RadioButton rb_green;

    @FXML
    private RadioButton rb_red;

    @FXML
    private TextField tf_username;

    @FXML
    private ToggleGroup toggleGroup;

    private String name;
    private String status;

    @FXML
    void initialize() {
        textFieldInitialize();

        addNewPersonButton.setOnAction(event -> {
            confirmAllInf();
            try {
                ChangeWindow.changeWindow.openWindow(addNewPersonButton, "/com/alexsis/client/resources/peopleAdmin.fxml", "ПОСЕТИТЕЛЬ добавлен!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(backButton, "/com/alexsis/client/resources/admin_window.fxml", "Не захотели добавлять?", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmAllInf() {

        ToggleGroup toggleGroup = new ToggleGroup();
        rb_green.setToggleGroup(toggleGroup);
        rb_red.setToggleGroup(toggleGroup);
        rb_black.setToggleGroup(toggleGroup);

        name = tf_username.getText();
        status = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        if (!name.equals("")) {
            Client.interactionsWithServer.send("добавить посетителя");
            Client.interactionsWithServer.send(name + ";" + status);
            Client.interactionsWithServer.addNewPersonInDatabase(addNewPersonButton, name, status);
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки!");
        }

    }

    private void textFieldInitialize(){
        tf_username.setText("");
    }
}
