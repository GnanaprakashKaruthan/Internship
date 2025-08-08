package com.library;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private String name;
    protected List<LibraryItem> borrowedItems = new ArrayList<>();

    public Person(String name, String string) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    // Each subclass can define its own role label
    public abstract String getRole();
}
