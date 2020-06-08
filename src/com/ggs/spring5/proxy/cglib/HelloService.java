package com.ggs.spring5.proxy.cglib;

/**
 * @author: Starbug
 * @date: 2020/6/6 21:34
 */
public class HelloService {
  public HelloService() {
    System.out.println("HelloService构造");
  }

  /** 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的 */
  public final String sayOthers(String name) {
    System.out.println("HelloService:sayOthers>>" + name);
    return null;
  }

  public int sum(int a,int b){
    return a+b;
  }
}
