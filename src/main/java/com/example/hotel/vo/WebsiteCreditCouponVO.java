package com.example.hotel.vo;

public class WebsiteCreditCouponVO extends CouponVO{
    private double discount=-1;
    private Integer discountMoney=-1;
    private Integer targetMoney=-1;
    private Integer hotelId;
    private Integer targetCredit;

    public void setTargetCredit(Integer targetCredit){
        this.targetCredit=targetCredit;
    }

    public Integer getTargetCredit(){
        return  targetCredit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }
}
