package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailOrder;
import com.example.mvc.bean.rep.RepOrder;
import com.example.mvc.bean.req.ReqOrder;
import com.example.mvc.model.Order;

import java.util.List;
import java.util.Map;

public interface DaoOrder extends IDao<Order> {

  /**
   * 添加订单表。空值将被忽略。
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(Order order);

  /**
   * 添加订单表。空值也会插入。
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(Order order);

  /**
   * 更新订单表。空值将被忽略。
   * @param order 订单表。
   * @return 返回更新的记录数。
   */
  int update(Order order);

  /**
   * 更新订单表。空值也会更新。
   * @param order 订单表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(Order order);

  /**
   * 更新订单表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除订单表。可以设置主键、外键和常量字段可作为条件字段。
   * @param order 订单表。
   * @return 返回删除的记录数。
   */
  int delete(Order order);

  /**
   * 删除订单表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条订单表。
   * @param id 订单编号。
   * @return 返回的订单表。
   */
  Order selectByPrimary(Integer id);

  /**
   * 查询一条订单表。
   * @param order 订单表。
   * @return 返回的订单表。
   */
  Order selectOne(Order order);

  /**
   * 查询订单表列表。返回所有符合条件的订单表，未分页。
   * @param order 订单表。
   * @return null无记录；非空为返回的订单表列表。
   */
  List<Order> select(Order order);

  /**
   * 查询一条订单表详情。
   * @param order 订单表。
   * @return 返回的订单表详情。
   */
  DetailOrder selectDetail(Order order);

  /**
   * 根据主键查询一条订单表详情。
   * @param id 订单编号。
   * @return 返回的订单表详情。
   */
  DetailOrder selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询订单表的记录数。
   * @param reqOrder 查询的参数。
   * @return 返回订单表的记录数。
   */
  int selectTotal(ReqOrder reqOrder);

  /**
   * 综合查询订单表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqOrder 查询的参数。
   * @return 返回的订单表列表。
   */
  List<RepOrder.OrderRow> selectRelative(ReqOrder reqOrder);

  /**
   * 综合查询订单表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqOrder 查询的参数。
   * @return 返回的订单表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqOrder reqOrder);

  /**
   * 综合查询订单表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqOrder} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询订单表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询订单表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的订单表列表。
   */
  List<Order> selectConditional(ParamQuery paramQuery);

  /**
   * 统计订单表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}