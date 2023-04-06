package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewIssuedGoodsController implements Initializable {

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> categoryName;

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> datetimeSold;

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> goodsName;

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> number;

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> quantitySold;

    @FXML
    private MFXTableColumn<ViewIssuedGoodsInfo> receiptId;

    @FXML
    private MFXButton refreshBtn;

    @FXML
    private ImageView refreshImg;

    @FXML
    private MFXTableView<ViewIssuedGoodsInfo> viewIssuedGoodsTableView;


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpViewIssuedGoodsTable();
        TooltipClass.tooltipMessage("Refresh Table", refreshBtn);
    }

    /**
     * sets up issued goods table
     * */
    public void setUpViewIssuedGoodsTable(){
        number.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getNumber));
        goodsName.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getGoodsName));
        categoryName.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getCategoryName));
        quantitySold.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getQuantitySold));
        receiptId.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getReceiptId));
        datetimeSold.setComparator(Comparator.comparing(ViewIssuedGoodsInfo::getDatetimeSold));

        number.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getNumber));
        goodsName.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getGoodsName));
        categoryName.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getCategoryName));
        quantitySold.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getQuantitySold));
        receiptId.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getReceiptId));
        datetimeSold.setRowCellFactory(viewIssuedGoodsInfo -> new MFXTableRowCell<>(ViewIssuedGoodsInfo::getDatetimeSold));

        viewIssuedGoodsTableView.getTableColumns().addAll(number, goodsName, categoryName, quantitySold, receiptId, datetimeSold);
        viewIssuedGoodsTableView.getFilters().addAll(
                new StringFilter<>("GOOD(S) NAME", ViewIssuedGoodsInfo::getGoodsName),
                new StringFilter<>("CATEGORY NAME", ViewIssuedGoodsInfo::getCategoryName),
                new StringFilter<>("DATETIME SOLD", ViewIssuedGoodsInfo::getDatetimeSold),
                new StringFilter<>("RECEIPT ID", ViewIssuedGoodsInfo::getReceiptId)
        );
        viewIssuedGoodsTableView.setItems(ViewIssuedGoodsInfoList.issuedGoodsInfo);
    }

    @FXML
    void refreshTable(MouseEvent event) throws SQLException {
        viewIssuedGoodsTableView.getItems().clear();
        viewIssuedGoodsTableView.setItems(DataAccess.allIssuedGoodsInfo());
        MyAnimationsClass.rotateRotateRefreshImages(refreshImg);
    }
}
