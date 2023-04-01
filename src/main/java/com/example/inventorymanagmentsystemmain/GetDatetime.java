package com.example.inventorymanagmentsystemmain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetDatetime {

    private static String time;
    public static String todayDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        return dtf.format(currentDateTime);
    }

    public static String formatDate(String formatDate){
        LocalDate date = LocalDate.parse(extractDateFromDatetime(formatDate));
        return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu")) + " " + time;
    }

    /*public static String formatDate(String formatDate){
        LocalDate date = LocalDate.parse(formatDate);
        return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu HH:mm:ss"));
    }*/

    public String todayDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        return dtf.format(currentDate);
    }

    public static String extractDateFromDatetime(String datetime){
        String[] d = datetime.split(" ");
        time = d[1];
        return d[0];
    }
}
