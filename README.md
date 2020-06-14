# Spring5(官网spring.io)

## Spring框架概述

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612150818.bmp)

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

![image-20200602224612952](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612150625.jpg)

需要导入的基本包(日志包不是Spring自带的,需要自行导入,否则报错)

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151039.png)

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

  ![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151104.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151141.png)



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

​	![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151320.png)

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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151345.png)

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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151432.png)

​	<font color=lightgreen>正确写法方式二</font>

把特殊符号内容写到CDATA中, 格式: <![CDATA[内容]]>

```xml
<!--属性值包含特殊符号-->
<property name="address">
    <value><![CDATA[<<广东>>]]></value>
</property>
```

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151455.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151523.png)

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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151536.png)

2、如何配置多实例

(1)在Spring配置文件bean标签内,使用scope属性,用于指定多例还是单例

(2)scope属性值:

第一个值: 默认值, singleton, 表示单实例对啊ing

第二个值: prototype,表示多实例对象

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151548.png)

测试: 多例 ,地址不相同

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151652.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151711.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151737.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151749.png)

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

  ![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151844.png)

第二种: 没有接口, 使用CGLIB动态代理

- 创建子类的代理对象, 增强类的方法

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151856.png)



---------

### JDK动态代理

**1、 使用JDK动态代理,使用Proxy类里面的方法创建代理对象**

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151916.png)

​	(1)调用newProxyInstance方法

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151932.png)

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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612151952.png)

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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152005.png)

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152015.png)

额外的三个jar包(与aspects一起使用)

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152101.png)



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

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152121.png)



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

**4、创建dao和service进行测试,dao中注入jdbcTemplate对象**

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

**5、测试**

实体类

```java
@Data
public class Book {
  private String userId;
  private String username;
  private String ustatus;
}
```

Dao层方法(Service层调用该方法)

```java
public void add(Book book) {
    String sql="insert into t_book(user_id,username,ustatus) values(?,?,?)";
    Object[] args={book.getUserId(),book.getUsername(),book.getUstatus()};
    jdbcTemplate.update(sql,args);
}
```

测试用例

```java
@Test
public void testJdbcTemplate() {
    ApplicationContext context=new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    Book book = new Book("1","xxx","xxx");
    bookService.addBook(book);
}
```

-----------

修改和删除

```java
public void deleteBook(String id) {

    String sql="delete from t_book where user_id=?";
    jdbcTemplate.update(sql,id);
}
public void updateBook(Book book) {
    String sql="update t_book set username=?,ustatus=? where user_id=?";
    Object[] args={book.getUsername(),book.getUstatus(),book.getUserId()};
    jdbcTemplate.update(sql,args);
}
```

------

**6、查询操作**

①查询记录数

```java
public Long selectCount() {
    String sql="select count(*) from t_book";
    Long count = jdbcTemplate.queryForObject(sql, Long.class);
    return count;
}
```

②查询返回对象

第一个参数: sql语句

第二个参数: RowMapper是接口,针对返回不同数据类型数据, 使用这个接口里面的实现类完成数据封装

第三个参数: sql语句参数



```java
public Book findBookInfo(String id) {
    String sql = "select * from t_book where user_id=?";
    Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
    return book;
}
```

③查询多个对象(集合)

```java
public List<Book> findAllBook() {
    String sql="select * from t_book";
    List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    return bookList;
}
```

**7、批量操作**

①批量添加操作

```java
@Override
public void batchAddBook(List<Object[]> batchArgs) {
    String sql="insert into t_book values(?,?,?)";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}
```

```java
@Test
public void testBatchAddBook() {
    ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
    BookService bookService = context.getBean("bookService", BookService.class);
    List<Object[]> batchArgs=new ArrayList<>();
    Object[] o1={"5","java","a"};
    Object[] o2={"6","c++","b"};
    Object[] o3={"7","Mysql","c"};
    batchArgs.add(o1);
    batchArgs.add(o2);
    batchArgs.add(o3);
    bookService.batchAddBook(batchArgs);
}
```

传入一个集合,泛型是一个Object数组

集合中的每一个Object数组,就是一条sql语句的参数

jdbcTemplate会遍历集合,拿出每一条数据,进行插入

②批量修改

```java
public void batchUpdate(List<Object[]> batchArgs) {
    String sql = "update t_book set username=?,ustatus=? where user_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}
```

③批量删除

```java
public void batchDelete(List<Object[]> batchArgs) {
    String sql="delete from t_book where user_id=?";
    int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
    System.out.println(Arrays.toString(ints));
}
```

总结:

​	增改查都是用update方法

​	查询某个值,查询返回对象使用queryForObject方法

​	查询集合,使用query方法



