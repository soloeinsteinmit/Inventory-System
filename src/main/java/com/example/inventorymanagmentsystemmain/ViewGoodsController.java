package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ViewGoodsController implements Initializable {
    @FXML
    private MFXTableColumn<ViewGoodsInfo> categoryName;

    @FXML
    private MFXTableColumn<ViewGoodsInfo> goodsName;

    @FXML
    private MFXTableColumn<ViewGoodsInfo> number;

    @FXML
    private MFXTableColumn<ViewGoodsInfo> quantity;

    @FXML
    private MFXButton refreshBtn;

    @FXML
    private ImageView refreshImg;

    @FXML
    private Label totalProfitLabel;

    @FXML
    private MFXTableColumn<ViewGoodsInfo> sellingPrice;

    @FXML
    private MFXTableView<ViewGoodsInfo> viewGoodsTableView;
    @FXML
    private MFXTableColumn<ViewGoodsInfo> profitColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpViewGoodsTable();
        totalProfitLabel.setText(String.valueOf(DataAccess.totalProfit));
        TooltipClass.tooltipMessage("Refresh Table", refreshBtn);
    }
    /**
     * Setup up goods table
     * */
    private void setUpViewGoodsTable(){
        number.setComparator(Comparator.comparing(ViewGoodsInfo::getNumber));
        goodsName.setComparator(Comparator.comparing(ViewGoodsInfo::getGoodsName));
        categoryName.setComparator(Comparator.comparing(ViewGoodsInfo::getCategoryName));
        quantity.setComparator(Comparator.comparing(ViewGoodsInfo::getQuantity));
        sellingPrice.setComparator(Comparator.comparing(ViewGoodsInfo::getSellingPrice));
        profitColumn.setComparator(Comparator.comparing(ViewGoodsInfo::getProfit));

        number.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getNumber));
        goodsName.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getGoodsName));
        categoryName.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getCategoryName));
        quantity.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getQuantity));
        sellingPrice.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getSellingPrice));
        profitColumn.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getProfit));

        viewGoodsTableView.getTableColumns().addAll(number, goodsName, categoryName, quantity, sellingPrice, profitColumn);
        viewGoodsTableView.getFilters().addAll(
                new StringFilter<>("GOODS NAME", ViewGoodsInfo::getGoodsName),
                new StringFilter<>("CATEGORY NAME", ViewGoodsInfo::getCategoryName)
        );
        viewGoodsTableView.setItems(ViewGoodsInfoList.goodsInfo);
    }
    @FXML
    void refreshViewGoodsTable(MouseEvent event) throws SQLException {
        viewGoodsTableView.getItems().clear();
        viewGoodsTableView.setItems(DataAccess.allGoodsInfo());
//        AlertNotification.trayNotification("REFRESH TABLE", "VIEW GOODS TABLE REFRESHED", 5, NotificationType.NOTICE);

        MyAnimationsClass.rotateRotateRefreshImages(refreshImg);

    }


}
