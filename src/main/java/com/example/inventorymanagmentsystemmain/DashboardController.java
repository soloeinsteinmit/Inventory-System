package com.example.inventorymanagmentsystemmain;

import com.jfoenix.controls.JFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private StackPane parentStackContainer;
    @FXML
    private Tab addGoodsTab;

    @FXML
    private MFXButton noBtn;

    @FXML
    private MFXButton closeDialog;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    public JFXDialog dialog;

    @FXML
    private Tab issuedGoodsTab;

    @FXML
    private Tab myProfileTab;

    @FXML
    private Tab register_vendor;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab viewBillsTab;

    @FXML
    private Tab viewGoods;

    @FXML
    private Tab viewIssuedGoodsTab;

    @FXML
    private Tab viewVendorTab;

    @FXML
    private Label signOutMessage;
    public static AnchorPane blurAnchorPane;
    public static JFXDialog myDialog;
    public static MFXButton noButton;
    public static MFXButton close;
    public static StackPane parentStack;
    static Stage stage1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        noButton = noBtn;
        myDialog = dialog;
        close = closeDialog;
        parentStack = parentStackContainer;

        myDialog.setDialogContainer(parentStackContainer);
        blurAnchorPane = dashboardPane;

        // tab Tooltip Messages
        TooltipClass.tooltipMessage("VIEW VENDOR", viewVendorTab);
        TooltipClass.tooltipMessage("VIEW BILLS", viewBillsTab);
        TooltipClass.tooltipMessage("VIEW ISSUED GOODS", viewIssuedGoodsTab);
        TooltipClass.tooltipMessage("VIEW GOODS", viewGoods);
        TooltipClass.tooltipMessage("MY PROFILE", myProfileTab);
        TooltipClass.tooltipMessage("ISSUED GOODS", issuedGoodsTab);
        TooltipClass.tooltipMessage("ADD GOODS", addGoodsTab);
        TooltipClass.tooltipMessage("REGISTER VENDOR", register_vendor);
    }

    static BoxBlur blur = new BoxBlur(3, 3, 3);

    public static void closeWindowAlert(){
        blurAnchorPane.setEffect(blur);
        myDialog.show();

        blurAnchorPane.setDisable(true);

        noButton.setOnMouseClicked(event -> {
            blurAnchorPane.setDisable(false);
            myDialog.setOnDialogClosed(e-> blurAnchorPane.setEffect(null));
            myDialog.close();

            ChangingScenes.close(event);

        });
        close.setOnMouseClicked(e1->{
            blurAnchorPane.setDisable(false);
            myDialog.setOnDialogClosed(e-> blurAnchorPane.setEffect(null));
            myDialog.close();

        });
    }

    public void signOut(String message){
        blurAnchorPane.setEffect(blur);
        myDialog.show();

        blurAnchorPane.setDisable(true);
        signOutMessage.setText(message);

        noBtn.setOnMouseClicked(event -> {
            blurAnchorPane.setDisable(false);
            myDialog.setOnDialogClosed(e-> blurAnchorPane.setEffect(null));
            myDialog.close();
        });

        closeDialog.setOnMouseClicked(e1->{
            blurAnchorPane.setDisable(false);

            myDialog.setOnDialogClosed(e-> blurAnchorPane.setEffect(null));
            myDialog.close();
            try {
                ChangingScenes.changeWindow(e1, "InventoryLogin");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



}