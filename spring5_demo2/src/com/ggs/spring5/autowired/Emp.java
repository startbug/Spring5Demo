package com.ggs.spring5.autowired;

/**
 * @author: Starbug
 * @date: 2020/6/5 21:28
 */
public class Emp {

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }
}
