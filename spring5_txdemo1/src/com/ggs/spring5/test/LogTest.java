package com.ggs.spring5.test;

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
