package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MyProfileController implements Initializable {

    @FXML
    private MFXButton changePasswordBtn;

    @FXML
    private MFXPasswordField newPasswordTextField;

    @FXML
    private Label messageLabel;

    @FXML
    private MFXButton logoutBtn;

    @FXML
    private MFXPasswordField previousPasswordTextField;

    @FXML
    private MFXPasswordField reEnterPasswordTextField;

    @FXML
    private Label vendorIdLabel;
    @FXML
    private Label vendorGenderLabel;

    @FXML
    private Label vendorNameLabel;

    @FXML
    private Label vendorStatusLabel;

    @FXML
    private Label dateRegisteredLabel;

    @FXML
    private Label telephoneNoLabel;

    public static MFXFilterComboBox<String> goodsCategoryComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("here "+ DataAccess.getLoggedInUserId);
        //gets user profile from db
        try {
            if (DataAccess.getUserProfile()){
                vendorNameLabel.setText(DataAccess.myProfileData.get(0));
                vendorIdLabel.setText(DataAccess.myProfileData.get(1));
                vendorStatusLabel.setText(DataAccess.myProfileData.get(2));
                vendorGenderLabel.setText(DataAccess.myProfileData.get(3));

                LocalDate date = LocalDate.parse(DataAccess.myProfileData.get(4));
                String dateRegistered = date.format(DateTimeFormatter.ofPattern("EEE, MMMM d uuuu"));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime currentDateTime = LocalDateTime.now();
                String todaysDateTime = dtf.format(currentDateTime);
                System.out.println(dtf.format(currentDateTime));


                dateRegisteredLabel.setText(dateRegistered);
                telephoneNoLabel.setText(DataAccess.myProfileData.get(5));
                messageLabel.setText("WELCOME " + DataAccess.myProfileData.get(0).toUpperCase());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //set tooltip
        TooltipClass.tooltipMessage("Logout", logoutBtn);
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
        ChangingScenes.changeWindow(event, "InventoryLogin");
        DataAccess.myProfileData.clear();

        System.out.println("category list = "+ goodsCategoryComboBox.getItems());
        goodsCategoryComboBox.getItems().clear();

        /*DataAccess.itemCategory.clear();
        DataAccess.goodsName.clear();*/

        AlertNotification.trayNotification("LOGOUT", "YOU LOGGED OUT", 5, NotificationType.INFORMATION);
    }

    @FXML
    void changePassword(MouseEvent event) throws SQLException {
        if (DataAccess.checkPassword(previousPasswordTextField.getText()) &&
                newPasswordTextField.getText().equals(reEnterPasswordTextField.getText())){
            DataAccess.changePassword(newPasswordTextField.getText());

            previousPasswordTextField.clear();
            reEnterPasswordTextField.clear();
            newPasswordTextField.clear();

        }
        else {
            System.out.println("Password not same");
            AlertNotification.trayNotification("ERROR", "PASSWORD MISMATCH", 4, NotificationType.ERROR);
        }
    }
}
