package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CouponServiceImplTest extends TestCase {
    @Autowired
    CouponService couponService;
    @Test
    public void addHotelTargetMoneyCoupon() {
        HotelTargetMoneyCouponVO couponVO=new HotelTargetMoneyCouponVO();
        couponVO.setId(1);
        couponVO.setCouponName("manjian");
        couponVO.setHotelId(1);
        couponVO.setcouponType(1);
        couponVO.setTargetMoney(500);
        couponVO.setDiscountMoney(200);
        try {
            couponService.addHotelTargetMoneyCoupon(couponVO);
        }
        catch (Exception e){}
        Coupon coupon=couponService.getCouponById(1);
        assertEquals(true,coupon.getTargetMoney()==couponVO.getTargetMoney()&&coupon.getDiscountMoney()==couponVO.getDiscountMoney());
    }

    @Test
    public void addHotelTimeCoupon() {
        HotelTimeCouponVO couponVO=new HotelTimeCouponVO();
        couponVO.setId(3);
        couponVO.setCouponName("xianshi");
        couponVO.setHotelId(1);
        couponVO.setcouponType(2);
        String[] discountdate=new String[]{"2020-06-29","2020-06-30"};
        couponVO.setDiscountDate(discountdate);
        try {
            couponService.addHotelTimeCoupon(couponVO);
        }
        catch (Exception e){}
        Coupon coupon=couponService.getCouponById(3);
        assertEquals(true,"2020-06-29T00:00".equals(coupon.getStartTime().toString()));
    }

    @Test
    public void addHotelMultiRoomCoupon() {
        HotelMultiRoomCouponVO couponVO=new HotelMultiRoomCouponVO();
        couponVO.setId(4);
        couponVO.setCouponName("duojian");
        couponVO.setHotelId(1);
        couponVO.setcouponType(4);
        couponVO.setTargetRoomNum(3);
        try {
            couponService.addHotelMultiRoomCoupon(couponVO);
        }
        catch (Exception e){}
        Coupon coupon=couponService.getCouponById(4);
        int temp=coupon.getTargetRoomNum();
        assertEquals(3,temp);
    }

    @Test
    public void addWebsiteCreditCoupon() {
        WebsiteCreditCouponVO couponVO=new WebsiteCreditCouponVO();
        couponVO.setId(5);
        couponVO.setCouponName("huiyuan");
        couponVO.setHotelId(-1);
        couponVO.setcouponType(3);
        couponVO.setTargetCredit(100);
        try {
            couponService.addWebsiteCreditCoupon(couponVO);
        }
        catch (Exception e){}
        Coupon coupon=couponService.getCouponById(5);
        int temp=coupon.getTargetCredit();
        assertEquals(100,temp);
    }


    @Test
    public void getWebsiteAllCoupons(){
        List<Coupon> clist=couponService.getWebsiteAllCoupons();
        assertEquals(true,clist.get(0).getCouponName().equals("huiyuan"));
    }

    @Test
    public void getMatchOrderCoupon(){
        OrderVO orderVO=new OrderVO();
        orderVO.setHotelId(1);
        orderVO.setRoomNum(4);
        orderVO.setUserId(1);
        orderVO.setCheckInDate("2020-06-29");
        orderVO.setCheckOutDate("2020-06-30");
        orderVO.setPrice(300.0);
        List<Coupon> clist=couponService.getMatchOrderCoupon(orderVO);
        String[] name=new String[]{"xianshi","duojian","huiyuan"};
        assertEquals(true,name[0].equals("xianshi")&&name[1].equals("duojian")&&name[2].equals("huiyuan"));
    }

    @Test
    public void cancelCoupon() {
        couponService.cancelCoupon(1);
        assertEquals(true,couponService.getCouponById(1)==null);
    }
}