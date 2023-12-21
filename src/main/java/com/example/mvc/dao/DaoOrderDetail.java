package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailOrderDetail;
import com.example.mvc.bean.rep.RepOrderDetail;
import com.example.mvc.bean.req.ReqOrderDetail;
import com.example.mvc.model.OrderDetail;

import java.util.List;
import java.util.Map;

public interface DaoOrderDetail extends IDao<OrderDetail> {

  /**
   * 添加订单明细表。空值将被忽略。
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(OrderDetail orderDetail);

  /**
   * 添加订单明细表。空值也会插入。
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(OrderDetail orderDetail);

  /**
   * 更新订单明细表。空值将被忽略。
   * @param orderDetail 订单明细表。
   * @return 返回更新的记录数。
   */
  int update(OrderDetail orderDetail);

  /**
   * 更新订单明细表。空值也会更新。
   * @param orderDetail 订单明细表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(OrderDetail orderDetail);

  /**
   * 更新订单明细表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除订单明细表。可以设置主键、外键和常量字段可作为条件字段。
   * @param orderDetail 订单明细表。
   * @return 返回删除的记录数。
   */
  int delete(OrderDetail orderDetail);
  /**
   * 删除订单明细表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条订单明细表。
   * @param id 订单编号。
   * @return 返回的订单明细表。
   */
  OrderDetail selectByPrimary(Integer id);

  /**
   * 查询一条订单明细表。
   * @param orderDetail 订单明细表。
   * @return 返回的订单明细表。
   */
  OrderDetail selectOne(OrderDetail orderDetail);

  /**
   * 查询订单明细表列表。返回所有符合条件的订单明细表，未分页。
   * @param orderDetail 订单明细表。
   * @return null无记录；非空为返回的订单明细表列表。
   */
  List<OrderDetail> select(OrderDetail orderDetail);

  /**
   * 查询一条订单明细表详情。
   * @param orderDetail 订单明细表。
   * @return 返回的订单明细表详情。
   */
  DetailOrderDetail selectDetail(OrderDetail orderDetail);

  /**
   * 根据主键查询一条订单明细表详情。
   * @param id 订单编号。
   * @return 返回的订单明细表详情。
   */
  DetailOrderDetail selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询订单明细表的记录数。
   * @param reqOrderDetail 查询的参数。
   * @return 返回订单明细表的记录数。
   */
  int selectTotal(ReqOrderDetail reqOrderDetail);

  /**
   * 综合查询订单明细表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqOrderDetail 查询的参数。
   * @return 返回的订单明细表列表。
   */
  List<RepOrderDetail.OrderDetailRow> selectRelative(ReqOrderDetail reqOrderDetail);

  /**
   * 综合查询订单明细表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqOrderDetail 查询的参数。
   * @return 返回的订单明细表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqOrderDetail reqOrderDetail);

  /**
   * 综合查询订单明细表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqOrderDetail} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询订单明细表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询订单明细表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的订单明细表列表。
   */
  List<OrderDetail> selectConditional(ParamQuery paramQuery);

  /**
   * 统计订单明细表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}