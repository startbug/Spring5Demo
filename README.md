# Spring5(官网spring.io)

## Spring框架概述

![Spring5!jWW](F:\study\Spring5\笔记源码资料\笔记\笔记\分析图\Spring5模块.bmp)

1. **Spring是轻量级的开源的JavaEE框架**

2. **Spring可以解决企业应用开发的复杂性**

3. **Spring有两个核心部分: IOC 和 Aop**

   (1)IOC(Inversion of Control): 控制反转, 把创建对象过程交给Spring进行管理

   (2)Aop(Aspect Oriented Programming): 面向切面, 不修改源代码进行功能增强

4. **Spring特点**

   (1)方便解耦, 简化开发

   (2)Aop编程支持

   (3)方便程序测试

   (4)方便和其他框架进行整合

   (5)方便进行事务操作

   (6)降低API开发难度

5. **此次使用Spring版本5.x, 仓库地址: https://repo.spring.io/**

 

## 入门案例

### 1.创建一个普通Java项目

### 2.导入Spring5相关jar包

Beans和Core组成IOC容器, Context是上下文, Expression是表达式

![image-20200602224258753](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200602224258753.png)

需要导入的基本包(日志包不是Spring自带的,需要自行导入,否则报错)

![image-20200602224612952](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200602224612952.png)

### 3.创建User对象用于测试

```Java
public class User {

  public void add() {
    System.out.println("add......");
  }

}
```

### 4.创建配置文件

路径: src下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 配置User对象创建 -->
    <bean id="user" class="com.ggs.spring5.User"></bean>

</beans>
```

### 5.进行测试代码编写

```Java
  @Test
  public void testAdd() {
    // 1.加载spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

    // 2.获取配置创建对象
    User user = context.getBean("user", User.class);

    System.out.println(user);
    user.add();
  }
```



## Spring5框架

1. IOC容器

​	(1)IOC底层原理

​	(2)IOC接口(BeanFactory)

​	(3)IOC操作Bean管理(基于xml)

​	(4)IOC操作Bean管理(基于注解)



### IOC(概念和原理)

- 什么是IOC

  (1)控制反转, 把对象创建和对象之间的调用过程, 交给Spring进行管理

  (2)使用IOC的目的: 为了耦合度降低

  (3)入门案例使用的就是IOC实现

- IOC底层原理

  (1)xml解析、工厂模式、反射

- 图解IOC底层原理

  ![image-20200602233511909](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200602233511909.png)



### IOC(接口)

**1、IOC思想基于IOC容器完成, IOC容器底层就是对象工厂**

**2、Spring提供IOC容器实现两种方式: (两个接口J)**

​	(1)BeanFactory: IOC容器基本实现, 是Spring内容的使用接口, 不提供(推荐)开发人员进行使用

​		* 加载配置文件时候不会创建对象, 在获取对象(使用)的时候才去创建对象

​	(2)ApplicationContext: BeanFactory接口的子接口,提供更多更强大的功能, 一般由开发人员进行使用

​		* 加载配置文件时候就会把配置文件的对象进行创建

**3、Application接口所有实现类**

ClassPathXmlApplicationContext: 读取配置文件的路径是类路径下

FileSystemXmlApplicationContext:读取配置文件的路径是在硬盘下的全路径C:/xx/xx

![image-20200602235408361](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200602235408361.png)



### IOC操作Bean管理

1. 什么是Bean管理

   (0)Bean管理指的是两个操作

   (1)Spring创建对象

   (2)Spring注入属性

2. Bean管理操作有两种方式

   (1)基于xml配置文件的方式

   (2)基于注解方式实现



#### IOC操作Bean管理(基于xml方式)

​	**1.基于xml方式创建对象**

​	![image-20200604180443168](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200604180443168.png)

​	(1)在Spring配置文件中,使用bean标签,标签里面添加对应属性,就可以实现对象创建

​	(2)在bean标签有很多属性,介绍常用的属性

 		id属性: 唯一标识

​		class属性: 类全路径(包类路径)
​	(3)创建对象的时候,默认执行无参构造方法完成对象的创建

​	**2.基于xml方式注入属性**

​		DI: 依赖注入, 就是注入属性

​		**1)第一种注入方式: 使用setter方法进行注入**

​			(1)创建类, 定义属性和对应的set方法

```Java
/**
 * @author: Starbug
 * @date: 2020/6/4 18:07
 * 演示set方法进行注入属性
 */
