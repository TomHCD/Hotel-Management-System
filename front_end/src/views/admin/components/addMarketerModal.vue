<template>
    <a-modal
            :visible="addMarketerModalVisible"
            title="添加营销人员"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
            :maskStyle="{'opacity':'0.2','animation':'none'}"
    >
        <a-Form :form="form">
            <a-form-item v-bind="formItemLayout" label="用户名">
                <a-input
                        placeholder="请填写用户名"
                        v-decorator="[
                        'userName',
                        { rules: [{required: true, message: '请输入用户名', }] }
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
        name: 'addUserModal',
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
                'addMarketerModalVisible',
                'managerList',
                'addMarketerParams'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addMarketerModal' });/////////////////////
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addMarketerModalVisible',
                'set_addMarketerParams',
            ]),
            ...mapActions([
                'getManagerList',
                'addMarketer',
            ]),
            cancel() {
                this.set_addMarketerModalVisible(false)
            },
            handlePassword(rule, value, callback) {
                if (value.length < 6) {
                    callback(new Error('密码长度至少6位'))
                }
                callback()
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            email: this.form.getFieldValue('email'),
                            password: this.form.getFieldValue('password'),
                            userName:this.form.getFieldValue('userName'),
                            phoneNumber:this.form.getFieldValue('phoneNumber'),
                        }
                        this.set_addMarketerParams(data)
                        this.addMarketer().then(()=>{
                            this.form.resetFields()
                        })
                    }
                });
            },
        }
    }
</script>