-------

### 事务

事务概念

#### **1、什么是事务**

(1)事务是数据库操作最基本单元,逻辑上一组操作,要么都成功,如果一个失败,所有操作都会失败

(2)典型场景: 转账

​	tom转账100元给lucy

​	tom少100元,lucy多100元

2、事务四个特性(ACID)

以转账为例子说明

(1)**原子性**: 转账要么都成功,要么都失败

(2)**一致性**: 转账前后,总数不变; tom和lucy转账前后两个账户的总和不变

(3)**隔离性**: 多事务之间,操作不会受影响

(4)**持久性:** 修改数据后, 数据库中的数据相应改变,保存起来



#### 2、搭建事务操作环境

**①配置好数据库,增加Dao层的类,编写加钱和减钱的代码**

```java
// lucy转账100给marry
// 加钱
@Override
public void decreaseMoney() {
    String sql = "update t_account set money=money-? where username=?";
    jdbcTemplate.update(sql, 100, "lucy");
}
// 减钱
@Override
public void increaseMoney() {
    String sql = "update t_account set money=money+? where username=?";
    jdbcTemplate.update(sql, 100, "marry");
}
```

Service层执行转账操作

```java
//转账的方法
public void transferMoney(){
    //lucy减钱
    userDao.decreaseMoney();
    
    //int i=10/0; //准备一个异常
    
    //marry加钱
    userDao.increaseMoney();
}
```

----------



#### 3、Spring事务管理介绍

1、事务添加到JavaEE三层结构中的Service层(业务逻辑层)

2、在Spring进行事务管理操作

(1)两种方式: 编程式事务管理(手写事务代码) 和 声明式事务管理(常用)

3、声明式事务管理

​	(1)基于注解方式(常用)

​	(2)基于xml配置文件方式

4、在Spring进行声明式事务管理,底层使用AOP原理

5、Spring事务管理API

​	(1)提供一个接口,代表事务管理器,这个接口针对不同的框架提供不同的实现类

​		==PlatformTransactionManager==接口是Spring进行事务管理的顶层接口

​		关注==AbstractPlatformTransactionManager==类下的实现类,有几个是多余的,实际上是有五个

关注==AbstractPlatformTransactionManager==类下的实现类,有几个是多余的,实际上是有五个

​	![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152141.png)



**4、==注解==声明式事务管理**

1.在Spring配置文件配置事务管理器

```xml
<!--创建事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!--注入数据源-->
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

2.开启事务注解,并且添加事务管理器

```xml
<!--开启事务注解,并且指定事务管理器-->
<tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
```

3.Service类上添加事务注解@Transactional

```java
@Service
@Transactional
public class UserService {
```

--------

#### 4、XML声明式事务管理

1、在Spring配置文件中进行配置

2、配置通知

3、配置切入点和切面

```xml
<!--1.创建事务管理器-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!--注入数据源-->
    <property name="dataSource" ref="dataSource"></property>
</bean>

<!--2.配置通知-->
<tx:advice id="txAdvice">
    <!--配置事务参数-->
    <tx:attributes>
        <!--在aop中配置切入点的范围内,再根据tx:method中的规则对匹配的方法添加事务-->
        <tx:method name="transferMoney" propagation="REQUIRED"/>
        <!--可以使用通配符-->
        <!--<tx:method name="transfer*"></tx:method>-->
    </tx:attributes>
</tx:advice>

<!--3.配置切入点和切面-->
<aop:config>
    <!--配置切入点-->
    <aop:pointcut id="pointCut" expression="execution(* com.ggs.spring5.service.UserService.*(..))"/>
    <!--配置切面-->
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pointCut"></aop:advisor>
</aop:config>
```



------------

5、完全注解开发配置类

```java
@Configuration
@ComponentScan(basePackages = {"com.ggs.spring5"})
@EnableTransactionManagement // 开启事务管理器
public class TxConfig {

  @Bean
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl(
        "jdbc:mysql://localhost:3306/spring5?serverTimezone=GMT%2B8&characterEncoding=UTF-8");
    dataSource.setUsername("root");
    dataSource.setPassword("123456");
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }

