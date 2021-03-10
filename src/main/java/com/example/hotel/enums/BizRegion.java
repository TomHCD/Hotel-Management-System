package com.example.hotel.enums;

public enum BizRegion {
    XiDan("西单"),
    DongDan("东单"),
    NanDan("南单"),
    BeiDan("北单");
    private String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
