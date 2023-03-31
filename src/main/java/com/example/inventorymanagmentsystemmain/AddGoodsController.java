package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * @author .py_ML_ai_MIT(Solomon Eshun)
 * */
public class AddGoodsController implements Initializable {

    @FXML
    private MFXFilterComboBox<String> goodsCategoryComboBox;

    @FXML
    private MFXFilterComboBox<String> goodsName1;
    @FXML
    private MFXFilterComboBox<String> goodsCategoryComboBoxNew;

    @FXML
    private MFXButton addNewCategory;

    @FXML
    private MFXButton addNewGoods;
    @FXML
    private MFXButton refreshBtn;

    @FXML
    private MFXTextField newCategoryName;

    @FXML
    private MFXTextField newGoodsName;

    @FXML
    private MFXTextField grossPriceField;

    @FXML
    private MFXTextField quantityField;

    @FXML
    private MFXButton removeGoods;

    @FXML
    private MFXButton saveBtn;

    @FXML
    private MFXTextField sellingPriceField;

    @FXML
    private MFXTextField totalPriceField;

    @FXML
    private MFXButton addGoods;

    @FXML
    private MFXTextField buyingPrice;
    @FXML
    private ImageView refreshImg;
    @FXML
    private Label overStockingMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ADD ALL INITIAL SUM IN DATABASE TO ARRAY
        /*try {
//            GoodsCategoryDSChecker.addInitialSum();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        MyProfileController.goodsCategoryComboBox = goodsCategoryComboBox;
        grossPriceField.setEditable(false);
        TooltipClass.tooltipMessage("Refresh", refreshBtn);

        goodsCategoryComboBox.setOnAction(event -> {
            try {
                ItemCategoryData.categoryName = goodsCategoryComboBox.getValue();
                System.out.println("category = "+ goodsCategoryComboBox.getValue());
                goodsName1.getItems().clear();
                goodsName1.setItems(ItemCategoryData.goodsName());

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // adding goods to combo box
        try {
            goodsCategoryComboBox.setItems(ItemCategoryData.goodsCategoryItems());
            System.out.println("Items list =  "+ItemCategoryData.goodsCategoryItems());
            goodsCategoryComboBoxNew.setItems(ItemCategoryData.goodsCategoryItems());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Calculate gross price for each good.
     * */
    @FXML
    void calculateGross(MouseEvent event) {
        if (!buyingPrice.getText().isEmpty() && !quantityField.getText().isEmpty()){
            grossPriceField.setText(String.valueOf(Double.parseDouble(buyingPrice.getText()) *
                    Double.parseDouble(quantityField.getText())));
        }

    }
    private final Stack<ArrayList<String>> stackGoods = new Stack<>();
    private final Queue<ArrayList<String>> queueGoods = new LinkedList<>();
    private final ArrayList<ArrayList<String>> listGoods = new ArrayList<>();

    private final ArrayList<String> items = new ArrayList<>();
    private char addedCollection; // keeps track of the data structure which took the added goods
    private int itemsListSize;
    private final Stack<Character> trackItemAdded = new Stack<>(); // keeps track of goods added each time
    private static boolean isOverstocked = false; // keeps track of overstocked goods category to determine whether to clear fields or maintain inputs in fields
    private static int GET_ITEM_INDEX;


