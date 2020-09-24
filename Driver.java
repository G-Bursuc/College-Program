/*
* File name : Driver.java
* Author : George Bursuc
* Student number : C18399946
* Description of class : Lets user store a list of between 1-10 students, sort the students by number if they want,
* 						 remove a student and remove students from a programme
*/

import java.util.Scanner;

public class Driver {
	Scanner scan = new Scanner(System.in);

	LinkedList<Student> list = new LinkedList<Student>(); // declares the linked list
	int size; // stores the size of the linked list

	public Driver() {
		inputSetup();
		printInfo();
		deleteAStudent();
		printInfo();

		if (size != 0) { // if user stored 1 student, list would be empty by now
			deleteProg();
			printInfo();
		}
	}

	// Sets up the input that user wants: how many students, sorted or not and then
	// input students
	public void inputSetup() {
		inputSize(); // get user input for how many students to store

		// ask user if they want to sort
		String ans;
		System.out.println("\nWould you like the students to be sorted or not? (yes/no)");
		do { // ensure user inputs a valid answer
			ans = scanS();
			if (!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no"))
				System.out.print("\nError - Please enter a valid answer (yes/no)");
		} while (!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no"));

		list.add(inputAStudent()); // put one student in list, doesn't matter if it is sorted or not. makes
									// comparing easier

		if (size > 1) { // if user chose to store more than 1 student
			// if user chose to sort the students
			if (ans.equalsIgnoreCase("yes")) {
				Student stu; // placeholder student to compare in list before putting in list
				boolean sorted; // keeps track if student was put into list

				for (int x = 0; x < (size - 1); x++) { // inputs students (user input - 1) times
					stu = inputAStudent();
					sorted = false;

					// compares current student against every student in list
					for (int i = 0; i < list.size(); i++) {
						if (stu.isLess(list.get(i))) { // if student number is lower than compared student in list
							list.add(stu, i); // add student to list in position i
							sorted = true; // student has been sorted and loop stops
							break;
						}
					}

					if (!sorted) // if student never got put into list, that means its the biggest number
						list.add(stu); // add student to end of list
				}
			} else { // non sorted linked list
				for (int x = 0; x < (size - 1); x++) { // input students regularly
					list.add(inputAStudent());
				}
			}
		}

	}

	// Input the number of students to be stored
	public void inputSize() {
		System.out.println("Please input the number of students you'd like to store (max 10 students)");
		// make sure user inputs less than 10 and more than 1
		do {
			size = scanI();
			if (size > 10)
				System.out.println("\nError - You cannot store more than 10 students.");
			else if (size < 1)
				System.out.println("\nError - Please enter a positive number.");
		} while (size > 10 || size < 1);
	}

	// Input the student details, makes sure there are no duplicates and stores into
	// linked list
	public Student inputAStudent() {
		String numb, name, code;
		int yoe;
		boolean dupe;
		System.out.println("\nInput student details...");
		System.out.println("Please input the student number: ");
		
		// verify that student number isn't already in list
		do {
			dupe = false;
			numb = scanS();

			// checks against existing students in linked list to ensure it's not a
			// duplicate
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(numb)) {
					System.out.println("/nError - This student has already been input.");
					dupe = true;
				}
			}
		} while (dupe);

		System.out.println("Please input the student name: ");
		name = scanS();
		System.out.println("Please input the students programme code: ");
		code = scanS();
		System.out.println("Please input the students year of entry: ");
		yoe = scanI();

		// create student and return it
		Student stu = new Student(numb, name, yoe, code);
		return stu;
	}

	// Deletes a student by using their number
	public void deleteAStudent() {
		System.out.println("Input a student number to delete");
		boolean invalid;
		String numb;
		Student myStudent = new Student("");
		// makes sure student exists in linked list
		do {
			invalid = false;
			numb = scanS();
			myStudent.setNumb(numb);
			if (!list.isDupe(myStudent)) {
				System.out.println("\nError - Student not found. Please enter an existing student number.");
				invalid = true;
			}
		} while (invalid);

		list.remove(myStudent);
		System.out.println("\nStudent has been removed");
		size--;

	}

	// User inputs a programme code, students in that programme will then be deleted
	public void deleteProg() {
		System.out.println("\nInput a programme to delete all students from: ");
		String prog = "";
		boolean invalid;
		// make sure programme code is valid
		do {
			invalid = false;
			try {
				prog = scanS();
				if (!prog.substring(0, 2).equalsIgnoreCase("DT")) {
					invalid = true;
					System.out.println("\nError - Please enter a valid course code");
				}
			} catch (Exception e) {
				invalid = true;
				System.out.println("\nError - Please enter a valid course code");
			}

		} while (invalid);

		int count = 0;
		for (int x = 0; x < list.size(); x++) {
			if (list.get(x).getCode().equalsIgnoreCase(prog)) {
				list.remove(list.get(x));
				count++;
				x--;
			}
		}
		if (count != 0)
			System.out.println("\n" + count + " students have been removed.");
		else
			System.out.println("\nNo Students were removed.");
		size = -count;
	}

	// Prints the toString() for the linked list
	public void printInfo() {
		System.out.println("\nStudent Details are: " + list.toString());
	}

	// Methods that make taking input from user easier
	public String scanS() { // input a string
		return scan.nextLine();
	}

	public int scanI() { // input an int
		int num = scan.nextInt();
		scan.nextLine();
		return num;
	}

	public double scanD() { // input a double
		double num = scan.nextDouble();
		scan.nextLine();
		return num;
	}

	// Main constructor
	public static void main(String[] args) {
		new Driver();
	}

}
