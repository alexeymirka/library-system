package com.alexsis.client.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.alexsis.client.actions.Client;

import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;


public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_login;

    @FXML
    private Button button_sign_up;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_show_password;

    @FXML
    private TextField tf_username;

    @FXML
    private CheckBox pass_toggle;


    private String login;
    private String password;
    private String show_password;
    private String role;

    @FXML
    void initialize() {
        textFieldInitialize();
        this.togglevisiblePassword();

        button_login.setOnAction(event -> {
            confirmLoginAndPassword();
        });

        button_sign_up.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(button_sign_up, "/com/alexsis/client/resources/sign_up.fxml",
                        "Зарегистируйся!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmLoginAndPassword() {

        login = tf_username.getText();
        password = tf_password.getText();

        if (!login.equals("") && !password.equals("")) {
            Client.interactionsWithServer.send("авторизация");
            Client.interactionsWithServer.send(login + " " + password);
            Client.interactionsWithServer.chekUserInDatabase(button_login);
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки !");
        }
    }


    private void textFieldInitialize() {
        tf_username.setText("");
        tf_password.setText("");
    }

    @FXML
    public void togglevisiblePassword() {
        if (pass_toggle.isSelected()) {
            tf_show_password.setText(tf_password.getText());
            tf_show_password.setVisible(true);
            tf_password.setVisible(false);
            return;
        }
        tf_password.setText(tf_show_password.getText());
        tf_password.setVisible(true);
        tf_show_password.setVisible(false);
    }
}

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        this.togglevisiblePassword(null);
//
//            button_login.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    ClientActionsWithServer.logInUser(event, tf_username.getText(), tf_password.getText());
//                }
//            });
//
//            button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    changeWindow.changeWindow(event, "../client/resources/sign_up.fxml", "Зарегистируйся!", null, null);
//                }
//            });
//    }

  /*  public static void logInUser(javafx.event.ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "root");
            preparedStatement = connection.prepareStatement("SELECT password, role FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("Пользователь с таким логином не найден!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Неверный ввод");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedPerson = resultSet.getString("role");
                    if (retrievedPassword.equals(password) && (retrievedPerson.equals("Работник") | retrievedPerson.equals("Гость"))) {
                        ChangeWindow.changeWindow(event, "../client/resources/user_window.fxml", "Приветствую!", username, retrievedPerson);
                    } else if (retrievedPassword.equals(password) && retrievedPerson.equals("Админ")) {
                        ChangeWindow.changeWindow(event, "../client/resources/admin_window.fxml", "Приветствую, АДМИН!", username, retrievedPerson);
                    } else {
                        System.out.println("Неверный пароль");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Неправильные данные для входа");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/