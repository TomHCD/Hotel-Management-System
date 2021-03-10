import { message } from 'ant-design-vue'
import {getOrderDetailsAPI} from '@/api/order'

const getDefaultSate=()=>{

}

const order={
    state: {
        currentOrderId: '', 
        currentOrderInfo: {}
    },
    mutations: {
        set_currentOrderId: (state,data) =>{
            state.currentOrderId = data
        },
        set_currentOrderDetail:(state,data)=>{
            state.currentOrderInfo=data
        },

    },
    actions: {
        getOrderDetails:async ({ state, commit })=> {
            const res=await getOrderDetailsAPI(state.currentOrderId)
            //console.log(res)
            if(res){
                commit('set_currentOrderDetail',res)
            }
        },
    }
};
export default order