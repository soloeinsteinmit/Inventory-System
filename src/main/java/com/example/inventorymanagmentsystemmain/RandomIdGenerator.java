package com.example.inventorymanagmentsystemmain;

import java.util.Random;

public class RandomIdGenerator {
    public static String id;

    public static String randomId(int minValue, int maxValue){
        Random acc_id = new Random();
        id = String.valueOf(acc_id.nextInt(maxValue - minValue + 1)+ minValue);
        return id;
    }
}
