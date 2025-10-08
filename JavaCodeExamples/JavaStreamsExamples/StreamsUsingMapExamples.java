package codeexamples.streams;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsUsingMapExamples {

	public static void main(String[] args) {
		List<String> intsAsStrs = Stream.of("1", "2", "3", "4", "5", "22", "19").collect(Collectors.toList());
		List<Integer> parsedIntsList = convertStringIntsListToIntsList(intsAsStrs);
		System.out.println("The parsed list of ints is: " + parsedIntsList);
		System.out.println("Iterating a Map via forEach and streams: ");
		iteratingAMapViaStreams();
		System.out.println();
		System.out.println("Now, sorting a Map by value, using streams...");
		HashMap<String, Double> unsortedMap = new HashMap<>();
		unsortedMap.put("three", 3.0d);
		unsortedMap.put("one", 1.0d);
		unsortedMap.put("ten", 10.0d);
		unsortedMap.put("two", 2.0d);
		unsortedMap.put("nine", 9.0d);
		LinkedHashMap<String, Double> valSortedOrder = sortAnUnsortedHashMapByValuesNotKeysUsingStreams(unsortedMap);
		System.out.println(valSortedOrder.toString());
	}

	public static List<Integer> convertStringIntsListToIntsList(List<String> intsAsStrs) {
		if (intsAsStrs == null) {
			throw new IllegalArgumentException();
		}
		List<Integer> intsList = intsAsStrs.stream().map(Integer::valueOf).collect(Collectors.toList());

		return intsList;
	}

	public static void iteratingAMapViaStreams() {
		Map<String, Number> codeNums = new HashMap<>();
		codeNums.put("first", 1);
		codeNums.put("second", 2.0f);
		codeNums.put("third", 3f);

		codeNums.forEach((k, v) -> {
			System.out.println("The key is: " + k + ", value: " + v);
		});
	}

	public static LinkedHashMap<String, Double> sortAnUnsortedHashMapByValuesNotKeysUsingStreams(
			HashMap<String, Double> unsortedMap) {

		if (unsortedMap.isEmpty()) {
			return new LinkedHashMap<>();
		}
		return unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
	}

}
