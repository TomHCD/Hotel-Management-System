<template>
    <div>
        <a-modal
                :visible="couponVisible"
                title="优惠策略"
                width="1200px"
                :footer="null"
                @cancel="cancel"
        >
            <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
            <div style="width: 100%; text-align: right; margin:20px 0">
                <a-button type="primary" @click="addCoupon"><a-icon type="plus" />添加优惠策略</a-button>
            </div>
            <a-table
                    :columns="columns"
                    :dataSource="couponList"
                    bordered
            >
                <a-tag color="red" slot="couponName" slot-scope="text">
                    {{text}}
                </a-tag>
                <span slot="couponType" slot-scope="text">
                <span v-if="text==1">满减特惠</span>
                <span v-if="text==2">限时特惠</span>
                <span v-if="text==4">多间特惠</span>
            </span>
            <span slot="discount" slot-scope="text">
               <span v-if="text>0"> {{text}}折</span>
            </span>
            </a-table>
        </a-modal>
        <AddCoupon></AddCoupon>
    </div>
</template>
<script>
    import Vue from 'vue'
    var vm=new Vue({
        el:''

    })
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddCoupon from './addCoupon'

    const columns = [
        // 这里定义列表头
        {
            title:"id",
            dataIndex: 'id'
        },
        {
            title:"优惠简介",
            dataIndex: 'description'
        },
        {
            title:"酒店ID",
            dataIndex: 'hotelId'
        },
        {
            title:"优惠类型",
            dataIndex: 'couponType',
            scopedSlots: {customRender: 'couponType'}
        },
        {
            title:"优惠名称",
            dataIndex: 'couponName'
        },
        {
            title:"满减金额",
            dataIndex: 'targetMoney'
        },
        {
            title:"折扣",
            dataIndex: 'discount',
            scopedSlots:{customRender: 'discount'}
        },
        {
            title: "开始时间",
            dataIndex: 'startTime',
        },
        {
            title: "截止时间",
            dataIndex: 'endTime',
        },
        {
            title: "优惠金额",
            dataIndex: 'discountMoney',
        },
        {
            title: "目标间数",
            dataIndex: 'targetRoomNum',
        }
    ];
    export default {
        name: 'coupon',
        data() {
            return {
                columns
            }
        },
        components: {
            AddCoupon,
        },
        computed: {
            ...mapGetters([
                'couponVisible',
                'couponList',
            ])
        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_couponVisible',
            ]),
            ...mapActions([
                'getHotelCoupon'
            ]),
            cancel() {
                this.set_couponVisible(false)
            },
            addCoupon() {
                this.set_addCouponVisible(true),
                    this.set_couponVisible(false)
            },
        },
    }
</script>
<style scoped>

</style>