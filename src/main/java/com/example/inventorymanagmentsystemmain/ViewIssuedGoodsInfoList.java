package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewIssuedGoodsInfoList {
    public static ObservableList<ViewIssuedGoodsInfo> issuedGoodsInfo;
    static {
        try {
            issuedGoodsInfo = DataAccess.allIssuedGoodsInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
