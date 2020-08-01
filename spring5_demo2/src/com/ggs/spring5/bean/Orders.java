package com.ggs.spring5.bean;

/**
 * @author: Starbug
 * @date: 2020/6/5 19:44
 */
public class Orders {

  private String oname;

  public Orders() {
    System.out.println("第一步: 调用构造方法创建对象实例");
  }

  public void setOname(String oname) {
    this.oname = oname;
    System.out.println("第二步: 设置对象的属性值或对其他对象的引用(通过set方法)");
  }

  //初始化方法,还需要到配置文件中指定该方法,名字随意
  public void iniMethod(){
    System.out.println("第三步: 调用初始化方法");
  }

  //实例销毁方法,还需要到配置文件中指定该方法,名字随意
  public void destroy(){
    System.out.println("第五步: 当IOC容器关闭的时候,会调用该方法进行销毁实例");
  }
}