public class Book {

    //创建属性
    private String bname;
    private String bauthor;
    //创建属性对应的set方法
    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                '}';
    }
}
```

​			(2)在Spring配置文件配置对象创建,配置属性注入

```xml
<!-- set方法注入属性 -->
<bean id="book" class="com.ggs.spring5.Book">
    <!--
        使用property完成属性注入
        name: 类里面属性名称
        value: 向属性注入的值
    -->
    <property name="bname" value="=论语="></property>
    <property name="bauthor" value="老子"></property>
</bean>
```

​		

​		**2)第二种注入方式: 使用有参构造进行注入**

​	<constructor-arg></constructor-arg>标签中可以使用name指定属性,也可以使用索引指定属性(索引按照构造方法中的参数列表的先后顺序进行排列)

```xml
<!-- 3 有参构造注入属性-->
<bean id="orders" class="com.ggs.spring5.Orders">
    <constructor-arg index="0" value="我的家"></constructor-arg>
    <constructor-arg name="oname" value="红米red30"></constructor-arg>
</bean>
```

​		

​		**3)第三种注入方式: p名称空间注入**(了解)

​		第一步: 导入p名称空间

![image-20200604183428443](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200604183428443.png)

​		第二步: 在bean标签内使用p名称空间的属性进行赋值

```xml
<!-- 4 p名称空间注入属性值 首先要导入P名称空间 -->
<bean id="book" class="com.ggs.spring5.Book" p:bauthor="老王" p:bname="邻居传奇">
</bean>
```



-----------------

#### IOC操作Bean管理(xml注入其他类型属性)

**1、字面量(固定值的意思)**

​	(1)null值

```xml
<!-- null值 -->
<property name="address">
    <null/>
</property>
```

​	(2)属性值包含特殊符号

​	<font color=red>有特殊符号(错误方式写法)</font>

```xml
<property name="address" value="<<南京>>"></property>
```

​	<font color=lightgreen>正确写法方式一</font>

​	把< > 符号进行转义\&lt; \&gt;

```xml
<!--属性值包含特殊符号-->
<property name="address" value="&lt;&lt;广东&gt;&gt;"></property>
```

![image-20200604185407256](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200604185407256.png)

​	<font color=lightgreen>正确写法方式二</font>

把特殊符号内容写到CDATA中, 格式: <![CDATA[内容]]>

```xml
<!--属性值包含特殊符号-->
<property name="address">
    <value><![CDATA[<<广东>>]]></value>
</property>
```

![image-20200604185419913](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200604185419913.png)



**2、注入属性-外部bean**

​	(1)创建两个类service类和dao类

​	(2)在service调用dao里面的方法

​	(3)在spring配置文件中进行配置

Service类

```Java
public class UserService {

  //创建UserDao类型属性, 生成set方法
  private UserDao userDao;

