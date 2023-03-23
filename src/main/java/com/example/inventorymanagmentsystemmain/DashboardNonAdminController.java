package com.example.inventorymanagmentsystemmain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardNonAdminController implements Initializable {
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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // tab Tooltip Messages
        TooltipClass.tooltipMessage("VIEW BILLS", viewBillsTab);
        TooltipClass.tooltipMessage("VIEW ISSUED GOODS", viewIssuedGoodsTab);
        TooltipClass.tooltipMessage("VIEW GOODS", viewGoods);
        TooltipClass.tooltipMessage("MY PROFILE", myProfileTab);
        TooltipClass.tooltipMessage("ISSUED GOODS", issuedGoodsTab);
        TooltipClass.tooltipMessage("ADD GOODS", addGoodsTab);

    }
}
