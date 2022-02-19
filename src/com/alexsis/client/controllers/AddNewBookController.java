package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.windowsAlert.AlertWindow;

import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddNewBookController {


    @FXML
    private Button addNewBookButton;

    @FXML
    private TextField b_authorField;

    @FXML
    private TextField b_nameField;

    @FXML
    private TextField b_quantityField;

    @FXML
    private TextField b_yearField;

    @FXML
    private Button backButton;

    private String b_name;
    private String b_author;
    private String b_year;
    private String b_quantity;

    @FXML
    void initialize() {
        textFieldInitialize();

        addNewBookButton.setOnAction(event -> {
            confirmAllInf();
            try {
                ChangeWindow.changeWindow.openWindow(addNewBookButton, "/com/alexsis/client/resources/user_window.fxml", "КНИГА добавлена!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(backButton, "/com/alexsis/client/resources/user_window.fxml", "Не захотели добавлять?", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmAllInf() {

        b_name =  b_nameField.getText();
        b_author = b_authorField.getText();
        b_year = b_yearField.getText();
        b_quantity = b_quantityField.getText();


        if (!b_name.equals("") && !b_author.equals("") && !b_year.equals("") && !b_quantity.equals("")) {
            Client.interactionsWithServer.addNewBookInDataBase(b_nameField.getText(), b_authorField.getText(), b_yearField.getText(), b_quantityField.getText());
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки !");
        }

    }

    private void textFieldInitialize(){
        b_nameField.setText("");
        b_authorField.setText("");
        b_yearField.setText("");
        b_quantityField.setText("");
    }
}

