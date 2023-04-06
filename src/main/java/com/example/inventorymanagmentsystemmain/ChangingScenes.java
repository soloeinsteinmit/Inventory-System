package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;

import java.io.IOException;
import java.util.Objects;

/**
 * @author .py_ML_ai_MIT (Solomon Eshun)
 * <p>This is a class for the changing of scenes in the application</p>
 **/
public class ChangingScenes {

    /**
     * Performs a forward animation transition when moving from one scene to another in the application
     * @param filename name of fxml to be changed to
     * */
    public static void translateForward(StackPane parentContainer, AnchorPane container, String filename){
        Parent fxmlName = null;
        try {
            fxmlName = FXMLLoader.load(
                    Objects.requireNonNull(ChangingScenes.class.getResource(filename + ".fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parentContainer.getChildren().removeAll();
        parentContainer.getChildren().setAll(fxmlName);

        fxmlName.translateXProperty().set(container.getWidth());
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(fxmlName.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(container));
        timeline.play();
    }


    /**
     * Performs a forward animation transition when moving from one scene to another in the application
     * @param filename name of fxml to be changed to
     * */
    public static void translateBackward(StackPane parentContainer, AnchorPane container, String filename){
        Parent fxmlName = null;
        try {
            fxmlName = FXMLLoader.load(
                    Objects.requireNonNull(ChangingScenes.class.getResource(filename + ".fxml")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parentContainer.getChildren().removeAll();
        parentContainer.getChildren().setAll(fxmlName);

        fxmlName.translateXProperty().set(-container.getWidth());
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(fxmlName.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(container));
        timeline.play();
    }


    /**
     * @param event takes mouseEvent that should happen for the changing for a particular scene
     * @param fxmlFileName name of fxml to be changed to.
     * */
    public static void changeWindow(MouseEvent event, String fxmlFileName) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ChangingScenes.class.getResource(fxmlFileName + ".fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    /**
     * @code toHome method uses concurrency to load the next <i>fxmlFileName</i> while
     * <p>retrieving data from the database to display content in the application</p>
     * @param event takes mouseEvent that should happen for the changing for a particular scene
     * @param fxmlFileName name of fxml to be changed to
     */
    public static void toHome(MouseEvent event, String fxmlFileName, MFXProgressSpinner spinner) throws IOException {
        Task<Parent> fxmlTask = new Task<Parent>() {
            @Override
            protected Parent call() throws Exception {
                spinner.setVisible(true);
                return FXMLLoader.load(
                        Objects.requireNonNull(getClass().getResource(fxmlFileName + ".fxml")));
            }
        };
        fxmlTask.setOnSucceeded(e ->{
            spinner.setVisible(false);
            Parent root = fxmlTask.getValue();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            /*stage.setOnCloseRequest(windowEvent -> {
                stage.close();
            });*/
        });
        fxmlTask.setOnFailed(e->{
            spinner.setVisible(false);
            AlertNotification.trayNotification("ERROR", "INTERNAL ERROR",
                    4, NotificationType.ERROR);
        });
        Thread thread = new Thread(fxmlTask);
        thread.start();

    }

}
