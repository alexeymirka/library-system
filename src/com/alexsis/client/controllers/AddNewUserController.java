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

public class AddNewUserController {

    @FXML
    private Button addNewUserButton;

    @FXML
    private Button backButton_1;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private RadioButton radb_admin;

    @FXML
    private RadioButton radb_guest;

    @FXML
    private RadioButton radb_worker;

    @FXML
    private TextField usernameField;

    private String username;
    private String email;
    private String password;
    private String role;

    @FXML
    void initialize() {
        textFieldInitialize();

        addNewUserButton.setOnAction(event -> {
            confirmAllInf();
            try {
                ChangeWindow.changeWindow.openWindow(addNewUserButton, "/com/alexsis/client/resources/admin_window.fxml", "Вы добавили пользователя!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton_1.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(backButton_1, "/com/alexsis/client/resources/admin_window.fxml", "Не захотели добавлять?", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmAllInf() {

        ToggleGroup toggleGroup = new ToggleGroup();
        radb_admin.setToggleGroup(toggleGroup);
        radb_worker.setToggleGroup(toggleGroup);
        radb_guest.setToggleGroup(toggleGroup);

        username = usernameField.getText();
        email = emailField.getText();
        password = passwordField.getText();
        //role = roleField.getText();
        role = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        if (!username.equals("") && !email.equals("") && !password.equals("")) {
                Client.interactionsWithServer.send("добавить пользователя");
                Client.interactionsWithServer.send(username + " " + email + " " + password + " " + role);
                Client.interactionsWithServer.addNewUserInDatabase(addNewUserButton, username, email, password, role);
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки!");
        }

    }

    private void textFieldInitialize(){
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        //roleField.setText("");
    }

}