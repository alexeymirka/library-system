package com.alexsis.client.windowsAlert;

import javafx.scene.control.Alert;

public class AlertWindow {
    public static AlertWindow alertWindow = new AlertWindow();

    public void alertWindow(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private AlertWindow() {
    }
}

