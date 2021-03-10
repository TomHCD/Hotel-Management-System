package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 获得异常状态的订单信息
     * @return
     */
    List<Order> getAbnormalOrders();

    /**
     * 执行订单
     * @param orderid
     * @return
     */
    ResponseVO executeOrder(int orderid);

    /**
     * 获得待入住状态的订单信息
     * @return
     */
    List<Order> getCurrentOrders();

    ResponseVO executeCurrentOrder(int orderid, int userid);

    ResponseVO annulAbnormalOrder(int orderid, int userid);

    OrderVO getOrderById(int orderid);
    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);   //从hotelservce中移过来

    /**
     * 添加订单评论
     * @param orderid
     * @param comment
     * @return
     */
    ResponseVO updateComment(int orderid, String comment);

    /**
     * 查看酒店的所有评价
     * @param hotelId
     * @return
     */
    List<CommentVO> getHotelComments(int hotelId);   //这里是vo的吗

    ResponseVO setOrderState(int orderid, String orderState);

}
