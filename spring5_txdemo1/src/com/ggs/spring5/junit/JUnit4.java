package com.ggs.spring5.junit;

import com.ggs.spring5.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: Starbug
 * @date: 2020/6/11 23:51
 */
@RunWith(SpringJUnit4ClassRunner.class)//单元测试框架
@ContextConfiguration(locations = {"classpath:bean1.xml"}) //加载配置文件
public class JUnit4 {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.transferMoney();
    }
}
