package com.example.inventorymanagmentsystemmain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author .py_ML_ai_MIT (Solomon Eshun)
 *This class is mainly for sending and receiving data from the database
* */
public class DataAccess {
    /**
     * view vendors details
     * */
    private static String name; // name of vendor
    private static String id; //  id of vendor
    private static String status; // status of vendor
    private static String gender; // gender of vendor
    private static String date_registered;
    private static String telephone_number;

    /**
    * view goods details
    * */
    private static String goodsNames;
    private static String categoryNames;
    private static int quantity;
    private static String sellingPrice;
    private static double profit;
    public static double totalProfit;

    /**
     * goods and category ids
     * */
    public static String goodsId;
    public static String categoryId;
    public static String categoryName;
    public static String goodName;

    /**
     * Good quantity variables
     * */
    public static int GET_EACH_GOOD_QUANTITY;
    public static int HIGH_STOCK_VALUE = 1000;
    public static int HIGHEST_STOCK_VALUE = 1200;
    public static int LOW_STOCK_VALUE = 100;
    public static int LOWEST_STOCK_VALUE = 50;
    public static int SUM_OF_GOODS_QUANTITY_UNDER_EACH_CATEGORY;

    /**
     * view bills info
     * */
    private static String product;
    private static int retrieveQuantity;
    private static float amount;
    public static float totalAmount = 0;

    /**
    * variables for issued goods table
    * */
    private static String gName;
    private static String cId;
    private static int quantitySold;
    private static String receiptId;
    private static String receiptID;
    private static String datetimeSold;


    /**
     * goods name and item category
     * */
    private static String item_category;
    private static String goods_name;

    public static ArrayList<String> myProfileData = new ArrayList<String>(7);
    public static ArrayList<String> receiptDetails = new ArrayList<String>(5);
    public static String getLoggedInUserId;

    private static ObservableList<VendorsInfo> viewVendorsInfo = FXCollections.observableArrayList();
    private static ObservableList<ViewIssuedGoodsInfo> viewIssuedGoodsInfo = FXCollections.observableArrayList();
    private static ObservableList<ViewBillsInfo> viewBillsInfo = FXCollections.observableArrayList();
    private static ObservableList<ViewGoodsInfo> viewGoodsInfo = FXCollections.observableArrayList();
    private static ObservableList<String> itemCategory = FXCollections.observableArrayList();
    private static ObservableList<String> allProducts = FXCollections.observableArrayList();
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

    public static boolean getReceiptDetails() throws SQLException, ParseException {
        Connection connection;
        PreparedStatement psGetReceiptDetails;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetReceiptDetails = connection.prepareStatement("""
                SELECT DISTINCT(client_name), datetime_of_good_sold, user_id
                FROM view_issued_goods
                WHERE receipt_id ="""+ ViewBillsController.receiptId);
        resultSet = psGetReceiptDetails.executeQuery();
        if (!resultSet.isBeforeFirst()){
            return false;
        }else {
            while (resultSet.next()){
                receiptDetails.add(0, resultSet.getString("client_name"));
                receiptDetails.add(1, GetDatetime.formatDate(resultSet.getString("datetime_of_good_sold")));
                receiptDetails.add(2, getUserName(resultSet.getString("user_id")));

            }
        }

        return true;
    }

