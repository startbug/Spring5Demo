package com.ggs.spring5.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: Starbug
 * @date: 2020/6/6 21:38 自定义MethodInterceptor
 */
public class MyMethodInterceptor implements MethodInterceptor {
  /**
   * @param proxyObj cglib生成的代理对象
   * @param method 被代理对象的方法
   * @param args  方法的入参
   * @param methodProxy 代理方法
   * @return
   * @throws Throwable
   */
  @Override
  public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.println(method.getName() + "被执行,参数有: " + Arrays.toString(args));

    Object result = methodProxy.invokeSuper(proxyObj, args);

    System.out.println("方法执行后的返回值: " + result);
    return result;
  }
}
