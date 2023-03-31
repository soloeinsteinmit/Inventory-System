package com.example.inventorymanagmentsystemmain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDatetime {
    public static String todayDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        return dtf.format(currentDateTime);
    }

    public static String formatDate(String formatDate){
        LocalDate date = LocalDate.parse(extractDateFromDatetime(formatDate));
        return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu"));
    }

    public String todayDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        return dtf.format(currentDate);
    }

    public static String extractDateFromDatetime(String datetime){
        String[] d = datetime.split(" ");
        return d[0];
    }
}
