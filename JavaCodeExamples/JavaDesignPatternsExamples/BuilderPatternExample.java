/*
 * @author: bsisk
 */
package codeexamples.design.patterns.examples;

/**
 * This class is Really just the Main-class Test, of a buildable class: class
 * VehicleWithBuilder This class' main method uses that class's Builder pattern
 * to build a Vehicle, and then to output info about it
 */
public class BuilderPatternExample {

	public static void main(String[] args) {
		/// We will build a Vehicle, using the class VehicleWithBuilder, also in this
		/// package.
		// Invoke the builder() method, on this class, and use the builder pattern to
		/// populate it:
		VehicleWithBuilder vehicle = VehicleWithBuilder.builder().make("Dodge").model("Challenger").modelYear(2024)
				.color("white").type(VehicleType.SEDAN).isAutomatic(true).is4x4(false).totalWeight(4210.00f).build();

		// when we call .build(), the Vehicle is instantiated with those properties, and
		// we get it returned.
		System.out.println("Built a vehicle using its Builder methods....");
		System.out.println("Here is the vehicle built: " + vehicle);
	}

}
