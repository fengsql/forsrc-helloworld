package com.example.init;

import com.forsrc.common.spring.db.DbOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class InitThread {

  @Resource
  private ThreadPoolTaskExecutor localTaskExecutor;
  @Resource
  private DbOperator<?> dbOperator;

  // <<----------------------- public -----------------------

  @PostConstruct
  private void init() {
    startThread();
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void startThread() {
    startThreadDbOperator();
  }

  private void startThreadDbOperator() {
    localTaskExecutor.execute(() -> {
      dbOperator.exec();
    });
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}