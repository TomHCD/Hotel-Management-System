package com.example.hotel.controller.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HotelService hotelService;  //新导入

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{orderid}/{userid}/annulAbnormalOrder")
    public ResponseVO annulAbnormalOrder(@PathVariable int orderid, @PathVariable int userid) {
        return orderService.annulAbnormalOrder(orderid, userid);
    }


    @PostMapping("/{orderId}/{comment}/updateComment")     //新增
    public ResponseVO updateComment(@PathVariable int orderId,@PathVariable String comment){
       // System.out.println(orderId);
      //  System.out.println(comment);

        return orderService.updateComment(orderId,comment);
    }

    @GetMapping("/getAbnormalOrders")
    public ResponseVO retrieveAbnormalOrders() {
        return ResponseVO.buildSuccess(orderService.getAbnormalOrders());
    }

    @GetMapping("/{orderid}/getOrder")
    public ResponseVO retrieveOrder(@PathVariable int orderid) {
        return ResponseVO.buildSuccess(orderService.getOrderById(orderid));
    }

    //执行订单相关部分
    @GetMapping("/{orderid}/executeOrder")
    public ResponseVO executeOrder(@PathVariable int orderid) {
        return orderService.executeOrder(orderid);
    }

    @GetMapping("/{orderid}/{userid}/executeCurrentOrder")
    public ResponseVO executeCurrentOrder(@PathVariable int orderid, @PathVariable int userid) {
        return orderService.executeCurrentOrder(orderid, userid);
    }

    @GetMapping("/getCurrentOrders")
    public ResponseVO retrieveCurrentOrders() {
        return ResponseVO.buildSuccess(orderService.getCurrentOrders());
    }
}
