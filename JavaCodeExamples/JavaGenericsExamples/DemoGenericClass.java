package codeexamples.generics;

public class DemoGenericClass<T> {
	// T is a type, which can accept any type such as
	// Integer, Double, String etc.
	private T obj;

	// Constructor which uses the parameter T to
	// return the object of the passed type
	DemoGenericClass(T obj) {
		this.obj = obj;
	}

	public T getObject() {
		return this.obj;
	}
}