  public void add() {
    System.out.println("service add+++++++++++++++++++");
    userDao.update();
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
```

配置文件

```xml
    <!-- 创建service和dao对象 -->
    <bean id="userService" class="com.ggs.spring5.service.UserService">
        <!--
            注入UserDao对象
            name属性: 类里面属性名称
            ref属性: 创建UserDao对象bean标签的id值
         -->
        <property name="userDao" ref="userDao"></property>

    </bean>
    <bean id="userDao" class="com.ggs.spring5.dao.UserDaoImpl"></bean>
```

**3、内部属性-内部bean**

(1)一对多关系: 部门和员工

一个部门有多个员工, 一个员工属于一个部门

部门是一, 员工是多

(2)在实体类之间表示一对多关系,员工表示所属部门,使用对象类型属性进行表示

```java
/**
 * @author: Starbug
 * @date: 2020/6/4 22:33
 * 员工类
 */
public class Emp {

  private String id;
  private String ename;
  //员工属于某个部门,使用对象的形式表示
  private Dept dept;

  public void setId(String id) {
    this.id = id;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public void setDept(Dept dept) {
    this.dept = dept;
  }

  public void printInfo() {
    System.out.println(id + "::" + ename + "::" + dept);
  }
}
```

```
/**
 * @author: Starbug
 * @date: 2020/6/4 22:33
 * 部门类
 */
public class Dept {

  private String dname;

  public void setDname(String dname) {
    this.dname = dname;
  }

  @Override
  public String toString() {
    return "Dept{" + "dname='" + dname + '\'' + '}';
  }
}
```

(3)Spring配置文件的配置

```xml
    <!--内部bean-->
    <bean id="emp" class="com.ggs.spring5.bean.Emp">
        <!--设置两个普通属性-->
        <property name="id" value="1"></property>
        <property name="ename" value="露西"></property>
        <property name="dept">
            <bean id="dept" class="com.ggs.spring5.bean.Dept">
                <property name="dname" value="销售部"></property>
            </bean>
        </property>
    </bean>
```

**4、注入属性-级联赋值**

①第一种写法

```xml
<!--级联赋值方式一-->
<bean id="emp" class="com.ggs.spring5.bean.Emp">
    <!--设置两个普通属性-->
    <property name="id" value="1"></property>
    <property name="ename" value="露西"></property>
    <!--级联赋值-->
    <property name="dept" ref="dept"></property>
</bean>
<bean id="dept" class="com.ggs.spring5.bean.Dept">
    <property name="dname" value="物理部"></property>
</bean>
```

②第二种写法

```xml
<!--级联赋值方式二-->
<bean id="emp" class="com.ggs.spring5.bean.Emp">
    <property name="id" value="2"></property>
    <property name="ename" value="凯德"></property>
    <property name="dept" ref="dept"></property>
    <property name="dept.dname" value="技术部"></property>
</bean>
<bean id="dept" class="com.ggs.spring5.bean.Dept">
</bean>
```

注意:方式二,需要给dept属性添加get方法

```java
  public Dept getDept() {
    return dept;
  }
```



#### IOC操作Bean管理(xml注入集合属性)

```Java
  // 1 数组类型属性
  private String[] courses;
  // 2 list集合类型
  private List<String> list;
  // 3 map集合类型
  private Map<String, String> maps;
  // 4 set集合类型
  private Set<String> sets;
```

**1、注入数据类型属性**

```xml
<property name="courses">
    <array>
        <value>java课程</value>
        <value>php课程</value>
    </array>
</property>
```

**2、注入List集合类型属性**

```xml
<property name="list">
    <list>
        <value>张三</value>
        <value>李思思</value>
    </list>
</property>
```

**3、注入Map集合类型属性**

```xml
<property name="maps">
    <map>
        <entry key="JAVA" value="java"></entry>
        <entry key="PHP" value="php"></entry>
    </map>
</property>
```

**4、注入Set集合类型属性**

```xml
<property name="sets">
    <set>
        <value>啊啊啊</value>
        <value>lllllllllll</value>
    </set>
</property>
```

**5、在集合里面设置对象类型值**

```Java
  // 多门课程
  List<Course> courseList;
```

创建多个对象

```xml
    <!--创建多个course对象-->
    <bean id="course1" class="com.ggs.spring5.collection.type.Course">
        <property name="cname" value="Spring5"></property>
    </bean>
    <bean id="course2" class="com.ggs.spring5.collection.type.Course">
        <property name="cname" value="Mybatis"></property>
    </bean>
```

注入对象值

```xml
<!--注入List集合类型,值是对象类型-->
<property name="courseList">
    <list>
        <ref bean="course1"></ref>
        <ref bean="course2"></ref>
    </list>
</property>
```



**6、把集合注入部分提取出来(公共使用)**

```java
public class Book {

  private List<String> list;

  public void setList(List<String> list) {
    this.list = list;
  }
}
```

第一步: 导入util命名空间

![image-20200604232120962](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200604232120962.png)

第二步:使用util命名空间生成list集合进行使用

```xml
<!--1 提取list集合类型属性注入-->
<util:list id="bookList">
    <value>西游记</value>
    <value>九阳神功</value>
    <value>龙族</value>
</util:list>

<!--2 提取list集合类型属性注入使用-->
<bean id="book" class="com.ggs.spring5.collection.type.Book">
    <property name="list" ref="bookList"></property>
</bean>
```



-----------------



#### IOC操作Bean管理(FactoryBean)

**1、Spring有两种类型的bean, 一种是普通bean,另一种是工厂Bean(FactoryBean)**

**2、普通bean: 在配置文件中定义bean类型就是返回类型**

**3、工厂bean: 在配置文件中定义bean类型可以和返回类型不一样**

第一步: 创建类, 实现FactoryBean接口,让这个类作为工厂类

第二步: 实现接口中的方法,在实现的方法中定义返回bean类型(通过泛型指定)

① 工厂bean

```Java
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
```

②配置文件

```xml
<bean id="myBean" class="com.ggs.spring5.factorybean.MyBean">
</bean>
```

③测试用例

注意: getBean的类型要指定为工厂Bean实际返回的类型

```Java
@Test
public void testFactoryBean() {
    BeanFactory context = new ClassPathXmlApplicationContext("bean3.xml");
    Course course = context.getBean("myBean", Course.class);
    System.out.println(course);
}
```



--------



#### IOC操作Bean管理(bean作用域)

1、 在Spring里面, 设置创建bean实例是单实例还是多实例?

答: 在Spring里面,默认情况下,bean是单实例对象(singleton)

​	默认情况下: 地址相同

![image-20200605192353800](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605192353800.png)

2、如何配置多实例

(1)在Spring配置文件bean标签内,使用scope属性,用于指定多例还是单例

(2)scope属性值:

第一个值: 默认值, singleton, 表示单实例对啊ing

第二个值: prototype,表示多实例对象

![image-20200605192637471](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605192637471.png)

测试: 多例 ,地址不相同

![image-20200605192708098](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605192708098.png)



(3)singleton和prototype区别

第一 singleton单实例, prototype多实例

第二 设置scope值为singleton时,加载Spring配置文件的时候就会创建实例对象

​		 设置scope值为prototype时, 不会再加载Spring配置文件的时候创建实例对象, 而是在用的时候再创建对象(调用getBean方法获取对象的时候)



-----------

IOC操作Bean管理(bean生命周期)

**1、生命周期**

​	(1)从对象创建到对象销毁的过程

**2、Spring管理的bean的生命周期(简化 5 步)**

​	(1)通过构造器创建bean实例

​	(2)为bean的属性设置值和对其他bean的引用(调用set方法设置)

​	(3)调用bean的初始化方法(需要配置初始化方法)

​	(4)bean已经可以使用

​	(5)容器关闭的时候, 调用bean的销毁的方法(需要进行配置销毁的方法)

**3、测试bean生命周期:**

​	创建一个Orders对象

```Java
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
```

配置文件设置

```xml
<bean id="orders" class="com.ggs.spring5.bean.Orders" init-method="iniMethod" destroy-method="destroy">
    <property name="oname" value="电脑"></property>
</bean>
```

测试用例

```Java
@Test
public void testOrders() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
    Orders orders = context.getBean("orders", Orders.class);
    System.out.println("第四步: 实例对象使用ing");
    System.out.println(orders);

    ((ClassPathXmlApplicationContext) context).close();
}
```

测试结果:

![image-20200605195307874](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605195307874.png)



**4、bean生命周期有7步(加上bean的后置处理器)**

第一步: 在以上的代码基础之上, 再建一个类实现BeanPostProcessor接口,并重写前置处理方法和后置处理方法

```java
/**
 * @author: Starbug
 * @date: 2020/6/5 20:09
 */
public class MyBeanPost implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("Bean初始化的前置处理");
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("Bean初始化的后置处理");
    return bean;
  }
}
```

将MyBeanPost类注册到IOC容器中

```xml
<bean id="beanPost" class="com.ggs.spring5.bean.MyBeanPost"></bean>
```

再次调用测试用例, 结果(共七步): 

![image-20200605201435200](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605201435200.png)



----------

#### IOC操作Bean管理(xml自动装配)

**1、 什么是自动装配**

​	根据指定装配规则(属性名称或者属性类型), Spring自动将匹配的属性值进行注入

2、演示自动装配过程

​	(1)根据属性名称自动注入 autowire="byName"

​	(2)根据属性类型自动注入 autowire="byType"

```xml
<!-- 演示根据类型进行装配 -->
<bean id="emp" class="com.ggs.spring5.autowired.Emp" autowire="byType"></bean>
<bean id="dept" class="com.ggs.spring5.autowired.Dept"></bean>
```



--------

#### IOC操作Bean管理(外部属性文件)

​	(1)引入的路与连接池依赖jar包

​	(2)引入外部属性文件配置数据库连接池

​	(3)配置德鲁伊连接池

```xml
<!--配置连接池-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${prop.driverClassName}"></property>
    <property name="url" value="${prop.url}"></property>
    <property name="username" value="${prop.username}"></property>
    <property name="password" value="1${prop.password}"></property>
