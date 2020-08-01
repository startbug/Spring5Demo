package com.ggs.reactor.reactor8;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: Starbug
 * @date: 2020/6/12 19:18
 */
public class ObserveDemo extends Observable {
  /**
   * 当数据发生改变,会将观察者中的一个布尔值改为true,触发观察者执行方法
   * 但是没有什么场景可以发生改变,所以调用setChanged()方法手动将boolean值修改为true,强行改变,
   * 然后调用notifyObservers,通知所有观察者
   */
  public static void main(String[] args) {

    ObserveDemo observer = new ObserveDemo();
    ObserveDemo observer1 = new ObserveDemo();
    // 添加观察者
    observer.addObserver(
        (o, arg) -> {
          System.out.println("手动被观察者通知,准备改变");
        });

    observer.addObserver(
        (o, arg) -> {
          System.out.println("发生变化");
        });
    observer.setChanged(); //
    observer.notifyObservers();
  }
}
