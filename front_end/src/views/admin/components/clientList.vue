<template>
    <div>
            <a-table
                    :columns="columns"
                    :dataSource="clientList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                         <a-button  @click="updateUserInfo(record)">修改信息</a-button>
                    </span>
                </a-table>
        <UpdateClientInfo></UpdateClientInfo>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import UpdateClientInfo from './updateClientInfo'
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
        title: '信用值',
        dataIndex: 'credit',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
export default {
    name: 'clientList',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
        }
    },
    components: {
        UpdateClientInfo
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
      this.getClientList();
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
            'set_updateClientModalVisible',
            'set_userInfo',
            'set_addUserType'
        ]),
        updateUserInfo(data){
            this.set_userInfo(data)
            this.set_updateClientModalVisible(true)
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