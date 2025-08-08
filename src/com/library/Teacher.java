package com.library;

public class Teacher extends Person {

    /**
     * Constructs a new Teacher with the given name.
     *
     * @param name the teacher's name
     */
    public Teacher(String name, String role) {
        super(name, "Teacher");
    }

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Teacher";
	}
}
