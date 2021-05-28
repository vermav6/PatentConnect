package com.patentconnect.backend.db;

import java.util.ArrayList;
import java.util.List;
/**
 * A utility class to sort Strings for display
 * @author Yousef Al Hashemi
 */
public class MergeSort {
	
	/**
	 * Merge function for merge sort, takes in an array to mutate,
	 * a left index, a middle index, and a right index for the part to merge
	 * @param arr array to merge
	 * @param left left index of array to merge
	 * @param middle middle of the array to merge
	 * @param right right index of array to merge
	 */
	private static void merge(List<String> arr, int left, int middle, int right) {
		// size of sub-arrays:
		int size1 = middle - left + 1;
		int size2 = right - middle;
		
		// Auxiliary arrays:
		String[] auxLeft = new String[size1];
		String[] auxRight = new String[size2];
		
		// initialize values of the two halves:
		for (int i = 0; i < size1; i++) {
			auxLeft[i] = arr.get(left + i); 
		}
		
		for (int i = 0; i < size2; i++) {
			auxRight[i] = arr.get(middle + 1 + i); 
		}
		
		
		// merge the two auxiliary arrays
		
		int indLeft = 0;
		int indRight = 0;
		
		int indMain = left;
		
		while (indLeft < size1 && indRight < size2) {
			int comp = auxLeft[indLeft].compareTo(auxRight[indRight]); 
			if (comp <= 0) {
				arr.set(indMain, auxLeft[indLeft]);
				indLeft++;
			}else {
				arr.set(indMain, auxRight[indRight]);
				indRight++;
			}
			indMain++;
		}
		
		// copy leftover:
		while (indLeft < size1) {
			arr.set(indMain, auxLeft[indLeft]);
			indMain++;
			indLeft++;
		}
		
		while (indRight < size2) {
			arr.set(indMain, auxRight[indRight]);
			indMain++;
			indRight++;
		}
	}
	
	/**
	 * A method to sort a list of strings, takes the strings, and puts them in alphabetical order.
	 * @param arr an unsorted array of strings
	 * @return a sorted array of strings
	 */
	public static List<String> sort(List<String> arr){
		List<String> tempArr = new ArrayList<>(arr);
		sort(tempArr, 0, tempArr.size() - 1);
		return tempArr; 
	}
	
	/**
	 * A method to mutate an unsorted list of strings into a sorted one
	 * @param arr unsorted list to sort
	 * @param left starting index to sort
	 * @param right ending index to sort
	 */
	private static void sort(List<String> arr, int left, int right){
		if (left < right) {
			//get split point
			int middle = (left + right) / 2;
			
			sort(arr, left, middle);
			sort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	
}
