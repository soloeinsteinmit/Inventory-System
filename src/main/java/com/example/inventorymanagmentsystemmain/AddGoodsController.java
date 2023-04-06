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
    private MFXTextField totalProfitField;

    @FXML
    private MFXTextField totalSellingField;

    @FXML
    private MFXFilterComboBox<String> goodsCategoryComboBox;

    @FXML
    private MFXFilterComboBox<String> goodsName1;

    @FXML
    private MFXFilterComboBox<String> removeGoodComboBox;
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


    /**
     * {@code initialize} method runs every code in it as soon as the class is invoked
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        quantityField.setOnMouseClicked(event -> {
            if (overStockingMessage.isVisible()){
                overStockingMessage.setVisible(false);
            }
        });

        // Adding goods category to combo box
        try {
            removeGoodComboBox.setItems(ItemCategoryData.allProducts());
            goodsCategoryComboBox.setItems(ItemCategoryData.goodsCategoryItems());
            goodsCategoryComboBoxNew.setItems(ItemCategoryData.goodsCategoryItems());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <p>Calculate total cosr price for each good.
     * <p>The calculation is done by simply clicking the <i>cost price field</i> in the application.
     *
     * */
    @FXML
    void calculateGross(MouseEvent event) {
        if (!buyingPrice.getText().isEmpty() && !quantityField.getText().isEmpty()){
            grossPriceField.setText(String.valueOf(Double.parseDouble(buyingPrice.getText()) *
                    Double.parseDouble(quantityField.getText())));
        }

    }

    /**Stores stack goods*/
    private final Stack<ArrayList<String>> stackGoods = new Stack<>();

    /**Stores queue goods*/
    private final Queue<ArrayList<String>> queueGoods = new LinkedList<>();

    /**Stores list goods*/
    private final List<ArrayList<String>> listGoods = new ArrayList<>();

    /**Stores each good data into various data structure*/
    private final ArrayList<String> items = new ArrayList<>();
    private char addedCollection; // keeps track of the data structure which took the added goods
    private int itemsListSize;
    /**
     * trackItemAdded stack keeps track of goods added each time
     * for removal of a goods from their various data structure
     * */
    private final Stack<Character> trackItemAdded = new Stack<>();


    /**
     * {@code addGoods} for adding of goods to their various data structure
     * <p>This is the code that runs behind the <i>ADD GOODS<i/> button in the app
     * */
    @FXML
    void addGoods(MouseEvent event) throws SQLException {

        items.add(0, goodsName1.getSelectedItem()); // goods name
        items.add(1, buyingPrice.getText()); // stores buying price
        items.add(2, sellingPriceField.getText()); // stores selling price
        items.add(3, totalProfitField.getText()); // stores total profit
        items.add(4, quantityField.getText()); // stores good quantity
        items.add(5, GetDatetime.todayDateTime()); // stores datetime of when good was added

        /*checks from database if good should be added in stack*/
        if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "s")){
            if (DataAccess.updateQuantity(goodsName1.getSelectedItem(), Integer.parseInt(quantityField.getText()),
                    'a')){
                overStockingMessage.setVisible(false);
                stackGoods.push(new ArrayList<>(items));
                addedCollection = 's';
                trackItemAdded.push(addedCollection);

                  }else {

                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());


            }


        /*checks from database if good should be added in queue*/
        } else if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "q")) {

            if (DataAccess.updateQuantity(goodsName1.getSelectedItem(), Integer.parseInt(quantityField.getText()),
                    'a')){

                overStockingMessage.setVisible(false);
                queueGoods.offer(new ArrayList<>(items));
                addedCollection = 'q';
                trackItemAdded.push(addedCollection);
                   }else {


                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());

            }

            /*checks from database if good should be added in list*/
        } else if (Objects.equals(DataAccess.getDataStructure(goodsCategoryComboBox.getSelectedItem()), "l")) {
            if (DataAccess.updateQuantity(goodsName1.getSelectedItem(), Integer.parseInt(quantityField.getText()),
                    'a')){
                overStockingMessage.setVisible(false);
                listGoods.add(new ArrayList<>(items));
                itemsListSize = listGoods.size();
                addedCollection = 'l';
                trackItemAdded.push(addedCollection);

                       }else {

                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());


            }
        }


        items.clear();

        /*clear fields once a good has been added successfully*/
        goodsCategoryComboBox.clearSelection();
        goodsName1.clear();
        quantityField.clear();
        sellingPriceField.clear();
        buyingPrice.clear();
        grossPriceField.clear();
        totalSellingField.clear();
        totalProfitField.clear();


    }

    /**
     * {@code removeGoods} helps to remove a goods from added goods
     * <p>This is the code that runs behind the <i>REMOVE GOODS<i/> button in the app
     **/
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

    /**
     * {@code saveGoods} methods save all goods from each data structure into database.
     * <p>This is the code behind the <i>SAVE</i> button in the application</p>
     * */
    @FXML
    void saveGoods(MouseEvent event) throws SQLException{
        if (stackGoods.isEmpty() && queueGoods.isEmpty() && listGoods.isEmpty()){
            AlertNotification.trayNotification("ERROR", "PLEASE ADD GOODS TO THE INVENTORY",
                    4, NotificationType.SUCCESS);
        }else {

            /*
             * Removes all items from stack into database
             * */
            while(!stackGoods.isEmpty()){
                ArrayList<String> getEachGoodsDataStack = stackGoods.pop();
                DataAccess.addGoods(getEachGoodsDataStack.get(0), getEachGoodsDataStack.get(1), getEachGoodsDataStack.get(2),
                        getEachGoodsDataStack.get(3), getEachGoodsDataStack.get(4), getEachGoodsDataStack.get(5), overStockingMessage);
            }

            /*Removes all items from queue into database*/
            while (!queueGoods.isEmpty()){
                ArrayList<String> getEachGoodsDataQueue = queueGoods.poll();
                assert getEachGoodsDataQueue != null;
                DataAccess.addGoods(getEachGoodsDataQueue.get(0), getEachGoodsDataQueue.get(1), getEachGoodsDataQueue.get(2),
                        getEachGoodsDataQueue.get(3), getEachGoodsDataQueue.get(4), getEachGoodsDataQueue.get(5), overStockingMessage);

            }
            /*Removes all items from list into database*/
            while (!listGoods.isEmpty()){
                for (int i = 0; i < itemsListSize; i++){
                    System.out.println("count value = "+ i);
                    ArrayList<String> getEachGoodsDataList = listGoods.remove(i);

                    assert getEachGoodsDataList != null;

                    DataAccess.addGoods(getEachGoodsDataList.get(0), getEachGoodsDataList.get(1), getEachGoodsDataList.get(2),
                            getEachGoodsDataList.get(3), getEachGoodsDataList.get(4), getEachGoodsDataList.get(5), overStockingMessage);

                }
            }

            if (stackGoods.isEmpty() && queueGoods.isEmpty() && listGoods.size() == 0){

                /*Clear all fields goods has been saved into database*/
                goodsCategoryComboBox.clearSelection();
                goodsName1.clear();
                quantityField.clear();
                sellingPriceField.clear();
                buyingPrice.clear();
                grossPriceField.clear();
                totalSellingField.clear();
                totalProfitField.clear();

                AlertNotification.trayNotification("SUCCESS", "GOODS SUCCESSFULLY SAVED TO INVENTORY",
                        4, NotificationType.SUCCESS);
                trackItemAdded.clear();
            }
        }

    }

    /**
     * {@code addNewCategory} method adds new good category into database
     * <p>This is the code behind the <i>ADD NEW CATEGORY TO INVENTORY</i> button in the application</p>
    * */
    @FXML
    void addNewCategory(MouseEvent event) throws SQLException{
        String assignDSCheckerGroup = GoodsCategoryDSChecker.assignDS.get(Integer.parseInt(RandomIdGenerator.randomId(0, GoodsCategoryDSChecker.assignDS.size()-1)));
        switch (assignDSCheckerGroup) {
            case "s" -> GoodsCategoryDSChecker.stackGoodsCategory.add(newCategoryName.getText());
            case "q" -> GoodsCategoryDSChecker.queueGoodsCategory.add(newCategoryName.getText());
            case "l" -> GoodsCategoryDSChecker.arrayListGoodsCategory.add(newCategoryName.getText());
        }

        DataAccess.addNewCategory(RandomIdGenerator.randomId(111, 999), newCategoryName.getText(), assignDSCheckerGroup);
        AlertNotification.trayNotification("SUCCESS", "A NEW CATEGORY\n "+
                newCategoryName.getText().toUpperCase()+" HAS BEEN ADDED SUCCESSFULLY.", 4, NotificationType.NOTICE);
        newCategoryName.clear();



    }

    @FXML
    void removeOverstockMessage(MouseEvent event){
        if (!quantityField.getText().isEmpty()){
            overStockingMessage.setVisible(false);
        }

    }
    /**
     * {@code topUpQuantity} tops up quantity of good by selecting the good and it category
     * */
    @FXML
    void topUpQuantity(MouseEvent event)throws SQLException{
        if (!quantityField.getText().isEmpty() && !goodsName1.getText().isEmpty() && !goodsCategoryComboBox.getText().isEmpty()){
            if (DataAccess.updateQuantity(goodsName1.getSelectedItem(), Integer.parseInt(quantityField.getText()),
                    'a')){

                overStockingMessage.setVisible(false);
             }else {
                overStockingMessage.setVisible(true);
                overStockingMessage.setText("OVERSTOCKING "+ goodsCategoryComboBox.getSelectedItem().toUpperCase());



            }
        }else {
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN THE GOODS NAME, CATEGORY NAME \nAND THE QUANTITY FIELD", 4, NotificationType.NOTICE);
        }

    }

    /**
     * {@code addNewGoods} add new goods into inventory
     * */
    @FXML
    void addNewGoods(MouseEvent event) throws SQLException{
        DataAccess.addNewGoods(RandomIdGenerator.randomId(111, 999), newGoodsName.getText(), 0.00, 0.00,
                0.00, 0, GetDatetime.todayDateTime(), goodsCategoryComboBoxNew.getSelectedItem());
        AlertNotification.trayNotification("SUCCESS", "A NEW GOOD\n "+
                newGoodsName.getText().toUpperCase()+" HAS BEEN ADDED SUCCESSFULLY.", 4, NotificationType.NOTICE);
        newGoodsName.clear();
        goodsCategoryComboBoxNew.clearSelection();


    }

    /**
     * Refresh item in various combo box's once and update has been made
     * */
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


    /**
     * <p>Calculate total selling price for each good.
     * <p>The calculation is done by simply clicking the <i>selling price field</i> in the application.
     *
     * */
    @FXML
    void calculateTotalSellingPrice(MouseEvent event){
        if (!quantityField.getText().isEmpty() && !sellingPriceField.getText().isEmpty()){
            float totalSellingPrice = (Float.parseFloat(sellingPriceField.getText()) * Integer.parseInt(quantityField.getText()));
            totalSellingField.setText(String.valueOf(totalSellingPrice));
        }
    }


    /**
     * <p>Calculate total profit for each good.
     * <p>The calculation is done by simply clicking the <i>total profit field</i> in the application.
     *
     * */
    @FXML
    void calculateTotalProfit(MouseEvent event){
        if (!totalSellingField.getText().isEmpty() && !grossPriceField.getText().isEmpty()){
            float profit = Float.parseFloat(totalSellingField.getText()) - Float.parseFloat(grossPriceField.getText());
            totalProfitField.setText(String.valueOf(profit));
        }
    }

    @FXML
    void removeGoodFromIventory(MouseEvent event){

    }
}
