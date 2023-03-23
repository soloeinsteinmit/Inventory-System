package com.example.inventorymanagmentsystemmain;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithms {
    public static void main(String[]args){

        ArrayList<String> test = new ArrayList<>(List.of("a", "b", "c"));
        System.out.println(Algorithms.linearSearch("d", test));
    }
}
