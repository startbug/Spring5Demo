package com.ggs.spring5.collection.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Starbug
 * @date: 2020/6/4 22:52
 */
public class Stu {
  // 1 数组类型属性
  private String[] courses;
  // 2 list集合类型
  private List<String> list;
  // 3 map集合类型
  private Map<String, String> maps;
  // 4 set集合类型
  private Set<String> sets;
  // 多门课程
  List<Course> courseList;

  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
  }

  public void setCourses(String[] courses) {
    this.courses = courses;
  }

  public void setList(List<String> list) {
    this.list = list;
  }

  public void setMaps(Map<String, String> maps) {
    this.maps = maps;
  }

  public void setSets(Set<String> sets) {
    this.sets = sets;
  }

  public void test() {
    System.out.println(Arrays.toString(courses));
    System.out.println(list);
    System.out.println(maps);
    System.out.println(sets);
    System.out.println(courseList);
  }
}
