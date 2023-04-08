package com.naver.webnovel.purchase;

public enum PurchaseStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    REFUNDED("refunded");

    private final String value;

    private PurchaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
