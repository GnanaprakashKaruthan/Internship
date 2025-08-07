package com.lib;

public class User extends Person {
    public User(String name, String role) {
        super(name, role);
    }

    @Override
    public String getRole() {
        return "User";
    }
}
