package com.example.inventorymanagmentsystemmain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for getting date and time from the local pc
 * */
public class GetDatetime {

    private static String time;

    /**Formats date to Year-Month-Day Hours:Minutes:Seconds*/
    public static String todayDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();

        return dtf.format(currentDateTime);
    }

    /** Formats date to Week, Month Day, Year*/
    public static String formatDate(String formatDate){
        LocalDate date = LocalDate.parse(extractDateFromDatetime(formatDate));
        return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu")) + " " + time;
    }

    /**Gets today's date*/
    public String todayDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();

        return dtf.format(currentDate);
    }

    /**Extract datetime to a particular date and time
     * <p>Week, Month Day, Year time</p>*/
    public static String extractDateFromDatetime(String datetime){
        String[] d = datetime.split(" ");
        time = d[1];
        return d[0];
    }
}
