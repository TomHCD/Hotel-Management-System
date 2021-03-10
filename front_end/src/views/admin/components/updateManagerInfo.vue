x<template>
    <a-modal
             :visible="updateManagerModalVisible"
             title="修改个人信息"
             ok-text="确认"
             cancel-text="取消"
             @ok="handleSubmit"
             @cancel="cancel"
             :maskStyle="{'opacity':'0.2','animation':'none'}"
    >
        <a-Form :form="form" :label-col="{ span: 5}" :wrapper-col="{ span: 16 }" >
            <a-form-item label="用户名">
                <a-input
                        v-decorator="[
                                    'username',
                                    {initialValue:$store.state.user.userInfo.userName,rules: [{ required: true, message: '请输入用户名' }] },]"
                        />
            </a-form-item>


            <a-form-item label="密码">
                <a-input
                        v-decorator="[
                                    'password',
                                    {initialValue:$store.state.user.userInfo.password,rules: [{ required: true,
                                    message:
                                    '请输入用户密码' }, { validator: this.handlePassword }], validateTrigger: 'blur' },]"
                        />
            </a-form-item>

            <a-form-item label="电话号码">
                <a-input
                        v-decorator="[
                                    'phoneNumber',
                                    {initialValue:$store.state.user.userInfo.phoneNumber,rules: [{ required: true,
                                    message:
                                    '请输入电话号码' }] },]"
                        />
            </a-form-item>
        </a-Form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import { message } from 'ant-design-vue'
    export default {
        name: "updateManagerInfo",
        data() {
            return{}
        },

        computed: {
            ...mapGetters([
                'updateClientModalVisible',
                'updateManagerModalVisible',
                'updateMarketerModalVisible',
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateInfo' });
        },
        mounted() {

        },

        methods: {
            ...mapMutations([
                'set_updateClientModalVisible',
                'set_updateMarketerModalVisible',
                'set_updateManagerModalVisible',
                'set_userInfo',
            ]),
            ...mapActions([
                'updateClientInfo',
                'updateMarketerInfo',
                'updateManagerInfo',
            ]),
            cancel() {
                this.set_updateManagerModalVisible(false)
            },
            handlePassword(rule, value, callback) {
                if (value.length < 6) {
                    callback(new Error('密码长度至少6位'))
                }
                callback()
            },            
            handleSubmit() {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('username'),
                            password: this.form.getFieldValue('password'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                        }
                        this.set_userInfo(data)
                        this.updateManagerInfo().then(()=>{
                            this.set_updateManagerModalVisible(false)
                        })

                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>