</bean>

<!--引入外部属性文件-->
<context:property-placeholder location="classpath:jdbc.properties" />
```



-----

#### IOC操作Bean管理(基于注解方式)

**1、什么是注解**

（1）注解是代码特殊标记，格式：@注解名称(属性名称=属性值, 属性名称=属性值..)

（2）使用注解，注解作用在类上面，方法上面，属性上面

（3）使用注解目的：简化xml配置

**2、Spring针对Bean管理中创建对象提供注解**

（1）@Component

（2）@Service

（3）@Controller

（4）@Repository

​	上面四个注解功能是一样的，都可以用来创建bean实例

**3、基于注解方式实现对象创建**

第一步引入依赖(AOP包)

![image-20200605215816987](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200605215816987.png)

第二步开启组件扫描

```xml
<!--
    开启组件扫描
        1 如果扫描多个包，多个包使用逗号隔开
        2 扫描包上层目录
-->
<context:component-scan base-package="com.ggs.spring5"></context:component-scan>
```

第三步 创建类，在类上面添加创建对象注解

```java
//在注解里面value属性值可以省略不写，
// 默认值是类名称，首字母小写
// UserService --userService
@Component(value = "userService")  //<bean id="userService" class="..></bean>
public class UserService {
    public void add(){
    System.out.println("add....");
    }
}
```

**4、开启组件扫描的细节配置**

**示例1**

默认扫描base-package指定包及子包下的所有类

以下配置表示,只扫描com.ggs包及子包下表有Controller注解的类

```xml
<!-- 示例1
    use-default-filters="false" 表示现在不使用默认filter，只使用自己配置filter
    context:include-filter ，设置扫描哪些内容
