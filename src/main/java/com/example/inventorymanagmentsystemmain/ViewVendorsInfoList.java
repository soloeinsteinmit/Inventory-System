package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewVendorsInfoList {
    public static ObservableList<VendorsInfo> vendorsInfo;

    static {
        try {
            vendorsInfo = DataAccess.allVendorsInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
