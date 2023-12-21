package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailStatDayOrder;
import com.example.mvc.bean.rep.RepStatDayOrder;
import com.example.mvc.bean.req.ReqStatDayOrder;
import com.example.mvc.model.StatDayOrder;

import java.util.List;
import java.util.Map;

public interface DaoStatDayOrder extends IDao<StatDayOrder> {

  /**
   * 添加每日订单统计。空值将被忽略。
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(StatDayOrder statDayOrder);

  /**
   * 添加每日订单统计。空值也会插入。
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(StatDayOrder statDayOrder);

  /**
   * 更新每日订单统计。空值将被忽略。
   * @param statDayOrder 每日订单统计。
   * @return 返回更新的记录数。
   */
  int update(StatDayOrder statDayOrder);

  /**
   * 更新每日订单统计。空值也会更新。
   * @param statDayOrder 每日订单统计。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(StatDayOrder statDayOrder);

  /**
   * 更新每日订单统计。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除每日订单统计。可以设置主键、外键和常量字段可作为条件字段。
   * @param statDayOrder 每日订单统计。
   * @return 返回删除的记录数。
   */
  int delete(StatDayOrder statDayOrder);
  /**
   * 删除每日订单统计。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条每日订单统计。
   * @param id 编号。
   * @return 返回的每日订单统计。
   */
  StatDayOrder selectByPrimary(Integer id);

  /**
   * 查询一条每日订单统计。
   * @param statDayOrder 每日订单统计。
   * @return 返回的每日订单统计。
   */
  StatDayOrder selectOne(StatDayOrder statDayOrder);

  /**
   * 查询每日订单统计列表。返回所有符合条件的每日订单统计，未分页。
   * @param statDayOrder 每日订单统计。
   * @return null无记录；非空为返回的每日订单统计列表。
   */
  List<StatDayOrder> select(StatDayOrder statDayOrder);

  /**
   * 查询一条每日订单统计详情。
   * @param statDayOrder 每日订单统计。
   * @return 返回的每日订单统计详情。
   */
  DetailStatDayOrder selectDetail(StatDayOrder statDayOrder);

  /**
   * 根据主键查询一条每日订单统计详情。
   * @param id 编号。
   * @return 返回的每日订单统计详情。
   */
  DetailStatDayOrder selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询每日订单统计的记录数。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回每日订单统计的记录数。
   */
  int selectTotal(ReqStatDayOrder reqStatDayOrder);

  /**
   * 综合查询每日订单统计列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回的每日订单统计列表。
   */
  List<RepStatDayOrder.StatDayOrderRow> selectRelative(ReqStatDayOrder reqStatDayOrder);

  /**
   * 综合查询每日订单统计列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回的每日订单统计列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqStatDayOrder reqStatDayOrder);

  /**
   * 综合查询每日订单统计列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqStatDayOrder} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询每日订单统计列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询每日订单统计列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的每日订单统计列表。
   */
  List<StatDayOrder> selectConditional(ParamQuery paramQuery);

  /**
   * 统计每日订单统计列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

  /**
   * 实时统计每日订单统计。返回所有统计结果，未分页。
   * @param reqStatDayOrder 统计参数。
   * @return 返回实时统计每日订单统计。
   */
  List<StatDayOrder> stat(ReqStatDayOrder reqStatDayOrder);

}