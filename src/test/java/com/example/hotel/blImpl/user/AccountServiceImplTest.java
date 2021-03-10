package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserVO;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceImplTest extends TestCase {
    @Autowired
    AccountService accountService;

    @Test
    public void registerAccount() {
        UserVO userVO=new UserVO();
        userVO.setUserName("test1号");
        userVO.setBirth_date("1111-11-11");
        userVO.setSexType("男");
        userVO.setEmail("11111@qq.com");
        userVO.setPassword("111111");
        userVO.setPhoneNumber("11111111111");
        accountService.registerAccount(userVO);
        User user=accountService.getUserInfo("11111@qq.com");
        assertEquals(userVO.getUserName(),user.getUserName());   //拿到的username和注册用的是一样的
    }

    @Test
    public void login() {    //体现在前端
    }

    @Test
    public void getUserInfo() {   //和注册一样的方法测试

        UserVO userVO=new UserVO();
        userVO.setUserName("test1号");
        userVO.setBirth_date("1111-11-11");
        userVO.setSexType("男");
        userVO.setEmail("11111@qq.com");
        userVO.setPassword("111111");
        userVO.setPhoneNumber("11111111111");
        try {
            accountService.registerAccount(userVO);
        }
        catch (Exception e){}
        User user=accountService.getUserInfo("11111@qq.com");
        assertEquals(userVO.getUserName(),user.getUserName());   //拿到的username和注册用的是一样的
    }

    @Test
    public void updateUserInfo() {
        accountService.updateUserInfo(7,"123456","test1号","11111111112","11111@qq.com");
        User user=accountService.getUserInfo("11111@qq.com");
        assertEquals("123456",user.getPassword());     //修改密码后对比一下
    }

    @Test
    public void addCreditByAnnulAbnormalOrder() {
        User user=accountService.getUserInfo(7);
        accountService.addCreditByAnnulAbnormalOrder(7,100.0);
        double tempcredit=user.getCredit()+100.0;
        user=accountService.getUserInfo(7);
        assertEquals(tempcredit,user.getCredit());    //原来的+100=后来的
    }

    @Test
    public void addCreditForUser() {
        User user=accountService.getUserInfo(7);
        accountService.addCreditForUser("11111@qq.com",200.0);
        double tempcredit=user.getCredit()+200.0;
        user=accountService.getUserInfo(7);
        assertEquals(tempcredit,user.getCredit());    //原来的+200=后来的
    }
}