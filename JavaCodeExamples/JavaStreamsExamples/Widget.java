package codeexamples.streams;

import java.util.Random;

public class Widget {
	
	private static Random someRandom = new Random();
	
	
	private static int generateRandomId() {
		int min = 0;
		int max = 100000;
		return someRandom.nextInt((max - min)+min);
	}
	
	private int id;
	private String name;
	private int type;
	
	public Widget(String widgetNm, int wtype) {
		this.id = generateRandomId();
		this.name = widgetNm;
		this.type = wtype;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append(this.getClass().getSimpleName());
		buff.append(": "+this.id+", name: "+this.name+", type: "+this.type);
		return buff.toString();
	}

}
