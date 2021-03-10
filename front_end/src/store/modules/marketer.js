import {
    getAbnormalOrderListAPI,
    cancelAbnormalOrderAPI,
    getCurrentOrderListAPI,
    executeCurrentOrderAPI
} from '@/api/order'
import {
    addCreditAPI
} from '@/api/user'
import {
    websiteAllCouponsAPI,
    cancelCouponAPI,
    getCouponById,
    hotelTargetMoneyAPI,
    hotelTimeAPI,
    websiteCreditAPI
} from '@/api/coupon'
import {message} from 'ant-design-vue'

const marketer = {
    state: {
        abnormalOrderList: [],
        currentOrderList:[],
        addWebCouponVisible: false,
        websiteCouponList: [],
        currentCouponId: 0,
        currentCouponInfo: [],
        couponDetailVisible: false,
    },
    mutations: {
        set_abnormalOrderList(state, data) {
            state.abnormalOrderList = data
        },
        set_currentOrderList(state, data) {
            state.currentOrderList = data
        },
        set_addWebCouponVisible(state, data) {
            state.addWebCouponVisible = data
        },
        set_websiteCouponList(state, data) {
            state.websiteCouponList = data
        },
        set_currentCouponId(state, data) {
            state.currentCouponId = data
        },
        set_currentCouponInfo(state, data) {
            state.currentCouponInfo = data
        },
        set_couponDetailVisible(state, data) {
            state.couponDetailVisible = data
        }

    },
    actions: {
        //获取异常订单列表
        getAbnormalOrders: async ({state, commit}) => {
            const res = await getAbnormalOrderListAPI()
            if (res) {
                commit('set_abnormalOrderList', res)
            }
        },
        //获取现存订单列表
        getCurrentOrders: async ({state, commit}) => {
            const res = await getCurrentOrderListAPI()
            if (res) {
                commit('set_currentOrderList', res)
            }
        },
        //获取网站优惠列表
        getWebsiteCouponList: async ({state, commit}) => {
            const res = await websiteAllCouponsAPI()
            console.log(res)
            for (var i=0;i<res.length;i++){
                if (res[i].startTime!=null){
                res[i].startTime=res[i].startTime.replace('T',' ')
                res[i].endTime=res[i].endTime.replace('T',' ')
                }
            }
            if (res) {
                commit('set_websiteCouponList', res)
            }
        },
        //撤销异常订单
        cancelAbnormalOrder: async ({state, dispatch}, data) => {
            const res = await cancelAbnormalOrderAPI(data.orderId, data.userId)
            if (res) {
                dispatch('getAbnormalOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        //执行订单
        executeCurrentOrder: async ({state, dispatch}, data) => {
            const res = await executeCurrentOrderAPI(data.orderId, data.userId)
            if (res) {
                dispatch('getCurrentOrders')//dispatch是异步操作action的方法
                message.success('执行成功')
            } else {
//                message.error('执行失败')
            }
        },
        //撤销优惠券
        cancelCoupon: async ({state, dispatch}, couponId) => {
            const res = await cancelCouponAPI(couponId)
            if (res) {
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        addCredit: async ({state, commit}, data) => {
            const res = await addCreditAPI(data)
            if (res) {
                message.success('充值成功')
            } else {
                message.error('充值失败')
            }
        },
        getCurrentCouponInfo: async ({state, commit}, couponId) => {
            const res = await getCouponById(couponId)
            if (res) {
                commit('set_currentCouponInfo',res)
            } else {
                message.error('查看失败')
            }
        },
        addWebsiteCoupon: async({state,dispatch ,commit}, data) => {
            let res=null;
            if(Number(data.couponType)===1){
                res=await hotelTargetMoneyAPI(data)
            }else if(Number(data.couponType)===2){
                res=await hotelTimeAPI(data)
            }else if(Number(data.couponType)===3){
                res=await websiteCreditAPI(data)
            }
            if(res){
                message.success('添加成功')
            }else{
                // 添加失败后的操作1
                message.error('添加失败');
            }
        },
    }
}
export default marketer