package com.example.statis.task;

import com.forsrc.common.exception.CommonException;
import com.forsrc.common.scheduler.base.BTask;
import com.forsrc.common.tool.ToolTask;
import com.example.mvc.model.StatDayOrder;
import com.example.statis.work.clear.ClearStatDayOrder;
import com.example.statis.work.insert.InsertStatDayOrder;
import com.example.statis.work.stat.StatStatDayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class TaskStatDayOrder extends BTask {
  private static final String task_name = "statisStatDayOrder";

  @Value("${project.statis.task.statDayOrder.enable:false}")
  private Boolean enable;
  @Value("${project.statis.task.statDayOrder.cron:null}")
  private String cron;
  @Value("${project.statis.task.statDayOrder.statis-num:1}")
  private Integer statisNum;
  @Resource
  private ClearStatDayOrder clearStatDayOrder;
  @Resource
  private StatStatDayOrder statStatDayOrder;
  @Resource
  private InsertStatDayOrder insertStatDayOrder;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  @Override
  public String getName() {
    return task_name;
  }

  @Override
  public void run() {
    if (enable == null || !enable) {
      return;
    }
    doRun();
  }

  public void check() {
    if (enable == null || !enable) {
      return;
    }
    if (!ToolTask.isValidCron(cron)) {
      throw new CommonException("invalid cron! cron: " + cron + ". task: " + task_name);
    }
    if (statisNum == null || statisNum < 0) {
      throw new CommonException("invalid 'statis-num'! statisNum: " + statisNum + ". task: " + task_name);
    }
  }

  public String getCron() {
    return cron;
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void doRun() {
    clear();
    List<StatDayOrder> list = aggregate();
    insert(list);
  }

  private void clear() {
    if (statisNum > 1) {
      clearStatDayOrder.work(statisNum);
    }
  }

  private List<StatDayOrder> aggregate() {
    return statStatDayOrder.work(statisNum);
  }

  private void insert(List<StatDayOrder> list) {
    insertStatDayOrder.work(list);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}