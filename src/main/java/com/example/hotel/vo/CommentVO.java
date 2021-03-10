package com.example.hotel.vo;

/**
 * 用户评价的vo封装类，新增的
 * @author
 */
public class CommentVO {
    private int hotelId;
    private String userName;
    private String context;

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}
