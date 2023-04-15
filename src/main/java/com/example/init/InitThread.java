package com.example.init;

import com.forsrc.common.db.thread.ThreadDbBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.ScheduledFuture;

@Component
@Slf4j
public class InitThread {
  private static final int workInterval_dbBatch_default = 3;  //秒

  @Resource
  private ThreadPoolTaskScheduler threadPoolTaskScheduler;
  @Resource
  private ThreadDbBatch threadDbBatch;

  private ScheduledFuture<?> scheduledDbBatch;

  // <<----------------------- public -----------------------

  @PostConstruct
  private void init() {
    startSchedule();
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void startSchedule() {
    int ms = workInterval_dbBatch_default * 1000;
    scheduledDbBatch = threadPoolTaskScheduler.scheduleAtFixedRate(threadDbBatch, ms);
    log.info("start scheduledDbBatch ok. ms: {}", ms);
  }

  @PreDestroy
  public void destroy() {
    if (scheduledDbBatch != null) {
      scheduledDbBatch.cancel(true);
    }
    log.info("destroy scheduledDbBatch ok.");
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}