package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private MFXTableColumn<ViewGoodsInfo> sellingPrice;

    @FXML
    private MFXTableView<ViewGoodsInfo> viewGoodsTableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpViewGoodsTable();
        TooltipClass.tooltipMessage("Refresh Table", refreshBtn);
    }
    private void setUpViewGoodsTable(){
        number.setComparator(Comparator.comparing(ViewGoodsInfo::getNumber));
        goodsName.setComparator(Comparator.comparing(ViewGoodsInfo::getGoodsName));
        categoryName.setComparator(Comparator.comparing(ViewGoodsInfo::getCategoryName));
        quantity.setComparator(Comparator.comparing(ViewGoodsInfo::getQuantity));
        sellingPrice.setComparator(Comparator.comparing(ViewGoodsInfo::getSellingPrice));

        number.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getNumber));
        goodsName.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getGoodsName));
        categoryName.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getCategoryName));
        quantity.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getQuantity));
        sellingPrice.setRowCellFactory(viewGoodsInfo -> new MFXTableRowCell<>(ViewGoodsInfo::getSellingPrice));

        viewGoodsTableView.getTableColumns().addAll(number, goodsName, categoryName, quantity, sellingPrice);
        viewGoodsTableView.getFilters().addAll(
                new IntegerFilter<>("NO.", ViewGoodsInfo::getNumber),
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
