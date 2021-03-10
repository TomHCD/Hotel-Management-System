import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function getOrderDetailsAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/getOrder`,
        method: 'GET',
    })

}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}

export function updateCommentAPI(data) {   //新增
    return axios({
        url: `${api.orderPre}/${data.orderId}/${data.comment}/updateComment`,
        method: 'POST',
    })
}

export function getAbnormalOrderListAPI() {
    return axios({
        url: `${api.orderPre}/getAbnormalOrders`,
        method: 'GET',
    })
}

export function cancelAbnormalOrderAPI(orderId,userId) {
    return axios({
        url: `${api.orderPre}/${orderId}/${userId}/annulAbnormalOrder`,
        method: 'GET',
    })
}

export function getCurrentOrderListAPI() {
    return axios({
        url: `${api.orderPre}/getCurrentOrders`,
        method: 'GET',
    })
}

export function executeCurrentOrderAPI(orderId,userId) {
    return axios({
        url: `${api.orderPre}/${orderId}/${userId}/executeCurrentOrder`,
        method: 'GET',
    })
}