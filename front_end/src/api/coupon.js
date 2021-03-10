import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}

export function hotelTimeAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTime`,
        method: 'POST',
        data,
    })
}

export function websiteCreditAPI(data){
    return axios({
        url: `${api.couponPre}/websiteCredit`,
        method: 'POST',
        data,
    })
}
export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}

export function websiteAllCouponsAPI() {
    return axios({
        url: `${api.couponPre}/websiteAllCoupons`,
        method: 'GET',
    })
}

export function getCouponById(couponId) {
    return axios({
        url: `${api.couponPre}/${couponId}/getCouponInfo`,
        method: 'GET',
        couponId,
    })
}

export function cancelCouponAPI(couponId) {
    return axios({
        url: `${api.couponPre}/${couponId}/cancelCoupon`,
        method: 'POST',
        couponId,
    })
}
export function hotelMultiRoomAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelMultiRoom`,
        method: 'POST',
        data,
    })
}