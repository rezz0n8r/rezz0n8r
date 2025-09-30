package org.rezz0n8r.exercises.java.lang.streams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsUsingMapExamples {
	
	public static void main(String[] args) {
		List<String> intsAsStrs = Stream.of("1","2","3","4","5","22","19").collect(Collectors.toList());
		List<Integer> parsedIntsList = convertStringIntsListToIntsList(intsAsStrs);
		System.out.println("The parsed list of ints is: "+parsedIntsList);
		System.out.println("Iterating a Map via forEach and streams: ");
		iteratingAMapViaStreams();
	}
	
	public static List<Integer> convertStringIntsListToIntsList(List<String> intsAsStrs){
		if(intsAsStrs == null) {
			throw new IllegalArgumentException();
		}
		List<Integer> intsList = intsAsStrs.stream()
		.map(Integer::valueOf)
		.collect(Collectors.toList());
	
	  return intsList;
	}
	
	public static void iteratingAMapViaStreams() {
		Map<String,Number> codeNums = new HashMap<>();
		codeNums.put("first", 1);
		codeNums.put("second", 2.0f);
		codeNums.put("third",3f);
		
		codeNums.forEach((k,v)-> {
			System.out.println("The key is: "+k+", value: "+v);
		});
		
	}

}
