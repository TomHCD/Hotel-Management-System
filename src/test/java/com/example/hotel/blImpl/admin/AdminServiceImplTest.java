package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest extends TestCase {
    @Autowired
    AdminService adminService;
    @Autowired
    AccountService accountService;
    @Test
    public void addManager() {
        UserForm userForm=new UserForm();
        userForm.setEmail("122@qq.com");
        userForm.setPassword("123456");
        userForm.setPhoneNumber("123456789");
        userForm.setUserName("111");
        adminService.addManager(userForm);
        User user=accountService.getUserInfo("122@qq.com");
        assertEquals(userForm.getUserName(),user.getUserName());
    }

    @Test
    public void getAllManagers() {
        List<User> mlist=adminService.getAllManagers();
       assertEquals(true,mlist.get(0).getUserName().equals("测试一号")&&mlist.get(1).getUserName().equals("测试二号")&&mlist.get(2).getUserName().equals("测试三号"));
    }
}