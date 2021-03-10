// import {
//     getManagerListAPI,
//     addManagerAPI,
//     getMarketerListAPI,
//     addMarketerAPI,
//     getClientListAPI,
// } from '@/api/admin'
// import { message } from 'ant-design-vue'

// const admin = {
//     state: {
//         managerList: [

//         ],
//         addManagerModalVisible: false,
//         updateManagerModalVisible:false,
//         addManagerParams: {
//             email:'',
//             password:'',
//             userName:'',
//         },
//         marketerList: [

//         ],
//         addMarketerModalVisible: false,
//         updateMarketerModalVisible:false,
//         addMaketerParams: {
//             email:'',
//             password:'',
//             userName:'',
//         },
//         updateClientModalVisible:false,
//         clientList:[

//         ]
//     },
//     mutations: {
//         set_managerList: function(state, data) {
//             state.managerList = data
//         },
//         set_addManagerModalVisible: function(state, data) {
//             state.addManagerModalVisible = data
//         },
//         set_addManagerParams: function(state, data) {
//             state.addManagerParams = {
//                 ...state.addManagerParams,
//                 ...data,
//             }
//         },
//         set_marketerList: function(state, data) {
//             state.marketerList = data
//         },
//         set_addMarketerModalVisible: function(state, data) {
//             state.addMarketerModalVisible = data
//         },
//         set_addMarketerParams: function(state, data) {
//             state.addMarketerParams = {
//                 ...state.addMarketerParams,
//                 ...data,
//             }
//         },
//         set_clientList: function(state, data) {
//             state.clientList = data
//         },
//     },
//     actions: {
//         getManagerList: async({ commit }) => {
//             const res = await getManagerListAPI()
//             if(res){
//                 commit('set_managerList', res)
//             }
//         },
//         addManager: async({ state, commit, dispatch }) => {
//             const res = await addManagerAPI(state.addManagerParams)
//             if(res) {
//                 commit('set_addManagerParams',{
//                     email:'',
//                     password:'',
//                     userName:''
//                 })
//                 commit('set_addManagerModalVisible', false)
//                 message.success('添加成功')
//                 dispatch('getManagerList')
//             }else{
//                 message.error('添加失败')
//             }
//         },
//         getMarketerList: async({ commit }) => {
//             const res = await getMarketerListAPI()
//             if(res){
//                 commit('set_marketerList', res)
//             }
//         },
//         addMarketer: async({ state, commit, dispatch }) => {
//             const res = await addMarketerAPI(state.addManagerParams)
//             if(res) {
//                 commit('set_addMarketerParams',{
//                     email:'',
//                     password:'',
//                     userName:''
//                 })
//                 commit('set_addMarketerModalVisible', false)
//                 message.success('添加成功')
//                 dispatch('getMarketerList')
//             }else{
//                 message.error('添加失败')
//             }
//         },
//         getClientList: async({ commit }) => {
//             const res = await getClientListAPI()
//             if(res){
//                 commit('set_clientList', res)
//             }
//         },
//         updateInfo: async ({state, commit, dispatch}) => {
//             const res = await changeUserInfoAPI(state.userInfo)
//             if (res) {
//                 commit('set_userInfo', {
//                     id:0,
//                     email:'',
//                     password:'',
//                     userName:'',
//                     type:'',
//                     phone:''
//                 })
//                 commit('set_updateUserModalVisible', false)
//                 message.success('修改成功')
//                 dispatch('getManagerList')
//             } else {
//                 message.error('修改失败')
//             }
//         },
//     }
// }
// export default admin