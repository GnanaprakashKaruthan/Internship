package com.lib;

public class Student extends Person {

    /**
     * Constructs a new Student with the given name.
     *
     * @param name the student's name
     */
    public Student(String name, String role) {
        super(name, "Student");
    }

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Student";
	}
}

