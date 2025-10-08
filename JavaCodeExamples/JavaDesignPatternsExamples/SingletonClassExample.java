/*
 * @author: bsisk
 */
package codeexamples.design.patterns.examples;

public class SingletonClassExample {

	private static SingletonClassExample instance;

	public static SingletonClassExample getInstance(String key) {
		if (instance == null) {
			instance = new SingletonClassExample(key);
		}
		return instance;
	}

	private String id;

	private SingletonClassExample(String key) {
		this.id = key;
	}

	public String getId() {
		return this.id;
	}

	public boolean isIdNumericOnly() {
		if (this.id == null) {
			return false;
		}
		return this.id.matches("^\\d+$");
	}

	public boolean isIdAlphaNumericOnly() {
		if (this.id == null) {
			return false;
		}
		return this.id.matches("^\\w+$");
	}

}
