package com.example.statis.work.insert;

import com.forsrc.common.db.batch.DbBatch;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoStatDayGoods;
import com.example.mvc.model.StatDayGoods;
import com.example.statis.base.BStatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class InsertStatDayGoods extends BStatis {

  @Resource
  private DaoStatDayGoods daoStatDayGoods;
  @Resource
  private DbBatch<StatDayGoods> dbBatch;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  public void work(List<StatDayGoods> list) {
    doWork(list);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void doWork(List<StatDayGoods> list) {
    insert(list);
  }

  private void insert(List<StatDayGoods> list) {
    Tool.throwNull(list);
    for (StatDayGoods statDayGoods : list) {
      insert(statDayGoods);
    }
    log.info("add insert ok. count: {}.", list.size());
  }

  private void insert(StatDayGoods statDayGoods) {
    dbBatch.insert(statDayGoods, daoStatDayGoods);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}