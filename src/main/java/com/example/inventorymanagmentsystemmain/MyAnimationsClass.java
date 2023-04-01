package com.example.inventorymanagmentsystemmain;

import javafx.animation.FadeTransition;
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

    public void loginGuideNotesAnimationNext(Label notes){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(notes);
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            int currentIndex = ItemCategoryData.guideNotes.indexOf(notes.getText());
            notes.setText(ItemCategoryData.guideNotes.get(currentIndex + 1));
            int currentIndex1 = ItemCategoryData.guideNotes.indexOf(notes.getText());
            if (currentIndex1 == ItemCategoryData.guideNotes.size()-1){
                System.out.println("cI n= "+currentIndex1);
                System.out.println("s n= "+ (ItemCategoryData.guideNotes.size() -1));
                notes.setText(ItemCategoryData.guideNotes.get(0));
            }
            FadeTransition fade = new FadeTransition();
            fade.setNode(notes);
            fade.setDuration(Duration.millis(500));
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        });

    }

    public void loginGuideNotesAnimationPrevious(Label notes){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(notes);
        fadeTransition.setDuration(Duration.millis(500));
        fadeTransition.setInterpolator(Interpolator.LINEAR);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            int currentIndex = ItemCategoryData.guideNotes.indexOf(notes.getText());
            if (currentIndex == 0){
                System.out.println("cI n= "+currentIndex);
                System.out.println("s n= "+ (ItemCategoryData.guideNotes.size() -1));
                notes.setText(ItemCategoryData.guideNotes.get(ItemCategoryData.guideNotes.size()-1));
            }
            int currentIndex1 = ItemCategoryData.guideNotes.indexOf(notes.getText());
            notes.setText(ItemCategoryData.guideNotes.get(currentIndex1-1));

            FadeTransition fade = new FadeTransition();
            fade.setNode(notes);
            fade.setDuration(Duration.millis(500));
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play();
        });

    }
}
