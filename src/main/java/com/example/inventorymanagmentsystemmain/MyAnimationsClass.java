package com.example.inventorymanagmentsystemmain;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import tray.notification.NotificationType;

/**
 * @author .py_ML_ai_MIT
 * this is a custom animation class
 * */
public class MyAnimationsClass {
    public static void rotateRotateRefreshImages(ImageView image){
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(image);
        rotateTransition.setDuration(Duration.seconds(3));
//        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(360);
        rotateTransition.play();

        rotateTransition.setOnFinished(event -> {
            rotateTransition.stop();
            AlertNotification.trayNotification("REFRESH",
                    "ITEMS HAS BEEN UPDATED SUCCESSFULLY", 4, NotificationType.NOTICE);
        });
    }

    public static void refreshIssueGoods(ImageView image, String message, Label p){
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(image);
        rotateTransition.setDuration(Duration.seconds(4));
//        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(360);
        rotateTransition.play();

        rotateTransition.setOnFinished(event -> {
            rotateTransition.stop();
            AlertNotification.trayNotification("REFRESH",
                    message, 4, NotificationType.NOTICE);
            p.setVisible(false);
            image.setVisible(false);

        });
    }

    public static void viewBillsAnimation(ImageView image, String message, Label p, StackPane s, AnchorPane a, String file){
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(image);
        rotateTransition.setDuration(Duration.seconds(4));
//        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(360);
        rotateTransition.play();

        rotateTransition.setOnFinished(event -> {
            rotateTransition.stop();
            AlertNotification.trayNotification("REFRESH",
                    message, 4, NotificationType.NOTICE);

            ChangingScenes.translateForward(s, a, file);
            p.setVisible(false);
            image.setVisible(false);

        });
    }
}
