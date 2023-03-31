package com.example.inventorymanagmentsystemmain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryManagementController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane parentContainer;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField passwordText;
    @FXML
    TextField firstNameText;
    @FXML
    TextField lastNameText;
    @FXML
    private PasswordField passwordHidden;
    @FXML
    private Button createAccountButton;
    @FXML
    private ImageView alert;
    @FXML
    private ImageView alert1;
    @FXML
    private ImageView alert2;
    @FXML
    private ImageView alert3;
    @FXML
    private Circle cover1;
    @FXML
    private Circle cover2;
    @FXML
    private Circle cover3;
    @FXML
    private Circle cover4;
    @FXML
    private TextField emailText;
    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void createUserAccountButton(MouseEvent event) throws IOException{
//        checkEntries();
        ChangingScenes.changeWindow(event, "Dashboard");
    }

    // performing transitions on the fxml files

    public void switchToLoginScreen(ActionEvent event) throws IOException{

        ChangingScenes.translateForward(parentContainer, anchorPane, "InventoryLogin");
    }


    // hiding and showing password
    @FXML
    void changeVisibility(ActionEvent event){
        if (checkBox.isSelected()){
            passwordText.setText(passwordHidden.getText());
            passwordText.setVisible(true);
            passwordHidden.setVisible(false);
            return;
        }
        passwordHidden.setText(passwordText.getText());
        passwordHidden.setVisible(true);
        passwordText.setVisible(false);
    }

    Stage stage;
    public void closePage(MouseEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close window");
        alert.setHeaderText("You are about to close this window");
        alert.setContentText("Are you sure you want to close this window!");

        String title = "Close Window";
        String message = "You are about to close this window";
        TrayNotification tray = new TrayNotification();
        AnimationType  type = AnimationType.POPUP;

        tray.setAnimationType(type);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) parentContainer.getScene().getWindow();
            System.out.println("You closed the window");
            stage.close();
        }
    }

    public void minimizePage(MouseEvent event){
        stage = (Stage) parentContainer.getScene().getWindow();
        stage.setIconified(true);
        // stage.setFullScreen(true);  used for maximizing screen
    }

    private boolean validateEmail(){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(emailText.getText());
        if (matcher.find() && matcher.group().equals(emailText.getText())){
            return true;
        } else {

            String title = "Email Validation Error";
            String message = "Please enter a valid email";
            TrayNotification tray = new TrayNotification();
            AnimationType  type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Validate Email");
//            alert.setHeaderText(null);
//            alert.setContentText("Please enter a valid email");
//            alert.showAndWait();
            return false;
        }
    }

}
