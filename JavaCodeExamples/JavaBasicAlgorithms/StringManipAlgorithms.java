package org.rezz0n8r.exercises.java.lang.strings;

import java.util.Arrays;

public class StringManipAlgorithms {
	
	public static void main(String[] args) {
		String stringToReverse = "redrum";
		String reversedStr = reverseStringWithoutJDK(stringToReverse);
		System.out.println("After calling the reverse... method, 'redrum' becomes: "+reversedStr);
		
		System.out.println("Now, testing the Anagrams-finding algorithm...");
		boolean areAnagrams = isTwoStringsAnagramOfEachOther("a gentleman", "elegant man");///should be true
		System.out.println("are 'a gentleman' and 'elegant man' anagrams of ea other?");
		System.out.println("The answer is: "+areAnagrams);
		
		//Test char-array sorting (ascending):
		String toSortStr = "zam3ertuqps";
		char[] sortedCharArr = sortCharArrayAlphabetically(toSortStr.toCharArray());
		String sortedAsStr = String.valueOf(sortedCharArr);
		System.out.println("After sorting, the char array becomes: "+sortedAsStr);
		
		//Test Comparable-types array-sorting:
		Float[] floatsArr = {
		  Float.valueOf(1.1f),
		  Float.valueOf(0f),
		  Float.valueOf(13.9f),
		  Float.valueOf(0.115f),
		  Float.valueOf(2.2f)
		};    
		
		sortComparableTypesArray(floatsArr);
		System.out.println("After passing to the sorting algorithm, the floats array is sorted to: ");
		System.out.println(Arrays.toString(floatsArr));
		
		///Test the Palindrom-detection algorithm:
		String candidatePalindrome = "redlight";
		String secondCandidate = "taco cat";
		System.out.println("Using our test method....");
		System.out.println("is "+candidatePalindrome+" a palindrome? :"+isThisStringAPalindrome(candidatePalindrome, false));
		System.out.println("is "+secondCandidate+" a palindrome? : "+isThisStringAPalindrome(secondCandidate, true));
	}
	
	
	public static String reverseStringWithoutJDK(String sourceStr) {
		if(sourceStr == null) {
			throw new IllegalArgumentException("null source string passed into method");
		}
		if(sourceStr.length() < 2) {
			return sourceStr;
		}
		
		char[] strChars = sourceStr.toCharArray();
		int start = 0;
		int end = strChars.length-1;
		
		while(start < end) {
			char temp = strChars[start];
			strChars[start]=strChars[end];
			strChars[end] = temp;
			start++;
			end--;
		}
	  //the char array is now reversed, so, stringify it:
	  return String.valueOf(strChars);
	}
	
	public static char[] sortCharArrayAlphabetically(char[] chars) {
		int i,j; //declare the loop vars
		int maxIndex = (chars.length -1);
		
		for(i = maxIndex; i>=0; i--) {
			for(j=1; j<= i; j++) {
				char charLeftOfCursor = chars[j-1];
				char cursorChar = chars[j];
				if(charLeftOfCursor > cursorChar) {
					//swap them, in the array:
					char temp = charLeftOfCursor;
					chars[j-1] = cursorChar;
					chars[j] = temp;
				}
			}
		}
	 return chars;
	}
	
	public static<T extends Comparable> T[] sortComparableTypesArray(T[] items) {
		if(items == null) {
			throw new IllegalArgumentException("Null items array passed.");
		}
		if(items.length < 2) {
			return items;
		}
		int i,j;
		int maxIndex = items.length - 1;
		for(i=maxIndex; i >=0; i--) {
			for(j=1; j <=i; j++ ) {
				T itemLeftOfCursor = items[j-1];
				T cursorItem = items[j];
				if(itemLeftOfCursor.compareTo(cursorItem) >= 0) {
					//the swap:
					T temp = itemLeftOfCursor;
					items[j-1] = cursorItem;
					items[j]=temp;
				}
			}
		}
     return items;		
	}
	
	
	public static boolean isTwoStringsAnagramOfEachOther(String first, String second) {
		if(first == null || second == null) {
			throw new IllegalArgumentException("null strings were passed to this method");
		}
		first= first.replaceAll("\\s", "");
		second = second.replaceAll("\\s", "");
		if(first.length() != second.length()) {
			return false;
		}
	  char[] firstChars = first.toLowerCase().toCharArray();
	  char[] secondChars = second.toLowerCase().toCharArray();
	  Arrays.sort(firstChars);
	  Arrays.sort(secondChars);
	  return Arrays.equals(firstChars, secondChars);
	}
	
	public static boolean isThisStringAPalindrome(String candidate, boolean ignoringWhiteSpaces) {
	  if(candidate == null || candidate.length() < 2) {
		  throw new IllegalArgumentException("invalid string passed-in: "+candidate);
	  }
	  if(ignoringWhiteSpaces) {
		  candidate = candidate.replaceAll("\\s", "");
	  }
	  char[] candidateAsChars = candidate.toCharArray();
	  int numChars = candidate.length();
	  char[] reversedChars = new char[candidateAsChars.length];
	  ///now, reverse the array of chars:
	  for(int j=0; j < numChars; j++) {
		  reversedChars[j] = candidateAsChars[numChars-1-j];
	  }
	  return Arrays.equals(candidateAsChars, reversedChars);	
	}

}