    // TODO: THERE IS A BUG IN CHECKING OF HIGH STOCK GOODS FOR EACH CATEGORY. ARRAYLIST SEEMS TO BE INCREASING IN SIZE INSTEAD OF MAINTAIN ITS ORIGINAL SIZE
    @FXML
    void addGoods(MouseEvent event) throws SQLException {
//        CURRENT_SUM_QUANTITY = DataAccess.getSumQuantity(goodsCategoryComboBox.getSelectedItem());

        items.add(0, goodsName1.getSelectedItem());
        System.out.println("good id = "+DataAccess.getGoodsId(goodsName1.getSelectedItem()));
        items.add(1, buyingPrice.getText());
        items.add(2, sellingPriceField.getText());
        items.add(3, grossPriceField.getText());
        items.add(4, quantityField.getText());
        items.add(5, GetDatetime.todayDateTime());


        System.out.println(itemsListSize+" size here");
        /*items.add(6, goodsCategoryComboBox.getSelectedItem());
        System.out.println("category id = "+DataAccess.getCategoryId(goodsCategoryComboBox.getSelectedItem()));*/


//        System.out.println("pushed items array = " + items);
//        System.out.println("before push = " + stackGoods);
        if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "s")){
//            GoodsCategoryDSChecker.stackGoodsCategory.contains(goodsCategoryComboBox.getSelectedItem())
            GET_ITEM_INDEX = Algorithms.getIndexOfItemFound(goodsCategoryComboBox.
                    getSelectedItem(), GoodsCategoryDSChecker.allCategory);

//            GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GET_ITEM_INDEX,
//                    GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.get(GET_ITEM_INDEX) + Integer.parseInt(quantityField.getText()));


//            if (DataAccess.isStockHigh(goodsCategoryComboBox.getSelectedItem()) ||
//                    GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.get(GET_ITEM_INDEX) > DataAccess.HIGH_STOCK_VALUE){
//                overStockingMessage.setVisible(true);
//                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());
//                isOverstocked = true;
//                System.out.println("HIGH STOCK");
//                AlertNotification.trayNotification("HIGH STOCK", "OVERSTOCKING "+
//                        goodsCategoryComboBox.getSelectedItem().toUpperCase() + " CATEGORY", 4, NotificationType.WARNING);
//            }else {
                stackGoods.push(new ArrayList<>(items));
                addedCollection = 's';
                trackItemAdded.push(addedCollection);
//            }
        } else if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "q")) {
            //GoodsCategoryDSChecker.queueGoodsCategory.contains(goodsCategoryComboBox.getSelectedItem())
            GET_ITEM_INDEX = Algorithms.getIndexOfItemFound(goodsCategoryComboBox.
                    getSelectedItem(), GoodsCategoryDSChecker.allCategory);

//            GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GET_ITEM_INDEX,
//                    GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.get(GET_ITEM_INDEX) + Integer.parseInt(quantityField.getText()));

            if (DataAccess.isStockHigh(goodsCategoryComboBox.getSelectedItem())){
                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());
                isOverstocked = true;
                System.out.println("HIGH STOCK");
                AlertNotification.trayNotification("HIGH STOCK", "OVERSTOCKING "+
                        goodsCategoryComboBox.getSelectedItem().toUpperCase() + " CATEGORY", 4, NotificationType.WARNING);
            }else {
                queueGoods.offer(new ArrayList<>(items));
                addedCollection = 'q';
                trackItemAdded.push(addedCollection);
            }
        } else if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "l")) {
            //GoodsCategoryDSChecker.arrayListGoodsCategory.contains(goodsCategoryComboBox.getSelectedItem())
            GET_ITEM_INDEX = Algorithms.getIndexOfItemFound(goodsCategoryComboBox.
                    getSelectedItem(), GoodsCategoryDSChecker.allCategory);

//            GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.add(GET_ITEM_INDEX,
//                    GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory.get(GET_ITEM_INDEX) + Integer.parseInt(quantityField.getText()));

            if (DataAccess.isStockHigh(goodsCategoryComboBox.getSelectedItem())){
                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());
                isOverstocked = true;
                System.out.println("HIGH STOCK");
                AlertNotification.trayNotification("HIGH STOCK", "OVERSTOCKING "+
                        goodsCategoryComboBox.getSelectedItem().toUpperCase() + " CATEGORY", 4, NotificationType.WARNING);
            }else {
                listGoods.add(new ArrayList<>(items));
                itemsListSize = listGoods.size();
                addedCollection = 'l';
                trackItemAdded.push(addedCollection);
            }
        }


        items.clear();
