package com.example.statis.task;

import com.forsrc.common.exception.CommonException;
import com.forsrc.common.scheduler.base.BTask;
import com.forsrc.common.tool.ToolTask;
import com.example.mvc.model.StatDayGoods;
import com.example.statis.work.clear.ClearStatDayGoods;
import com.example.statis.work.insert.InsertStatDayGoods;
import com.example.statis.work.stat.StatStatDayGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class TaskStatDayGoods extends BTask {
  private static final String task_name = "statisStatDayGoods";

  @Value("${project.statis.task.statDayGoods.enable:false}")
  private Boolean enable;
  @Value("${project.statis.task.statDayGoods.cron:null}")
  private String cron;
  @Value("${project.statis.task.statDayGoods.statis-num:1}")
  private Integer statisNum;
  @Resource
  private ClearStatDayGoods clearStatDayGoods;
  @Resource
  private StatStatDayGoods statStatDayGoods;
  @Resource
  private InsertStatDayGoods insertStatDayGoods;

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
    List<StatDayGoods> list = aggregate();
    insert(list);
  }

  private void clear() {
    if (statisNum > 1) {
      clearStatDayGoods.work(statisNum);
    }
  }

  private List<StatDayGoods> aggregate() {
    return statStatDayGoods.work(statisNum);
  }

  private void insert(List<StatDayGoods> list) {
    insertStatDayGoods.work(list);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}