    public static String getUserName(String userId) throws SQLException {
        Connection connection;
        PreparedStatement psGetUserName;
        ResultSet resultSet;
        String name = null;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetUserName = connection.prepareStatement("""
                SELECT user_name
                FROM users
                WHERE user_id = ?
                """);
        psGetUserName.setString(1, userId);
        resultSet = psGetUserName.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                name = resultSet.getString("user_name");
            }
        }
        return name;

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

    public static boolean registerVendor(String name, String id, String status, String gender,
             String telephoneNo, String date) throws SQLException {

        Connection connection;
        PreparedStatement psRegisterVendor;
        PreparedStatement checkId;
        ResultSet resultset;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        checkId =connection.prepareStatement("""
                SELECT user_id
                FROM users
                WHERE user_id = ?
                """);
        checkId.setString(1, id);
        resultset = checkId.executeQuery();
        if (resultset.isBeforeFirst()){
            return true;
        }else {
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
            return false;
        }

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

    public static ObservableList<ViewIssuedGoodsInfo> allIssuedGoodsInfo() throws SQLException{
        int i = 1;
        Connection connection;
        PreparedStatement psIssuedGoodGetDetails;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psIssuedGoodGetDetails = connection.prepareStatement("""
                SELECT goods_table.goods_name, goods_table.category_id,
                	view_issued_goods.quantity_bought, view_issued_goods.receipt_id,
                    view_issued_goods.datetime_of_good_sold
                FROM view_issued_goods
                INNER JOIN goods_table
                ON view_issued_goods.goods_id = goods_table.goods_id
                """);
        resultSet = psIssuedGoodGetDetails.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                gName = resultSet.getString("goods_name");
                cId = resultSet.getString("category_id");
                quantitySold = resultSet.getInt("quantity_bought");
                receiptId = resultSet.getString("receipt_id");
                datetimeSold = GetDatetime.formatDate(resultSet.getString("datetime_of_good_sold"));
//                GetDatetime.formatDate(date_registered);
                viewIssuedGoodsInfo.add(new ViewIssuedGoodsInfo(i, gName, getCategoryName(cId), quantitySold, receiptId, datetimeSold));
                i++;
            }
        }
        return viewIssuedGoodsInfo;
    }
    /**
     *
     * */
    public static ObservableList<VendorsInfo> allVendorsInfo() throws SQLException {
        int i = 1; Connection connection;
        PreparedStatement psGetDetails; ResultSet resultSet;

        HashMap<String, VendorsInfo> vInfo = new HashMap<String, VendorsInfo>(); // hashmap to store vendor info

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
                id = resultSet.getString("user_id"); status = resultSet.getString("status");
                gender = resultSet.getString("gender"); date_registered = resultSet.getString("date_registered");
                telephone_number = resultSet.getString("telephone_number");

                vInfo.put(id, new VendorsInfo(i, name, status, gender, date_registered, telephone_number)); //puts data into hashmap
                i++;
            }
        }
        for(Map.Entry<String, VendorsInfo> eachVendorInfo : vInfo.entrySet()){ // displays data into hashmap
            viewVendorsInfo.add(new VendorsInfo(eachVendorInfo.getValue().getNumber(), eachVendorInfo.getValue().getName(), eachVendorInfo.getKey(), eachVendorInfo.getValue().getStatus(),
                eachVendorInfo.getValue().getGender(), eachVendorInfo.getValue().getDate_registered(),
                    eachVendorInfo.getValue().getTelephone_number()));
        }
        return viewVendorsInfo;
    }

    public static ObservableList<ViewBillsInfo> allBillsInfo() throws SQLException{
        Connection connection;
        PreparedStatement psGetBills;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetBills = connection.prepareStatement("""
                SELECT goods_id, quantity_bought, amount
                FROM view_issued_goods
                WHERE receipt_id =
                """+ViewBillsController.receiptId);
        resultSet = psGetBills.executeQuery();
        if (resultSet.isBeforeFirst()){
            while(resultSet.next()){
                product = getGoodName(resultSet.getString("goods_id"));
                retrieveQuantity = resultSet.getInt("quantity_bought");
                amount = resultSet.getFloat("amount");
                viewBillsInfo.add(new ViewBillsInfo(product, retrieveQuantity, amount));
                totalAmount += amount;
            }
        }

        return viewBillsInfo;
    }

    public static String getDataStructure(String categoryName) throws SQLException{
        Connection connection;
        PreparedStatement getDS;
        ResultSet resultSet;
        String dataStructure = null;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        getDS = connection.prepareStatement("""
                SELECT data_structure
                FROM category_table
                WHERE category_name = ?
                """);
        getDS.setString(1, categoryName);
        resultSet = getDS.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                dataStructure = resultSet.getString("data_structure");
            }
        }
        return dataStructure;
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
                	goods_table.quantity, goods_table.selling_price, goods_table.profit
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
                profit = resultSet.getDouble("profit");

                DecimalFormat df = new DecimalFormat("#.00");
                String formattedNumber = df.format(profit);

                getEachItem.add(0, goodsNames);
                getEachItem.add(1, categoryNames);
                getEachItem.add(2, String.valueOf(quantity));
                getEachItem.add(3, sellingPrice);
                getEachItem.add(4, String.valueOf(formattedNumber));

                /*if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.stackGoodsCategory)){
                    stackItems.push(new ArrayList<>(getEachItem));
                } else if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.queueGoodsCategory)) {
                    queueItems.offer(new ArrayList<>(getEachItem));
                } else if (Algorithms.linearSearch(categoryNames, GoodsCategoryDSChecker.arrayListGoodsCategory)) {
                    arrayListItems.add(new ArrayList<>(getEachItem));
                }*/

                if (Objects.equals(getDataStructure(getEachItem.get(1)), "s")){
                    stackItems.push(new ArrayList<>(getEachItem));
                } else if (Objects.equals(getDataStructure(getEachItem.get(1)), "q")) {
                    queueItems.offer(new ArrayList<>(getEachItem));
                }else if(Objects.equals(getDataStructure(getEachItem.get(1)), "l")){
                    arrayListItems.add(new ArrayList<>(getEachItem));
                }
                getEachItem.clear();
