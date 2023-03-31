package com.example.inventorymanagmentsystemmain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsCategoryDSChecker {
    public static int arrayIndexBeverages = 0;
    public static int arrayIndexBread = 1;
    public static int arrayIndexCanned = 2;
    public static int arrayIndexDairy = 3;
    public static int arrayIndexDry = 4;
    public static int arrayIndexFrozen = 5;
    public static int arrayIndexMeat = 6;
    public static int arrayIndexProduce = 7;
    public static int arrayIndexCleaners = 8;
    public static int arrayIndexPaperGoods = 9;
    public static int arrayIndexPersonalCare = 10;

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

    public static void addInitialSum() throws SQLException {
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexBeverages,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(0)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexBread,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(1)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexCanned,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(2)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexDairy,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(3)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexDry,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(4)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexFrozen,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(5)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexMeat,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(6)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexProduce,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(7)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexCleaners,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(8)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexPaperGoods,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(9)));
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexPersonalCare,
                DataAccess.getSumQuantity(GoodsCategoryDSChecker.allCategory.get(10)));

        System.out.println("Here goods checker = " + checkQuantityOfUnderEachCategory);

    }

    public static void setAllQuantityArrayElementZero(){
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexBeverages, 0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexBread,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexCanned,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexDairy,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexDry,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexFrozen,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexMeat,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexProduce,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexCleaners,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexPaperGoods,0);
        GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GoodsCategoryDSChecker.arrayIndexPersonalCare,0);

        System.out.println("Here goods checker down here= " + checkQuantityOfUnderEachCategory);
    }


}
