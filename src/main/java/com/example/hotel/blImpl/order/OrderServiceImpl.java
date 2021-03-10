package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "删除失败";
    private final static String EXECUTE_ERROR = "执行失败";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;

    @Autowired
    private OrderService orderService;  //新导入

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getHotelId(),orderVO.getRoomType());
        if(reserveRoomNum>curNum){
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());



            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(),orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
       return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
    }

    @Override
    public ResponseVO annulOrder(int orderid) {

            //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
            Order ordertoannul=orderMapper.getOrderById(orderid);
            ordertoannul.setOrderState(RESERVE_ERROR);     //设定了取消的状态
            hotelService.updateRoomInfo(ordertoannul.getHotelId(),ordertoannul.getRoomType(),ordertoannul.getRoomNum());//更新酒店房间
           // orderMapper.annulOrder(orderid);       //删除数据库中这一条order
             orderMapper.setOrderState(orderid,"已撤销");
            return ResponseVO.buildSuccess(true);
        }

    @Override
    public ResponseVO executeOrder(int orderid) {

        //执行订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        Order ordertoexecute=orderMapper.getOrderById(orderid);
//        ordertoexecute.setOrderState("已入住");
        hotelService.updateRoomInfo(ordertoexecute.getHotelId(),ordertoexecute.getRoomType(),ordertoexecute.getRoomNum());//更新酒店房间
        // orderMapper.annulOrder(orderid);       //删除数据库中这一条order
        orderMapper.setOrderState(orderid,"已入住");
        return ResponseVO.buildSuccess(true);
    }


    @Override
    public List<Order> getCurrentOrders() {
        return orderMapper.getCurrentOrders();
    }

    @Override
    public List<Order> getHotelOrders(Integer hotelId) {   //从hotelservceimpl移过来
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    @Override
    public List<Order> getAbnormalOrders() {
        return orderMapper.getAbnormalOrders();
    }

    @Override
    public ResponseVO annulAbnormalOrder(int orderid, int userid) {
        Order order = orderMapper.getOrderById(orderid);
        try {
            orderMapper.annulOrder(orderid);
            double amount = order.getPrice();
            accountService.addCreditByAnnulAbnormalOrder(userid, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO executeCurrentOrder(int orderid, int userid) {
        Order order = orderMapper.getOrderById(orderid);
        if(order.getOrderState().equals("已预订")){
            orderMapper.executeOrder(orderid);               //执行已预订订单
            return ResponseVO.buildSuccess(true);
        }else{
            return ResponseVO.buildFailure(EXECUTE_ERROR);
        }
    }


    @Override
    public OrderVO getOrderById(int orderid) {
        Order order = orderMapper.getOrderById(orderid);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        return orderVO;
    }
    /**
     * 添加订单评论
     * @param orderid
     * @param comment
     * @return
     */
    @Override
    public ResponseVO updateComment(int orderid, String comment) {
        orderMapper.updateComment(orderid, comment);
        orderMapper.setOrderState(orderid,"已评价");
        return ResponseVO.buildSuccess(true);
    }

    /**
     * 查看酒店的所有评价
     * @param hotelId
     * @return
     */
    @Override
    public List<CommentVO> getHotelComments(int hotelId) {

        List<Order> orders = orderMapper.getHotelComments(hotelId);
        List<CommentVO> comments = new ArrayList<>();

       // orders.stream().filter(order -> order.getComment()!=null);
        for (Order o: orders){
            if(o.getComment()!=null){
            CommentVO comment = new CommentVO();
            comment.setHotelId(o.getHotelId());
            comment.setUserName(accountService.getUserInfo(o.getUserId()).getUserName());
            comment.setContext(o.getComment());
            comments.add(comment);
            }
        }
        Collections.reverse(comments);
        return comments;
    }


    @Override
    public ResponseVO setOrderState(int orderid, String orderState){      //设置订单状态
        orderMapper.setOrderState(orderid,orderState);
        return ResponseVO.buildSuccess(true);
    }


}

