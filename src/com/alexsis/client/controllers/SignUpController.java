package com.alexsis.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.awt.event.KeyEvent;
import com.alexsis.client.actions.Client;
import com.alexsis.client.windowsAlert.AlertWindow;


import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_log_in;

    @FXML
    private Button button_signup;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_password_again;

    @FXML
    private RadioButton rb_guest;

    @FXML
    private RadioButton rb_worker;

    @FXML
    private CheckBox pass_toggle;

    @FXML
    private TextField tf_show_password;

    private String login;
    private String password;
    private String password_again;
    private String email;
    private String toggleName;

    @FXML
    void initialize() {
        textFieldInitialize();

        this.togglevisiblePassword();

        button_signup.setOnAction(event -> {
            confirmNewUser();
        });


        button_log_in.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(button_log_in, "/com/alexsis/client/resources/log_in.fxml", "Войди!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void confirmNewUser() {

        ToggleGroup toggleGroup = new ToggleGroup();
        rb_guest.setToggleGroup(toggleGroup);
        rb_worker.setToggleGroup(toggleGroup);

        login = tf_username.getText();
        email = tf_email.getText();
        password = tf_password.getText();
        password_again = tf_password_again.getText();
        toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

        if (!login.equals("") && !email.equals("") && !password.equals("")) {
            if (password_again.equals(password)) {
                Client.interactionsWithServer.send("добавить пользователя");
                Client.interactionsWithServer.send(login + " " + email + " " + password + " " + toggleName);
                Client.interactionsWithServer.addUserInDatabase(button_signup, login, email, password, toggleName);
            } else {
                AlertWindow.alertWindow.alertWindow("Вы ввели два разных пароля, попробуйте еще раз!");
            }
        } else {
            AlertWindow.alertWindow.alertWindow("Заполните все ячейки!");
        }

    }

    private void textFieldInitialize(){
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

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup toggleGroup = new ToggleGroup();
        rb_guest.setToggleGroup(toggleGroup);
        rb_worker.setToggleGroup(toggleGroup);

        rb_worker.setSelected(true);

        this.togglevisiblePassword(null);

        tf_password.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        tf_password_again.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        tf_username.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty() && !tf_password_again.getText().trim().isEmpty()) {
                    ClientActionsWithServer.signUpUser(event,tf_username.getText(), tf_password.getText(), toggleName, tf_email.getText(), tf_password_again.getText());
                } else {
                    System.out.println("Пожалуйста заполните все поля");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Пожалуйста заполните всю информацию в регистрационном окне");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ChangeWindow.changeWindow(event, "../client/resources/log_in.fxml", "Войди!", null, null);
            }
        });
    }

    public static void signUpUser(ActionEvent event, String username, String password, String role, String email, String password_again){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root", "root");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Пользователь с таким логином уже есть!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Вы не можете использовать данный логин");
                alert.show();
            } else if (!password.equals(password_again)) {
                System.out.println("Вы ввели два разных пароля!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Вы ввели два разных пароля, попробуйте еще раз!");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO  users (username, password, role, email) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, role);
                psInsert.setString(4, email);
                psInsert.executeUpdate();

                ChangeWindow.changeWindow(event, "../client/resources/user_window.fxml", "Привет!", username, role);
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
            if(psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
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
    }

    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField tf_password = (TextField) e.getSource();
                TextField tf_password_again = (TextField) e.getSource();
                if (tf_password.getText().length() >= max_Lengh && tf_password_again.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[0-9.]")){
                    if((tf_password.getText().contains(".") && tf_password_again.getText().contains(".")) && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if((tf_password.getText().length() == 0 && tf_password_again.getText().length() == 0) && e.getCharacter().matches("[.]")){
                        e.consume();
                    }
                }else{
                    e.consume();
                }
            }
        };
    }

    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField tf_username = (TextField) e.getSource();
                if (tf_username.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if(e.getCharacter().matches("[A-Za-z]")){
                }else{
                    e.consume();
                }
            }
        };
    }*/
}
