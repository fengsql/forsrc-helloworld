package com.example.common.constant;

import com.forsrc.common.constant.Enum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConfigProject {

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
  }

  public static final class stat {
    public static boolean enable;

  }

  public static final class store {
    public static Enum.StoreMode mode;

  }

  //database
  @Value("${spring.datasource.jdbc-url}")
  public void setSpring_datasource_jdbcUrl(String value) {
    datasource.url = value;
  }

  @Value("${spring.datasource.username}")
  public void setSpring_datasource_username(String value) {
    datasource.username = value;
  }

  @Value("${spring.datasource.password}")
  public void setSpring_datasource_password(String value) {
    datasource.password = value;
  }

  @Value("${spring.datasource.driver-class-name}")
  public void setSpring_datasource_driverClassName(String value) {
    datasource.driverClassName = value;
  }

  @Value("${spring.datasource.druid.initial-size:3}")
  public void setSpring_datasource_druid_initialSize(int value) {
    datasource.initialSize = value;
  }

  @Value("${spring.datasource.druid.min-idle:3}")
  public void setSpring_datasource_druid_minIdle(int value) {
    datasource.minIdle = value;
  }

  @Value("${spring.datasource.druid.max-active:50}")
  public void setSpring_datasource_druid_maxActive(int value) {
    datasource.maxActive = value;
  }

  @Value("${spring.datasource.druid.max-wait:60000}")
  public void setSpring_datasource_druid_maxWait(int value) {
    datasource.maxWait = value;
  }

  @Value("${spring.datasource.druid.time-between-eviction-runs-millis:60000}")
  public void setSpring_datasource_druid_timeBetweenEvictionRunsMillis(int value) {
    datasource.timeBetweenEvictionRunsMillis = value;
  }

  @Value("${spring.datasource.druid.min-evictable-idle-time-millis:60000}")
  public void setSpring_datasource_druid_minEvictableIdleTimeMillis(int value) {
    datasource.minEvictableIdleTimeMillis = value;
  }

  @Value("${spring.datasource.druid.validation-query:SELECT 1}")
  public void setSpring_datasource_druid_validationQuery(String value) {
    datasource.validationQuery = value;
  }

  @Value("${spring.datasource.druid.auto-commit:true}")
  public void setSpring_datasource_druid_autoCommit(boolean value) {
    datasource.autoCommit = value;
  }

  @Value("${spring.datasource.druid.test-while-idle:true}")
  public void setSpring_datasource_druid_testWhileIdle(boolean value) {
    datasource.testWhileIdle = value;
  }

  @Value("${spring.datasource.druid.test-on-borrow:false}")
  public void setSpring_datasource_druid_testOnBorrow(boolean value) {
    datasource.testOnBorrow = value;
  }

  @Value("${spring.datasource.druid.test-on-return:false}")
  public void setSpring_datasource_druid_testOnReturn(boolean value) {
    datasource.testOnReturn = value;
  }

  //stat
  @Value("${project.stat.enable:false}")
  public void setProject_stat_enable(boolean value) {
    stat.enable = value;
  }

  //store
  @Value("${project.store.mode:sql}")
  public void setProject_store_mode(String value) {
    store.mode = Enum.StoreMode.get(value);
  }

}