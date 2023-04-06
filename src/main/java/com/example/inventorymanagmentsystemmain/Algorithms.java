package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author .py_ML_ai_MIT(Solomon Eshun):
 * <p>Class for writing algorithms
 * */
public class Algorithms {

    /**
     * <b>Linear search algorithm</b> was for searching for items in a list while retrieving items from
     * <p>the database to remove duplicate item from the list.
     * <p>Returns true if item is found in list. Hence, item would be skipped and not added to list and vice versa
     *
     * @param findString takes the string value you wish to look for in the provided arrayList.
     * @param searchArray takes the array you wish to search for the string value.
     * @implNote The linear search algorithm was used here. Its time complexity is 0(n)
     * */
    public static boolean linearSearch(String findString, ObservableList<String> searchArray){

        for (int i = 0; i <= searchArray.size()-1;){
            if (findString.equals(searchArray.get(i))){
                return true;
            }
            else {
                i++;
            }
        }
        return false;
    }

    private List<String> searchList(String searchClass, List<String> listOfClass) {
        List<String> searchClasses = Arrays.asList(searchClass.trim().split(" "));
        return listOfClass.stream().filter(input ->{
            return searchClasses.stream().allMatch(class_names -> input.toLowerCase().contains(class_names.toLowerCase()));
        }).collect(Collectors.toList());
    }


    /**
    * Using merge sort recursive algorithm for sorting of items
     * <p>Runs in O(n log n)
     * <p>This sorting algorithm has not been used yet in the application
    * */
    public static void mergeSort(ArrayList<Integer> array){
        int length = array.size();
        if (length <= 1) return; //base case

        int middle = length / 2;
        ArrayList<Integer> leftArray = new ArrayList<>(middle);
        ArrayList<Integer> rightArray = new ArrayList<>(length - middle);

        int i = 0;
        int j = 0;

        for (; i <length; i++){
            if (i < middle){
                leftArray.add(i, array.get(i));
            }else {
                rightArray.add(j, array.get(j));
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }
    public static void merge(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray, ArrayList<Integer> array){
        int leftSize = array.size() / 2;
        int rightSize = array.size() - leftSize;
        int i = 0, l = 0, r = 0;//indices

        // i = 0, leftsize=3 r=0 , rightsize=3
        // check conditions for merging
        while (l < leftSize && r < rightSize){
            if (leftArray.get(l) < rightArray.get(r)){
                array.add(i, leftArray.get(l));
                i++;
                l++;
            }else {
                array.add(i, rightArray.get(r));
                i++;
                r++;
            }
        }
        while (l < leftSize){
            array.add(i, leftArray.get(l));
            i++;
            l++;
        }
        while (r < rightSize){
            array.add(i, rightArray.get(r));
            i++;
            r++;
        }
    }
}
