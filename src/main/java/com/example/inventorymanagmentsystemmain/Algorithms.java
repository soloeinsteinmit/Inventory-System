package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

import java.util.ArrayList;
/**
 * @author .py_ML_ai_MIT(Solomon Eshun):
 * @implNote A class for writing algorithms
 * */
public class Algorithms {
    /**
     * @param findString takes the string value you wish to look for in the provided arrayList.
     * @param searchArray takes the array you wish to search for the string value.
     * @implNote The linear search algorithm was used here. Its time complexity is 0(n)
     * */
    public static boolean linearSearch(String findString, ArrayList<String> searchArray){

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

    public static int getIndexOfItemFound(String findString, ArrayList<String> searchArray){

        for (int i = 0; i <= searchArray.size()-1;){
            if (findString.equals(searchArray.get(i))){
                return i;
            }
            else {
                i++;
            }
        }
        return -1;
    }
}
