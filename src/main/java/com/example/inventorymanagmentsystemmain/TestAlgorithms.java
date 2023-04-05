package com.example.inventorymanagmentsystemmain;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithms {
    public static void main(String[]args){

//        ArrayList<String> test = new ArrayList<>(List.of("a", "b", "c"));
//        System.out.println(Algorithms.linearSearch("d", test));
        ArrayList<Integer> sort = new ArrayList<>(List.of(5, 12, 5, 6, 2, 6, 1, 90, 23, 13, 54, 23));

        Algorithms.mergeSort(sort);
        System.out.println(sort);
    }
}
