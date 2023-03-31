package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewBillsInfoList {
    public static ObservableList<ViewBillsInfo> billsInfo;
    static {
        try {
            billsInfo = DataAccess.allBillsInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
