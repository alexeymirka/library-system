package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddNewBookingAdminController {

    @FXML
    private Button addNewBookngButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField tf_book_name;

    @FXML
    private TextField tf_date;

    @FXML
    private TextField tf_person_name;

    @FXML
    private TextField tf_username;

    private String username;
    private String book_name;
    private String person_name;
    private String date;

    @FXML
    void initialize() {
        textFieldInitialize();

        addNewBookngButton.setOnAction(event -> {
            confirmAllInf();
            try {
                ChangeWindow.changeWindow.openWindow(addNewBookngButton, "/com/alexsis/client/resources/admin_window.fxml", "БРОНЬ добавлена!", null, null);
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

        username =  tf_username.getText();
        book_name = tf_book_name.getText();
        person_name = tf_person_name.getText();
        date = tf_date.getText();


        if (!tf_username.equals("") && !tf_book_name.equals("") && !tf_person_name.equals("") && !tf_date.equals("")) {
            Client.interactionsWithServer.addNewBookingInDataBase(tf_username.getText(), tf_book_name.getText(), tf_person_name.getText(), tf_date.getText());
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки !");
        }

    }

    private void textFieldInitialize(){
        tf_username.setText("");
        tf_book_name.setText("");
        tf_person_name.setText("");
        tf_date.setText("");
    }
}
