package com.example.hotel.vo;

/**
 * @Author: chenyizong
 * @Date: 2020-03-03
 */
public class UserInfoVO {
    private String password;
    private String userName;
    private String phoneNumber;
    private String email;
    /**
     * 新增四个用户得属性：性别、VIP等级、余额、出生日期
     * @author: 胡子华
     * @date :2020.06.18
     */
    private String sexType;      //用户性别
    private Integer vip_level;    //VIP等级
    private double  balance;     //用户账号钱余额
    private String birth_date;   //用户出生日期
    private String idNumber;    //用户身份证号码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//新增

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public String getSexType(){
        return this.sexType;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBirth_date(){
        return this.birth_date;
    }

    public void setVip_level(Integer vip_level) {
        this.vip_level = vip_level;
    }

    public Integer getVip_level(){
        return this.vip_level;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void reduceBalance(double money){
        this.balance=this.balance-money;
    }

    public void addBalance(double money){
        this.balance=this.balance+money;
    }

    public void setIdNumber(String idNumber){
        this.idNumber=idNumber;
    }

    public String getIdNumber(){
        return this.idNumber;
    }

}
