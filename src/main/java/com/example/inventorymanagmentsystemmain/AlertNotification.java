package com.example.inventorymanagmentsystemmain;

import javafx.scene.control.Alert;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AlertNotification {
    public static void trayNotification(String title, String message, double duration, NotificationType nt){
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(nt);
        tray.showAndDismiss(Duration.seconds(duration));
    }


    public static void alert(Alert.AlertType alertType, String title, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        /*if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) container.getScene().getWindow();
            System.out.println("You closed the window");
            stage.close();
        }*/
    }
}
