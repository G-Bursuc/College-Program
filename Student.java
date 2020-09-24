/*
* File name : Student.java
* Author : George Bursuc
* Student number : C18399946
* Description of class : Defines Student class
*/



public class Student {
	// Student class variables
	private String numb, name, code;
	private int yoe;

	// Student constructors
	public Student() { // empty
		this.numb = this.name = this.code = "";
		this.yoe = 0;
	}

	public Student(String numb) { // student with number only
		this.name = this.code = "";
		this.numb = numb;
		this.yoe = 0;
	}

	public Student(String n, String na, int y, String c) { // student with full details
		this.numb = n;
		this.name = na;
		
		// verify year
		if (y < 2013 || y > 2019) {
			this.yoe = 0000;
			System.out.println("\nInvalid year has been input, setting year of entry to: 0000");
		} else
			this.yoe = y;
		
		// verify course code, try-catch in case user doesn't input "DT"
		try {
			if (!c.substring(0, 2).equalsIgnoreCase("DT")) {
				this.code = "ERROR";
				System.out.println("\nProgramme code not recognizable, setting code to: ERROR");
			} else
				this.code = c;
		} catch (Exception e) {
			this.code = "ERROR";
			System.out.println("\nProgramme code not recognizable, setting code to: ERROR");
		}
	}

	// Setter and getter methods
	public String getNumb() {
		return numb;
	}

	public void setNumb(String numb) {
		this.numb = numb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getYoe() {
		return yoe;
	}

	public void setYoe(int yoe) {
		this.yoe = yoe;
	}

	// toString method
	public String toString() {
		return "Student Name: " + this.name + "\nStudent Number: " + this.numb + "\nProgramme Code: " + this.code
				+ "\nYear Of Entry: " + this.yoe;
	}

	// Check if a given student number matches this student number
	public boolean equals(String numb) {
		if (numb.equalsIgnoreCase(this.numb))
			return true;
		else
			return false;
	}

	// Check if a given student object's number matches this student number
	public boolean equals(Object stu) {
		Student myStu = (Student) stu;
		if (myStu.getNumb().equalsIgnoreCase(this.numb))
			return true;
		else
			return false;
	}

	// Compares and returns true if this student number is greater than a given
	// student number
	public boolean isGreater(Object myStu) {
		Student stu = (Student) myStu;

		if (this.numb.compareTo(stu.getNumb()) > 0)
			return true;
		else
			return false;
	}

	// Compares and returns true if this student number is less than a given student
	// number
	public boolean isLess(Object myStu) {
		Student stu = (Student) myStu;

		if (this.numb.compareTo(stu.getNumb()) < 0) {
			return true;
		} else
			return false;
	}
}
