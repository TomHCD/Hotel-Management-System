<template>
    <div class="manageWeb-wrapper">
        <a-tabs>
            <a-tab-pane tab="客户信息管理" key="1">
                <ClientList></ClientList>
            </a-tab-pane>
            <a-tab-pane tab="酒店人员信息管理" key="2">
                <ManagerList></ManagerList>
            </a-tab-pane>
            <a-tab-pane tab="网站营销人员信息管理" key="3">
                <MarketerList></MarketerList>
            </a-tab-pane>
            <a-tab-pane tab="添加酒店及其工作人员" key="4">
                <a-button type="primary" block @click="addHotel"> 
                    点击添加
                </a-button>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import ClientList from "../admin/components/clientList";
    import MarketerList from "../admin/components/marketerList";
    import ManagerList from "../admin/components/managerList";
    import AddManagerModal from "../admin/components/addManagerModal";
    export default {
        name: "manageUser",
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
            }
        },
        components: {
            ClientList,
            MarketerList,
            ManagerList,
            AddManagerModal,
        },
        methods: {
            ...mapMutations([
            'set_addManagerModalVisible',
        ]),
        addHotel(){
            this.set_addManagerModalVisible(true)
        }
        }
    }
</script>

<style scoped lang="less">
    .manageWeb-wrapper {
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
    .manageWeb-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>