package com.example.inventorymanagmentsystemmain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author .py_ML_ai_MIT (Solomon Eshun)
 *This class is mainly for sending and receiving data from the database
* */
public class DataAccess {
    // view vendors details
    private static String name; // name of vendor
    private static String id; //  id of vendor
    private static String status; // status of vendor
    private static String gender; // gender of vendor
    private static String date_registered;
    private static String telephone_number;

    // view goods details
    private static String goodsNames;
    private static String categoryNames;
    private static int quantity;
    private static String sellingPrice;

    // goods and category ids
    public static String goodsId;
    public static String categoryId;
    public static String categoryName;

    /**
     * Good quantity variables
     * */
    public static int GET_EACH_GOOD_QUANTITY;
    public static int HIGH_STOCK_VALUE = 1000;
    public static int LOW_STOCK_VALUE = 100;
    public static int SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY;



    // goods name and item category
    private static String item_category;
    private static String goods_name;

    public static ArrayList<String> myProfileData = new ArrayList<String>();
    public static String getLoggedInUserId;

    private static ObservableList<ViewVendorsInfo> viewVendorsInfo = FXCollections.observableArrayList();
    private static ObservableList<ViewGoodsInfo> viewGoodsInfo = FXCollections.observableArrayList();
    private static ObservableList<String> itemCategory = FXCollections.observableArrayList();
    private static ObservableList<String> goodsName = FXCollections.observableArrayList();

    private static Stack<ArrayList<String>> stackItems = new Stack<>();
    private static Queue<ArrayList<String>> queueItems = new LinkedList<>();
    private static ArrayList<ArrayList<String>> arrayListItems = new ArrayList<>();
    private static ArrayList<String> getEachItem = new ArrayList<>();


    public static boolean login(String id, String password) throws SQLException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psCheckUserExist = connection.prepareStatement("""
                SELECT user_id, password\s
                FROM users
                WHERE user_id = ? AND password = ?""");
        psCheckUserExist.setString(1, id);
        psCheckUserExist.setString(2, password);
        resultSet = psCheckUserExist.executeQuery();

