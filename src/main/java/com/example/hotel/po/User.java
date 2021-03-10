package com.example.hotel.po;


import com.example.hotel.enums.UserType;

public class User {
    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private double credit;
    private UserType userType;
    /**
     * 新增3个用户得属性：性别、出生日期、身份证号码
     * @author: 胡子华
     * @date :2020.06.18
     */
    private String sexType;      //用户性别
    private String birth_date;   //用户出生日期
    private String idNumber;      //用户身份证号码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public void setIdNumber(String idNumber){
        this.idNumber=idNumber;
    }

    public String getIdNumber(){
        return this.idNumber;
    }
}