-->
<context:component-scan base-package="com.ggs" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

**示例2**

以下配置表示扫描com.ggs包及子包下除了标注@Controller注解之外的所有类

```xml
<!-- 
    下面配置扫描包所有内容
    context:exclude-filter：设置哪些内容不进行扫描
-->
<context:component-scan base-package="com.ggs">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

**5、基于注解方式实现属性注入**

**(1)@Autowired: 根据属性类型进行自动注入**  <font color=lightgreen>tips:Spring注解</font>

第一步: 把service和dao对象创建，在service和dao类添加创建对象注解

第二步: 在service注入dao对象，在service类添加dao类型属性，在属性上面使用注解

测试

```Java
@Service // <bean id="userService" class="..></bean>
public class UserService {

  // 定义dao类型属性
  // 不需要添加set方法
  // 添加注入属性注解
  @Autowired //根据类型进行注入
  private UserDao userDao;

  public void add() {
    System.out.println("add....");
    userDao.add();
  }
}
```



---------

**(2)@Qualifier: 根据属性名称进行注入**   <font color=lightgreen>tips:Spring注解</font>

@Qualifier注解的使用, 和上面@Autowired一起使用, 否则报错(java.lang.NullPointerException)

```Java
  // 定义dao类型属性
  // 不需要添加set方法
  // 添加注入属性注解
  @Autowired //根据类型进行注入
  @Qualifier("userDaoImpl1")//根据名称进行注入
  private UserDao userDao;
