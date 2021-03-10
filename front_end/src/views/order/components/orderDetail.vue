<template>
    <div>
        <a-descriptions borderd size="default" title="订单详情" bordered layout="vertical">
            <a-descriptions-item label="订单号">
                {{currentOrderInfo.id}}
            </a-descriptions-item>
            <a-descriptions-item label="用户名" >
                {{currentOrderInfo.clientName}}
            </a-descriptions-item>
             <a-descriptions-item label="手机号码">
                {{currentOrderInfo.phoneNumber}}
            </a-descriptions-item>
            <a-descriptions-item label="酒店名称">
                {{currentOrderInfo.hotelName}}
            </a-descriptions-item>
            <a-descriptions-item label="创建日期">
                {{currentOrderInfo.createDate}}
            </a-descriptions-item>
            <a-descriptions-item label="预定入住时间">
                {{currentOrderInfo.checkInDate}}
            </a-descriptions-item>
            <a-descriptions-item label="预定离店时间" >
                {{currentOrderInfo.checkOutDate}}
            </a-descriptions-item>
            <a-descriptions-item label="订单状态" >
                <a-tag color="grey" v-if="currentOrderInfo.orderState=='已撤销'">已撤销</a-tag>
                <a-tag color="blue" v-else-if="currentOrderInfo.orderState=='已执行'">已执行</a-tag>
                <a-tag color="red" v-else-if="currentOrderInfo.orderState=='异常'">异常</a-tag>
                <a-tag color="yellow" v-else-if="currentOrderInfo.orderState=='已退房'">已退房</a-tag>
                <a-tag color="green" v-else>已预订</a-tag>
            </a-descriptions-item>

            <a-descriptions-item label="登记人数">
                {{currentOrderInfo.peopleNum}}人
            </a-descriptions-item>
            <a-descriptions-item label="房间信息">
                <span v-if="currentOrderInfo.roomType == 'BigBed'">大床房</span>
                <span v-if="text == 'DoubleBed'">双床房</span>
                <span v-if="text == 'Family'">家庭房</span>
                ：{{currentOrderInfo.roomNum}}间<br/>
            </a-descriptions-item>
            <a-descriptions-item label="有无儿童">
                {{Number(currentOrderInfo.haveChild)===0?'无':'有'}}<br/>
            </a-descriptions-item>
        </a-descriptions>
        <a-button @click="back" type="primary" block>
            返回
        </a-button>
    </div>
</template>

<script>
    import {mapGetters,mapActions} from 'vuex'
    export default {
        name: 'orderDetail',
        props: {
            back: {
                function() {
                }
            },
        },
        data() {
            return {
                orderId: null,
                loading: false,
                visible: false,
                pane: 1,

            }
        },

        computed: {
            ...mapGetters([
                'currentOrderInfo'
            ]),
        },
        methods:{
            ...mapActions([]),
        }
    }
</script>