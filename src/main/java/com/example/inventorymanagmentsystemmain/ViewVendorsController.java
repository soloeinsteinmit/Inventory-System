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

public class ViewVendorsController implements Initializable {

    @FXML
    private MFXTableColumn<VendorsInfo> dateRegistered;

    @FXML
    private MFXTableColumn<VendorsInfo> number;

    @FXML
    private MFXTableColumn<VendorsInfo> gender;

    @FXML
    private MFXTableColumn<VendorsInfo> status;

    @FXML
    private MFXTableColumn<VendorsInfo> telepnoneNumber;

    @FXML
    private MFXTableColumn<VendorsInfo> vendorId;

    @FXML
    private MFXTableColumn<VendorsInfo> vendorName;

    @FXML
    private MFXTableView<VendorsInfo> viewVendorsTableView;

    @FXML
    private MFXButton refreshBtn;
    @FXML
    private ImageView refreshImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TooltipClass.tooltipMessage("Refresh Table", refreshBtn);
        setUpViewVendorsTable();
    }

    private void setUpViewVendorsTable(){
        number.setComparator(Comparator.comparing(VendorsInfo::getNumber));
        vendorName.setComparator(Comparator.comparing(VendorsInfo::getName));
        vendorId.setComparator(Comparator.comparing(VendorsInfo::getVendor_id));
        status.setComparator(Comparator.comparing(VendorsInfo::getStatus));
        gender.setComparator(Comparator.comparing(VendorsInfo::getGender));
        dateRegistered.setComparator(Comparator.comparing(VendorsInfo::getDate_registered));
        telepnoneNumber.setComparator(Comparator.comparing(VendorsInfo::getTelephone_number));

        number.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getNumber));
        vendorName.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getName));
        vendorId.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getVendor_id));
        status.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getStatus));
        gender.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getGender));
        dateRegistered.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getDate_registered));
        telepnoneNumber.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(VendorsInfo::getTelephone_number));

        viewVendorsTableView.getTableColumns().addAll(number, vendorName, vendorId, status, gender, dateRegistered, telepnoneNumber);
        viewVendorsTableView.getFilters().addAll(
                new StringFilter<>("VENDOR NAME", VendorsInfo::getName),
                new StringFilter<>("VENDOR ID", VendorsInfo::getVendor_id),
                new StringFilter<>("STATUS", VendorsInfo::getStatus),
                new StringFilter<>("GENDER", VendorsInfo::getGender)
        );
        viewVendorsTableView.setItems(ViewVendorsInfoList.vendorsInfo);
    }

    @FXML
    void refreshViewVendorsTable(MouseEvent event) throws SQLException {
        viewVendorsTableView.getItems().clear();
        viewVendorsTableView.setItems(DataAccess.allVendorsInfo());
//        AlertNotification.trayNotification("REFRESH TABLE", "VIEW VENDORS TABLE REFRESHED", 5, NotificationType.NOTICE);
        MyAnimationsClass.rotateRotateRefreshImages(refreshImg);
    }
}