  //创建事务管理器
  @Bean
  public TransactionManager transactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }
}
```



-----



#### 6、事务操作参数配置说明

1、在service类上的添加的@Transactional注解,可以配置相关参数

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152159.png)



2、==propagation: 事务传播行为==

​	(1)多事务方法直接进行调用,这个过程中事务是如何进行管理的	

​	总共有七种, 常用的大概有两种

**1.REQUIRED(最常用)**

​	使用当前的事务,如果当前没有食物,则自己创建一个事务

​	如果已经存在事务,则加入这个事务,形成一个整体

​	多用于增删改操作

​	例子:A方法调用B方法, A方法已经开启事务,那么B方法也会在事务中运行

**2.SUPPORTS**(较常用)

​	如果当前存在事务,则使用事务; 如果当前不存在事务, 则不使用事务(有就用,没有就不用,随缘)

​	例子:: A方法调用B方法,如果A方法已经开启事务, B方法就会加入到A方法的事务中

**3.MANDATORY**(不常用)

​	该事务传播属性强制要求必须存在一个事务,如果没有事务存在,则抛出异常

​	例子:A方法调用B方法, 要求A方法必须要有事务,没有则抛出异常,很少用

**4.REQUIRED_NEW**(不常用)

​	如果当前有事务,则挂起该事务,并且自己创建一个新的事务执行(你做你的,我做我的,互不相干)

​	例子:A方法调用B方法,A方法在调用完B方法后,如果之后出了异常,不会影响B方法的运行,B方法自己会开启一个事务,自己去提交

**5.NOT_SUPPORTED**(不常用)

​	如果当前有事务,则把事务挂起,自己无事务的方式运行方法(有事务我也不用)

​	相对于support,在本例中,相当于强制要求方法不使用事务

​	例子: A方法调用B方法,A方法开启了事务,但B方法不会使用A方法的事务

**6.NEVER**(不常用)

​	如果当前存在事务,则抛出异常

​	强制要求父方法没有事务

​	例子: A方法调用B方法,B方法上添加了(@Transactional(propagation = Propagation.NEVER),则要求A方法中没有开启事务,否则会抛出异常;

​	如果B方法又调用C方法,而C方法中有事务,这是被允许的

**7.NESTED**(不常用)

​	父子嵌套的方式运行事务, 当子事务抛出异常之后, 父事务可以决定是否进行回滚

​	例子: A方法调用B方法,用try..catch包裹B方法,如果B方法抛出异常,则可以在catch后做决定,是否进行回滚,回滚则执行rollback,不回滚则继续执行

```java
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
```



3、==isolation: 事务隔离级别==

​	(1)事务有个特性称为隔离性, 多事务操作之间不会产生影响,不考虑隔离性会产生很多问题

​	(2)有三个读问题: 脏读、不可重复读、虚(幻)读

​	(3)<font color=red>脏读</font>: 一个未提交事务读取到另一个未提交事务的数据

	脏读是指在一个事务处理过程里读取了另一个未提交的事务中的数据。
	
	　　当一个事务正在多次修改某个数据，而在这个事务中这多次的修改都还未提交，这时一个并发的事务来访问该数据，就会造成两个事务得到的数据不一致。例如：用户A向用户B转账100元，对应SQL命令如下
	
	　　当只执行第一条SQL时，A通知B查看账户，B发现确实钱已到账（此时即发生了脏读），而之后无论第二条SQL是否执行，只要该事务不提交，则所有操作都将回滚，那么当B以后再次查看账户时就会发现钱其实并没有转。
	
	---------------



​	(4)<font color=red>不可重复读</font>: 一个未提交事务读取到另一个提交事务提交修改的数据

>　　不可重复读是指在对于数据库中的某个数据，一个事务范围内多次查询却返回了不同的数据值，这是由于在查询间隔，被另一个事务修改并提交了。
>
>　　例如事务T1在读取某一数据，而事务T2立马修改了这个数据并且提交事务给数据库，事务T1再次读取该数据就得到了不同的结果，发送了不可重复读。
>
>　　不可重复读和脏读的区别是，脏读是某一事务读取了另一个事务未提交的脏数据，而不可重复读则是读取了前一事务提交的数据。
>
>　　在某些情况下，不可重复读并不是问题，比如我们多次查询某个数据当然以最后查询得到的结果为主。但在另一些情况下就有可能发生问题，例如对于同一个数据A和B依次查询就可能不同，A和B就可能打起来了……
>
>------------------------



(5)<font color=red>幻(虚)读</font>: 一个未提交事务读取到另一个

>幻读是事务非独立执行时发生的一种现象。例如事务T1对一个表中所有的行的某个数据项做了从“1”修改为“2”的操作，这时事务T2又对这个表中插入了一行数据项，而这个数据项的数值还是为“1”并且提交给数据库。而操作事务T1的用户如果再查看刚刚修改的数据，会发现还有一行没有修改，其实这行是从事务T2中添加的，就好像产生幻觉一样，这就是发生了幻读。
>
>　　幻读和不可重复读都是读取了另一条已经提交的事务（这点就脏读不同），所不同的是不可重复读查询的都是同一个数据项，而幻读针对的是一批数据整体（比如数据的个数）。
>
>---------------



(6)解决: 通过设置事务隔离级别,解决读问题(mysql默认隔离级别为可重复读)

|                                  | 脏读 | 不可重复读 | 幻读 |
| :------------------------------: | :--: | :--------: | :--: |
| READ UNCOMMITTED<br />(读未提交) |  有  |     有     |  有  |
|  READ COMMITTED<br />(读已提交)  |  无  |     有     |  有  |
| REPEATABLE READ<br />(可重复读)  |  无  |     无     |  有  |
|    SERIALIZABLE<br />(串行化)    |  无  |     无     |  无  |



```java
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.REPEATABLE_READ)
public class UserService {
```

--------------



4、==time: 超时时间==

​	(1)事务需要在一定时间(内执行完方法)内进行提交, 如果不提交则进行回滚

​	(2)默认值是-1,设置时间以秒单位进行运算

--------



5、readOnly: 是否只读

​	(1) 读: 查询操作, 写: 添加、修改和删除操作

​	(2) 当值为false时,表示可以查询, 也可以进行添加、修改和删除操

​		 当值为true时,表示仅能查询, 不可以进行添加、修改和删除操
​		 默认值为: false



6、rollbackFor: 回滚

​	设置出现哪一些异常进行事务回滚

```java
//设置当只出现空指针异常才进行回滚
@Transactional(rollbackFor = {NullPointerException.class})
```



6、noRollbackFor: 不会滚

​	设置出现哪一些异常不进行事务回滚	

```java
//设置当出现空指针异常的时候不进行回滚
@Transactional(noRollbackFor = {NullPointerException.class})
```

----------



## Spring5新功能

**1、整个Spring5框架代码基于Java8, 运行时兼容JDK9, 许多不建议使用的类和方法在代码库中删除**

**2、Spring5.0框架自带了通用的日志封装**

​	(1)Spring5已经移除Log4jConfigListener, 官方建议使用Log4j2

​	(2)Spring5框架已经整合Log4j2

​		第一步: 引入jar包

​		![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152210.png)

​		第二步: 创建log4j2.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<!--Configuration后面的status用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，可以看到log4j2内部各种详细输出-->
<configuration status="INFO">
    <!--先定义所有的appender-->
    <appenders>
        <!--输出日志信息到控制台-->
        <console name="Console" target="SYSTEM_OUT">
            <!--控制日志输出的格式-->
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </console>
    </appenders>
    <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
    <!--root：用于指定项目的根日志，如果没有单独指定Logger，则会使用root作为默认的日志输出-->
    <loggers>
        <root level="trace">
            <appender-ref ref="Console"/>
        </root>
    </loggers>
</configuration>
```

测试方法: 注意:不要导错包

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Starbug
 * @date: 2020/6/11 22:57
 */
public class LogTest {
  private static final Logger log = LoggerFactory.getLogger(LogTest.class);
  public static void main(String[] args) {
    log.error("error级别的日志");
    log.warn("warn级别的日志");
    log.info("info级别的日志");
    log.debug("debug级别的日志");
    log.trace("trace级别的日志");
  }
}
```

结果: 

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152223.png)



**3、Spring5框架核心容器支持@Nullable注解**

​	(1)Nullable注解可以使用在方法上面,属性上面,参数上面,表示方法返回值可以为空,属性值可以为空,参数值可以为空

​	(2)注解使用在方法上面,方法返回值可以为空

​	![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152238.png)

​	(3)注解使用在方法参数里面,方法参数可以为空

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152249.png)

​	(4)注解使用在属性上面,属性值可以为空

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152257.png)



>----------
>
>==@NonNull==可以标注在方法、字段、参数之上，表示对应的值不可以为空
>==@Nullable==注解可以标注在方法、字段、参数之上，表示对应的值可以为空
>
>以上两个注解在程序运行的过程中不会起任何作用，只会在IDE、编译器、FindBugs检查、生成文档的时候有做提示；
>
>我使用的IDE是STS，不会做自动的检查，只有安装了FindBugs插件并运行后会做对应的提示
>
>摘自CSDN
>
>-----------



**4、Spring5核心容器支持函数式风格GenericApplicationContext**

函数式风格创建对象,交给Spring进行管理

不给bean起名字,就只能通过全类名获取bean,不会像注解注册那样,以类名首字母小写为bean名

```java
@Test
public void testGenericApplicationContext() {
    // 1.创建GenericApplicationContext对象
    GenericApplicationContext context = new GenericApplicationContext();
    // 2.调用context的方法对象注册
    context.refresh(); // 清空容器
    context.registerBean("user11",User.class, () -> new User());
    // 3.获取在spring注册的对象
    User user = (User) context.getBean("user11");
    //    User user = (User) context.getBean("com.ggs.spring5.entity.User");
    System.out.println(user);
}
```

--------



**5、Spring5支持整合JUnit5**

(1)整合JUnit4

第一步: 引入Spring针对测试的依赖

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152307.png)

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152314.png)

第二部: 创建测试类, 使用注解方式完成

```java
@RunWith(SpringJUnit4ClassRunner.class)//单元测试框架
@ContextConfiguration(locations = {"classpath:bean1.xml"}) //加载配置文件
public class JUnit4 {
    @Autowired
    private UserService userService;

