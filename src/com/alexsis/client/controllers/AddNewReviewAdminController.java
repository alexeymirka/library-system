package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddNewReviewAdminController {


    @FXML
    private Button addNewReviewButton;

    @FXML
    private TextField b_idField;

    @FXML
    private Button backButton;

    @FXML
    private TextField review_textField;

    @FXML
    private TextField user_idField;

    private String book_name;
    private String username;
    private String review_text;

    @FXML
    void initialize() {
        textFieldInitialize();

        addNewReviewButton.setOnAction(event -> {
            confirmAllInf();
            try {
                ChangeWindow.changeWindow.openWindow(addNewReviewButton, "/com/alexsis/client/resources/admin_window.fxml", "ОТЗЫВ добавлена!", null, null);
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

        username = user_idField.getText();
        book_name =  b_idField.getText();
        review_text = review_textField.getText();


        if (!book_name.equals("") && !username.equals("") && !review_text.equals("")) {
            Client.interactionsWithServer.addNewReviewInDataBase(user_idField.getText(), b_idField.getText(), review_textField.getText());
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки !");
        }

    }

    private void textFieldInitialize(){
        user_idField.setText("");
        b_idField.setText("");
        review_textField.setText("");
    }
}
