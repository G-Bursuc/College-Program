/*
* File name : LinkedList.java
* Author : George Bursuc
* Student number : C18399946
* Description of class : Defines the linked list class
*/



public class LinkedList<T> implements LinkedListADT<T> {

	private int count; // the current number of elements in the list
	private LinearNode<T> first; // pointer to the first element
	private LinearNode<T> last; // pointer to the last element

	// Creates an empty list.
	public LinkedList() {
		this.count = 0;
		this.last = null;
		this.first = null;
	}

	// Adds one element to the end of this list
	public void add(T element) {
		LinearNode<T> newnode = new LinearNode<T>(element);

		if (size() == 0) { // if list is empty, set the new node first and last node
			this.last = newnode;
			this.first = newnode;
		} else { // list is not empty, set last node pointer to new node then set last to new
					// node
			this.last.setNext(newnode);
			this.last = newnode;
		}
		this.count++; // add one to the list size
	}

	// Adds an element to the list into given position
	public void add(T element, int pos) {
		if (pos == this.count) // if it's in the last position, call regular add method
			add(element);
		else { // must be first or in between first and last
			LinearNode<T> newnode = new LinearNode<T>(element); // create new node with given element

			// if it's the first position, set pointer of new node to first then set first
			// to new node
			if (pos == 0) {
				newnode.setNext(this.first);
				this.first = newnode;
			} else { // otherwise, position is somewhere in between first and last
				LinearNode<T> previous = this.first; // keeps track of previous node
				LinearNode<T> current; // keeps track of current node
				int counter = 1; // keeps track of current position in list. start at 1 because 0 was already
									// checked

				// for loop that starts at second position in list and iterates through every
				// node in list
				for (current = this.first.getNext(); current != null; current = current.getNext()) {
					if (counter == pos) // if the positions match, stop the loop. keep the states of current and
										// previous
						break;

					// if the positions don't match yet, add one to counter and set previous to
					// current node. current will be updated to it's next node in next iteration
					counter++;
					previous = current;
				}
				// eventually positions match, new node pointer is set to current node and
				// prevous node pointer is set to new node
				newnode.setNext(current);
				previous.setNext(newnode);
			}
			this.count++; // add one to list size
		}
	}

	// Removes specified element from list
	public void remove(T element) {
		// if the matching element is first in the list, set first next node pointer to
		// first
		if (this.first.getElement().equals(element)) {
			this.first = this.first.getNext();
		} else { // else it is somewhere else in list
			LinearNode<T> current = null; // keeps track of current element
			LinearNode<T> node; // used to iterate through the elements in list
			LinearNode<T> previous = this.first; // keep track of previous element, starting with first element in list

			// start at second position in list and iterate through every node in list
			for (node = this.first.getNext(); node != null; node = node.getNext()) {
				// update current to current node selected in list
				current = node;

				// if current node matches given element, stop loop. save the states of previous
				// and current
				if (current.getElement().equals(element))
					break;

				// if nodes didn't match, set previous to current
				previous = current;
			}

			// if current's next node isn't null (i.e. not last in list), set previous node
			// pointer to current's next node
			if (current.getNext() != null)
				previous.setNext(current.getNext());
			else { // else it must be the last node in list, set previous node pointer to null and
					// set last to previous node
				previous.setNext(null);
				this.last = previous;
			}
		}

		// remove one from list size
		this.count--;
	}

	// Returns a specified element within list using a number for the position
	// of element
	public T get(int pos) {
		LinearNode<T> node = this.first; // assume its the first in list, loop will not run if pos == 0

		// goes through each node in list until x reaches pos
		for (int x = 0; x < pos; x++) {
			node = node.getNext();
		}
		return node.getElement();
	}

	// Returns true if this list contains no items
	public boolean isEmpty() {
		if (this.first == null)
			return true;
		else
			return false;
	}

	// Returns the number of elements in this list
	public int size() {
		return this.count;
	}

	// Returns the first element in the list
	public T getFirst() {
		return this.first.getElement();
	}

	// Returns the last element in the list
	public T getLast() {
		return this.last.getElement();
	}

	// Returns the next element in list according to given element
	public T getNext(T element) {
		// iterates through every node in list until it matches element
		for (LinearNode<T> current = this.first; current != null; current = current.getNext()) {
			// if element matches, return current next node's element
			if (current.getElement().equals(element))
				return current.getNext().getElement();
		}
		return null; // if element was not found
	}

	// Checks for a duplicate in the list with given element
	public boolean isDupe(T element) {
		// iterates through every node in list until it matches element
		for (LinearNode<T> current = this.first; current != null; current = current.getNext()) {
			// if element matches an element in list, return true
			if (current.getElement().equals(element)) {
				return true;
			}
		}
		return false;
	}

	// Returns a string representation of this list
	public String toString() {
		if (this.count == 0) // display message if list is empty
			return "\nList is empty";
		else { // iterates through every node in list and stores details of every nodes' element in a string
			String fullList = "";

			for (LinearNode<T> current = this.first; current != null; current = current.getNext()) {
				fullList = fullList + "\n" + current.getElement().toString() + "\n";
			}
			return fullList + "\n";
		}
	}

}
