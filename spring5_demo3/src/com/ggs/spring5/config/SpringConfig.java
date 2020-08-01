package com.ggs.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Starbug
 * @date: 2020/6/5 22:49
 */
@Configuration  //作为配置类,代替xml配置文件
@ComponentScan(basePackages = "com.ggs")
public class SpringConfig {
}
