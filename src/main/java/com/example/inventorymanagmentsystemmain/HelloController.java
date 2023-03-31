package com.example.inventorymanagmentsystemmain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    RegisterVendorController r = new RegisterVendorController();

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText(RandomIdGenerator.randomId(1, 3));
        for(Map.Entry<String, VendorsInfo> info : r.getVendorInfo().entrySet()){
            System.out.println(info.getKey());
            System.out.println(info.getValue().getName());
            System.out.println(info.getValue().getStatus());
            System.out.println(info.getValue().getGender());
            System.out.println(info.getValue().getTelephone_number());
            System.out.println(info.getValue().getDate_registered() + "\n\n");
        }
    }
    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r.getVendorInfo().put("12345", new VendorsInfo("Solo", "admin", "male", "01101", "2022-22-11"));
        r.getVendorInfo().put("54322", new VendorsInfo("Mon", "admin", "male", "5443", "2022-22-11"));
        r.getVendorInfo().put("21243", new VendorsInfo("Yue", "admin", "male", "2344", "2022-22-11"));
        r.getVendorInfo().put("1234", new VendorsInfo("In", "admin", "male", "23432", "2022-22-11"));
    }
}