```

因为@Autowired是根据类型进行注入的,当有多个同类型的类时,Spring框架无法分辨到底该注入哪一个bean,这时需要使用@Qualifier注解,在@Autowired指定的类型之下,再指定bean的名字完成bean的注入



--------------------------

**(3)@Resource: 可以根据类型注入,可以根据名称注入**   <font color=lightgreen>tips:JDK注解</font>

不加name属性值,则根据类型注入; 

加入name属性值, 则根据名称注入

```java
//  @Resource //根据类型进行注入
  @Resource(name = "userDaoImpl1") //根据名称进行注入
  private UserDao userDao;
```

----------

@Autowired和@Qualifier是Spring提供的注解,@Resource是JDK提供的注解;

@Autowired和@Resource都能完成相应的功能,但是Spring官方更推荐使用@Autowired

---------



**(4)@Value: 注入普通类型属性**

```java
  @Value(value = "abc")
  private String name;
```



----------------------------



**6、完全注解开发**

(1)创建配置类, 代替xml配置文件

```java
@Configuration  //作为配置类,代替xml配置文件
@ComponentScan(basePackages = "com.ggs")
public class SpringConfig {
}
```

(2)测试用例

```java
@Test
public void testServicec2() {
    // 加载配置类,代替加载xml配置文件
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    UserService userService = context.getBean("userService", UserService.class);
    System.out.println(userService);
    userService.add();
}
```



--------------------

### AOP(概念)

**1、 什么是AOP**

(1)面向切面编程(方面),利用AOP可以对业务逻辑的各个部分进行隔离,从而使得业务逻辑各个部分之间的耦合度降低, 提高程序的可重用性, 同时提高开发的效率

(2)通俗描述：不通过修改源代码方式，在主干功能里面添加新功能

(3)使用登录例子说明AOP



----------

### AOP(底层原理)

1、AOP底层使用动态代理

(1)有两种情况动态代理

第一种: 有接口的情况, 使用JDK代理

- 创建接口实现类代理对象, 增强类的方法

  ![image-20200606192350302](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606192350302.png)

第二种: 没有接口, 使用CGLIB动态代理

- 创建子类的代理对象, 增强类的方法

![image-20200606192455118](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606192455118.png)



---------

### JDK动态代理

**1、 使用JDK动态代理,使用Proxy类里面的方法创建代理对象**

![image-20200606192924082](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606192924082.png)

​	(1)调用newProxyInstance方法

![image-20200606192943307](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606192943307.png)

方法中有三个参数:

​	第一个参数: 类加载器

​	第二个参数: 增强方法所在的类, 该类所实现的接口, 支持多个接口

​	第三个参数: 实现这个接口InvocationHandler, 创建代理对象, 写增强的方法



**2、编写JDK动态代理的代码**

(1)创建接口,定义方法

```java
public interface UserDao {
  int add(int a, int n);

  String update(String id);
}
```

(2)创建接口实现类, 实现方法

```java
public class UserDaoImpl implements UserDao {
  @Override
  public int add(int a, int b) {
    System.out.println("add方法执行了....");
    return a + b;
  }

  @Override
  public String update(String id) {
    System.out.println("update方法执行了....");
    return id;
  }
}
```

(3)使用Proxy类创建接口代理对象

```Java
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
```



------

### CGLib代理

介绍:

​		CGLIB(Code Generation Library)是一个开源项目！是一个强大的，高性能，高质量的Code生成类库，

​		它可以在运行期扩展Java类与实现Java接口。Hibernate用它来实现PO(Persistent Object 持久化对象)字节码的动态生成。

​		CGLIB是一个强大的高性能的代码生成包。它广泛的被许多AOP的框架使用，例如Spring AOP为他们提供

1、导包

```xml
<dependency>
    <groupId>asm</groupId>
    <artifactId>asm</artifactId>
    <version>3.3.1</version>
