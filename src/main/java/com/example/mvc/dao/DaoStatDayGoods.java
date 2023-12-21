package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailStatDayGoods;
import com.example.mvc.bean.rep.RepStatDayGoods;
import com.example.mvc.bean.req.ReqStatDayGoods;
import com.example.mvc.model.StatDayGoods;

import java.util.List;
import java.util.Map;

public interface DaoStatDayGoods extends IDao<StatDayGoods> {

  /**
   * 添加每日订单商品统计。空值将被忽略。
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(StatDayGoods statDayGoods);

  /**
   * 添加每日订单商品统计。空值也会插入。
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(StatDayGoods statDayGoods);

  /**
   * 更新每日订单商品统计。空值将被忽略。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回更新的记录数。
   */
  int update(StatDayGoods statDayGoods);

  /**
   * 更新每日订单商品统计。空值也会更新。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(StatDayGoods statDayGoods);

  /**
   * 更新每日订单商品统计。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除每日订单商品统计。可以设置主键、外键和常量字段可作为条件字段。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回删除的记录数。
   */
  int delete(StatDayGoods statDayGoods);
  /**
   * 删除每日订单商品统计。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条每日订单商品统计。
   * @param id 编号。
   * @return 返回的每日订单商品统计。
   */
  StatDayGoods selectByPrimary(Integer id);

  /**
   * 查询一条每日订单商品统计。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回的每日订单商品统计。
   */
  StatDayGoods selectOne(StatDayGoods statDayGoods);

  /**
   * 查询每日订单商品统计列表。返回所有符合条件的每日订单商品统计，未分页。
   * @param statDayGoods 每日订单商品统计。
   * @return null无记录；非空为返回的每日订单商品统计列表。
   */
  List<StatDayGoods> select(StatDayGoods statDayGoods);

  /**
   * 查询一条每日订单商品统计详情。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回的每日订单商品统计详情。
   */
  DetailStatDayGoods selectDetail(StatDayGoods statDayGoods);

  /**
   * 根据主键查询一条每日订单商品统计详情。
   * @param id 编号。
   * @return 返回的每日订单商品统计详情。
   */
  DetailStatDayGoods selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询每日订单商品统计的记录数。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回每日订单商品统计的记录数。
   */
  int selectTotal(ReqStatDayGoods reqStatDayGoods);

  /**
   * 综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回的每日订单商品统计列表。
   */
  List<RepStatDayGoods.StatDayGoodsRow> selectRelative(ReqStatDayGoods reqStatDayGoods);

  /**
   * 综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回的每日订单商品统计列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqStatDayGoods reqStatDayGoods);

  /**
   * 综合查询每日订单商品统计列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqStatDayGoods} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询每日订单商品统计列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询每日订单商品统计列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的每日订单商品统计列表。
   */
  List<StatDayGoods> selectConditional(ParamQuery paramQuery);

  /**
   * 统计每日订单商品统计列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

  /**
   * 实时统计每日订单商品统计。返回所有统计结果，未分页。
   * @param reqStatDayGoods 统计参数。
   * @return 返回实时统计每日订单商品统计。
   */
  List<StatDayGoods> stat(ReqStatDayGoods reqStatDayGoods);

}