    @Test
    public void test1(){
        userService.transferMoney();
    }
}
```



(2)Spring5真个和JUnit5

第一步: 引入JUnit5的jar包

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152322.png)

![](https://starbug.oss-cn-shenzhen.aliyuncs.com/PicBed/20200612152338.png)

​	第二步: 创建测试类, 使用注解完成

​		方式一: 

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:bean1.xml"})
public class JUnit5 {
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        userService.transferMoney();
    }
}
```

​		方式二: 将@ExtendWith和@ContextConfiguration二合一

```java
@SpringJUnitConfig(locations = {"classpath:bean1.xml"})
public class JUnit5 {
    @Autowired
    private UserService userService;

    @Test
    public void test(){
        userService.transferMoney();
    }
}
```

----------



## Spring WebFlux

**1、SpringWebFlux介绍**

​	(1)SpringWebFlux是Spring5新添加的模块, 用于web开发的, 功能和SpringMVC类似的, WebFlux使用当前一种比较流行的方式: 响应式编程;

​	SpringWebFlux是一款响应式编程框架

![](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612182850.png)

​	(2)使用传统web框架, 比如SpringMVC, 这些基于Servlet容器, WebFlux是一种异步非阻塞的框架, 异步非阻塞的框架在Servlet3.1以后才支持, 核心是基于Reactor的相关API进行实现



​	(3) 什么是异步非阻塞

​		异步 和 同步

​			==异步和同步针对调用者==, 调用者发送请求, 如果等着对方回应之后才去做其他事情就是同步; 如果发送请求之后不等待对方的回应就去做其他事情, 就是异步

​			==阻塞和非阻塞针对被调用者==, 被调用者收到请求之后, 做完请求任务之后才给出反馈就是阻塞; 收到请求之后马上给出返回, 然后在做事情, 就是非阻塞

​	

​	(4) WebFlux特点: 

​		第一 非阻塞式: 在有限资源下, 提高系统吞吐量和伸缩性, 以Reactor为基础实现响应式编程

​		第二 函数式编程: Spring5框架基于java8, WebFlux使用Java8函数式编程方式实现路由请求

​	

​	(5) 比较SpringMVC

​		图片来自SpringWebFlux官方文档(https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux)

![image-20200612190413758](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612190413.png)

​			第一: 两个框架都可以使用注解方式, 都可以运行在Tomcat等容器中

​			第二: SpringMVC采用命令式编程, WebFlux采用异步响应式编程

​			说明: SpringMVC方式实现, 同步阻塞的方式, 基于SpringMVC+Servlet+Tomcat

​					  SpringWebFlux方式实现, 异步非阻塞方式, 基于SpringWebFlux+Reactor+Netty

**2、响应式编程(Java实现)**

​	(1)什么是响应式编程

