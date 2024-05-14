package org.rezz0n8r.exercises.java.lang.sortingswapping;

import java.util.Arrays;

public class SomeExampleSortsAndReOrderings {
	
	public static void main(String[] args) {
		int alpha = 11;
		int beta = 33;
		int[] swapped = swapTwoNumbersWithoutUsingATempVar(alpha,beta);
		System.out.println("After swapping, the 2 numbers become: "+ Arrays.toString(swapped));
		int[] testArrReverse = {9,11,0};
		reverseArray(testArrReverse);
		System.out.println("After reversing, the arr becomes: ");
		System.out.println(Arrays.toString(testArrReverse));
	}
	
	
	public static int[] swapTwoNumbersWithoutUsingATempVar(int first, int second) {
		/*Swap without a temp var*/  
        first = first + second;   
        second = first - second;   
        first = first - second;  
        int[] retval = {first,second};
        return retval;
	}
	
	public static void reverseArray(int arr[])
	{
		if(arr == null) {
			throw new IllegalArgumentException("null arr");
		}
		if(arr.length == 1) {
			return;
		}
		int start = 0;
		int end = arr.length -1;
	    while (start < end)
	    {
	        int temp = arr[start]; 
	        arr[start] = arr[end];
	        arr[end] = temp;
	        start++;
	        end--;
	    } 
	}     

}
