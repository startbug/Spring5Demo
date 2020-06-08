package com.ggs.spring5.collection.type;

/**
 * @author: Starbug
 * @date: 2020/6/4 23:07
 * 课程类
 */
public class Course {

    private String cname;//课程名称

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
