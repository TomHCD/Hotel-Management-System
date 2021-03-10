package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteCreditCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    private AccountService accountService;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        User user= accountService.getUserInfo(orderVO.getUserId());
        if (coupon.getCouponType() == 3 &&
                user.getCredit() >= coupon.getTargetCredit() &&
                coupon.getTargetCredit() !=-1 &&
                coupon.getHotelId().equals(-1)) {
            return true;
        }

        return false;
    }
}
