package com.example.inventorymanagmentsystemmain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Tab addGoodsTab;

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Tab issuedGoodsTab;

    @FXML
    private Tab myProfileTab;

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
    private Tab register_vendor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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



}