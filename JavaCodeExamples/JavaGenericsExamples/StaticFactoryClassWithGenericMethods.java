package codeexamples.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaticFactoryClassWithGenericMethods {

	public class Node<K, V> {

		private K key;
		private V value;
		private List<Node<K, V>> children;

		public Node(K k, V v) {
			this.children = new ArrayList<>();
			this.key = k;
			this.value = v;
		}

		public void setKey(K theKey) {
			key = theKey;
		}

		public K getKey() {
			return key;
		}

		public void setValue(V val) {
			value = val;
		}

		public V getValue() {
			return value;
		}

		public List<Node<K, V>> getChildren() {
			return children;
		}

		public void addChild(Node<K, V> child) {
			children.add(child);
		}

		public List<Node<K, V>> getChildrenHavingKey(K key) {
			return children.stream().filter(node -> node.key.equals(key)).collect(Collectors.toList());
		}

		public List<Node<K, V>> getChildrenHavingValue(V val) {
			return children.stream().filter(node -> node.value.equals(val)).collect(Collectors.toList());
		}

	}

	/**
	 * This method accepts an Array of ANY type, as well as a Collection of that
	 * same Type. It will return a populated Collection of Said Type, from the array
	 * elements.
	 * 
	 * @param <T> The Erasure Type definition For this Method: only needs to be
	 *            declared w. placeholder Here Invoker do not have to Define this
	 *            type in their Call: it gets Inferred from the passed args.
	 * @param a   the Array of objects of type T
	 * @param c   the Collection of type T: empty at first, but To Be Populated:
	 */
	public static <T> void populateCollectionFromAnyArray(T[] a, Collection<T> c) {
		for (T o : a) {
			c.add(o); // Correct
		}
	}

	/**
	 * Useful simple method: pass it an Array of any (Object) type, it returns a
	 * typed List of same type, with all elements, in same order.
	 * 
	 * @param <T>
	 * @param a
	 * @return a typed List<T> of the same elements as array, in same order.
	 */
	public static <T> List<T> fromAnyArrayToSameTypedList(T[] a) {
		return Arrays.stream(a).collect(Collectors.toList());
	}

	/**
	 * Generic method: pass it varargs: one or more objects which all belong to a
	 * subclass of class Number. So: Integer, Float, Double, Byte, etc...any one of
	 * these types. a variable number of arguments can be passed.
	 * 
	 * This method sums them all up, and returns their sum as a double (primitive
	 * type)
	 * 
	 * @param <T>     the Type of the arguments: must be a subclass of class Number
	 * @param numbers : a variable-sized number of arguments (varargs), to be
	 *                summed. All args must be subclasses of class Number.
	 * @return
	 */
	public static <T extends Number> double sumNumbers(T... numbers) {
		double workingTotal = 0.0d;
		for (T number : numbers) {
			workingTotal += number.doubleValue();
		}
		return workingTotal;
	}

	/**
	 * Compares ANY 2 objects which implement Comparable. Uses polymorphism, so
	 * Differernt Types can be passed, as long as the 2 different args Also
	 * implement Comparable interface
	 * 
	 * @param first  the first value for comparison. Must implement Comparable
	 * @param second the second value for comparision. Must implement Comparable.
	 * @return
	 */
	public static int compareAny(Comparable first, Comparable second) {
		return first.compareTo(second);
	}

	/**
	 * For any one Type of object, which implements Comparable, you can pass the
	 * number, and pass a Range, and this method tests if the passed number is
	 * Inside said range. It returns true if it IS in range, false otherwise.
	 * 
	 * This method can be used for numbers, but also for Strings, Dates, or your own
	 * custom Type which implements Comparable.
	 * 
	 * @param <T>       the Type of the object to check, a Type implementing
	 *                  Comparable
	 * @param value     the value to check, of the declared type.
	 * @param minVal    the minValue of the Range to check, in that declared Type
	 * @param maxVal    the max value of the Range to check, in that delcared type.
	 * @param inclusive a boolean flag: indicates whether the passed Range is
	 *                  Inclusive, or Exclusive
	 * @return a boolean result: true if the value IS in range, false if not.
	 */
	public static <T extends Comparable> boolean numberIsInRange(T value, T minVal, T maxVal, boolean inclusive) {
		int againstMin = value.compareTo(minVal);
		int againstMax = value.compareTo(maxVal);
		boolean withinMin = ((inclusive && againstMin < 0) || (!inclusive && againstMin <= 0));
		boolean withinMax = ((inclusive && againstMax > 0) || ((!inclusive) && againstMax >= 0));
		return withinMin && withinMax;
	}

	public static <K, V> Optional<Node<K, V>> getChildByKeyIfExists(Node<K, V> searchNode, K key) {
		if (searchNode.getChildren().isEmpty()) {
			return null;
		}
		return searchNode.getChildrenHavingKey(key).stream().findFirst();
	}

}
