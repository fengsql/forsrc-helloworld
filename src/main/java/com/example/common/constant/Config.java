package com.example.common.constant;

import com.forsrc.common.constant.Enum;
import com.forsrc.common.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {

  public static final class datasource {
    public static String url;
    public static String username;
    public static String password;
    public static String driverClassName;

    public static int initialSize;
    public static int minIdle;
    public static int maxActive;
    public static long maxWait;
    public static long timeBetweenEvictionRunsMillis;
    public static long minEvictableIdleTimeMillis;
    public static String validationQuery;
    public static boolean autoCommit;
    public static boolean testWhileIdle;
    public static boolean testOnBorrow;
    public static boolean testOnReturn;
    public static boolean removeAbandoned;
    public static int removeAbandonedTimeout;
  }

  public static final class project {
    public static final class stat {
      public static boolean enable;
    }

    public static final class store {
      public static Enum.StoreMode mode;
    }
  }

  //database
  @Value("${spring.datasource.jdbc-url}")
  public void setSpring_datasource_jdbcUrl(String value) {
    datasource.url = Tool.toString(value);
  }

  @Value("${spring.datasource.username}")
  public void setSpring_datasource_username(String value) {
    datasource.username = Tool.toString(value);
  }

  @Value("${spring.datasource.password}")
  public void setSpring_datasource_password(String value) {
    datasource.password = Tool.toString(value);
  }

  @Value("${spring.datasource.driver-class-name}")
  public void setSpring_datasource_driverClassName(String value) {
    datasource.driverClassName = Tool.toString(value);
  }

  @Value("${spring.datasource.druid.initial-size:3}")
  public void setSpring_datasource_druid_initialSize(String value) {
    datasource.initialSize = Tool.toInt(value);
  }

  @Value("${spring.datasource.druid.min-idle:3}")
  public void setSpring_datasource_druid_minIdle(String value) {
    datasource.minIdle = Tool.toInt(value);
  }

  @Value("${spring.datasource.druid.max-active:50}")
  public void setSpring_datasource_druid_maxActive(String value) {
    datasource.maxActive = Tool.toInt(value);
  }

  @Value("${spring.datasource.druid.max-wait:60000}")
  public void setSpring_datasource_druid_maxWait(String value) {
    datasource.maxWait = Tool.toInt(value);
  }

  @Value("${spring.datasource.druid.time-between-eviction-runs-millis:60000}")
  public void setSpring_datasource_druid_timeBetweenEvictionRunsMillis(String value) {
    datasource.timeBetweenEvictionRunsMillis = Tool.toLong(value);
  }

  @Value("${spring.datasource.druid.min-evictable-idle-time-millis:60000}")
  public void setSpring_datasource_druid_minEvictableIdleTimeMillis(String value) {
    datasource.minEvictableIdleTimeMillis = Tool.toLong(value);
  }

  @Value("${spring.datasource.druid.validation-query:SELECT 1}")
  public void setSpring_datasource_druid_validationQuery(String value) {
    datasource.validationQuery = Tool.toString(value);
  }

  @Value("${spring.datasource.druid.default-auto-commit:true}")
  public void setSpring_datasource_druid_autoCommit(String value) {
    datasource.autoCommit = Tool.toBoolean(value);
  }

  @Value("${spring.datasource.druid.test-while-idle:true}")
  public void setSpring_datasource_druid_testWhileIdle(String value) {
    datasource.testWhileIdle = Tool.toBoolean(value);
  }

  @Value("${spring.datasource.druid.test-on-borrow:false}")
  public void setSpring_datasource_druid_testOnBorrow(String value) {
    datasource.testOnBorrow = Tool.toBoolean(value);
  }

  @Value("${spring.datasource.druid.test-on-return:false}")
  public void setSpring_datasource_druid_testOnReturn(String value) {
    datasource.testOnReturn = Tool.toBoolean(value);
  }

  @Value("${spring.datasource.druid.remove-abandoned:false}")
  public void setSpring_datasource_druid_removeAbandoned(String value) {
    datasource.removeAbandoned = Tool.toBoolean(value);
  }

  @Value("${spring.datasource.druid.remove-abandoned-timeout:300}")
  public void setSpring_datasource_druid_removeAbandonedTimeout(String value) {
    datasource.removeAbandonedTimeout = Tool.toInt(value);
  }

  //project-stat
  @Value("${project.stat.enable:false}")
  public void setProject_stat_enable(String value) {
    project.stat.enable = Tool.toBoolean(value);
  }

  //project-store
  @Value("${project.store.mode:sql}")
  public void setProject_store_mode(String value) {
    project.store.mode = Enum.StoreMode.get(Tool.toString(value));
  }

}