<template>
    <div class="comment-list">
        <div class="list">
    <a-table
            :columns="columns"
            :data-source="comments"
            :pagination="{ pageSize: 8 }"
            :scroll="{ y: 240 }"
            :loading="false"

    >
        <span slot="userName" slot-scope="text">
                    <span>{{ text }}</span>
                </span>
        <span slot="comment" slot-scope="text">
                    <span>{{ text }}</span>
                </span>
    </a-table>
        </div>
    </div>
</template>
<script>
    const columns = [
        {
            title: '用户名称',
            dataIndex: 'userName',
            key: 'userName',
            width: '20%',
            scopedSlots: { customRender: 'name' },
        },
        {
            title: '用户评价',
            dataIndex: 'context',
            key: 'comment',
            scopedSlots: { customRender: 'comment' },
        }

    ];

    export default {
        name: "commentList",
        props: {
            comments:{
                type: Array
            }
        },
        data() {
            return {
                data: [],
                pagination: {
                    total: 0,
                    pageSize: 2,
                    showTotal: total => `共有${total}条评价`,
                },
                loading: false,
                columns: columns,

            };
        },
        mounted() {
           // this.fetch();
            console.log(this.comments);
        },
        beforeRouteUpdate(to, from, next){
            next();
        },
        methods: {
            handleTableChange(pagination) {
                console.log(pagination);
                this.pagination.current = pagination.current;
                this.pagination.pageSize = pagination.pageSize;
               // this.fetch();
            },
        }

    };
</script>

