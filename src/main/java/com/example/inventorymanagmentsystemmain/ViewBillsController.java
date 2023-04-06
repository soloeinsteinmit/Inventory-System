package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewBillsController implements Initializable {


    @FXML
    private Label clientNameLabel;

    @FXML
    private Label dateIssuedLabel;

    @FXML
    private MFXPaginatedTableView<ViewBillsInfo> issuedProductsTableView;

    @FXML
    private MFXTableColumn<ViewBillsInfo> productColumn;

    @FXML
    private MFXTableColumn<ViewBillsInfo> quantityColumn;

    @FXML
    private Label receiptIdLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private MFXTableColumn<ViewBillsInfo> unitPriceColumn;

    @FXML
    private Label vendorNameLabel;
    @FXML
    private AnchorPane viewBillsAnchorPane;

    public static StackPane parentContainerStackPane;
    public static String receiptId;


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUpReceiptTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (DataAccess.getReceiptDetails()){
                clientNameLabel.setText(DataAccess.receiptDetails.get(0));
                receiptIdLabel.setText(receiptId);
                dateIssuedLabel.setText(DataAccess.receiptDetails.get(1));
                vendorNameLabel.setText(DataAccess.receiptDetails.get(2));
                totalPriceLabel.setText(String.valueOf(DataAccess.totalAmount));
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Set up receipt table
     * */
    public void setUpReceiptTable() throws SQLException {
        productColumn.setComparator(Comparator.comparing(ViewBillsInfo::getProduct));
        quantityColumn.setComparator(Comparator.comparing(ViewBillsInfo::getQuantity));
        unitPriceColumn.setComparator(Comparator.comparing(ViewBillsInfo::getAmount));

        productColumn.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewBillsInfo::getProduct));
        quantityColumn.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewBillsInfo::getQuantity));
        unitPriceColumn.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewBillsInfo::getAmount));

        issuedProductsTableView.getTableColumns().addAll(productColumn, quantityColumn, unitPriceColumn);
        issuedProductsTableView.getFilters().addAll(
                new StringFilter<>("PRODUCT NAME", ViewBillsInfo::getProduct)
        );
        issuedProductsTableView.setItems(DataAccess.allBillsInfo());


    }

    @FXML
    void back(MouseEvent event) throws SQLException {
        ChangingScenes.translateBackward(parentContainerStackPane, viewBillsAnchorPane, "bills_dashboard");
        DataAccess.allBillsInfo().clear();
        DataAccess.totalAmount = 0;
        BillsDashboardController.clearRecentReceipt = false;
        totalPriceLabel.setText("0");
    }
}
