package codeexamples.generics;

public class DemoGeneric2TypeClass<T extends Number, V extends CharSequence> {

	private T index;
	private V mantissa;

	public DemoGeneric2TypeClass(T tIndex, V vMantissa) {
		this.index = tIndex;
		this.mantissa = vMantissa;
	}

	public String toString() {
		String mantissaStr = this.mantissa.toString();
		return "DemoGeneric2TypeClass: " + mantissaStr;
	}

}
