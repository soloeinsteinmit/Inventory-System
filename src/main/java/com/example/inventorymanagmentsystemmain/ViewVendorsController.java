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

public class ViewVendorsController implements Initializable {

    @FXML
    private MFXTableColumn<ViewVendorsInfo> dateRegistered;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> number;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> gender;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> status;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> telepnoneNumber;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> vendorId;

    @FXML
    private MFXTableColumn<ViewVendorsInfo> vendorName;

    @FXML
    private MFXTableView<ViewVendorsInfo> viewVendorsTableView;

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
        number.setComparator(Comparator.comparing(ViewVendorsInfo::getNumber));
        vendorName.setComparator(Comparator.comparing(ViewVendorsInfo::getName));
        vendorId.setComparator(Comparator.comparing(ViewVendorsInfo::getVendor_id));
        status.setComparator(Comparator.comparing(ViewVendorsInfo::getStatus));
        gender.setComparator(Comparator.comparing(ViewVendorsInfo::getGender));
        dateRegistered.setComparator(Comparator.comparing(ViewVendorsInfo::getDate_registered));
        telepnoneNumber.setComparator(Comparator.comparing(ViewVendorsInfo::getTelephone_number));

        number.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getNumber));
        vendorName.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getName));
        vendorId.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getVendor_id));
        status.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getStatus));
        gender.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getGender));
        dateRegistered.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getDate_registered));
        telepnoneNumber.setRowCellFactory(viewVendorsInfo -> new MFXTableRowCell<>(ViewVendorsInfo::getTelephone_number));

        viewVendorsTableView.getTableColumns().addAll(number, vendorName, vendorId, status, gender, dateRegistered, telepnoneNumber);
        viewVendorsTableView.getFilters().addAll(
                new IntegerFilter<>("NO.", ViewVendorsInfo::getNumber),
                new StringFilter<>("VENDOR NAME", ViewVendorsInfo::getName),
                new StringFilter<>("VENDOR ID", ViewVendorsInfo::getVendor_id),
                new StringFilter<>("STATUS", ViewVendorsInfo::getStatus),
                new StringFilter<>("GENDER", ViewVendorsInfo::getGender),
                new StringFilter<>("TELEPHONE NUMBER", ViewVendorsInfo::getTelephone_number)
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