	>响应式编程是一种面向数据流和变化传播的编程范式。这意味着可以在编程语言中很方便地表达静态或动态的数据流，而相关的计算模型会自动将变化的值通过数据流进行传播。

>电子表格程序(Excel)就是响应式编程的一个例子。单元格可以包含字面值或类似"=B1+C1"的公式，而包含公式的单元格的值会依据其他单元格的值的变化而变化

-----百度百科

​	(2)Java8及其之前版本

​		设计模式: 观察者模式

​		JDK8提供两个观察者模式使用的类: Observer 和 Observable		



```java
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
```



**3、 响应式编程(Reactor实现)**

​	(1) 响应式编程操作中, Reactor是满足Reactive规范框架

​	(2) Reactor有两个核心类, Mono 和 Flux, 这两个类实现接口Publisher, 提供丰富操作符.

​			Flux对象实现发布者对象, 返回N个元素

![image-20200612205419360](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612205419.png)

​			

​			Mono实现发布者, 返回0个或1个元素

![image-20200612205442855](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612205442.png)



​	(3)Flux和Mono都是数据流的发布者, 使用Flux和Mono都可以发出三种数据信号: 元素值, 错误信号, 完成信号, 错误信号和完成信号都代表终止信号, 终止信号用于告诉订阅者数据流结束了, 错误信号终止数据流同时把错误信息传递给订阅者



​	(4) 代码演示Flux和Mono

​		①引入依赖

```xml
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-core</artifactId>
    <version>3.1.5.RELEASE</version>
</dependency>
```

​		②代码演示

```java
  public static void main(String[] args) {
    //just方法直接声明
      Flux.just(1,2,3,4);
      Mono.just(1);
      //其他方法
      Integer[] arr={1,2,3,4};
      Flux.fromArray(arr);

      List<Integer> list = Arrays.asList(arr);
      Flux.fromIterable(list);

      Stream<Integer> stream = list.stream();
      Flux.fromStream(stream);
  }
```

​	

​	(5) 三种信号特点

- 错误信号和完成信号都是终止信号, 不能共存
- 如果没有发送任何元素值, 而是直接发送错误或者完成信号, 表示是空数据流
- 如果没有错误信号, 没有完成信号, 表示是无限数据流



​	(6)调用just或其他方法只是声明数据流, 数据流并没有发出, 只有进行订阅之后才发送数据流, 不订阅就执行, 什么都不会发生

​		输出订阅的内容

```java
Flux<Integer> flux = Flux.just(1, 2, 3, 4);
flux.subscribe(System.out::println);

Mono<Integer> mono = Mono.just(1);
mono.subscribe(System.out::println);
```

​		上面代码可以两句代替

```java
Flux.just(1, 2, 3, 4).subscribe(System.out::println);

Mono.just(1).subscribe(System.out::println);
```

​	

​	(7) 操作符

① 对数据流进行一道道操作, 成为操作符, 比如工厂流水线

​	有两种方式, map 和 flatMap

- map: 将原元素映射为新元素

![image-20200612211611919](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612211612.png)

