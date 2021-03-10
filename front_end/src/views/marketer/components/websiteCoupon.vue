<template>
    <div>
        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addWebCoupon">
                <a-icon type="plus"/>
                添加优惠策略
            </a-button>
        </div>
        <a-table
                :columns="columns"
                :dataSource="websiteCouponList"
                bordered
        >
            <a-tag color="red" slot="couponName" slot-scope="text">
                {{text}}
            </a-tag>
            <span slot="couponType" slot-scope="text">
                <span v-if="text==1">满减特惠</span>
                <span v-if="text==2">限时特惠</span>
                <span v-if="text==3">会员特惠</span>
            </span>
            <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showCouponDetails(record.id)">查看详情</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="您确定要撤销该网站优惠吗？"
                                @confirm="confirmCancelCoupon(record.id)"
                                @cancel="cancelCancelCoupon"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">撤销优惠</a-button>
                        </a-popconfirm>
            </span>
        </a-table>
        <WebsiteCouponDetail></WebsiteCouponDetail>
        <AddWebsiteCoupon></AddWebsiteCoupon>
    </div>
</template>
<script>
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddWebsiteCoupon from "./addWebsiteCoupon";
    import WebsiteCouponDetail from "./websiteCouponDetail";

    const columns = [
        // 这里定义列表头
        {
            title: "优惠类型",
            dataIndex: 'couponType',
            scopedSlots: {customRender: 'couponType'}  //自定义渲染
        },
        {
            title: "优惠券名",
            dataIndex: 'couponName'
        },
        {
            title: "优惠简介",
            dataIndex: 'description',
        },
        {
            title: "开始时间",
            dataIndex: 'startTime',
            scopedSlots: {customRender: 'startTime'}
        },
        {
            title: "结束时间",
            dataIndex: 'endTime',
            scopedSlots: {customRender: 'endTime'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: "websiteCoupon",
        data() {
            return {
                columns
            }
        },
        components: {
            AddWebsiteCoupon,
            WebsiteCouponDetail,
        },
        computed: {
            ...mapGetters([
                'websiteCouponList',
                'currentCouponId',
                'currentCouponInfo',
            ])
        },
        async mounted() {
            await this.getWebsiteCouponList()
        },
        methods: {
            ...mapMutations([
                'set_addWebCouponVisible',
                'set_currentCouponId',
                'set_couponDetailVisible',
            ]),
            ...mapActions([
                'getWebsiteCouponList',
                'cancelCoupon',
                'getCurrentCouponInfo',
            ]),
            addWebCoupon() {
                this.set_addWebCouponVisible(true)
            },
            confirmCancelCoupon(couponId) {
                this.cancelCoupon(couponId).then(() => {
                    this.getWebsiteCouponList()
                })
            },
            cancelCancelCoupon() {

            },
            showCouponDetails(couponId) {
                this.set_currentCouponId(couponId)
                this.getCurrentCouponInfo(couponId).then(()=>{
                this.set_couponDetailVisible(true)
                })

            },
        },
    }
</script>

<style scoped>

</style>