package com.example.inventorymanagmentsystemmain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDatetime {
    public static String todayDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        return dtf.format(currentDateTime);
    }

    public String todatDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        return dtf.format(currentDate);
    }
}
