package com.ggs.spring5.junit;


import com.ggs.spring5.entity.User;
import com.ggs.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author: Starbug
 * @date: 2020/6/11 23:56
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = {"classpath:bean1.xml"})
@SpringJUnitConfig(locations = {"classpath:bean1.xml"})
public class JUnit5 {
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        userService.transferMoney();
    }
}
