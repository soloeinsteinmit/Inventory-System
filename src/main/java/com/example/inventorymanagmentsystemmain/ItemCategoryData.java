package com.example.inventorymanagmentsystemmain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author .py_ML_ai_MIT (Solomon Eshun)
 * @implNote This class receives item category and goods name from DB and places them into an ObservableList
 * to been shown at the AddGoodsController class for goods to be added to inventory.
 * */
public class ItemCategoryData {

    public static String categoryName;

    /**
     * @return All categories from database.
     * */
    public static ObservableList<String> goodsCategoryItems() throws SQLException {
        return DataAccess.allItemCategory();
    }

    /**
     * @return Goods names from database based on the category selected.
     * */
    public static ObservableList<String> goodsName() throws SQLException {
        return DataAccess.allGoodsName(categoryName);
    }

    public static ObservableList<String> attendance(){
        ArrayList<String> attendanceData = new ArrayList<String>();
        for(int i = 0; i <= 150; i++){
            attendanceData.add(String.valueOf(i));
        }
        return FXCollections.observableArrayList(attendanceData);
    }
}
