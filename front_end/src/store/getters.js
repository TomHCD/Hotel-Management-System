const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  commentList: state => state.hotel.commentList,

  //admin
  managerList: state => state.user.managerList,
  updateManagerModalVisible: state => state.user.updateManagerModalVisible,
  addManagerModalVisible: state => state.user.addManagerModalVisible,
  addManagerParams: state => state.user.addManagerParams,
  marketerList: state => state.user.marketerList,
  updateMarketerModalVisible: state => state.user.updateMarketerModalVisible,
  addMarketerModalVisible: state => state.user.addMarketerModalVisible,
  addMarketerParams: state => state.user.addMarketerParams,
  updateClientModalVisible: state => state.user.updateClientModalVisible,
  clientList: state => state.user.clientList,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  updateHotelModalVisible: state => state.hotelManager.updateHotelModalVisible,
  deleteHotelModalVisible: state => state.hotelManager.deleteHotelModalVisible,
  //order
  currentOrderId: state => state.order.currentOrderId,     
  currentOrderInfo: state => state.order.currentOrderInfo, 
  //marketer
  abnormalOrderList: state => state.marketer.abnormalOrderList,
  currentOrderList:state => state.marketer.currentOrderList,
  websiteCouponList: state => state.marketer.websiteCouponList,
  addWebCouponVisible: state => state.marketer.addWebCouponVisible,
  currentCouponId: state => state.marketer.currentCouponId,
  currentCouponInfo: state => state.marketer.currentCouponInfo,
  couponDetailVisible: state => state.marketer.couponDetailVisible,
  }
  
  export default getters