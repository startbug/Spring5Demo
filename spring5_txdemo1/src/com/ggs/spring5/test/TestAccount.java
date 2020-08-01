package com.ggs.spring5.test;

import com.ggs.spring5.config.TxConfig;
import com.ggs.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author: Starbug
 * @date: 2020/6/10 22:24
 */
public class TestAccount {

    @Nullable
    private String name;

    @Test
    public void testAnnoTransactional(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.transferMoney();
    }

    @Test
    public void testXmlTransactional(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService", UserService.class);
//        userService.transferMoney();
        userService.zhuanzhang();
    }

    @Test
    public void testWholeAnnoTransactional(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.transferMoney();
//        userService.zhuanzhang();
    }

}
