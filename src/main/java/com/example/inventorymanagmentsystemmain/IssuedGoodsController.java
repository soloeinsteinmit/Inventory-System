package com.example.inventorymanagmentsystemmain;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tray.notification.NotificationType;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class IssuedGoodsController implements Initializable {

    @FXML
    private MFXButton addToCartBtn;

    @FXML
    private MFXFilterComboBox<String> goodsNameComboBox;

    @FXML
    private MFXPaginatedTableView<IssueGoodsInfo> issuedProductsTableView;

    @FXML
    private Label noOfGoodsLabel;

    @FXML
    private MFXTextField priceField;

    @FXML
    private MFXButton proceedBtn;

    @FXML
    private MFXTableColumn<IssueGoodsInfo> productColumn;

    @FXML
    private MFXTableColumn<IssueGoodsInfo> quantityColumn;

    @FXML
    private MFXTextField quantityField;

    @FXML
    private MFXButton removeFromCartBtn;

    @FXML
    private MFXTextField clientNameField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private MFXTableColumn<IssueGoodsInfo> unitPriceColumn;
    @FXML
    private Label processingGoodsLabel;
    @FXML
    private ImageView refreshingImage;
    @FXML
    private ImageView refreshImg;
    private static int NUMBER_OF_CARTS = 0;
    public static boolean carts = true; // variable name will change

    private Map<String, Integer> productSales = new HashMap<String, Integer>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        removeFromCartBtn.setDisable(issuedProductsTableView.getItems().isEmpty());
        totalPriceLabel.setText("0");
        // sets up table
        setUpViewVendorsTable();
        try {
            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));
            goodsNameComboBox.setItems(ItemCategoryData.allProducts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpViewVendorsTable(){
        productColumn.setComparator(Comparator.comparing(IssueGoodsInfo::getProduct));
        quantityColumn.setComparator(Comparator.comparing(IssueGoodsInfo::getQuantity));
        unitPriceColumn.setComparator(Comparator.comparing(IssueGoodsInfo::getUnitPrice));

        productColumn.setRowCellFactory(issueGoodsInfo -> new MFXTableRowCell<>(IssueGoodsInfo::getProduct));
        quantityColumn.setRowCellFactory(issueGoodsInfo -> new MFXTableRowCell<>(IssueGoodsInfo::getQuantity));
        unitPriceColumn.setRowCellFactory(issueGoodsInfo -> new MFXTableRowCell<>(IssueGoodsInfo::getUnitPrice));

        issuedProductsTableView.getTableColumns().addAll(productColumn, quantityColumn, unitPriceColumn);
        issuedProductsTableView.getFilters().addAll(
                new StringFilter<>("PRODUCT NAME", IssueGoodsInfo::getProduct)

        );

    }

    public static List<IssueGoodsInfo> productsToBeRemovedList = new ArrayList<>();
    public static ArrayList<String> eachProductsDetails = new ArrayList<>();
    public static Queue<ArrayList<String>> addToCartProductsQueue = new LinkedList<>();
    float totalPrice = 0;
    @FXML
    void addToCart(MouseEvent event) {
        if (!goodsNameComboBox.getText().isEmpty() && !quantityField.getText().isEmpty() && !priceField.getText().isEmpty()){
            removeFromCartBtn.setDisable(false);
            NUMBER_OF_CARTS++;
            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));

            eachProductsDetails.add(0, goodsNameComboBox.getSelectedItem());
            eachProductsDetails.add(1, quantityField.getText());

            float amount = Integer.parseInt(eachProductsDetails.get(1)) * Float.parseFloat(priceField.getText());

            eachProductsDetails.add(2, String.format("%.02f",amount));

            addToCartProductsQueue.offer(new ArrayList<>(eachProductsDetails));
            System.out.println("issued goods here 111 = "+ addToCartProductsQueue);

            IssueGoodsInfoList.issuedGoods = issuedProductsTableView.getItems(); // you this line erhhh i will never forget you
            IssueGoodsInfoList.issuedGoods.add(new IssueGoodsInfo(eachProductsDetails.get(0), Integer.parseInt(eachProductsDetails.get(1)), Float.parseFloat(String.format("%.02f",amount))));
            issuedProductsTableView.setItems(IssueGoodsInfoList.issuedGoods);



            totalPrice += amount;

            totalPriceLabel.setText(String.format("%.02f", totalPrice));

            priceField.clear();
            quantityField.clear();

            eachProductsDetails.clear();


        }else {
            AlertNotification.trayNotification("ERROR", "PLEASE FILL IN ALL TEXT FIELDS", 4, NotificationType.ERROR);
        }


    }
    float amountRemoved;
    @FXML
    void removeFromCart(MouseEvent event){
        if (Integer.parseInt(noOfGoodsLabel.getText())  == 0){
            NUMBER_OF_CARTS = 0;
            AlertNotification.trayNotification("EMPTY CART", "CANNOT REMOVE ANY PRODUCT FROM CART", 4, NotificationType.WARNING);

            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));
        }else {
            productsToBeRemovedList = issuedProductsTableView.getSelectionModel().getSelectedValues();
            for (IssueGoodsInfo issueGoodsInfo : productsToBeRemovedList) {
                issuedProductsTableView.getItems().remove(issueGoodsInfo);
                amountRemoved = issueGoodsInfo.getUnitPrice();
            }

//            removeFromCartBtn.setDisable(issuedProductsTableView.getItems().isEmpty());
            if (issuedProductsTableView.getItems().isEmpty()){
                removeFromCartBtn.setDisable(true);
            }

            totalPrice -= amountRemoved;
            totalPriceLabel.setText(String.format("%.02f", totalPrice));
            NUMBER_OF_CARTS -= productsToBeRemovedList.size();

            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));
        }

    }

    @FXML
    void proceed(MouseEvent event) throws SQLException{
        carts = false;
        if (NUMBER_OF_CARTS == 0){
            AlertNotification.trayNotification("EMPTY CART", "CANNOT PROCESS ANY CART", 4, NotificationType.WARNING);

        }else {

            // TODO: CODE HERE
            if (!issuedProductsTableView.getItems().isEmpty()){
                removeFromCartBtn.setDisable(false);
            }else{
                removeFromCartBtn.setDisable(true);
            }

            processingGoodsLabel.setVisible(true);
            refreshingImage.setVisible(true);
            if (clientNameField.getText().isEmpty()){
                AlertNotification.trayNotification("ERROR PROCESSING GOODS", "PLEASE CLIENT NAME", 4, NotificationType.WARNING);
            }
            processingGoodsLabel.setText("Processing carts...");
            MyAnimationsClass.refreshIssueGoods(refreshingImage, "CARTS HAS BEEN PROCESSED SUCCESSFULLY", processingGoodsLabel);
            final String receiptId = RandomIdGenerator.randomId(11111, 99999);
            while (!addToCartProductsQueue.isEmpty()){
                ArrayList<String> products = addToCartProductsQueue.poll();
                assert products != null;
                DataAccess.issueGoods(products.get(0), receiptId, Integer.parseInt(products.get(1)), Float.parseFloat(products.get(2)), clientNameField.getText());
                DataAccess.updateQuantity(products.get(0), Integer.parseInt(products.get(1)), 'i'); // subtract initial quantity in db from currently issued
                //TODO: HAVE TO WRITE SOME IF STATEMENT HERE


                productSales.put(DataAccess.getGoodsId(products.get(0)), Integer.valueOf(products.get(1)));
            }
            System.out.println("\nTracking product sales...");
            for (Map.Entry<String, Integer> pIdQuantity : productSales.entrySet()){
                System.out.println("[key(productId):"+pIdQuantity.getKey() + " = value(quantity): " + pIdQuantity.getValue()+"]");
            }


            NUMBER_OF_CARTS = 0;

            issuedProductsTableView.getItems().clear();
            clientNameField.clear();
            totalPriceLabel.setText("0");
            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));
        }

    }

    @FXML
    void clearCarts(MouseEvent event){
        carts = false;
        if (NUMBER_OF_CARTS == 0){
            AlertNotification.trayNotification("EMPTY CART", "CANNOT CLEAR CART", 4, NotificationType.WARNING);

        }else {
            removeFromCartBtn.setDisable(true);
            NUMBER_OF_CARTS = 0;

            processingGoodsLabel.setVisible(true);
            refreshingImage.setVisible(true);

            processingGoodsLabel.setText("Clearing carts...");
            MyAnimationsClass.refreshIssueGoods(refreshingImage, "CARTS HAS BEEN CLEARED SUCCESSFULLY", processingGoodsLabel);
            issuedProductsTableView.getItems().clear(); // clears table
            addToCartProductsQueue.clear(); // clear queue
            totalPriceLabel.setText("0");
            noOfGoodsLabel.setText(String.valueOf(NUMBER_OF_CARTS));
        }


    }

    @FXML
    void getSellingPrice(ActionEvent event) throws SQLException{
        if (!goodsNameComboBox.getText().isEmpty()){
           priceField.setText(String.valueOf(DataAccess.getSellingPriceOfProduct(goodsNameComboBox.getValue())));
//            priceField.setText(String.valueOf(DataAccess.getSellingPriceOfProduct(goodsNameComboBox.getSelectedItem())));
        }
    }

    @FXML
    void refreshViewGoodsTable(MouseEvent event)throws SQLException{
        goodsNameComboBox.clear();
        goodsNameComboBox.setItems(DataAccess.getAllGods());

        MyAnimationsClass.rotateRotateRefreshImages(refreshImg);

    }
}
