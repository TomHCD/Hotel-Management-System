import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function getMarketerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllMarketers`,
        method: 'POST'
    })
}
export function addMarketerAPI(data) {
    return axios({
        url: `${api.adminPre}/addMarketer`,
        method: 'POST',
        data
    })
}
export function getClientListAPI(){
    return axios({
        url: `${api.adminPre}/getAllClients`,
        method: 'POST'
    })
}