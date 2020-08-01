package com.ggs.spring5.factorybean;

import com.ggs.spring5.collection.type.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author: Starbug
 * @date: 2020/6/5 18:56
 */
public class MyBean implements FactoryBean<Course> {

    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("语文");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
