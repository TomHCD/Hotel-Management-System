<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">

                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写用户名"
                                v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写邮箱号"
                                v-decorator="['email', { rules: [{ required: true, message: '请输入邮箱号' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.email}}</span>

                    </a-form-item>

                    <!--身份证号码-->
                    <a-form-item label="身份证号码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.idNumber}}</span>
                    </a-form-item>

                    <!--性别-->
                    <a-form-item label="性别" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.sexType}}</span>
                    </a-form-item>
                    <!--出生日期-->
                    <a-form-item label="出生日期" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.birth_date }}</span>
                    </a-form-item>




                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写手机号"
                                v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>

                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                 v-if="modify">
                        <a-input
                                placeholder="请输入新密码"
                                v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2">
                <a-table
                        :columns="columns"
                        :dataSource="userOrderList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">

    <!--评论按钮-->      <a-button size="small"  :disabled="record.orderState!=='已预订'" @click="abletocomment(record.id)" type="primary" >评论</a-button>

                        <a-modal
                                       title="请写下您的评价"
                                       :visible="evaluateFormVisible&&commentIndex==record.id"
                                       @ok="confirmEvaluateOrder(record)"
                                       @cancel="cancelEvaluateOrder"
                                       okText="确定"
                                       cancelText="取消"
                               >
                              <div>
                                <a-rate v-model="orderRate" :tooltips="desc"/>
                                <span class="ant-rate-text">{{ desc[orderRate - 1] }}</span>
                                  <a-textarea style="margin-top: 20px"
                                              placeholder="请留下您友善的评论"
                                              :autoSize="{ minRows: 2, maxRows: 6 }"
                                              v-model="evaluateContent"
                                  />
                              </div>
                        </a-modal>



                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>

                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmCancelOrder(record.id)"
                                @cancel="cancelCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>

                        </a-popconfirm>


                        
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script>

    import {mapGetters, mapMutations, mapActions} from 'vuex'


    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: {customRender: 'checkInDate'}
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: {customRender: 'checkOutDate'}
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {text: '已入住', value: '已入住'}
                , {text: '已评价', value: '已评价'}],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: {customRender: 'orderState'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },

    ];
    export default {
        name: 'info',
        data() {
            return {
                modify: false,   //修改时状态为true
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),
                evaluateFormVisible: false,
                orderRate: 3,
                desc: ['terrible', 'bad', 'normal', 'good', 'wonderful'],
                evaluateContent: '',
                commentIndex:'',
            }
        },
        components: {},
        computed: {
            ...mapGetters([
                'userId',
                'userInfo',
                'userOrderList'
            ]),
            // hasCommented(){
            //     return this.record.orderState&&this.record.orderState==='已预订'
            // }
        },
        async mounted() {
            await this.getUserInfo()
            await this.getUserOrders()
            console.log(this.userInfo)
            console.log(this.evaluateContent)


        },
        methods: {
            ...mapActions([
                'getUserInfo',
                'getUserOrders',
                'updateUserInfo',
                'cancelOrder',
                'updateComment'   

            ]),
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('userName'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.form.getFieldValue('password'),
                            email: this.form.getFieldValue('email')
                        }
                        this.updateUserInfo(data).then(() => {
                            this.modify = false
                        })
                    }
                });
            },
            modifyInfo() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'userName': this.userInfo.userName,
                        'phoneNumber': this.userInfo.phoneNumber,
                        'email': this.userInfo.email
                    })
                }, 0)
                this.modify = true
            },
            cancelModify() {
                this.modify = false
            },
            abletocomment(orderid){
                this.commentIndex=orderid
                this.evaluateFormVisible=true
            },
            confirmCancelOrder(orderId) {
                this.cancelOrder(orderId)
            },
            cancelCancelOrder() {

            },
            confirmEvaluateOrder(record) {       //评论完后点击确认执行该函数，新增
                console.log("record",record)
                this.commentIndex=record.id
                const data={
                    orderId:record.id,
                    comment:this.evaluateContent
                }
                // console.log(data)
                this.updateComment(data)      //调用后端API更新评论内容到相应订单，新增
                this.evaluateFormVisible = false;
                // if (res){
                //     console.log("评价成功")
                // }
            },
            cancelEvaluateOrder() {       //评论完后点击取消啥都不做，新增
                this.evaluateFormVisible = false;
            }
        }
    }
</script>
<style scoped lang="less">
    .info-wrapper {
        padding: 50px;

        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>