	- flatMap: 元素映射为溜
	- 将每一个元素转换成流, 转换之后多个流合并成大的流

![image-20200612211622182](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612211622.png)

​		

**4、SpringWebFlux执行流程和核心API**

SpringWebWebFlux基于Reactor, 默认使用容器是Netty, Netty是高性能的NIO框架, 异步非阻塞的框架

​	(1)Netty

- BIO(blocking I/O) 阻塞

  如果请求太多, 其他请求就在阻塞在外面,等待被处理

![image-20200612215927984](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612215928.png)



- NIO(non-blocking I/O) 非阻塞

![image-20200612221045311](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612221045.png)

​	

​	(2)SpringWebFlux 执行过程和 SpringMVC类似

- SpringWebFLux核心控制器 ==DispatcherHandler==
- ==DispatcherHandler==实现WebHandler接口
- ==WebHandler==接口有一个handle方法

![image-20200612221332941](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612221333.png)

- =DispatcherHandler==实现类的handle方法

![image-20200612221436677](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200612221436.png)



​	(3)SpringWebFlux里面DispatcherHandler, 负责请求的处理

​		①HandlerMapping: 请求查询到处理的方法 

​		②HandlerAdapter: 真正负责请求处理

​		③HandlerResultHandler: 响应结果处理

​	

​	(4)SpringWebFlux实现函数式编程, 两个接口: RouterFunction(路由处理)和HandlerFunction(处理函数)





### SpringWebFlux(基于注解编程模型)

SpringWebFlux实现方式有两种: 注解编程模型和函数式编程模型

使用注解编程模型方式, 和之前SpringMVC使用相似的, 只需要把相关依赖配置到项目中, SpringBoot自动配置相关运行容器, 默认情况下使用Netty服务器

​	①创建SpringBoot工程, 引入WebFlux依赖和其他相关依赖, 编写配置文件application.yml

```xml
<!--SpringWebFlux的starter(场景启动器)-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<!--lombok插件,自动生成get、set等方法-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>
<!--mysql数据库连接-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.20</version>
</dependency>
<!--Hikari数据库连接池-->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>3.4.5</version>
</dependency>
<!--SpringBoot整合Mybatis的starter-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
```

​	application.yml

```yml
server:
  port: 8081
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
    username: root
    password: 123456
    type: com.zaxxer.hikari.HikariDataSource

mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 打印sql语句
    map-underscore-to-camel-case: true  # 开启驼峰命名法匹配规则
  mapper-locations: classpath:/mybatis/mapper/*.xml # 指定mapper.xml的文件位置
```

​	②创建实体类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Integer id;
  private Integer age;
  private String username;
}
```

​	③Mapper的代码

```java
public interface UserMapper {
    
    User findById(Integer id);

    List<User> findAll();