        return resultSet.isBeforeFirst();
    }

    public static boolean getUserProfile() throws SQLException {
        Connection connection;
        PreparedStatement psGetUserProfile;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetUserProfile = connection.prepareStatement("""
                SELECT user_name, user_id, status, gender, date_registered, telephone_number
                FROM users
                WHERE user_id ="""+ getLoggedInUserId);
        resultSet = psGetUserProfile.executeQuery();

        if (!resultSet.isBeforeFirst()){
            return false;
        }else {
            while (resultSet.next()){
                // adds user profiles to array
                myProfileData.add(0, resultSet.getString("user_name"));
                myProfileData.add(1, resultSet.getString("user_id"));
                myProfileData.add(2, resultSet.getString("status"));
                myProfileData.add(3, resultSet.getString("gender"));
                myProfileData.add(4, resultSet.getString("date_registered"));
                myProfileData.add(5, resultSet.getString("telephone_number"));
                System.out.println("gotten id=  "+ getLoggedInUserId);
            }
            return true;
        }
    }

    public static boolean checkPassword(String password) throws SQLException {
        Connection connection;
        PreparedStatement psCheckPassword;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psCheckPassword = connection.prepareStatement("""
                SELECT password
                FROM users
                WHERE password = ? AND user_id =""" + getLoggedInUserId);
        psCheckPassword.setString(1, password);
        resultSet = psCheckPassword.executeQuery();

        if (!resultSet.isBeforeFirst()){
            System.out.println("Incorrect password from data access");
            AlertNotification.trayNotification("ERROR", "INCORRECT PASSWORD... TRY AGAIN", 5, NotificationType.ERROR);
            return false;
        }else {
            AlertNotification.trayNotification("SUCCESS", "PASSWORD CHANGES SUCCESSFULLY", 5, NotificationType.SUCCESS);
            return true;
        }

    }

    public static void changePassword(String password) throws SQLException {
        Connection connection;
        PreparedStatement psInsertPassword;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

            psInsertPassword = connection.prepareStatement("""
                    UPDATE users
                    SET password = ?
                    WHERE user_id =""" + getLoggedInUserId);
            psInsertPassword.setString(1, password);
            psInsertPassword.executeUpdate();

    }

    public static void registerVendor(String name, String id, String status, String gender,
             String telephoneNo, String date) throws SQLException {

        Connection connection;
        PreparedStatement psRegisterVendor;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);


            psRegisterVendor = connection.prepareStatement("""
                            INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?)
                            """);

            psRegisterVendor.setString(1, id);
            psRegisterVendor.setString(2, name);
            psRegisterVendor.setString(3, "12345");
            psRegisterVendor.setString(4, status);
            psRegisterVendor.setString(5, gender);

            /*java.util.Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");
            dateFormat.format(date)
            */

            psRegisterVendor.setString(6, date);
            psRegisterVendor.setString(7, telephoneNo);
            psRegisterVendor.executeUpdate();


    }

    public static boolean checkId(String id) throws SQLException {
        Connection connection;
        PreparedStatement psCheckId;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psCheckId = connection.prepareStatement("""
                SELECT user_id, user_name
                FROM users
                WHERE user_id = ?""");
        psCheckId.setString(1, id);
        resultSet = psCheckId.executeQuery();

        if (!resultSet.isBeforeFirst()){
            System.out.println("Id not in db");
            AlertNotification.trayNotification("ERROR", "INCORRECT VENDOR ID", 4, NotificationType.ERROR);
            return true;
        }else {
            System.out.println("id in db");
            return false;
        }

    }

    public static ObservableList<ViewVendorsInfo> allVendorsInfo() throws SQLException {
        int i = 1;
        Connection connection;
        PreparedStatement psGetDetails;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetDetails = connection.prepareStatement("""
                SELECT user_name, user_id, status,\s
                \tgender, date_registered, telephone_number
                FROM users""");
        resultSet = psGetDetails.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                name = resultSet.getString("user_name");
                id = resultSet.getString("user_id");
                status = resultSet.getString("status");
                gender = resultSet.getString("gender");
                date_registered = resultSet.getString("date_registered");
                telephone_number = resultSet.getString("telephone_number");
                viewVendorsInfo.add(new ViewVendorsInfo(i, name, id, status, gender, date_registered, telephone_number));
                i++;
            }
        }
        return viewVendorsInfo;
    }

    public static ObservableList<ViewGoodsInfo> allGoodsInfo() throws SQLException {
        int i = 1;
        Connection connection;
        PreparedStatement psGetDetails;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetDetails = connection.prepareStatement("""
                SELECT goods_table.goods_name, category_table.category_name,
                	goods_table.quantity, goods_table.selling_price
                FROM goods_table
                INNER JOIN category_table
                ON category_table.category_id = goods_table.category_id
                """);
        resultSet = psGetDetails.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                goodsNames = resultSet.getString("goods_name");
                categoryNames = resultSet.getString("category_name");
                quantity = resultSet.getInt("quantity");
                sellingPrice = resultSet.getString("selling_price");

                getEachItem.add(0, goodsNames);
                getEachItem.add(1, categoryNames);
                getEachItem.add(2, String.valueOf(quantity));
                getEachItem.add(3, sellingPrice);

                if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.stackGoodsCategory)){
                    stackItems.push(new ArrayList<>(getEachItem));
                } else if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.queueGoodsCategory)) {
                    queueItems.offer(new ArrayList<>(getEachItem));
                } else if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.arrayListGoodsCategory)) {
                    arrayListItems.add(new ArrayList<>(getEachItem));
                }
                getEachItem.clear();
                System.out.println("from db stack = " + stackItems);
                System.out.println("from db queue = " + queueItems);
                System.out.println("from db list = " + arrayListItems);
                // TODO: CODE FOR RECEIVING STACK, QUEUE AND ARRAYLIST WILL BE HERE

            }
            while (!stackItems.isEmpty()){
                ArrayList<String> getEachGoodsDataStack = stackItems.pop();
                viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataStack.get(0), getEachGoodsDataStack.get(1),
                        Integer.parseInt(getEachGoodsDataStack.get(2)), "$ "+getEachGoodsDataStack.get(3)));
                i++;
            }

            while (!queueItems.isEmpty()){
                ArrayList<String> getEachGoodsDataQueue = queueItems.poll();
                viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataQueue.get(0), getEachGoodsDataQueue.get(1),
                        Integer.parseInt(getEachGoodsDataQueue.get(2)), "$ "+getEachGoodsDataQueue.get(3)));
                i++;
            }
            while (!arrayListItems.isEmpty()){
                for (int j = 0; j < arrayListItems.size(); j++){
                    ArrayList<String> getEachGoodsDataList = arrayListItems.remove(j);
                    viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataList.get(0), getEachGoodsDataList.get(1),
                            Integer.parseInt(getEachGoodsDataList.get(2)), "$ "+getEachGoodsDataList.get(3)));
                    i++;
                }

            }

