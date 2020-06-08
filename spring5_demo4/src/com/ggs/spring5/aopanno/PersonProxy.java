package com.ggs.spring5.aopanno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: Starbug
 * @date: 2020/6/8 21:59
 */
@Component
@Aspect
@Order(Integer.MAX_VALUE)
public class PersonProxy {

    @Pointcut(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
    public void pointCut(){
    }
    @Before(value = "pointCut()")
    public void before(){
    System.out.println("person before");
    }

}
