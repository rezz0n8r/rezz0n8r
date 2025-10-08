package codeexamples.generics;

import java.util.List;

public class DemoGenericClassWithLowerBounded {
	
    public static int sumNumericTypesToLowerBoundedList(List<? super Number> numbers) {
    	numbers.add(Long.valueOf(32l));
    	numbers.add(Float.valueOf(4.5f));
    	numbers.add(Double.valueOf(21.12d));
    	numbers.add(Integer.valueOf(7));
    	double sum = 0.0d;
    	return numbers.size();
    }
}
