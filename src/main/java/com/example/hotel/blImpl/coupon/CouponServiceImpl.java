package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final WebsiteCreditCouponStrategyImpl websiteCreditCouponStrategy;
    private final  TimeCouponStrategyImpl timeCouponStrategy;
    private  final MultiRoomsCouponStrategyimpl multiRoomsCouponStrategy;
    private final CouponMapper couponMapper;
    private static String COUPON_DONT_EXIST = "优惠不存在";
    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             MultiRoomsCouponStrategyimpl multiRoomsCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,WebsiteCreditCouponStrategyImpl websiteCreditCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.websiteCreditCouponStrategy=websiteCreditCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.multiRoomsCouponStrategy=multiRoomsCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(websiteCreditCouponStrategy);
        strategyList.add(multiRoomsCouponStrategy);
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> websiteCoupons = getWebsiteAllCoupons();

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        for (int i = 0; i < websiteCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, websiteCoupons.get(i))) {
                    availAbleCoupons.add(websiteCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO, coupon);
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        System.out.println(coupon.getTargetMoney());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTimeCoupon(HotelTimeCouponVO couponVO) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO, coupon);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        coupon.setStartTime(LocalDateTime.parse(couponVO.getDiscountDate()[0] + " 00:00:00", formatter));
        coupon.setEndTime(LocalDateTime.parse(couponVO.getDiscountDate()[1] + " 00:00:00", formatter));
        if (couponVO.getDiscount() > 0) {
            coupon.setDiscount(couponVO.getDiscount());
        } else {
            coupon.setTargetMoney(couponVO.getTargetMoney());
            coupon.setDiscountMoney(couponVO.getDiscountMoney());
        }

        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelMultiRoomCoupon(HotelMultiRoomCouponVO couponVO) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO, coupon);
        coupon.setTargetRoomNum(couponVO.getTargetRoomNum());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addWebsiteCreditCoupon(WebsiteCreditCouponVO couponVO) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO, coupon);
        coupon.setTargetCredit(couponVO.getTargetCredit());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public List<Coupon> getWebsiteAllCoupons() {
        List<Coupon> websiteCoupons = couponMapper.selectAllWebsiteCoupons();
        return websiteCoupons;
    }

    @Override
    public ResponseVO cancelCoupon(int couponId) {
        try {
            couponMapper.cancelCoupon(couponId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(COUPON_DONT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public Coupon getCouponById(int couponId) {
        try {
            Coupon coupon = couponMapper.selectCouponById(couponId);
            return coupon;
        } catch (Exception e) {
            System.out.println(COUPON_DONT_EXIST);
            return null;
        }
    }
}
