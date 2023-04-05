package com.example.inventorymanagmentsystemmain;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tray.notification.NotificationType;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RegisterVendorController implements Initializable {

    @FXML
    private JFXButton addVendorButton;

    @FXML
    private MFXRadioButton adminCheckbox;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private MFXRadioButton femaleCheckbox;

    @FXML
    private MFXTextField fullNameField;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXButton generateIdBtn;

    @FXML
    private MFXTextField idField;

    @FXML
    private MFXRadioButton maleCheckbox;

    @FXML
    private MFXRadioButton nonAdminCheckbox;

    @FXML
    private JFXButton removeVendorBtn;

    @FXML
    private JFXDialog dialog;

    @FXML
    private StackPane stackPane;
    @FXML
    private ToggleGroup status;

    @FXML
    private MFXTextField telephoneNoField;
    @FXML
    private MFXButton closeBtn;
    @FXML
    private MFXButton rVendorBtn;

    @FXML
    private MFXTextField vendorIdField;

    private static String dateRegistered;

    public HashMap<String, VendorsInfo> getVendorInfo() {
        return vendorInfo;
    }

    private HashMap<String, VendorsInfo> vendorInfo = new HashMap<String, VendorsInfo>();

    GetDatetime getDatetime = new GetDatetime();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialog.setDialogContainer(stackPane);
    }

    private static String g;
    private static String s;
    @FXML
    void addVendor(MouseEvent event) throws SQLException {
        if (DataAccess.checkId(idField.getText()) && !idField.getText().isEmpty() && !fullNameField.getText().isEmpty()
                && !telephoneNoField.getText().isEmpty()){

            if (maleCheckbox.isSelected()){
                g = maleCheckbox.getText();
            }else {
                g = femaleCheckbox.getText();
            }

            if (adminCheckbox.isSelected()){
                s = adminCheckbox.getText();
            }else {
                s = nonAdminCheckbox.getText();
            }

            // place vendors info into
            vendorInfo.put(idField.getText(), new VendorsInfo(fullNameField.getText(), s, g,
                    telephoneNoField.getText(), getDatetime.todayDate()));

            for (Map.Entry<String, VendorsInfo> vendorInfo : vendorInfo.entrySet()){
                if (DataAccess.registerVendor(vendorInfo.getValue().getName(), vendorInfo.getKey(),
                        vendorInfo.getValue().getStatus(), vendorInfo.getValue().getGender(),
                        vendorInfo.getValue().getTelephone_number(), vendorInfo.getValue().getDate_registered())){
                    System.out.println("Success");
                }else {
                    System.out.println("aaaaa");
                }

            }



            System.out.println("Gender  = "+ g);

            AlertNotification.trayNotification("SUCCESS", "YOU HAVE SUCCESSFULLY ADDED "+
                    fullNameField.getText().toUpperCase()+".", 5, NotificationType.SUCCESS);


            /*
            *
            * && !idField.getText().isEmpty() && !fullNameField.getText().isEmpty()
        && !telephoneNoField.getText().isEmpty() && !gender.getSelectedToggle().isSelected()
                && !status.getSelectedToggle().isSelected()
            * */
        }else {
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN ALL THE FORMS "
                    , 5, NotificationType.ERROR);
        }


        generateIdBtn.setDisable(false);
        idField.clear();
        fullNameField.clear();
        telephoneNoField.clear();

        maleCheckbox.setSelected(false);
        femaleCheckbox.setSelected(false);
        adminCheckbox.setSelected(false);
        nonAdminCheckbox.setSelected(false);
    }

    @FXML
    void generateId(MouseEvent event) {
        idField.setText(RandomIdGenerator.randomId(111111, 999999));
        if (!idField.getText().isEmpty()){
            generateIdBtn.setDisable(true);
        }

    }

    BoxBlur blur = new BoxBlur(3, 3, 3);
    @FXML
    void removeVendor(MouseEvent event) {
        anchorPane.setEffect(blur);
        dialog.show();

        anchorPane.setDisable(true);

        closeBtn.setOnMouseClicked(event1 -> {
            anchorPane.setDisable(false);
            dialog.setOnDialogClosed(e-> anchorPane.setEffect(null));
            vendorIdField.clear();
            dialog.close();
        });

        rVendorBtn.setOnMouseClicked(mouseEvent->{
            try {
                if (!vendorIdField.getText().isEmpty() && !DataAccess.checkId(vendorIdField.getText())){
                    DataAccess.removeVendor(vendorIdField.getText());

                    anchorPane.setDisable(false);
                    dialog.setOnDialogClosed(e-> anchorPane.setEffect(null));
                    vendorIdField.clear();
                    dialog.close();
                }else {
                    AlertNotification.trayNotification("ERROR", "PLEASE FILL IN THE FORMS",
                            4, NotificationType.ERROR);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }



}