    Integer insertUser(User user);
}
```

​	④Mapper.xml文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ggs.webflux.mapper.UserMapper">
    <insert id="insertUser">
        insert into user(id,age,username) values(#{id},#{age},#{username});
    </insert>

    <select id="findById" resultType="com.ggs.webflux.entity.User">
        select id,age,username from user where id=#{id};
    </select>

    <select id="findAll" resultType="com.ggs.webflux.entity.User">
        select id,age,username from user;
    </select>
</mapper>
```

​	⑤Service类, 1个或0个数据,使用Mono, 多个数据使用Flux, 参数类型作为Mono或Flux的泛型, 通过just、fromStream、fromArray、fromIterable等等方法添加元素到其中

```java
/**
 * @author: Starbug
 * @date: 2020/6/13 22:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Mono<User> findById(Integer id) {
        User user=userMapper.findById(id);
        return Mono.just(user);
    }

    @Override
    public Flux<User> findAll() {
        List<User> users=userMapper.findAll();
        Flux<User> userListFlux = Flux.fromIterable(users);
        return userListFlux;
    }

    @Override
    public Mono<Void> insertUser(Mono<User> userMono) {
        Mono<Void> voidMono = userMono
                .doOnNext(
                        user -> {
                            Integer effectRow = userMapper.insertUser(user);
                            System.out.println(effectRow);
                        })
                .thenEmpty(Mono.empty());// 结束之后传入终止信号
        return voidMono;
    }
}
```

​	⑥Controller

```java
/**
 * @author: Starbug
 * @date: 2020/6/13 22:35
 */
@RestController
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/{id}")
  public Mono<User> findById(@PathVariable Integer id) {
    return userService.findById(id);
  }

  @GetMapping("/user")
  public Flux<User> findAll() {
    return userService.findAll();
  }

  @PostMapping("/insert/user")
  public Mono<Void> insertUser(@RequestBody User user) {
    Mono<User> userMono = Mono.just(user);
    return userService.insertUser(userMono);
  }
}/**
 * @author: Starbug
 * @date: 2020/6/13 22:35
 */
@RestController
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/{id}")
  public Mono<User> findById(@PathVariable Integer id) {
    return userService.findById(id);
  }

  @GetMapping("/user")
  public Flux<User> findAll() {
    return userService.findAll();
  }

  @PostMapping("/insert/user")
  public Mono<Void> insertUser(@RequestBody User user) {
    Mono<User> userMono = Mono.just(user);
    return userService.insertUser(userMono);
  }
}
```

----------



### SpringWebFlux(基于函数式编程模型)

​	(1)在使用函数式编程模型操作的时候, 需要自己初始化服务器

​	(2)基于函数式编程模型的时候, 有两个核心接口: RouterFunction(实现路由功能, 请求转发对应的handler) 和 HandlerFunction(处理请求生成响应的函数). 核心任务定义两个函数式接口的实现并且启动需要的服务器

​	(3)SpringWebFlux请求和响应不再是==ServletRequest==和==ServletResponse==, 而是==ServerRequest==和==ServerResponse==



​	①将原先的工程复制一份,删除controller层,重新创建

​	②创建Handler(具体实现方法), 这表达式写的我懵逼

```java
/**
 * @author: Starbug
 * @date: 2020/6/13 23:30
 */
public class UserHandler {

  private final UserService userService;

  public UserHandler(UserService userService) {
    this.userService = userService;
  }

  // 根据id查询
  public Mono<ServerResponse> getById(ServerRequest request) {
    // 从路径中获取id值
    int id = Integer.parseInt(request.pathVariable("id"));
    // 空值处理,构建一个空的流
    Mono<ServerResponse> notFound = ServerResponse.notFound().build();
    // 调用Service方法获取数据
    Mono<User> userMono = userService.findById(id);
    // 把userMono通过flatMap方法将多个流(这里只有一个流Mono<User>)合并成一个流再输出到一个流中(Mono<ServerResponse>)
    return userMono
        .flatMap(
            user -> {
              // user值就是userMono
              return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(user));
            })
        .switchIfEmpty(notFound);
  }

  //查询所有
  public Mono<ServerResponse> getAll(){
    //调用Service得到结果
    Flux<User> usersListFlux = userService.findAll();

    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(usersListFlux,User.class);
  }
  
  //添加
  public Mono<ServerResponse> insertUser(ServerRequest request){
    Mono<User> userMono = request.bodyToMono(User.class);
    return ServerResponse.ok().build(userService.insertUser(userMono));
  }
}
```

​	③初始化服务器,编写Router

​		创建路由的方法