//                System.out.println("from db stack = " + stackItems);
//                System.out.println("from db queue = " + queueItems);
//                System.out.println("from db list = " + arrayListItems);
                // TODO: CODE FOR RECEIVING STACK, QUEUE AND ARRAYLIST WILL BE HERE -- DONE

            }
            while (!stackItems.isEmpty()){
                ArrayList<String> getEachGoodsDataStack = stackItems.pop();
                viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataStack.get(0), getEachGoodsDataStack.get(1),
                        Integer.parseInt(getEachGoodsDataStack.get(2)), "GHC "+getEachGoodsDataStack.get(3), "GHC "+getEachGoodsDataStack.get(4)));
                totalProfit += Double.parseDouble(getEachGoodsDataStack.get(4));
                i++;
            }

            while (!queueItems.isEmpty()){
                ArrayList<String> getEachGoodsDataQueue = queueItems.poll();
                viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataQueue.get(0), getEachGoodsDataQueue.get(1),
                        Integer.parseInt(getEachGoodsDataQueue.get(2)), "GHC "+getEachGoodsDataQueue.get(3), "GHC "+getEachGoodsDataQueue.get(4)));
                totalProfit += Double.parseDouble(getEachGoodsDataQueue.get(4));
                i++;
            }
            while (!arrayListItems.isEmpty()){
                for (int j = 0; j < arrayListItems.size(); j++){
                    ArrayList<String> getEachGoodsDataList = arrayListItems.remove(j);
                    viewGoodsInfo.add(new ViewGoodsInfo(i, getEachGoodsDataList.get(0), getEachGoodsDataList.get(1),
                            Integer.parseInt(getEachGoodsDataList.get(2)), "GHC "+getEachGoodsDataList.get(3), "GHC "+getEachGoodsDataList.get(4)));
                    totalProfit += Double.parseDouble(getEachGoodsDataList.get(4));
                    i++;
                }

            }

