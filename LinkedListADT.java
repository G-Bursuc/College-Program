/*
* File name : LinkedListADT.java
* Author : George Bursuc
* Student number : C18399946
* Description of class : Contains the definitions for the Linked List
*/



public interface LinkedListADT<T> {
	// Adds one element to the start or end of this list
	public void add(T element);

	// Adds one element to the list according to given position
	public void add(T element, int pos);

	// Removes and returns the first element from this list
	public void remove(T element);

	// Finds a specific element in the list
	public T get(int pos);

	// Returns true if this list contains no elements
	public boolean isEmpty();

	// Returns the number of elements in this list
	public int size();

	// Returns the first element in the list
	public T getFirst();

	// Returns the last element in the list
	public T getLast();

	// Returns the next element in list according to given object
	public T getNext(T element);

	// Checks for a duplicate in the list
	public boolean isDupe(T element);

	// Returns a string representation of this list
	public String toString();
}