//            viewGoodsInfo.add(new ViewGoodsInfo(i, goodsNames, categoryNames, quantity, "$ "+sellingPrice));

        }
        return viewGoodsInfo;
    }

    public static ObservableList<String> allItemCategory() throws SQLException {
        Connection connection;
        PreparedStatement psGetItemCategory;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetItemCategory = connection.prepareStatement("""
                SELECT category_name
                FROM category_table
            """);
        resultSet = psGetItemCategory.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                item_category = resultSet.getString("category_name");

                if (itemCategory.contains(item_category)){
                    continue;
                }else {
                    itemCategory.add(item_category);
                }

                System.out.println("each item = "+ item_category);

            }
        }
        return itemCategory;
    }

    public static ObservableList<String> allGoodsName(String categoryName) throws SQLException {
        Connection connection;
        PreparedStatement psGetGoodsName;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetGoodsName = connection.prepareStatement("""
                SELECT goods_table.goods_name
                FROM goods_table
                INNER JOIN category_table
                ON category_table.category_id = goods_table.category_id
                WHERE category_name = ?""");
        psGetGoodsName.setString(1, categoryName);
        resultSet = psGetGoodsName.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                goods_name = resultSet.getString("goods_name");
                goodsName.add(goods_name);
            }
        }
        return goodsName;
    }

    public static void removeVendor(String id) throws SQLException {
        Connection connection;
        PreparedStatement psDeleteUser;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

            psDeleteUser = connection.prepareStatement("""
                DELETE
                FROM users
                WHERE user_id = ?""");
            psDeleteUser.setString(1, id);
            psDeleteUser.executeUpdate();
            AlertNotification.trayNotification("SUCCESS", "VENDOR REMOVED SUCCESSFULLY",
                    4, NotificationType.SUCCESS);
    }

    public static String getGoodsId(String goods_name) throws SQLException {
        Connection connection;
        PreparedStatement psGetGoodsId;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetGoodsId = connection.prepareStatement("""
                SELECT goods_id
                FROM goods_table
                WHERE goods_name = ?""");
        psGetGoodsId.setString(1, goods_name);
        resultSet = psGetGoodsId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                goodsId = resultSet.getString("goods_id");
            }
        }
        return goodsId;
    }
    public static String getCategoryId(String category_name) throws SQLException {
        Connection connection;
        PreparedStatement psGetCategoryId;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetCategoryId = connection.prepareStatement("""
                SELECT category_id
                FROM category_table
                WHERE category_name = ?""");
        psGetCategoryId.setString(1, category_name);
        resultSet = psGetCategoryId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                categoryId = resultSet.getString("category_id");
            }
        }
        return categoryId;
    }

    public static String getCategoryNameViewGoods(String c_id) throws SQLException {
        Connection connection;
        PreparedStatement psGetCategoryId;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetCategoryId = connection.prepareStatement("""
                SELECT category_name
                FROM category_table
                WHERE category_id = ?""");
        psGetCategoryId.setString(1, c_id);
        resultSet = psGetCategoryId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                categoryName = resultSet.getString("category_name");
            }
        }
        return categoryName;
    }

    public static void addGoods(String goodsName, String buyingPrice,String sellingPrice, String grossPrice,
                String quantity,String datetimeRegistered) throws SQLException {

        Connection connection;
        PreparedStatement psAddGoods;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psAddGoods = connection.prepareStatement("""
                UPDATE goods_table
                SET buying_price = ?, selling_price = ?, gross_price = ?,
                	quantity = ?, datetime_added = ?
                WHERE goods_id = ?
                """);


        psAddGoods.setDouble(1, Double.parseDouble(buyingPrice));
        psAddGoods.setDouble(2, Double.parseDouble(sellingPrice));
        psAddGoods.setDouble(3, Double.parseDouble(grossPrice));
        psAddGoods.setInt(4, Integer.parseInt(quantity) + getGoodQuantity(goodsName));
        psAddGoods.setString(5, datetimeRegistered);
        psAddGoods.setString(6, getGoodsId(goodsName));

        psAddGoods.executeUpdate();

    }

    public static void addNewCategory(String cId, String categoryName) throws SQLException {
        Connection connection;
        PreparedStatement psAddNewCategory;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psAddNewCategory = connection.prepareStatement("""
                INSERT INTO category_table VALUES(?, ?)
                """);
        psAddNewCategory.setString(1, cId);
        psAddNewCategory.setString(1, categoryName);
        psAddNewCategory.executeUpdate();
    }

    public static void addNewGoods(String goodsId, String goodsName, Double bp, Double sp, Double gp, Integer quantity,
                   String datetimeRegistered, String categoryName) throws SQLException {
        Connection connection;
        PreparedStatement psAddNewGoods;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psAddNewGoods = connection.prepareStatement("""
                INSERT INTO goods_table VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """);
        //TODO: CHECK IF GOODS ID EXIST BEFORE ADDING ANY GOODS

        // gid, gName, bp, sp, gp, quantity, dtRegistered, cId
        psAddNewGoods.setString(1, goodsId);
        psAddNewGoods.setString(2, goodsName);
        psAddNewGoods.setDouble(3, bp);
        psAddNewGoods.setDouble(4, sp);
        psAddNewGoods.setDouble(5, gp);
        psAddNewGoods.setInt(6, quantity);
        psAddNewGoods.setString(7, datetimeRegistered);
        psAddNewGoods.setString(8, getCategoryId(categoryName));
        psAddNewGoods.executeUpdate();

    }

    public static int getGoodQuantity(String goodName) throws SQLException {
        Connection connection;
        PreparedStatement psGetEachGoodQuantity;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetEachGoodQuantity = connection.prepareStatement("""
                SELECT quantity
                FROM goods_table
                WHERE goods_name = ?""" );
        psGetEachGoodQuantity.setString(1, goodName);
        resultSet = psGetEachGoodQuantity.executeQuery();

        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                GET_EACH_GOOD_QUANTITY = resultSet.getInt("quantity");
            }
        }

        return GET_EACH_GOOD_QUANTITY;
    }

    public static boolean isStockHigh(String categoryName) throws SQLException {
        Connection connection;
        PreparedStatement psGetSumQuantity;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetSumQuantity = connection.prepareStatement("""
                SELECT SUM(goods_table.quantity)\s
                FROM goods_table
                INNER JOIN category_table
                ON category_table.category_id = goods_table.category_id
                WHERE category_name = ?
                """);

        psGetSumQuantity.setString(1, categoryName);
        resultSet = psGetSumQuantity.executeQuery();


        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY = resultSet.getInt("SUM(goods_table.quantity)");
                System.out.println(SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY+ " sum here top");
            }
        }
        System.out.println(SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY+ " sum here");
        return SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY > HIGH_STOCK_VALUE;
    }

    public static int getSumQuantity(String categoryName) throws SQLException {
        Connection connection;
        PreparedStatement psGetSumQuantity;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetSumQuantity = connection.prepareStatement("""
                SELECT SUM(goods_table.quantity)\s
                FROM goods_table
                INNER JOIN category_table
                ON category_table.category_id = goods_table.category_id
                WHERE category_name = ?
                """);

        psGetSumQuantity.setString(1, categoryName);
        resultSet = psGetSumQuantity.executeQuery();


        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY = resultSet.getInt("SUM(goods_table.quantity)");
                System.out.println(SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY+ " sum here top");
            }
        }
        return SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY;
    }


    private boolean validateEmail(String  email){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find() && matcher.group().equals(email)){
            return true;
        } else {
            String title = "Email Validation Error";
            String message = "Please enter a valid email";
            TrayNotification tray = new TrayNotification();
            AnimationType  type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            return false;
        }
    }

    public static String getName() {
        return name;
    }

    public static String getId() {
        return id;
    }

    public static String getStatus() {
        return status;
    }

    public static String getGender() {
        return gender;
    }

    public static String getDate_registered() {
        return date_registered;
    }

    public static String getTelephone_number() {
        return telephone_number;
    }
    public static ObservableList<ViewVendorsInfo> getViewVendorsInfo() {
        return viewVendorsInfo;
    }

}
