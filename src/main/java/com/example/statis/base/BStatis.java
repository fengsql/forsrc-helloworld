package com.example.statis.base;

import com.forsrc.common.spring.bean.param.ParamStatis;
import com.forsrc.common.spring.statis.base.BAggregate;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public abstract class BStatis extends BAggregate {

  protected ParamStatis getRangeManual(int days) {
    return super.getRangeDay(days);
  }

  protected Integer toNumberManual(Date date) {
    return super.toNumberDay(date);
  }

  protected Date toDateTimeManual(Date date) {
    return super.toDateTimeDay(date);
  }

  protected String toStringManual(Date date) {
    return super.toStringDay(date);
  }

}