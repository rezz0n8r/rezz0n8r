package org.rezz0n8r.exercises.java.lang.designpatterns;

import java.awt.Color;

public class BuilderPatternExample {
	
	
	public static void main(String[] args) {
		//Example of how to use the below Vehicle class, which uses the "Builder" Pattern:
		BuilderPatternExample usage = new BuilderPatternExample();
		Vehicle whiteDodge = usage.getBuiltVehicle();
		System.out.println("White Dodge vehicle instantiated using its builder:");
		System.out.println(whiteDodge);
		
	}
	
	private Vehicle builderVehicle;
	
	public BuilderPatternExample() {
		builderVehicle = new Vehicle()
				.extColor(Color.WHITE)
				.intColor(Color.BLACK)
				.make("Dodge")
				.modelName("Challenger")
				.modelNum("JE21235I9329AO098")
				.modelYr(2021)
				.mileage(100000)
				.grossWeight(2200)
				.build();
	}
	
	public Vehicle getBuiltVehicle() {
		return this.builderVehicle;
	}
	
	
	//This Inner Class demonstrates the "Builder" Pattern
	class Vehicle{
		private String make;
		private String modelName;
		private String modelNum;
		private int modelYr;
		private int mileage;
		private float grossWeight;
	    private Color extColor;
	    private Color intColor;
		
		public Vehicle() {}
		
		public Vehicle build() {
			return this;
		}
		
		public Vehicle make(String theMake) {
			this.make = theMake;
			return this;
		}
		
		public Vehicle modelName(String model) {
			this.modelName = model;
			return this;
		}
		
		public Vehicle modelNum(String mn) {
			this.modelNum = mn;
			return this;
		}
		
		public Vehicle modelYr(int yr) {
			this.modelYr = yr;
			return this;
		}
		
		public Vehicle mileage(int miles) {
			this.mileage = miles;
			return this;
		}
		
		public Vehicle grossWeight(float weight) {
			this.grossWeight = weight;
			return this;
		}
		
		public Vehicle extColor(Color color) {
			this.extColor = color;
			return this;
		}
		
		public Vehicle intColor(Color color) {
			this.intColor = color;
			return this;
		}

		public String getMake() {
			return make;
		}

		public String getModelName() {
			return modelName;
		}

		public String getModelNum() {
			return modelNum;
		}

		public int getModelYr() {
			return modelYr;
		}

		public int getMileage() {
			return mileage;
		}

		public float getGrossWeight() {
			return grossWeight;
		}

		public Color getExtColor() {
			return extColor;
		}

		public Color getIntColor() {
			return intColor;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("Vehicle: [ ");
			sb.append(" "+this.modelYr);
			sb.append(" make: "+this.make);
			sb.append(", model: "+this.modelName);
			sb.append(", color: { ext: "+this.extColor);
			sb.append(", intColor: "+this.intColor+" }");
			sb.append(" ] ");
		  return sb.toString();
		}
		
	}

}
