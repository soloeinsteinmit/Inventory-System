package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewGoodsInfoList {
    public static ObservableList<ViewGoodsInfo> goodsInfo;

    static {
        try {
            goodsInfo = DataAccess.allGoodsInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
