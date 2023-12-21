package com.example.statis.work.stat;

import com.forsrc.common.spring.bean.param.ParamStatis;
import com.example.mvc.bean.req.ReqStatDayOrder;
import com.example.mvc.dao.DaoStatDayOrder;
import com.example.mvc.model.StatDayOrder;
import com.example.statis.base.BStatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class StatStatDayOrder extends BStatis {

  @Resource
  private DaoStatDayOrder daoStatDayOrder;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 统计历史。
   * @param statisNum 统计间隔时间单位数。
   * @return 统计结果。
   */
  public List<StatDayOrder> work(int statisNum) {
    return doWork(statisNum);
  }

  /**
   * 实时统计。
   * @return 统计结果。
   */
  public List<StatDayOrder> work() {
    return doWork(0);
  }

  /**
   * 设置统计的时间范围，实时统计。
   * @param reqStatDayOrder 参数。
   */
  public void setTime(ReqStatDayOrder reqStatDayOrder) {
    if (reqStatDayOrder.getDayNo() != null) {
      return;
    }
    ParamStatis paramStatis = getRangeDay(0);
    Integer[] dayNo = new Integer[2];
    dayNo[0] = toNumberDay(paramStatis.getDateTime()[0]);
    dayNo[1] = toNumberDay(paramStatis.getDateTime()[1]);
    reqStatDayOrder.setDayNo(dayNo);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private List<StatDayOrder> doWork(int statisNum) {
    return stat(statisNum);
  }

  private List<StatDayOrder> stat(int statisNum) {
    ParamStatis paramStatis = getParamStatis(statisNum);
    ReqStatDayOrder reqStatDayOrder = getReqStatDayOrder(paramStatis);
    List<StatDayOrder> list = daoStatDayOrder.stat(reqStatDayOrder);
    int count = list == null ? 0 : list.size();
    log.info("stat ok. count: {}. start: {}. end: {}", count, paramStatis.getDateTime()[0], paramStatis.getDateTime()[1]);
    return list;
  }

  private ReqStatDayOrder getReqStatDayOrder(ParamStatis paramStatis) {
    Integer[] dayNo = new Integer[2];
    dayNo[0] = toNumberDay(paramStatis.getDateTime()[0]);
    dayNo[1] = toNumberDay(paramStatis.getDateTime()[1]);
    ReqStatDayOrder reqStatDayOrder = new ReqStatDayOrder();
    reqStatDayOrder.setDayNo(dayNo);
    return reqStatDayOrder;
  }

  private ParamStatis getParamStatis(int statisNum) {
    return getRangeDay(statisNum);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}