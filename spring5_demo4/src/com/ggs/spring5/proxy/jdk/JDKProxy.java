package com.ggs.spring5.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author: Starbug
 * @date: 2020/6/6 19:51
 * 创建接口实现类代理对象
 */
public class JDKProxy {
  public static void main(String[] args) {
    Class[] interfaces = {UserDao.class};

    //      匿名类写法,不太好理解
    //      Object proxyObj = Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces,
    // new InvocationHandler() {
    //          @Override
    //          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //              return null;
    //          }
    //      });

    UserDao userDao = new UserDaoImpl();

    UserDao proxyUserDao =
        (UserDao)
            Proxy.newProxyInstance(
                JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));

    proxyUserDao.update("1");
//    proxyUserDao.add(1,2);
  }
}

/** 创建代理对象的代码: 需要传入被增强对象的实例,这里通过构造方法进行传参 */
class UserDaoProxy implements InvocationHandler {

  // 创建谁的代理对象, 就把谁传入进来, 形参为Object, 更加通用
  private Object obj;

  public UserDaoProxy(Object obj) {
    this.obj = obj;
  }

  /**
   * @param proxy 根据调试信息,该参数是当前类(UserDaoProxy)自己的代理对象
   * @param method 当前调用的方法
   * @param args 当前调用方法的实参
   * @return
   * @throws Throwable invoke内写增强的逻辑
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("执行" + method.getName() + "方法,参数为:" + Arrays.toString(args));

    Object result = method.invoke(obj,args);

    System.out.println(method.getName() + "方法执行的返回值为:" + result);

    return result;
  }
}
