package com.example.inventorymanagmentsystemmain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @return all goods  from database for issuing of goods.
     * */
    public static ObservableList<String> allProducts() throws SQLException {
        return DataAccess.getAllGods();
    }

    /**
     * An array list for containing all guides notes at login.
     * */
    public static ArrayList<String> guideNotes = new ArrayList<>(List.of(
            "\"Use filters in the tableview to narrow down your search in tables...\"",
            "\"Second guide note here\"",
            "\"third guide note here\"",
            "\"forth guide note here\"",
            "\"fifth guide note here\"",
            "\"sixth guide note here\"",
            "\"seventh guide note here\""
    ));

    public static ObservableList<String> attendance(){
        ArrayList<String> attendanceData = new ArrayList<String>();
        for(int i = 0; i <= 150; i++){
            attendanceData.add(String.valueOf(i));
        }
        return FXCollections.observableArrayList(attendanceData);
    }
}
