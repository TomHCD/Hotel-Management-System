package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceImplTest {
    @Autowired
    OrderService orderService;

    OrderVO ov1 = new OrderVO();
    OrderVO ov2 = new OrderVO();
    OrderVO ov3 = new OrderVO();


    @Autowired
    RoomService roomService;

    public OrderServiceImplTest(){
        int uid = 4, oid = 0, hid = 1;
        Integer roomNum = 1;
//        ov1.setId(0);
        ov1.setUserId(uid++);
        ov1.setHotelId(hid);
        ov1.setComment("0x0");
        ov1.setRoomNum(roomNum);
        ov1.setRoomType("BigBed");
        ov1.setHotelName("汉庭酒店");
        ov1.setCheckInDate("2020-06-01");
        ov1.setCheckOutDate("2020-06-02");
        ov1.setPeopleNum(2);
        ov1.setCreateDate("2020-04-20");
        ov1.setHaveChild(false);
//        ov1.setOrderState("已支付");

//        ov2.setId(1);
        ov2.setUserId(uid);
        ov2.setHotelId(hid++);
        ov2.setComment("XoX");
        ov2.setRoomNum(roomNum);
        ov2.setRoomType("BigBed");
        ov2.setHotelName("汉庭酒店");
        ov2.setCheckInDate("2020-06-01");
        ov2.setCheckOutDate("2020-06-02");
        ov2.setPeopleNum(2);
        ov2.setPrice(199.0);
        ov2.setCreateDate("2020-04-20");
        ov2.setHaveChild(false);
//        ov2.setOrderState("已支付");

        ov3.setUserId(uid);
//        ov3.setId(2);
        ov3.setHotelId(hid);
        ov3.setComment("orz");
        ov3.setRoomNum(roomNum);
        ov3.setRoomType("Family");
        ov3.setHotelName("儒家酒店");
        ov3.setCheckInDate("2020-06-01");
        ov3.setCheckOutDate("2020-06-02");
        ov3.setPeopleNum(2);
        ov3.setPrice(399.0);
        ov3.setCreateDate("2020-04-20");
        ov3.setHaveChild(false);
    }
    @Test
    public void addOrderTest1(){
        List<OrderVO> vos = Arrays.asList(ov1, ov2, ov3);
        boolean res = false;
        for (OrderVO vo: vos){
            res |= orderService.addOrder(vo).getSuccess();
        }
        assertFalse(res);
    }

    @Test
    public void getOrderByIdTest(){
//        List<OrderVO> vos = Arrays.asList(ov1, ov2, ov3);
//        for (OrderVO vo: vos){
//            orderService.addOrder(vo).getSuccess();
//        }
        int id = 14;
        List<OrderVO> orderVOS = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            OrderVO order = orderService.getOrderById(id++);
            orderVOS.add(order);
        }
        assertEquals((int)orderVOS.get(0).getId(), 14);
        assertEquals((int)orderVOS.get(0).getHotelId(), 1);
        assertEquals((int)orderVOS.get(0).getUserId(), 4);
        assertEquals((int)orderVOS.get(1).getId(), 15);
        assertEquals((int)orderVOS.get(1).getHotelId(), 1);
        assertEquals((int)orderVOS.get(1).getUserId(), 5);
        assertEquals((int)orderVOS.get(2).getId(), 16);
//        assertEquals(orderVOS.get(2).getHotelId(), ov3.getHotelId());
//        assertEquals(orderVOS.get(2).getUserId(), ov3.getUserId());
//        assertEquals(orderVOS.get(3).getId(), ov4.getId());
//        assertEquals(orderVOS.get(3).getHotelId(), ov4.getHotelId());
//        assertEquals(orderVOS.get(3).getUserId(), ov4.getUserId());
//        assertEquals(orderVOS.get(4).getId(), ov5.getId());
//        assertEquals(orderVOS.get(4).getHotelId(), ov5.getHotelId());
//        assertEquals(orderVOS.get(4).getUserId(), ov5.getUserId());
    }

    @Test
    public void getHotelCommentsTest1(){
        List<CommentVO> c = orderService.getHotelComments(1);
        List<String> comments = new ArrayList<>();
        c.stream().map(commentVO -> commentVO.getContext()).forEach(comments::add);
        System.out.println(comments);
        assertTrue(comments.contains("XoX"));
        assertTrue(comments.contains("0x0"));
        assertFalse(comments.contains("orz"));
    }

//    @Test
//    public void getHotelCommentsTest2(){
//
//    }

    @Test
    public void setOrderStateTest(){
        List<OrderVO> vos = Arrays.asList(ov1, ov2, ov3);
        for (OrderVO vo: vos){
            orderService.addOrder(vo).getSuccess();
        }
        orderService.setOrderState(14, "已执行");
        orderService.setOrderState(16, "已撤销");
        orderService.setOrderState(18, "待评价");
        orderService.setOrderState(17, "已评价");
        assertEquals("已执行", orderService.getOrderById(14).getOrderState());
        assertEquals("已撤销", orderService.getOrderById(16).getOrderState());
        assertEquals("待评价", orderService.getOrderById(18).getOrderState());
        assertEquals("已评价", orderService.getOrderById(17).getOrderState());
    }

    @Test
    public void annulOrderTest(){
        orderService.setOrderState(18, "已撤销");
        orderService.setOrderState(15, "已撤销");
        assertEquals("已撤销", orderService.getOrderById(18).getOrderState());
        assertEquals("已撤销", orderService.getOrderById(15).getOrderState());
    }
}

