package com.lib;

public class Book extends LibraryItem {
    public Book(String title, String author) {
        super(title, author);
    }

    @Override
    public String getItemInfo() {
        String status = isIssued() ? "Issued" : "Available";
        return String.format("Book: %s by %s [%s]", getTitle(), getAuthor(), status);
    }
}
