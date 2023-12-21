package com.example.statis.work.clear;

import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamStatis;
import com.example.mvc.dao.DaoStatDayGoods;
import com.example.statis.base.BStatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ClearStatDayGoods extends BStatis {

  @Resource
  private DaoStatDayGoods daoStatDayGoods;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  public void work(int statisNum) {
    doWork(statisNum);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void doWork(int statisNum) {
    clear(statisNum);
  }

  private void clear(int statisNum) {
    ParamStatis paramStatis = getParamStatis(statisNum);
    ParamDelete paramDelete = getParamDelete(paramStatis);
    int count = daoStatDayGoods.deleteConditional(paramDelete);
    log.info("clear ok. count: {}. start: {}. end: {}", count, paramStatis.getDateTime()[0], paramStatis.getDateTime()[1]);
  }

  private ParamStatis getParamStatis(int statisNum) {
    return getRangeDay(statisNum);
  }

  private ParamDelete getParamDelete(ParamStatis paramStatis) {
    ParamDelete paramDelete = new ParamDelete();
    paramDelete.setCondition(getDeleteCondition(paramStatis));
    return paramDelete;
  }

  private String getDeleteCondition(ParamStatis paramStatis) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("day_no >= '").append(toNumberDay(paramStatis.getDateTime()[0])).append("'");
    stringBuilder.append(" and day_no < '").append(toNumberDay(paramStatis.getDateTime()[1])).append("'");
    return stringBuilder.toString();
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}