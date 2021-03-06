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
                    //??????userType?????????router.push?????????,?????????promise?????????????????????userInfo????????????
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
                message.success('????????????')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('?????????????????????????????????')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {       //??????????????????
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('????????????')
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
                message.success('????????????')
            }else{
                message.error('????????????')
            }
        },
        updateComment: async({ state, dispatch }, orderId) => {
            const res = await updateCommentAPI(orderId)
            if(res) {
                dispatch('getUserOrders')
                message.success('????????????')
            }else{
                message.error('????????????')
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
                message.error('????????????')
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
                message.success('????????????')
                dispatch('getMarketerList')
            }else{
                message.error('????????????')
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
                message.success('????????????????????????')
                dispatch('getClientList')
            } else {
                message.error('????????????????????????')
            }
        },
        updateMarketerInfo: async ({state, commit, dispatch}) => {
            const res = await updateUserInfoAPI(state.userInfo)
            if (res) {
                commit('set_updateMarketerModalVisible', false)
                message.success('????????????????????????????????????')
                dispatch('getMarketerList')
            } else {
                message.error('????????????????????????????????????')
            }
        },
        updateManagerInfo: async ({state, commit, dispatch}) => {
            const res = await updateUserInfoAPI(state.userInfo)
            if (res) {
                commit('set_updateManagerModalVisible', false)
                message.success('??????????????????????????????')
                dispatch('getManagerList')
            } else {
                message.error('??????????????????????????????')
            }
        },
    }
}

export default user