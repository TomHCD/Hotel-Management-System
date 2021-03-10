<template>
    <div>
        <div style="width: 100%; text-align: right; margin:20px 0">
                     <a-button type="primary" @click="addUser"><a-icon type="plus" />添加网站营销人员</a-button>
                </div>
            <a-table
                    :columns="columns"
                    :dataSource="marketerList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                         <a-button  @click="updateUserInfo(record)">修改信息</a-button>
                    </span>
                </a-table>
        <UpdateMarketerInfo></UpdateMarketerInfo>
        <AddMarketerModal></AddMarketerModal>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddMarketerModal from './addMarketerModal'
import UpdateMarketerInfo from './updateMarketerInfo'
import{message} from 'ant-design-vue'
const columns = [
    {  
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {  
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'marketerList',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
        }
    },
    components: {
        UpdateMarketerInfo,
        AddMarketerModal,
    },
    computed: {
        ...mapGetters([
            'clientList',
            'managerList',
            'marketerList',
            'updateClientModalVisible'
        ])
    },
    mounted() {
      this.getMarketerList();
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'changeInfo',
            'getClientList',
            'getMarketerList'
        ]),
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addMarketerModalVisible',
            'set_updateMarketerModalVisible',
            'set_userInfo',
        ]),

        addUser(){
            this.set_addMarketerModalVisible(true)
        },
        updateUserInfo(data){
            this.set_userInfo(data)
            this.set_updateMarketerModalVisible(true)
        },
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
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
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    
</style>