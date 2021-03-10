import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
} from '@/api/user'
import {
    getManagerListAPI,
    addManagerAPI,
    getMarketerListAPI,
    addMarketerAPI,
    getClientListAPI,
} from '@/api/admin'
import {
    getUserOrdersAPI,
    cancelOrderAPI,
    updateCommentAPI
} from '@/api/order'

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        userOrderList: [

        ]
    }
}

const user = {
    state : {userId: '',
        userInfo: {

        },
        userOrderList: [

        ],
        managerList: [

        ],
        addManagerModalVisible: false,
        updateManagerModalVisible:false,
        addManagerParams: {
            email:'',
            password:'',
            userName:'',
            phoneNumber:''
        },
        marketerList: [

        ],
        addMarketerModalVisible: false,
        updateMarketerModalVisible:false,
        addMaketerParams: {
            email:'',
            password:'',
            userName:'',
            phoneNumber:''
        },
        updateClientModalVisible:false,
        clientList:[

        ]},

    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_updateManagerModalVisible: function(state,data){
            state.updateManagerModalVisible=data
        },
        set_marketerList: function(state, data) {
            state.marketerList = data
        },
        set_addMarketerModalVisible: function(state, data) {
            state.addMarketerModalVisible = data
        },
        set_addMarketerParams: function(state, data) {
            state.addMarketerParams = {
                ...state.addMarketerParams,
                ...data,
            }
        },
        set_updateMarketerModalVisible: function(state,data){
            state.updateMarketerModalVisible=data
        },
        set_clientList: function(state, data) {
            state.clientList = data
        },
        set_updateClientModalVisible: function(state,data){
            state.updateClientModalVisible=data
        },
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userOrderList = []
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        }
    },

    actions: {
        login: async ({ dispatch, commit, state}, userData) => {
            const res = await loginAPI(userData)
            if(res){
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                dispatch('getUserInfo').then(() => {
                    console.log("this.userInfo.userType=" + state.userInfo.userType)
                    //根据userType来选择router.push的路由,要通过promise异步调用，保证userInfo已经更新
                    if (state.userInfo.userType == "Marketer") {
                        router.push('/marketer/marketer')
                    } else {
                        router.push('/hotel/hotelList')
                    }
                })
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {       //更改用户信息
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        updateComment: async({ state, dispatch }, orderId) => {
            const res = await updateCommentAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('评论成功')
            }else{
                message.error('评论失败')
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },


          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:'',
                    userName:'',
                    phoneNumber:''
                })
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        getMarketerList: async({ commit }) => {
            const res = await getMarketerListAPI()
            if(res){
                commit('set_marketerList', res)
            }
        },
        addMarketer: async({ state, commit, dispatch }) => {
            const res = await addMarketerAPI(state.addMarketerParams)
            if(res) {
                commit('set_addMarketerParams',{
                    email:'',
                    password:'',
                    userName:'',
                    phoneNumber:''
                })
                commit('set_addMarketerModalVisible', false)
                message.success('添加成功')
                dispatch('getMarketerList')
            }else{
                message.error('添加失败')
            }
        },
        getClientList: async({ commit }) => {
            const res = await getClientListAPI()
            if(res){
                commit('set_clientList', res)
            }
        },
        updateClientInfo: async ({state, commit, dispatch}) => {
            const res = await updateUserInfoAPI(state.userInfo)
            if (res) {
                // commit('set_userInfo', {
                //     id:0,
                //     email:'',
                //     password:'',
                //     userName:'',
                //     type:'',
                //     phone:''
                // })
                commit('set_updateClientModalVisible', false)
                message.success('修改客户信息成功')
                dispatch('getClientList')
            } else {
                message.error('修改客户信息失败')
            }
        },
        updateMarketerInfo: async ({state, commit, dispatch}) => {
            const res = await updateUserInfoAPI(state.userInfo)
            if (res) {
                commit('set_updateMarketerModalVisible', false)
                message.success('修改网站营销人员信息成功')
                dispatch('getMarketerList')
            } else {
                message.error('修改网站营销人员信息失败')
            }
        },
        updateManagerInfo: async ({state, commit, dispatch}) => {
            const res = await updateUserInfoAPI(state.userInfo)
            if (res) {
                commit('set_updateManagerModalVisible', false)
                message.success('修改酒店经理信息成功')
                dispatch('getManagerList')
            } else {
                message.error('修改酒店经理信息失败')
            }
        },
    }
}

export default user