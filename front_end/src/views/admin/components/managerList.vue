<template>
    <div>
            <a-table
                    :columns="columns"
                    :dataSource="managerList"
                    bordered
                >
                    <span slot="action" slot-scope="record">
                         <a-button  @click="updateUserInfo(record)">修改信息</a-button>
                    </span>
                </a-table>
        <UpdateManagerInfo></UpdateManagerInfo>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import UpdateManagerInfo from './updateManagerInfo'
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
        UpdateManagerInfo,
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
      this.getManagerList();
    },
    methods: {
        ...mapActions([
            'getManagerList',
            'changeInfo',
            'getClientList',
            'getMarketerList'
        ]),
        ...mapMutations([
            'set_updateManagerModalVisible',
            'set_userInfo',
        ]),

        updateUserInfo(data){
            this.set_userInfo(data)
            this.set_updateManagerModalVisible(true)
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