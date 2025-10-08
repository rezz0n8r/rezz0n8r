package codeexamples.design.patterns.examples;

enum VehicleType {
	COUPE,SEDAN,SUV,WAGON,TRUCK
}

public class VehicleWithBuilder {
	
	private static VehicleWithBuilder instance;
	
	public static VehicleWithBuilder builder() {
		instance = new VehicleWithBuilder();
		return instance;
	}
	
	private String make;
	private String model;
	private int modelYear;
	private VehicleType type;
	private String color;
	private float totalWeight;
	private boolean isAutomatic;
	private boolean is4x4;
	
	private VehicleWithBuilder() {
		this.is4x4 = false;
		this.isAutomatic = false;
	}
	
	public VehicleWithBuilder make(String vMake) {
		this.make = vMake;
		return this;
	}
	
	public VehicleWithBuilder model(String vModel) {
		this.model = vModel;
		return this;
	}
	
	public VehicleWithBuilder modelYear(int year) {
		this.modelYear = year;
		return this;
	}
	
	public VehicleWithBuilder type(VehicleType vtype) {
		this.type = vtype;
		return this;
	}
	
	public VehicleWithBuilder color(String clr) {
		this.color = clr;
		return this;
	}
	
	public VehicleWithBuilder totalWeight(float weight) {
		this.totalWeight = weight;
		return this;
	}
	
	public VehicleWithBuilder isAutomatic(boolean flag) {
		this.isAutomatic = flag;
		return this;
	}
	
	public VehicleWithBuilder is4x4(boolean flag) {
		this.is4x4 = flag;
		return this;
	}
	
	public VehicleWithBuilder build() {
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder state = new StringBuilder();
		state.append("Vehicle: {");
		state.append(String.format(" make: %s  ,", make));
		state.append(String.format(" model: %s ,", model));
		state.append(String.format(" modelYear: %d ," , modelYear));
		state.append(String.format(" type: %s ,", type));
		state.append(String.format(" color: %s ,", color));
		state.append(String.format(" totalWeight: %f ,", totalWeight));
		String driveType = (is4x4)? "4WD":"2WD";
		String transType = (isAutomatic)? "auto":"manual";
		state.append(" drive: "+driveType);
		state.append(", transmission: "+transType);
		return state.toString();
	}

}
