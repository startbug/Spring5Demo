package com.ggs.spring5.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author: Starbug
 * @date: 2020/6/6 21:42 
 * 
 * 需要的两个jar包
 * cglib-2.2.2.jar 
 * asm-3.3.1.jar
 */
public class CGLibProxy {
  public static void main(String[] args) {
    // 通过CGLIB动态代理获取代理对象的过程
    Enhancer enhancer = new Enhancer();
    // 设置enhancer对象的父类
    enhancer.setSuperclass(HelloService.class);
    // 设置enhancer的回调对象
    enhancer.setCallback(new MyMethodInterceptor());
    // 创建代理对象
    HelloService proxy = (HelloService) enhancer.create();
    // 通过代理对象调用目标方法
    System.out.println("-----------------------------");
    proxy.sayOthers("xxx");
    System.out.println("-----------------------------");
    int sum = proxy.sum(1, 3);
    System.out.println(sum);
  }
}