```java
// 1.创建router对象
public RouterFunction<ServerResponse> routingFunction() {
    // 创建handler对象
    UserService userService = new UserServiceImpl();
    UserHandler userHandler = new UserHandler(userService);
    // 设置路由
    return RouterFunctions.route(
        GET("/user/{id}").and(accept(APPLICATION_JSON)), userHandler::getById)
        .andRoute(GET("/users").and(accept(APPLICATION_JSON)), userHandler::getAll)
        .andRoute(POST("/insert/user").and(accept(APPLICATION_JSON)), userHandler::insertUser);
}
```

​	创建服务器完成适配

```java
//2.创建服务器完成适配
public void createReactorServer(){
    //路由和handler适配
    RouterFunction<ServerResponse> router = routingFunction();
    //HttpHandler完成http请求,存储http请求相关的信息
    HttpHandler httpHandler = toHttpHandler(router);
    //完成最终的适配
    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

    //创建服务器
    HttpServer httpServer = HttpServer.create();
    //传入适配器,一启动,就会启动服务器,完成适配,当访问的时候,通过路由分配到具体的访问路径
    httpServer.handle(adapter).bindNow();
}
```



由于不会基于函数式编程模型的SpringWebFlux整合Mybatis,所以将UserService修改成NewUserServiceImpl,数据保存到map中

```java
public class NewUserServiceImpl implements UserService { // 创建map集合存储数据
  private final Map<Integer, User> users = new HashMap<>();

  public NewUserServiceImpl() {
    this.users.put(1, new User(1, 20, "nan"));
    this.users.put(2, new User(2, 30, "sdf"));
    this.users.put(3, new User(3, 33, "aaaaa"));
  }

  // 根据id查询
  @Override
  public Mono<User> findById(Integer id) {
    return Mono.justOrEmpty(this.users.get(id));
  }
  // 查询多个用户
  @Override
  public Flux<User> findAll() {
    return Flux.fromIterable(this.users.values());
  }
  // 添加用户
  @Override
  public Mono<Void> insertUser(Mono<User> userMono) {
    return userMono
        .doOnNext(
            person -> { // 向map集合里面放值
              int id = users.size() + 1;
              users.put(id, person);
            })
        .thenEmpty(Mono.empty());
  }
}
```

写一个main方法,启动服务(这好像就与SpringBoot没关系了额....我也不知道怎么整合其他starter)

```java
public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.createReactorServer();
    System.out.println("已经开启了");
    System.in.read();
}
```

启动成功后,找到这一段日志,里面有端口号,由于没有指定端口号,所以端口号是随机的

![image-20200614144355554](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200614144355.png)

查询所有数据成功!

![image-20200614144432793](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200614144432.png)

---------

​	④使用WebClient调用, 要时刻开启服务器,不然无法访问,WebClient中的端口号根据服务器端口号进行修改

```java
/**
 * @author: Starbug
 * @date: 2020/6/14 14:45
 */
public class Client {
  public static void main(String[] args) {
    WebClient webClient = WebClient.create("http://127.0.0.1:4697");
    // 根据id查询
    Integer id = 1;
    User user =
        webClient
            .get()
            .uri("/user/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(User.class)
            .block();

    System.out.println(user);

    // 查询所有
    Flux<User> users =
        webClient
            .get()
            .uri("/users")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(User.class);

    users.map(userInfo->userInfo.toString()).buffer().doOnNext(System.out::println).blockFirst();
  }
}

```

id查询

![image-20200614145322804](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200614145322.png)

查询所有

![image-20200614145313687](https://gitee.com/starbug-gitee/PicBed/raw/master/img/20200614145313.png)

----------



## 课程总结

**1、Spring框架概述**

​	(1)轻量级开源JavaEE框架, 目的是解决企业应用开发的复杂性, 两个核心组成: IOC和AOP

​	(2)Spring5.2.6版本

2、IOC容器

​	(1)IOC底层原理(工厂、反射、xml解析等)

​	(2)IOC接口(BeanFactory)

​	(3)IOC操作Bean管理(基于xml)

​	(4)IOC操作Bean管理(基于注解)

3、AOP

​	(1)AOp底层原理: 动态代理, 有接口(JDK动态代理); 没有接口(CGLIB动态代理), 使用继承实现

​	(2)术语: 切入点、增强(通知)、切面

​	(3)基于AspectJ实现AOP操作

4、JdbcTemplate

​	(1)使用JdbcTemplate实现数据库crud操作

​	(2)使用JdbcTemplate实现数据库批量操作

5、事务管理

​	(1)事务概念

​	(2)重要概念(传播行为和隔离级别)

​	(3)基于注解实现声明式事务管理

​	(4)完全注解方式实现声明式事务管理

6、Spring5新功能

​	(1)整合日志框架(log4j2)

​	(2)@Nullable注解

​	(3)函数式注册对象

​	(4)整合JUnit5单元测试框架

​	(5)SpringWebFlux响应式编程框架