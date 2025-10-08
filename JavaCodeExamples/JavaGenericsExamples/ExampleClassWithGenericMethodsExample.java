package codeexamples.generics;

import java.io.InputStream;
import java.util.List;

public class ExampleClassWithGenericMethodsExample {

	private String data;
	private int dataWordCount;
	private InputStream theStream;

	public ExampleClassWithGenericMethodsExample() {
		this.data = "Hello World";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getDataWordCount() {
		return dataWordCount;
	}

	public void setDataWordCount(int dataWordCount) {
		this.dataWordCount = dataWordCount;
	}

	public <T> void setGenericData(T datum) {
		this.data = datum.toString();
		String[] dataWords = this.data.split("\\s+");
		this.dataWordCount = dataWords.length;
	}

	public <D, C extends Number> void setGenericDataAndCount(D datum, C countNum) {
		this.data = datum.toString();
		this.dataWordCount = countNum.intValue();
	}

	// This example Generic Method shows how to Upper-Bound a Type with 2 Types:
	public <C extends Number & Comparable> void setDataWordCount(C wordCount) {
		this.dataWordCount = wordCount.intValue();
	}

	// This example Generic Method shows how to define the Type-bounding of a
	// passed-in Generic argument
	public void pickFromDataStreams(List<? extends InputStream> dataStreams) {
		for (InputStream dataStream : dataStreams) {
			if (dataStream.markSupported()) {
				this.theStream = dataStream;
				break;
			}
		}
		return;
	}

}