</dependency>
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>2.2.2</version>
</dependency>
```

2、创建一个类(要增强的类)

```java
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
```

3、创建一个MethodInterceptor接口的实现类

```java
/**
 * @author: Starbug
 * @date: 2020/6/6 21:38 自定义MethodInterceptor
 */
public class MyMethodInterceptor implements MethodInterceptor {
  /**
   * @param proxyObj cglib生成的代理对象
   * @param method 被代理对象的方法
   * @param args  方法的入参
   * @param methodProxy 代理方法
   * @return
   * @throws Throwable
   */
  @Override
  public Object intercept(Object proxyObj, Method method, Object[] args, MethodProxy methodProxy)
      throws Throwable {
    System.out.println(method.getName() + "被执行,参数有: " + Arrays.toString(args));

    Object result = methodProxy.invokeSuper(proxyObj, args);

    System.out.println("方法执行后的返回值: " + result);
    return result;
  }
}
```

4、测试类

```java
/**
 * @author: Starbug
 * @date: 2020/6/6 21:42
 */
public class CGLibProxy {
  public static void main(String[] args) {
    // 通过CGLIB动态代理获取代理对象的过程
    Enhancer enhancer = new Enhancer();
    // 设置enhancer对象的父类
    enhancer.setSuperclass(HelloService.class);
    // 设置enhancer的回调对象
    enhancer.setCallback(new MyMethodInterceptor());
    // 创建代理对象
    HelloService proxy = (HelloService) enhancer.create();
    // 通过代理对象调用目标方法
    System.out.println("-----------------------------");
    proxy.sayOthers("xxx");
    System.out.println("-----------------------------");
    int sum = proxy.sum(1, 3);
    System.out.println(sum);
  }
}
```

5、结果: final修饰的方法无法被增强,只有非final才有被增强的效果

![image-20200606221019145](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606221019145.png)

小结:

​	CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
​    因为是继承，所以该类或方法最好不要声明成final



--------

### AOP(术语)

**1、连接点**

​	<font color=pink>类里面可以被增强的方法,称之为连接点</font>

**2、切入点**

​	<font color=lightgreen>实际被增强的方法,称为切入点</font>

**3、通知(增强)**

​	(1)实际增强的逻辑部分称为通知(增强)

​	(2)通知有五种类型

​		<font color=lightgreen>①前置通知</font>

​		<font color=lightgreen>②后置通知</font>

​		<font color=lightgreen>③环绕通知</font>

​		<font color=lightgreen>④异常通知</font>

​		<font color=lightgreen>⑤最终通知</font>

​	

**4、切面**

​	<font color=skyblue>是一个动作, 把通知应用到切入点的过程</font>



--------

### AOP操作(准备)

**1、Spring框架一般都是基于AspectJ实现AOP操作**

​	(1)什么是AspectJ

​		<font color=red>AspectJ不是Spring组成部分,  是独立的AOP框架, 一般把AspectJ和Spring框架一起使用, 进行AOP操作</font>

**2、基于AspectJ实现AOP操作**

​	(1)基于xml配置文件实现

​	(2)基于注解方式实现(常用)

**3、在项目工程里面引入AOP相关依赖**

Spring源码包中的两个aspects和aop

![image-20200606223257377](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606223257377.png)

![image-20200606223311194](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606223311194.png)

额外的三个jar包(与aspects一起使用)

![image-20200606223227597](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200606223227597.png)



**4、切入点表达式**

(1)切入点表达式作用：知道对哪个类里面的哪个方法进行增强
(2)语法结构：execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )

​    举例1：对com.atguigu.dao.BookDao类里面的add进行增强
​    execution(*com.atguigu.dao.BookDao.add(..))



​    举例2：对com.atguigu.dao.BookDao类里面的所有的方法进行增强
​    execution(*com.atguigu.dao.BookDao.*(..))



​	举例3：对com.atguigu.dao包里面所有类，类里面所有方法进行增强

​	execution(*com.atguigu.dao.*.*(..))



------

### AOP操作(AspectJ注解)

**1、创建类，在类里面定义方法**

```java
@Component
public class User {
  public void add() {
    System.out.println("add....");
  }
}
```

**2、创建增强类(编写增强逻辑)**

```java
/**
 * @author: Starbug
 * @date: 2020/6/6 23:15
 * 增强的类
 */
