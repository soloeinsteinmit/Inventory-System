module com.example.inventorymanagmentsystemmain {
    requires javafx.controls;
    requires javafx.fxml;
    requires TrayTester;
    requires mysql.connector.java;
    requires java.sql;
    requires MaterialFX;
    requires VirtualizedFX;
    requires com.jfoenix;


    opens com.example.inventorymanagmentsystemmain to javafx.fxml;
    exports com.example.inventorymanagmentsystemmain;
}