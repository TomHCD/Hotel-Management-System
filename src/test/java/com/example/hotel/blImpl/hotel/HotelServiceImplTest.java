package com.example.hotel.blImpl.hotel;


import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import  com.example.hotel.data.hotel.HotelMapper;

import com.example.hotel.util.ServiceException;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelServiceImplTest extends TestCase {


    @Autowired
    HotelService hotelService;

    @Autowired
    HotelMapper hotelMapper;


    @Test
    public void addHotel() throws ServiceException {
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("测试酒店");
        hotelVO.setDescription("这是测试用的");
        hotelVO.setAddress("广东");
        hotelVO.setPhoneNum("1234567891");
        hotelVO.setHotelStar("Five");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setRate(200.0);
        hotelVO.setManagerId(6);
        hotelVO.setId(4);

        hotelService.addHotel(hotelVO);
        assertEquals(hotelVO.getName(),hotelMapper.selectById(4).getName());



    }

    @Test
    public void updateHotel() throws ServiceException{
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("测试酒店");
        hotelVO.setDescription("这是测试用的");
        hotelVO.setAddress("广东");
        hotelVO.setPhoneNum("1234567891");
        hotelVO.setHotelStar("Five");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setRate(200.0);
        hotelVO.setManagerId(6);
        hotelVO.setId(4);

        hotelService.updateHotel(hotelVO);
        assertEquals(hotelVO.getRate(),hotelMapper.selectById(4).getRate());
    }

    @Test
    public void deleteHotel() throws ServiceException{
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("测试酒店2号");
        hotelVO.setDescription("这是测试用的");
        hotelVO.setAddress("广东");
        hotelVO.setPhoneNum("1234567891");
        hotelVO.setHotelStar("Five");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setRate(100.0);
        hotelVO.setManagerId(6);
        hotelVO.setId(4);
        hotelService.deleteHotel(hotelVO);
        assertEquals(null,hotelMapper.selectById(4));

    }

    @Test
    public void updateRoomInfo() {
        int temp_curnum=hotelService.getRoomCurNum(1,"Bigbed");
        hotelService.updateRoomInfo(1,"Bigbed",100);
        assertEquals(temp_curnum-100,hotelService.getRoomCurNum(1,"BigBed"));
    }

    @Test
    public void getRoomCurNum() {  //同上测试方法一样


    }

    @Test
    public void retrieveHotels() {
    }

    @Test
    public void retrieveHotelDetails() throws ServiceException{
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("测试酒店");
        hotelVO.setDescription("这是测试用的");
        hotelVO.setAddress("广东");
        hotelVO.setPhoneNum("1234567891");
        hotelVO.setHotelStar("Five");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setRate(100.0);
        hotelVO.setManagerId(6);
        hotelVO.setId(4);

        hotelService.addHotel(hotelVO);
        assertEquals(hotelVO.getDescription(),hotelService.retrieveHotelDetails(4).getDescription());
        assertEquals(hotelVO.getName(),hotelService.retrieveHotelDetails(4).getName());
        assertEquals(hotelVO.getAddress(),hotelService.retrieveHotelDetails(4).getAddress());

    }
}