package com.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<LibraryItem> items = new ArrayList<>();
    private List<Person> members = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    /**
     * Registers a new member (Student or Teacher) in the library.
     *
     * @param name the member's name
     * @param role either "Student" or "Teacher"
     * @return true if registration succeeded; false otherwise
     */
    public boolean registerMember(String name, String role) {
        Person newMember;
        
        if ("Student".equalsIgnoreCase(role)) {
            newMember = new Student(name, role);
        }
        else if ("Teacher".equalsIgnoreCase(role)) {
            newMember = new Teacher(name, role);
        }
        else {
            System.out.printf("Registration failed: invalid role \"%s\".%n", role);
            System.out.println("Valid roles are: Student, Teacher.");
            return false;
        }

        // 3. Add to library and confirm
        members.add(newMember);
        System.out.printf("Registration successful: %s %s added.%n",
                          newMember.getRole(), newMember.getName());
        return true;
    }

    // Reuse your existing helper
    public Person findMember(String name) {
        return members.stream()
                      .filter(m -> m.getName().equalsIgnoreCase(name))
                      .findFirst()
                      .orElse(null);
}

    // List all items not currently issued
    public void listAvailableItems() {
        System.out.println("\nAvailable Items:");
        List<LibraryItem> available = items.stream()
            .filter(item -> !item.isIssued())
            .collect(Collectors.toList());

        for (int i = 0; i < available.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, available.get(i).getItemInfo());
        }
    }

    // List all items borrowed by a given member
    public int listBorrowedItems(String memberName) {
        Person member = findMember(memberName);
        if (member == null) {
            System.out.println("Member not found.");
            return 0;
        }

        List<LibraryItem> borrowed = member.getBorrowedItems();
        if (borrowed.isEmpty()) {
            System.out.printf("%n%s has no books borrowed.%n", member.getName());
            return 0;
        }

        System.out.printf("%n%s’s Borrowed Items:%n", member.getName());
        for (int i = 0; i < borrowed.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, borrowed.get(i).getItemInfo());
        }
        return borrowed.size();
    }

    public boolean issueItem(int index, String memberName) {
        Person member = findMember(memberName);
        if (member == null) {
            System.out.println("Issue failed: member not found.");
            return false;
        }

        List<LibraryItem> available = items.stream()
            .filter(item -> !item.isIssued())
            .collect(Collectors.toList());

        if (index < 1 || index > available.size()) {
            System.out.println("Issue failed: invalid item selection.");
            return false;
        }

        LibraryItem item = available.get(index - 1);
        item.issue();
        member.borrowItem(item);
        System.out.printf("Issued: %s to %s %s%n",
            item.getTitle(), member.getRole(), member.getName());
        return true;
    }

    public boolean returnItem(int index, String memberName) {
        Person member = findMember(memberName);
        if (member == null) {
            System.out.println("Return failed: member not found.");
            return false;
        }

        List<LibraryItem> borrowed = member.getBorrowedItems();
        if (index < 1 || index > borrowed.size()) {
            System.out.println("Return failed: invalid item selection.");
            return false;
        }

        LibraryItem item = borrowed.get(index - 1);
        item.returnItem();
        member.returnItem(item);
        System.out.printf("Returned: %s by %s %s%n",
            item.getTitle(), member.getRole(), member.getName());
        return true;
    }

    public void showAllItems() {
        System.out.println("\nLibrary Inventory:");
        items.forEach(item -> System.out.println(item.getItemInfo()));
    }
    
    public void showAllMembers() {
        System.out.println("\nRegistered Members:");
        
        if (members.isEmpty()) {
            System.out.println("No members have been added yet.");
            return;
        }
        
        for (int i = 0; i < members.size(); i++) {
            Person m = members.get(i);
            System.out.printf("%d. %s (%s)%n", 
                              i + 1, 
                              m.getName(), 
                              m.getRole());
        }
    }
}
