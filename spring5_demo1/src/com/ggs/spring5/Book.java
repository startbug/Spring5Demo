package com.ggs.spring5;

/**
 * @author: Starbug
 * @date: 2020/6/4 18:07
 * 演示set方法进行注入属性
 */
public class Book {

    //创建属性
    private String bname;
    private String bauthor;
    private String address;
    //创建属性对应的set方法
    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setAddress(String address){
        this.address=address;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
