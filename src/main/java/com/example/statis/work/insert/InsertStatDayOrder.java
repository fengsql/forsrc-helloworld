package com.example.statis.work.insert;

import com.forsrc.common.db.batch.DbBatch;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoStatDayOrder;
import com.example.mvc.model.StatDayOrder;
import com.example.statis.base.BStatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class InsertStatDayOrder extends BStatis {

  @Resource
  private DaoStatDayOrder daoStatDayOrder;
  @Resource
  private DbBatch<StatDayOrder> dbBatch;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  public void work(List<StatDayOrder> list) {
    doWork(list);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void doWork(List<StatDayOrder> list) {
    insert(list);
  }

  private void insert(List<StatDayOrder> list) {
    Tool.throwNull(list);
    for (StatDayOrder statDayOrder : list) {
      insert(statDayOrder);
    }
    log.info("add insert ok. count: {}.", list.size());
  }

  private void insert(StatDayOrder statDayOrder) {
    dbBatch.insert(statDayOrder, daoStatDayOrder);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}