//            viewGoodsInfo.add(new ViewGoodsInfo(i, goodsNames, categoryNames, quantity, "$ "+sellingPrice));

        }
        return viewGoodsInfo;
    }
    public static void removeGoodFromInventory(String goodName) throws SQLException {
        Connection connection;
        PreparedStatement psRemoveGood;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psRemoveGood = connection.prepareStatement("""
                DELETE
                FROM goods_table
                WHERE goods_name = ?
                """);
        psRemoveGood.setString(1, goodName);
        psRemoveGood.executeUpdate();

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

                if (Algorithms.linearSearch(item_category, itemCategory)){
                    //itemCategory.contains(item_category);
                    continue;
                }else {
                    itemCategory.add(item_category);
                }

//                System.out.println("each item = "+ item_category);

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

    public static String getCategoryName(String c_id) throws SQLException {
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
    public static String getGoodName(String g_id) throws SQLException {
        Connection connection;
        PreparedStatement psGetGoodsId;
        ResultSet resultSet;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetGoodsId = connection.prepareStatement("""
                SELECT goods_name
                FROM goods_table
                WHERE goods_id = ?
            """);
        psGetGoodsId.setString(1, g_id);
        resultSet = psGetGoodsId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                goodName = resultSet.getString("goods_name");
            }
        }

        return goodName;
    }



    public static void addGoods(String goodsName, String buyingPrice,String sellingPrice, String profit,
                String quantity,String datetimeRegistered, Label overStockingMessage) throws SQLException {

        Connection connection;
        PreparedStatement psAddGoods;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psAddGoods = connection.prepareStatement("""
                UPDATE goods_table
                SET buying_price = ?, selling_price = ?, profit = ?,
                	quantity = ?, datetime_added = ?
                WHERE goods_id = ?
                """);


        psAddGoods.setDouble(1, Double.parseDouble(buyingPrice));
        psAddGoods.setDouble(2, Double.parseDouble(sellingPrice));
        psAddGoods.setDouble(3, Double.parseDouble(profit));

        if (Integer.parseInt(quantity) + getGoodQuantity(goodsName) >= HIGH_STOCK_VALUE &&
                Integer.parseInt(quantity) + getGoodQuantity(goodsName) < HIGHEST_STOCK_VALUE){

            psAddGoods.setInt(4, Integer.parseInt(quantity) + getGoodQuantity(goodsName));

            overStockingMessage.setVisible(true);
            overStockingMessage.setText("OVERSTOCKING "+ goodsName.toUpperCase());
            AlertNotification.trayNotification("OVERSTOCKING", gName.toUpperCase()+ " IS BEING OVERSTOCKED", 4, NotificationType.NOTICE);


        }else if(Integer.parseInt(quantity) + getGoodQuantity(goodsName) > HIGHEST_STOCK_VALUE){
//            psAddGoods.setInt(4, Integer.parseInt(quantity));
            overStockingMessage.setVisible(true);
            overStockingMessage.setText("OVERSTOCKING "+ goodsName.toUpperCase());
            AlertNotification.trayNotification("OVERSTOCKING", gName.toUpperCase()+ " IS BEING OVERSTOCKED \n" +
                    "CANNOT ADD ANYMORE GOODS TO INVENTORY.", 4, NotificationType.NOTICE);

        }else {
            psAddGoods.setInt(4, Integer.parseInt(quantity) + getGoodQuantity(goodsName));
        }


        psAddGoods.setString(5, datetimeRegistered);
        psAddGoods.setString(6, getGoodsId(goodsName));

        psAddGoods.executeUpdate();
        overStockingMessage.setVisible(false);

    }

    public static void addNewCategory(String cId, String categoryName, String ds) throws SQLException {
        Connection connection;
        PreparedStatement psAddNewCategory;
        PreparedStatement psCheckCategoryExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psCheckCategoryExist = connection.prepareStatement("""
                SELECT category_name
                FROM category_table
                WHERE category_name = ?
                """);
        psCheckCategoryExist.setString(1, categoryName);
        resultSet = psCheckCategoryExist.executeQuery();
        if (resultSet.isBeforeFirst()){
            AlertNotification.trayNotification("ERROR", "GOOD CATEGORY ALREADY EXIST", 4, NotificationType.NOTICE);
        }else {
            psAddNewCategory = connection.prepareStatement("""
                INSERT INTO category_table VALUES(?, ?, ?)
                """);
            psAddNewCategory.setString(1, cId);
            psAddNewCategory.setString(2, categoryName);
            psAddNewCategory.setString(3, ds);
            psAddNewCategory.executeUpdate();
        }

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



    public static ObservableList<String> getAllGods() throws SQLException{
        Connection connection;
        PreparedStatement psGetAllGoods;
        ResultSet resultSet;
        String productName;
        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetAllGoods = connection.prepareStatement("""
                SELECT goods_name
                FROM goods_table
                """);
        resultSet = psGetAllGoods.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                productName = resultSet.getString("goods_name");

                if (Algorithms.linearSearch(productName, allProducts)){
                    continue;
                    // allProducts.contains(productName)
                }else {
                    allProducts.add(productName);
                }

            }
        }
        return allProducts;
    }

    public static float getSellingPriceOfProduct(String productName) throws SQLException{
        Connection connection;
        PreparedStatement psGetSellingPrice;
        ResultSet resultSet;
        float sellingPrice = 0;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetSellingPrice = connection.prepareStatement("""
                SELECT selling_price
                FROM goods_table
                WHERE goods_name = ?
                """);
        psGetSellingPrice.setString(1, productName);
        resultSet = psGetSellingPrice.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                sellingPrice = resultSet.getFloat("selling_price");
            }
        }

        return sellingPrice;
    }

    public static int getEachProductQuantity(String gName) throws SQLException {
        Connection connection;
        PreparedStatement psGetEachProductQuantity;
        ResultSet resultSet;
        int initialQuantity = 0;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psGetEachProductQuantity = connection.prepareStatement("""
                SELECT quantity
                FROM goods_table
                WHERE goods_name = ?
                """);
        psGetEachProductQuantity.setString(1, gName);
        resultSet = psGetEachProductQuantity.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                initialQuantity = resultSet.getInt("quantity");
            }

        }

        return initialQuantity;
    }
    public static boolean updateQuantity(String gName, int quantity, char updateQuantityType) throws SQLException {
        Connection connection;
        PreparedStatement psUpdateQuantity;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);

        psUpdateQuantity = connection.prepareStatement("""
                UPDATE goods_table
                SET quantity = ?
                WHERE goods_name = ?
                """);

        if (updateQuantityType == 'a'){ // a - adding of goods into table
            if(getEachProductQuantity(gName) + quantity >= HIGH_STOCK_VALUE && getEachProductQuantity(gName) + quantity < HIGHEST_STOCK_VALUE){
                psUpdateQuantity.setInt(1, getEachProductQuantity(gName) + quantity);
                AlertNotification.trayNotification("OVERSTOCKING", gName.toUpperCase()+ " IS BEING OVERSTOCKED",
                        4, NotificationType.NOTICE);
                return true;

            } else if(getEachProductQuantity(gName) + quantity >= HIGHEST_STOCK_VALUE){
//                psUpdateQuantity.setInt(1, getEachProductQuantity(gName));
                AlertNotification.trayNotification("OVERSTOCKING", gName.toUpperCase()+ " IS BEING OVERSTOCKED\n" +
                        "CANNOT ADD ANYMORE GOODS TO INVENTORY", 4, NotificationType.NOTICE);
                return false;
            }else {
                psUpdateQuantity.setInt(1, getEachProductQuantity(gName) + quantity);
//                System.out.println("Initial quantity of "+ gName +" "+ getEachProductQuantity(gName));
//                System.out.println("DataAccess updated db= "+ (getEachProductQuantity(gName) + quantity));
                return true;
            }

        }else if(updateQuantityType == 'i'){ // i - issuing of goods
            if ( getEachProductQuantity(gName) - quantity <= LOW_STOCK_VALUE){
                psUpdateQuantity.setInt(1, getEachProductQuantity(gName) - quantity);
                AlertNotification.trayNotification("OUT OF STOCK", gName.toUpperCase()+ " IS GETTING OUT OF STOCK",
                        4, NotificationType.NOTICE);
                System.out.println("Initial quantity of "+ gName +" "+ getEachProductQuantity(gName));
                System.out.println("DataAccess line 694 updated db= "+ (getEachProductQuantity(gName) - quantity));
                return true;

            } else if (getEachProductQuantity(gName) - quantity <= LOWEST_STOCK_VALUE){
                psUpdateQuantity.setInt(1, getEachProductQuantity(gName) - quantity);
                AlertNotification.trayNotification("OUT OF STOCK", gName.toUpperCase()+ " VERY IS LOW IN STOCK",
                        4, NotificationType.NOTICE);
                System.out.println("Initial quantity of "+ gName +" "+ getEachProductQuantity(gName));
                System.out.println("DataAccess line 694 updated db= "+ (getEachProductQuantity(gName) - quantity));
                return true;

            }else if (getEachProductQuantity(gName) - quantity <= 0){
//                psUpdateQuantity.setInt(1, getEachProductQuantity(gName));
                AlertNotification.trayNotification("OUT OF STOCK", gName.toUpperCase()+ " IS OUT IN STOCK", 4, NotificationType.NOTICE);
                System.out.println("Initial quantity of "+ gName +" "+ getEachProductQuantity(gName));
                System.out.println("DataAccess line 694 updated db= "+ (getEachProductQuantity(gName) - quantity));
                return false;
            }else {
                psUpdateQuantity.setInt(1, getEachProductQuantity(gName) - quantity);
            }

        }

        psUpdateQuantity.setString(2, gName);
        psUpdateQuantity.executeUpdate();
        return false;
    }

    public static void issueGoods(String gName, String rId, int quantitySold, float amt, String cName) throws SQLException {
        Connection connection;
        PreparedStatement psIssueGoods;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psIssueGoods = connection.prepareStatement("""
                INSERT INTO view_issued_goods VALUES(?, ?, ?, ?, ?, ?, ?)
                """);
        psIssueGoods.setString(1, getLoggedInUserId);
        psIssueGoods.setString(2, rId);
        psIssueGoods.setString(3, getGoodsId(gName)); // gets id from the product name provided
        psIssueGoods.setString(4, GetDatetime.todayDateTime());
        psIssueGoods.setInt(5, quantitySold);
        psIssueGoods.setFloat(6, amt);
        psIssueGoods.setString(7, cName);
        psIssueGoods.executeUpdate();
    }

    public static String getReceiptId(String rId) throws SQLException {
        Connection connection;
        PreparedStatement psGetReceiptId;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetReceiptId = connection.prepareStatement("""
                SELECT DISTINCT(receipt_id)\s
                FROM view_issued_goods
                WHERE receipt_id = ?
                """);
        psGetReceiptId.setString(1, rId);
        resultSet = psGetReceiptId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                receiptID = resultSet.getString("receipt_id");
            }
        }else{
            AlertNotification.trayNotification("INVALID RECEIPT ID", "PLEASE ENTER RECEIPT ID AGAIN", 4, NotificationType.NOTICE);

        }

        return receiptID;
    }

    public static boolean checkReceiptIdExist(String rId) throws SQLException {
        Connection connection;
        PreparedStatement psGetReceiptId;
        ResultSet resultSet;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetReceiptId = connection.prepareStatement("""
                SELECT DISTINCT(receipt_id)\s
                FROM view_issued_goods
                WHERE receipt_id = ?
                """);
        psGetReceiptId.setString(1, rId);
        resultSet = psGetReceiptId.executeQuery();
        return resultSet.isBeforeFirst();
    }
    public static ObservableList<String> receiptIds = FXCollections.observableArrayList();
    public static ObservableList<String> getReceiptIds() throws SQLException {
        Connection connection;
        PreparedStatement psGetReceiptId;
        ResultSet resultSet;
        String eachId;

        connection = DriverManager.getConnection(DBConstantConnection.root_URL,
                DBConstantConnection.user, DBConstantConnection.password);
        psGetReceiptId = connection.prepareStatement("""
                SELECT DISTINCT(receipt_id)\s
                FROM view_issued_goods
                """);
        resultSet = psGetReceiptId.executeQuery();
        if (resultSet.isBeforeFirst()){
            while (resultSet.next()){
                eachId = resultSet.getString("receipt_id");
                receiptIds.add(eachId);
            }
        }


        return receiptIds;
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
    public static ObservableList<VendorsInfo> getViewVendorsInfo() {
        return viewVendorsInfo;
    }

}
