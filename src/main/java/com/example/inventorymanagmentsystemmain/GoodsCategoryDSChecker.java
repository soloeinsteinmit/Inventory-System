package com.example.inventorymanagmentsystemmain;

import java.util.ArrayList;
import java.util.List;

public class GoodsCategoryDSChecker {

    public static ArrayList<String> assignDS = new ArrayList<>(List.of(
            "s", "q", "l"
    ));
    public static ArrayList<String> stackGoodsCategory = new ArrayList<>(List.of(
            "Beverages", "Bread/Bakery", "Canned/Jarred Goods", "Dairy"
    ));
    public static ArrayList<String> queueGoodsCategory = new ArrayList<>(List.of(
            "Dry/Baking Goods", "Frozen Foods", "Meat", "Fruits"
    ));
    public static ArrayList<String> arrayListGoodsCategory = new ArrayList<>(List.of(
            "Produce", "Cleaners", "Paper Goods", "Personal Care"
    ));

    public static ArrayList<Integer> checkQuantityOfUnderEachCategory = new ArrayList<>(List.of(
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    ));

    public static ArrayList<String> allCategory = new ArrayList<>(List.of(
            "Beverages",
            "Bread/Bakery",
            "Canned/Jarred Goods",
            "Dairy",
            "Dry/Baking Goods",
            "Frozen Foods",
            "Meat",
            "Produce",
            "Cleaners",
            "Paper Goods",
            "Personal Care"
    ));


}
