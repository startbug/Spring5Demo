package com.ggs.spring5.aopanno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: Starbug
 * @date: 2020/6/6 23:15 增强的类
 */
@Component
@Aspect
public class UserProxy {
  // 前置通知
  @Before(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void before() {
    System.out.println("before");
  }

  // 后置通知(返回通知
  @AfterReturning(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void afterReturning() {
    System.out.println("AfterReturning");
  }

  //最终通知
  @After(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void after(){
    System.out.println("after");
  }

  //异常通知
  @AfterThrowing(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void afterThrowing(){
    System.out.println("AfterThrowing");
  }

  //环绕通知通知
  @Around(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("环绕之前");

    //执行被增强的方法
    proceedingJoinPoint.proceed();

    System.out.println("环绕之后");
  }
}
