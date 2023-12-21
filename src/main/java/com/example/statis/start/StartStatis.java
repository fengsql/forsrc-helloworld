package com.example.statis.start;

import com.forsrc.common.scheduler.manage.ManageScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import com.example.statis.task.*;

@Component
@Slf4j
public class StartStatis {

  @Value("${project.statis.enable:false}")
  private Boolean enable;

  @Resource
  private ManageScheduler manageScheduler;
  @Resource
  private TaskStatDayOrder taskStatDayOrder;
  @Resource
  private TaskStatDayGoods taskStatDayGoods;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------
  @PostConstruct
  private void start() {
    if (enable == null || !enable) {
      return;
    }
    doWork();
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void doWork() {
    startStatDayOrder();
    startStatDayGoods();
  }

  private void startStatDayOrder() {
    taskStatDayOrder.check();
    manageScheduler.add(taskStatDayOrder, taskStatDayOrder.getCron());
  }

  private void startStatDayGoods() {
    taskStatDayGoods.check();
    manageScheduler.add(taskStatDayGoods, taskStatDayGoods.getCron());
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}