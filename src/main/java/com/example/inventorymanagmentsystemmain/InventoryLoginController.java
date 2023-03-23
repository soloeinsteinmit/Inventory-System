package com.example.inventorymanagmentsystemmain;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InventoryLoginController implements Initializable {

    @FXML
    private JFXButton button;

    @FXML
    private AnchorPane container;

    @FXML
    private Label forgotPassword;

    @FXML
    private TextField idField;

    @FXML
    private MFXButton loginButton;

    @FXML
    private StackPane parent_container;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private Label promptMessage;

    @FXML
    private MFXProgressSpinner mfxSpinner;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void loginButton(MouseEvent event) throws IOException, SQLException {

        if (idField.getText().equals("") && passwordField.getText().equals("")){
//            promptMessage.setText("PLEASE FILL IN THE FORMS");
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN ALL THE FORMS", 4, NotificationType.ERROR);
        }  else {

            if (DataAccess.login(idField.getText(), passwordField.getText())){
                DataAccess.getLoggedInUserId = idField.getText();
                AlertNotification.trayNotification("SUCCESS", "YOU HAVE SUCCESSFUL LOGGED IN", 3.0, NotificationType.SUCCESS);

                if (DataAccess.getUserProfile()){
                    if (DataAccess.myProfileData.get(2).equals("Admin")){
//                        ChangingScenes.changeWindow(event, "Dashboard");
                        ChangingScenes.toHome(event, "Dashboard", mfxSpinner);
                    }else{
//                        ChangingScenes.changeWindow(event, "DashboardNonAdmin");
                        ChangingScenes.toHome(event, "DashboardNonAdmin", mfxSpinner);
                    }

                }


            }else {
                AlertNotification.trayNotification("ERROR", "INVALID USER CREDENTIAL", 4, NotificationType.ERROR);
            }
        }

    }

    // hiding and showing password


    Stage stage;
    public void closePage(MouseEvent event){

        AlertNotification.alert(Alert.AlertType.CONFIRMATION, "CLOSE WINDOW",
                "ARE YOU ABOUT TO CLOSE THIS WINDOW",
                "ARE YOU SURE YOU WANT TO CLOSE THIS WINDOW?");

    }



    public void minimizePage(MouseEvent event){
        stage = (Stage) container.getScene().getWindow();
        stage.setIconified(true);
        // stage.setFullScreen(true);  used for maximizing screen
    }


}
