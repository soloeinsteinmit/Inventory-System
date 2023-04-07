package com.example.inventorymanagmentsystemmain;

import javafx.collections.ObservableList;

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
     * <p> The linear search algorithm was used here. Its time complexity is 0(n)
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

}
