package codeexamples.streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples {

	public static List<Widget> getAllWidgets() {
		return Arrays.asList(new Widget("ghost", 12), new Widget("vampire", 3), new Widget("werewolf", 9),
				new Widget("leprechaun", 7));
	}
	

	public static void main(String[] args) {
		convertABunchOfStringIntsToAListOfInts();
		List<String> extractedWidgetNames = exampleExtractObjectPropValsListFromObjectList();
		System.out.println("Extracted WidgetNames: " + extractedWidgetNames);
		List<Widget> allWidgetsList = getAllWidgets();
		System.out.println("Before stream-filtering, the all-widgets list is: " + allWidgetsList);
		List<Widget> filteredList = objectFilteringByLambdaReferencingOutsideMethod(allWidgetsList);
		System.out.println("After filtering, the widgets list becomes: " + filteredList);
		System.out.println("");
		System.out.println("Using an example of filtering....");

		Stream<Widget> widgetsStream = howToConvertExistingTypesArrayIntoTypedStream();
		List<Widget> unfilteredWidgetsList = widgetsStream.collect(Collectors.toList());
		List<Widget> filteredWidgets = howToUseStreamsToFilterAListOfTypesByCondition(unfilteredWidgetsList);
		System.out.println("After filtering, the filtered Widgets are: ");
		System.out.println(filteredWidgets);

	}

	public static Stream<Integer> howToConvertDiscreteValuesListToAStream() {
		return Stream.of(11, 3, 0, -2, -4, 21);
	}

	public static Stream<Integer> howToConvertExistingIntArrayToAStream() {
		Integer[] arrayOfIntObjs = { Integer.valueOf(2), Integer.valueOf(11), Integer.valueOf(33) };
		Stream<Integer> intObjsStream = Stream.of(arrayOfIntObjs);
		return intObjsStream;
	}

	public static List<Object> howToConvertObjStreamToListOfObjs(Stream<Object> objStream) {
		return objStream.collect(Collectors.toList());
	}

	public static Object[] howToConvertObjStreamToObjArray(Stream<Object> objStream) {
		return objStream.toArray(Object[]::new);
	}

	public static List<Widget> howToUseStreamsToFilterAListOfTypesByCondition(List<Widget> unfilteredWidgets) {
		List<Widget> filteredWidgets = unfilteredWidgets.stream().filter(w -> w.getType() == 2)
				.collect(Collectors.toList());
		return filteredWidgets;
	}

	public static Stream<Widget> howToConvertExistingTypesArrayIntoTypedStream() {
		Widget[] widgets = { new Widget("Flange", 2), new Widget("Bracket", 3), new Widget("Hook", 9),
				new Widget("Bussle", 11) };
		return Stream.of(widgets);
	}

	public static IntStream howToConvertAStringToStreamOfCharsAsInts() {
		String food = "This is a String";
		return food.chars();
	}

	public static void convertABunchOfStringIntsToAListOfInts() {
		IntStream parsedIntStream = Stream.of("2", "12", "11", "0").mapToInt(Integer::parseInt);
		Stream<Integer> boxedIntAsStrm = parsedIntStream.boxed();
		List<Integer> parsedList = boxedIntAsStrm.collect(Collectors.toList());
		System.out.println("Created a list of Integer boxed objs: " + parsedList);
	}

	public static List<String> exampleExtractObjectPropValsListFromObjectList() {
		List<Widget> widgetsList = Arrays.asList(new Widget("hook", 1), new Widget("flange", 0),
				new Widget("bevel", 3));

		List<String> widgetNames = widgetsList.stream().map(w -> w.getName()).collect(Collectors.toList());
		return widgetNames;
	}

	public static Set<Integer> exampleExtractPrimitivePropValListFromWidgetsList(List<Widget> widgets) {
		Set<Integer> extractedWidgetTypes = widgets.stream().map(w -> w.getType()).collect(Collectors.toSet());
		return extractedWidgetTypes;
	}

	public static List<Widget> objectFilteringByCriteria(List<Widget> unfilteredWidgets) {
		if (unfilteredWidgets == null) {
			return Collections.<Widget>emptyList();
		}
		List<Widget> filteredWidgets = unfilteredWidgets.stream().filter(widget -> widget.getType() > 0)
				.collect(Collectors.toList());
		return filteredWidgets;
	}

	public static List<Widget> objectFilteringByLambdaReferencingOutsideMethod(List<Widget> unfilteredWidgets) {
		// When passing in a Filtering Method reference, the method must take 1 arg of
		// the Stream-Type, and return a boolean for it: true to include, false to
		// exclude
		return unfilteredWidgets.stream().filter(StreamExamples::filterWidgetsByName).collect(Collectors.toList());

	}

	public static boolean filterWidgetsByName(Widget candidate) {
		return candidate.getName().startsWith("v");
	}

	public static void variousExamplesOfSortingStreams() {
		// when employing the default, built-in comparison (here: Alpha), just use
		// .sorted() in between:
		Stream<String> unsortedStrings = Stream.of("first", "alpha", "beta", "second", "zulu");
		List<String> sortedStrs = unsortedStrings.sorted().collect(Collectors.toList());

		// to supply a specific comparator, supply it in sorted() call:
		Comparator<String> mySpecialComparator = new StreamExamples.LastLetterComparator();
		List<String> specialtySortedList = unsortedStrings.sorted(mySpecialComparator).collect(Collectors.toList());
		System.out.println("Using the specialty sorter, the sorted List is: ");
		System.out.println(specialtySortedList);

		// to sort in reverse-order (Strings as example):
		List<String> reverseSortedListStrs = unsortedStrings.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("The reverse-sorted list of Strings is this: ");
		System.out.println(reverseSortedListStrs);
	}

	static class LastLetterComparator implements Comparator<String> {
		public int compare(String s1, String s2) {
			if (s1 == null) {
				return -1;
			}
			if (s2 == null) {
				return 1;
			}
			char finalOf1 = s1.charAt(s1.length() - 1);
			char finalOf2 = s2.charAt(s2.length() - 1);
			String finalLetter1 = "" + finalOf1;
			String finalLetter2 = "" + finalOf2;
			return finalLetter1.toLowerCase().compareTo(finalLetter2.toLowerCase());
		}
	}
}
