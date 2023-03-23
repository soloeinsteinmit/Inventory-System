package com.example.inventorymanagmentsystemmain;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
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
                    "ITEMS HAS BEEN UPDATED SUCCESSFULLY", 5, NotificationType.NOTICE);
        });
    }
}
