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

    /**
     * The <i><b>merge sort</b></i> recursive algorithm was used in sorting for vendor in the view vendors table
     * <p>The complexity of this algorithm is {@code O(nlog n)}</p>
     * @param array takes the array with which you wish to sort
     * */
    public static void mergeSort(int[] array){
        int length = array.length;
        if (length <= 1) return; // base case

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; // left array
        int j = 0; // right array

        for (; i < length; i++){
            if (i < middle){
                leftArray[i] = array[i];
            }else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }
    private static void merge(int[] leftArray, int[] rightArray, int[] array){
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0; // indices

        // check the conditions for merging
        while (l < leftSize && r < rightSize){
            if (leftArray[l] < rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            }else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while (l < leftSize){
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while (r < rightSize){
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

}
