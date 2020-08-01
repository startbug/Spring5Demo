package com.ggs.spring5;

/**
 * @author: Starbug
 * @date: 2020/6/4 18:21
 */
public class Orders {

    private String oname;
    private String address;

    public Orders(String address, String oname) {
        this.address = address;
        this.oname = oname;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "address='" + address + '\'' +
                ", oname='" + oname + '\'' +
                '}';
    }
}
