package com.example.inventorymanagmentsystemmain;

import com.jfoenix.controls.JFXButton;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    private MFXButton previousGuideBtn;

    @FXML
    private Label guideLabel;

    @FXML
    private MFXButton nextGuideBtn;

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
    MyAnimationsClass myAnimationsClass = new MyAnimationsClass();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TooltipClass.tooltipMessage("Next Guide", nextGuideBtn);
        TooltipClass.tooltipMessage("Previous Guide", previousGuideBtn);


        //next guide
        nextGuideBtn.setOnMouseClicked(event -> {
            myAnimationsClass.loginGuideNotesAnimationNext(guideLabel);
        });
        //previous guide
        previousGuideBtn.setOnMouseClicked(event -> {
            myAnimationsClass.loginGuideNotesAnimationPrevious(guideLabel);
        });
    }

    @FXML
    public void loginButton(MouseEvent event) throws IOException, SQLException {

        if (idField.getText().equals("") && passwordField.getText().equals("")){
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN ALL THE FORMS", 4, NotificationType.ERROR);
        }  else {

            if (DataAccess.login(idField.getText(), passwordField.getText())){
                DataAccess.getLoggedInUserId = idField.getText();


                if (DataAccess.getUserProfile()){
                    if (DataAccess.myProfileData.get(2).equals("Admin")){
                        ChangingScenes.changeWindow(event, "Dashboard");
//                        ChangingScenes.toHome(event, "Dashboard", mfxSpinner);
                    }else{
//                        ChangingScenes.changeWindow(event, "DashboardNonAdmin");
                        ChangingScenes.toHome(event, "DashboardNonAdmin", mfxSpinner);
                    }
                    idField.clear();
                    passwordField.clear();
                    AlertNotification.trayNotification("SUCCESS", "YOU HAVE SUCCESSFULLY LOGGED IN", 3.0, NotificationType.SUCCESS);
                }


            }else {
                AlertNotification.trayNotification("ERROR", "INVALID USER CREDENTIAL", 4, NotificationType.ERROR);
            }
        }

    }
}
