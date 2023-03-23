package com.example.inventorymanagmentsystemmain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDatetime {
    public static String todayDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        return dtf.format(currentDateTime);
    }
}
