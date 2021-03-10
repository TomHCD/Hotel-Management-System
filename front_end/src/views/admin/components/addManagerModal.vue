<template>
    <a-modal
            :visible="addManagerModalVisible"
            title="添加营销人员"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
            :maskStyle="{'opacity':'0.2','animation':'none'}"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="酒店名">
                <a-input
                        placeholder="请填写酒店名"
                        v-decorator="[
                        'hotelName',
                        { rules: [{required: true, message: '请输入酒店名', }] }
                    ]"
                />
            </a-form-item>   
            <a-form-item label="酒店简介" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写酒店简介"
                        v-decorator="[
                        'description', { rules: [{ required: true, message: '请填写酒店简介' }] }
                        ]"
                />
            </a-form-item> 
            <a-form-item v-bind="formItemLayout" label="酒店地址">
                <a-input
                        placeholder="请填写酒店地址"
                        v-decorator="[
                        'address',
                        { rules: [{required: true, message: '请输入酒店地址', }] }
                    ]"
                />
            </a-form-item>   
            <a-form-item v-bind="formItemLayout" label="酒店商圈">
                <a-input
                        placeholder="请填写酒店商圈"
                        v-decorator="[
                        'bizRegion',
                        { rules: [{required: true, message: '请输入酒店商圈', }] }
                    ]"
                />
            </a-form-item> 
             <a-form-item v-bind="formItemLayout" label="酒店星级">
                <a-input
                        placeholder="请填写酒店星级"
                        v-decorator="[
                        'hotelStar',
                        { rules: [{required: true, message: '请输入酒店星级', },{ validator: this.handleStar }], validateTrigger: 'blur'}
                    ]"
                />
            </a-form-item> 
            <a-form-item v-bind="formItemLayout" label="工作人员用户名">
                <a-input
                        placeholder="请填写酒店工作人员用户名"
                        v-decorator="[
                        'userName',
                        { rules: [{required: true, message: '请输入酒店工作人员用户名', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                        placeholder="请填写密码"
                        v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }, { validator: this.handlePassword }], validateTrigger: 'blur' }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="邮箱">
                <a-input
                        placeholder="请填写邮箱"
                        v-decorator="[
                        'email',
                        { rules: [{required: true, type:'email', message: '请输入邮箱', }], validateTrigger: 'blur' }
                    ]"
                />
            </a-form-item >
            <a-form-item v-bind="formItemLayout" label="电话">
                <a-input
                        placeholder="请填写电话"
                        v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请输入电话', }] }
                    ]"
                />
            </a-form-item >

        </a-Form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addManagerModal',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList',
                'addManagerParams',
                'addHotelParams'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addManagerModal' });/////////////////////
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_addManagerParams',
                'set_addHotelParams',
                'set_userId'
            ]),
            ...mapActions([
                'getManagerList',
                'addManager',
                'addHotel'
            ]),
            cancel() {
                this.set_addManagerModalVisible(false)
            },
            handlePassword(rule, value, callback) {
                if (value.length < 6) {
                    callback(new Error('密码长度至少6位'))
                }
                callback()
            },
            handleStar(rule, value, callback) {
                if (value!='Three'&& value!='Four'&& value!='Five') {
                    callback(new Error('请按正确格式输入星级'))
                }
                callback()
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const manager = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password'),
                            userName:this.form.getFieldValue('userName'),
                            phoneNumber:this.form.getFieldValue('phoneNumber'),
                        }
                        this.set_addManagerParams(manager)
                        this.addManager()
                        const hotel = {
                            managerId:this.managerList[this.managerList.length-1].id,
                            phoneNum:this.managerList[this.managerList.length-1].phoneNumber,
                            name:this.form.getFieldValue('hotelName'),
                            description:this.form.getFieldValue('description'),
                            address:this.form.getFieldValue('address'),
                            bizRegion:this.form.getFieldValue('bizRegion'),
                            hotelStar:this.form.getFieldValue('hotelStar'),
                            rate:3.0,
                        }
                        this.set_addHotelParams(hotel)
                        this.addHotel().then(()=>{
                            this.set_addManagerModalVisible(false)
                            this.form.resetFields()
                        })
                    }
                });
            },
        }
    }
</script>