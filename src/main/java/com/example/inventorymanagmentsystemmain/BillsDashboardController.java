package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import tray.notification.NotificationType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * @author .py_ML_ai_MIT (Solomon Eshun)
 * */
public class BillsDashboardController implements Initializable {

    @FXML
    private AnchorPane billsDashboardAnchorPane;

    @FXML
    private MFXButton clearSearch;

    @FXML
    private MFXButton getReceiptBtn;

    @FXML
    private StackPane parentStackPane;

    @FXML
    private MFXTextField receiptId;
    @FXML
    private ImageView refreshImg;

    @FXML
    private ImageView refreshingImage;

    @FXML
    private Label retrievingReceiptLabel;

    @FXML
    private MFXButton searchBtn;

    @FXML
    private MFXTextField searchField;
    @FXML
    private MFXLegacyListView<String> recentReceiptsListView;

    private static ObservableList<String> recentlyGeneratedReceipts;
    private ArrayList<String> recentReceipts = new ArrayList<>();

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewBillsController.parentContainerStackPane = parentStackPane;
        try {
            recentReceiptsListView.setItems(DataAccess.getReceiptIds());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean clearRecentReceipt = false;
    @FXML
    void getReceipt(MouseEvent event) throws SQLException {


        if (!receiptId.getText().isEmpty() && DataAccess.checkReceiptIdExist(receiptId.getText())){

            ViewBillsController.receiptId = receiptId.getText();
            retrievingReceiptLabel.setVisible(true);
            refreshingImage.setVisible(true);
            MyAnimationsClass.viewBillsAnimation(refreshingImage, "RECEIPT HAS BEEN SUCCESSFULLY RETRIEVED",
                    retrievingReceiptLabel, parentStackPane, billsDashboardAnchorPane, "view_bills");


        }else {
            AlertNotification.trayNotification("ERROR", "PLEASE ENTER THE RECEIPT ID", 4, NotificationType.NOTICE);

        }
    }

    @FXML
    void getReceiptIds(MouseEvent event){
        receiptId.setText(recentReceiptsListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    void search(MouseEvent event) throws SQLException {

    }

    @FXML
    void clearSearch(MouseEvent event) throws SQLException {
        recentReceiptsListView.getItems().clear();
        recentReceiptsListView.setItems(DataAccess.getReceiptIds());
    }
}