//        System.out.println("pushed items after array clear = " + items);
        System.out.println("pushed after = " + stackGoods);
        System.out.println("offered after = " + queueGoods);
        System.out.println("added after = " + listGoods);
        System.out.println("tracked items = " + trackItemAdded);
        System.out.println("overstocked = " + isOverstocked);
        System.out.println("Quantiyy = " + GoodsCategoryDSChecker.checkQuantityOfUnderEachCategory);

        if (!isOverstocked){
            goodsCategoryComboBox.clearSelection();
            goodsName1.clear();
            quantityField.clear();
            sellingPriceField.clear();
            buyingPrice.clear();
            grossPriceField.clear();
        }

    }

    @FXML
    void removeGoods(MouseEvent event) {
        if (trackItemAdded.isEmpty()){
            AlertNotification.trayNotification("ERROR", "NO ITEM(S) ADDED YET",
                    4, NotificationType.ERROR);
        }
        addedCollection = trackItemAdded.pop();
        System.out.println("popped track = " + addedCollection);
        if (addedCollection == 's'){
            stackGoods.pop();
            System.out.println("popped = " + stackGoods);
        } else if (addedCollection == 'q') {
            queueGoods.poll();
            System.out.println("polled = " + queueGoods);
        } else if (addedCollection == 'l') {
            listGoods.remove(listGoods.size()-1);
            System.out.println("removed = " + listGoods);
        }
    }
    //TODO: adding each item collected in stack to data base... error in loop
    @FXML
    void saveGoods(MouseEvent event) throws SQLException{

        while(!stackGoods.isEmpty()){
            System.out.println("print hererererererererer1");
            ArrayList<String> getEachGoodsDataStack = stackGoods.pop();
            System.out.println("popped data stack = " + getEachGoodsDataStack);
            DataAccess.addGoods(getEachGoodsDataStack.get(0), getEachGoodsDataStack.get(1), getEachGoodsDataStack.get(2),
                getEachGoodsDataStack.get(3), getEachGoodsDataStack.get(4), getEachGoodsDataStack.get(5));

        }

        while (!queueGoods.isEmpty()){
            System.out.println("print hererererererererer2");
            ArrayList<String> getEachGoodsDataQueue = queueGoods.poll();
            System.out.println("polled data queue = " + getEachGoodsDataQueue);
            assert getEachGoodsDataQueue != null;
                DataAccess.addGoods(getEachGoodsDataQueue.get(0), getEachGoodsDataQueue.get(1), getEachGoodsDataQueue.get(2),
                        getEachGoodsDataQueue.get(3), getEachGoodsDataQueue.get(4), getEachGoodsDataQueue.get(5));

        }
        while (!listGoods.isEmpty()){
            System.out.println("print hererererererererer");
            for (int i = 0; i < itemsListSize; i++){
                System.out.println("count value = "+ i);
                ArrayList<String> getEachGoodsDataList = listGoods.remove(i);
                System.out.println(getEachGoodsDataList);
                System.out.println("removed data list = " + getEachGoodsDataList);
                assert getEachGoodsDataList != null;
                DataAccess.addGoods(getEachGoodsDataList.get(0), getEachGoodsDataList.get(1), getEachGoodsDataList.get(2),
                        getEachGoodsDataList.get(3), getEachGoodsDataList.get(4), getEachGoodsDataList.get(5));

            }
        }

        if (stackGoods.isEmpty() && queueGoods.isEmpty() && listGoods.size() == 0){
            System.out.println("print hererererererererer3");
            AlertNotification.trayNotification("SUCCESS", "GOODS SUCCESSFULLY SAVED TO INVENTORY",
                    4, NotificationType.SUCCESS);
            trackItemAdded.clear();
            // set all item to zero
            GoodsCategoryDSChecker.setAllQuantityArrayElementZero();
        }


    }

    @FXML
    void addNewCategory(MouseEvent event) throws SQLException{
        DataAccess.addNewCategory(RandomIdGenerator.randomId(111, 999), newCategoryName.getText());
        AlertNotification.trayNotification("SUCCESS", "A NEW CATEGORY\n "+
                newCategoryName.getText().toUpperCase()+" HAS BEEN ADDED SUCCESSFULLY.", 4, NotificationType.NOTICE);
        newCategoryName.clear();


        String assignDSCheckerGroup = GoodsCategoryDSChecker.assignDS.get(Integer.parseInt(RandomIdGenerator.randomId(0, GoodsCategoryDSChecker.assignDS.size()-1)));
        switch (assignDSCheckerGroup) {
            case "s" -> GoodsCategoryDSChecker.stackGoodsCategory.add(newCategoryName.getText());
            case "q" -> GoodsCategoryDSChecker.queueGoodsCategory.add(newCategoryName.getText());
            case "l" -> GoodsCategoryDSChecker.arrayListGoodsCategory.add(newCategoryName.getText());
        }
    }

    @FXML
    void removeOverstockMessage(MouseEvent event){
        if (!quantityField.getText().isEmpty()){
            overStockingMessage.setVisible(false);
        }

    }
    @FXML
    void topUpQuantity(MouseEvent event)throws SQLException{
        if (!quantityField.getText().isEmpty() && !goodsName1.getText().isEmpty() && !goodsCategoryComboBox.getText().isEmpty()){
            DataAccess.updateQuantity(goodsName1.getSelectedItem(), Integer.parseInt(quantityField.getText()), 'a');
        }else {
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN THE GOODS NAME, CATEGORY NAME \nAND THE QUANTITY FIELD", 4, NotificationType.NOTICE);
        }

    }

    @FXML
    void addNewGoods(MouseEvent event) throws SQLException{
        DataAccess.addNewGoods(RandomIdGenerator.randomId(111, 999), newGoodsName.getText(), 0.00, 0.00,
                0.00, 0, GetDatetime.todayDateTime(), goodsCategoryComboBoxNew.getSelectedItem());
        AlertNotification.trayNotification("SUCCESS", "A NEW GOOD\n "+
                newGoodsName.getText().toUpperCase()+" HAS BEEN ADDED SUCCESSFULLY.", 4, NotificationType.NOTICE);
        newGoodsName.clear();
        goodsCategoryComboBoxNew.clearSelection();


    }

    @FXML
    void refresh(MouseEvent event) throws SQLException{
        goodsCategoryComboBoxNew.clear();
        goodsCategoryComboBox.clear();
        goodsName1.clear();

        goodsCategoryComboBoxNew.setItems(DataAccess.allItemCategory());
        goodsCategoryComboBox.setItems(DataAccess.allItemCategory());
        goodsName1.setItems(DataAccess.allGoodsName(goodsCategoryComboBoxNew.getSelectedItem()));

        MyAnimationsClass.rotateRotateRefreshImages(refreshImg);

        /*AlertNotification.trayNotification("REFRESH SUCCESSFUL",
                "GOODS NAME AND GOODS CATEGORY HAS BEEN UPDATED.", 5, NotificationType.NOTICE);*/
    }
}
