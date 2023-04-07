package com.example.inventorymanagmentsystemmain;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithms {
    public static void main(String[]args){

//        ArrayList<String> test = new ArrayList<>(List.of("a", "b", "c"));
//        System.out.println(Algorithms.linearSearch("d", test));
        ArrayList<Integer> sort = new ArrayList<>(List.of(12, 5, 6, 2, 1, 90, 23, 13, 54, 23));

        int[] array = {9, 2, 1, 5, 7};
        int[] sort1 = new int[sort.size()];
        for (int i = 0; i < sort.size(); i++){
            sort1[i] = sort.get(i);
        }
        System.out.println(sort1);
        Algorithms.mergeSort(sort1);

        for (int j : sort1) {
            System.out.print(j + " ");
        }
    }
}
