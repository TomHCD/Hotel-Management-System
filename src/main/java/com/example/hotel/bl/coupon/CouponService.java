package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    CouponVO addHotelTimeCoupon(HotelTimeCouponVO couponVO);

    /**
     * 添加酒店多间优惠策略
     *
     * @param couponVO
     * @return
     */
    CouponVO addHotelMultiRoomCoupon(HotelMultiRoomCouponVO couponVO);

    CouponVO addWebsiteCreditCoupon (WebsiteCreditCouponVO websiteCreditCouponVO);

    List<Coupon> getWebsiteAllCoupons();

    ResponseVO cancelCoupon(int couponId);

    Coupon getCouponById(int couponId);
}
