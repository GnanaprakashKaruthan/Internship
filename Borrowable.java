package com.lib;

public interface Borrowable {
    void issue();
    void returnItem();
    boolean isIssued();
}
