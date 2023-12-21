package com.example.statis.work.stat;

import com.forsrc.common.spring.bean.param.ParamStatis;
import com.example.mvc.bean.req.ReqStatDayGoods;
import com.example.mvc.dao.DaoStatDayGoods;
import com.example.mvc.model.StatDayGoods;
import com.example.statis.base.BStatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class StatStatDayGoods extends BStatis {

  @Resource
  private DaoStatDayGoods daoStatDayGoods;

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 统计历史。
   * @param statisNum 统计间隔时间单位数。
   * @return 统计结果。
   */
  public List<StatDayGoods> work(int statisNum) {
    return doWork(statisNum);
  }

  /**
   * 实时统计。
   * @return 统计结果。
   */
  public List<StatDayGoods> work() {
    return doWork(0);
  }

  /**
   * 设置统计的时间范围，实时统计。
   * @param reqStatDayGoods 参数。
   */
  public void setTime(ReqStatDayGoods reqStatDayGoods) {
    if (reqStatDayGoods.getDayNo() != null) {
      return;
    }
    ParamStatis paramStatis = getRangeDay(0);
    Integer[] dayNo = new Integer[2];
    dayNo[0] = toNumberDay(paramStatis.getDateTime()[0]);
    dayNo[1] = toNumberDay(paramStatis.getDateTime()[1]);
    reqStatDayGoods.setDayNo(dayNo);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private List<StatDayGoods> doWork(int statisNum) {
    return stat(statisNum);
  }

  private List<StatDayGoods> stat(int statisNum) {
    ParamStatis paramStatis = getParamStatis(statisNum);
    ReqStatDayGoods reqStatDayGoods = getReqStatDayGoods(paramStatis);
    List<StatDayGoods> list = daoStatDayGoods.stat(reqStatDayGoods);
    int count = list == null ? 0 : list.size();
    log.info("stat ok. count: {}. start: {}. end: {}", count, paramStatis.getDateTime()[0], paramStatis.getDateTime()[1]);
    return list;
  }

  private ReqStatDayGoods getReqStatDayGoods(ParamStatis paramStatis) {
    Integer[] dayNo = new Integer[2];
    dayNo[0] = toNumberDay(paramStatis.getDateTime()[0]);
    dayNo[1] = toNumberDay(paramStatis.getDateTime()[1]);
    ReqStatDayGoods reqStatDayGoods = new ReqStatDayGoods();
    reqStatDayGoods.setDayNo(dayNo);
    return reqStatDayGoods;
  }

  private ParamStatis getParamStatis(int statisNum) {
    return getRangeDay(statisNum);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}