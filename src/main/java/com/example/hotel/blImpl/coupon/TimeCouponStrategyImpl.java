package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     *
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getStartTime() == null && coupon.getEndTime() == null) {
            return false;
        }
        if (coupon.getCouponType() == 2 &&
                (orderVO.getHotelId().equals(coupon.getHotelId()) || orderVO.getHotelId().equals(-1))) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            LocalDateTime orderCheckInDate = LocalDateTime.parse(orderVO.getCheckInDate() + " 00-00-00", df);
            return (orderCheckInDate.isAfter(coupon.getStartTime()) && orderCheckInDate.isBefore(coupon.getEndTime())) || orderCheckInDate.isEqual(coupon.getStartTime()) || orderCheckInDate.isEqual(coupon.getEndTime());
        }
        return false;
    }
}
