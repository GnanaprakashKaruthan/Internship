package com.library;

public interface Borrowable {
    void issue();
    void returnItem();
    boolean isIssued();
}