@Component
public class UserProxy {

  public void before() {
    System.out.println("before");
  }
}
```

**3、进行通知的配置**

(1)在Spring配置文件中,开启注解扫描

```xml
<!--开启注解扫描-->
<context:component-scan base-package="com.ggs.spring5.aopanno"></context:component-scan>
```

(2)在增强类上添加@Aspect注解

```java
@Aspect
public class UserProxy {
```

(3)在Spring配置文件中开启生成代理对象

```xml
<!--开启Aspect生成代理对啊ing-->
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```

**4、配置不同类型的通知**

(1)在增强类里面,在作为通知方法上面添加<font color=red>通知类型</font>注解,使用切入点表达式配置

```java
/**
 * @author: Starbug
 * @date: 2020/6/6 23:15 增强的类
 */
@Component
@Aspect
public class UserProxy {
  // 前置通知
  @Before(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void before() {
    System.out.println("before");
  }

  // 后置通知(返回通知)
  @AfterReturning(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void afterReturning() {
    System.out.println("AfterReturning");
  }

  //最终通知
  @After(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void after(){
    System.out.println("after");
  }

  //异常通知
  @AfterThrowing(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void afterThrowing(){
    System.out.println("AfterThrowing");
  }

  //环绕通知通知
  @Around(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
  public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    System.out.println("环绕之前");

    //执行被增强的方法
    proceedingJoinPoint.proceed();

    System.out.println("环绕之后");
  }
}
```

5、相同切入点抽取

```java
//相同切入点抽取
@Pointcut(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
public void pointCut(){
}
// 前置通知
//  @Before(value = "execution(* com.ggs.spring5.aopanno.User.add(..))")
@Before(value = "pointCut()")
public void before() {
    System.out.println("before");
}
```

6、有多个增强类对同一个方法进行增强,设置增强类优先级

(1)再增强类上添加@Order(数字类型值), 数字类型值越小优先级越高

```java
@Component
@Aspect
@Order(Integer.MAX_VALUE)
public class PersonProxy {
```





-------

### AOP操作(AspectJ配置文件)

1、创建两个类,增强类和被增强类,创建方法

2、在Spring配置文件中注册这两个对象

```xml
<!--注册两个对象-->
<bean id="book" class="com.ggs.spring5.aopxml.Book"></bean>
<bean id="bookProxy" class="com.ggs.spring5.aopxml.BookProxy"></bean>
```

3、 在Spring配置文件中配置切入点

```xml
<!--配置aop增强-->
<aop:config>
    <!--切入点-->
    <aop:pointcut id="pointCut" expression="execution(* com.ggs.spring5.aopxml.Book.addBook(..))"/>
    <!--配置切面,引用增强类 -->
    <aop:aspect ref="bookProxy">
        <!--指定前置方法,也还可以指定其他方法-->
        <aop:before method="before" pointcut-ref="pointCut"></aop:before>
    </aop:aspect>
</aop:config>
```



------

### JdbcTemplate(理解和准备)

**1、JdbcTemplate是什么**

​	(1)Spring框架对JDBC进行封装,使用JdbcTemplate方便对数据库进行操作

**2、导入jar包**

orm包用于整合其他框架操作数据库

![image-20200608235054427](C:\Users\Starbug\AppData\Roaming\Typora\typora-user-images\image-20200608235054427.png)



**3、配置Spring配置文件**

```xml
<!--开启包扫描-->
<context:component-scan base-package="com.ggs.spring5"></context:component-scan>

<!--读取jdbc配置信息-->
<context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
<!--配置数据库连接池-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc_driver}"></property>
    <property name="url" value="${jdbc_url}"></property>
    <property name="username" value="${jdbc_username}"></property>
    <property name="password" value="${jdbc_password}"></property>
</bean>
<!--jdbcTemplate对象-->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!--注入dataSource-->
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

4、创建dao和service进行测试,dao中注入jdbcTemplate对象

Dao层

```java
@Repository
public class BookDaoImpl implements BookDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
```

Service层

```java
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
}
```



