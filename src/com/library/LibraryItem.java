package com.library;

public abstract class LibraryItem implements Borrowable {
    private String title;
    private String author;
    private boolean issued;
    
    public LibraryItem(String title, String author) {
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean isIssued() {
        return issued;
    }

    @Override
    public void issue() {
        this.issued = true;
    }

    @Override
    public void returnItem() {
        this.issued = false;
    }

    // Each subclass must provide its own display format
    public abstract String getItemInfo();
}
