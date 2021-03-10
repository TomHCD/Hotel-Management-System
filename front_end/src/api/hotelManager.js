import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function updateHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/updateHotel`,
        method: 'POST',
        data,
    })
}
export function deleteHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/deleteHotel`,
        method: 'POST',
        data,
    })
}
