package org.rezz0n8r.exercises.java.lang.lambdas;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Lambdas {
	
	
	interface Arithmetic {
		int add(int a, int b);
	}
	
	class Widget{
		
		private String name;
		private double price;
		private double weight;
		
		public Widget(String nm, double wt) {
			this.name = nm;
			this.weight = wt;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}
		
		public String toString() {
			StringBuffer buff = new StringBuffer();
			buff.append("Widget: [");
			buff.append(" name: "+this.name);
			buff.append(", weight: "+this.weight);
			buff.append(", price: "+this.price);
			buff.append(" ]");
			return buff.toString();
		}
		
	}
	
	interface MyDateFormatter {
		
		public String getFormattedDate(Date date);
	}
	
	
	//example of passing a Lambda as a method arg:
	public static void main(String[] args) {
		Lambdas lambdas = new Lambdas();
		int sum = lambdas.doArithmeticOnNumbers((a,b)-> a+b, 2,2);
		System.out.println("Using this approach, the sum is: "+sum);
		lambdas.testLambdaReturningValue();
		lambdas.testInstantlyDefiningComparatorLogicUsingLambdas();
	}
	
	
	public int doArithmeticOnNumbers(Arithmetic math, int a, int b) {
		return math.add(a, b);
	}
	
	public void testInstantlyDefiningComparatorLogicUsingLambdas() {
		
		List<Widget> widgets = Arrays.asList(
			      new Widget("Flange", 9.93d), 
			      new Widget("Bushing", 1.09d),
			      new Widget("Hook",3.315d),
			      new Widget("Bearing",2.29d)
			    );
		
		//This is how we define the Comparator IN-LINE, inside the sort() call, using a lambda
		//This lamda comparator definition sorts alphabetically on the widget's name:
		widgets.sort( (widget1,widget2) -> widget1.getName().compareTo(widget2.getName()));
		System.out.println("Widgets sorted by Name:");
		System.out.println(Arrays.toString(widgets.toArray()));
		widgets.sort( (w1,w2) -> Double.compare(w1.getWeight(), w2.getWeight()) );
		System.out.println("Widgets sorted by weight, ascending:");
		System.out.println(Arrays.toString(widgets.toArray()));
		//
		// And, here is more complex sort, that first sorts by weight, and then by name:
		widgets.sort((w1,w2) -> {
			if(w1.getWeight() == w2.getWeight()) {
				return w1.getName().compareTo(w2.getName());
			}
			else {
				return Double.compare(w1.getWeight(), w2.getWeight());
			}
		}  );
		
		System.out.println("Widgets sorted by weight, then name:");
		System.out.println(Arrays.toString(widgets.toArray()));
	}
	
	public void testLambdaReturningValue() {
		String msg = "this is a supplier function";
		
		//How to store a lambda in a Variable, approach 4:
		Supplier<String> stringSupplier = () -> msg;
		//store it as the appropriate type of function
		String lambdaSuppliedStr = stringSupplier.get();
		if(!(lambdaSuppliedStr.equals(msg))) {
			System.err.println("Something went wrong! returned: "+lambdaSuppliedStr);
		}
		else {
			
			System.out.println("works as expected");
		}
		
		//Another example of using the built-in fxn interfaces to store a Lambda:
		// Use built-in, J2SE functional interface: BiPredicate<T,U> 
		// it takes 2 args of same or diff type, and returns a boolean
		BiPredicate<String,Integer> stringNumEquality = (numStringForm,intNumber) -> numStringForm.equals(Integer.toString(intNumber));
		boolean areEqual = stringNumEquality.test("1",1);
		System.out.println("Are those 2 equal, using your lambda? It returned: "+areEqual);
		
		//Example of how to store a Lambda in a Variable of type: Your Custom Interface:
		MyDateFormatter theLambda= (someDate) -> {
			if(someDate == null) {
				return "";
			}
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
			return formatter.format(someDate);
		};
		
		//the lambda is now stored, in variable "theLambda"
		//we can use it, like this:
		String lambdaFmttdDate = theLambda.getFormattedDate(new Date());
		System.out.println("Using the lambda stored in a Custom Interface variable, we get this:");
		System.out.println("Our lambda formats todays date as: "+lambdaFmttdDate);
		
	}

}
