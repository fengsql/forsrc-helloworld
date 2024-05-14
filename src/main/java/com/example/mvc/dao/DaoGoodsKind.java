package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailGoodsKind;
import com.example.mvc.bean.rep.RepGoodsKind;
import com.example.mvc.bean.req.ReqGoodsKind;
import com.example.mvc.model.GoodsKind;

import java.util.List;
import java.util.Map;

public interface DaoGoodsKind extends IDao<GoodsKind> {

  /**
   * 添加商品类别表。空值将被忽略。
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(GoodsKind goodsKind);

  /**
   * 添加商品类别表。空值也会插入。
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(GoodsKind goodsKind);

  /**
   * 更新商品类别表。空值将被忽略。
   * @param goodsKind 商品类别表。
   * @return 返回更新的记录数。
   */
  int update(GoodsKind goodsKind);

  /**
   * 更新商品类别表。空值也会更新。
   * @param goodsKind 商品类别表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(GoodsKind goodsKind);

  /**
   * 更新商品类别表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除商品类别表。可以设置主键、外键和常量字段可作为条件字段。
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  int delete(GoodsKind goodsKind);

  /**
   * 删除商品类别表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条商品类别表。
   * @param id 商品编号。
   * @return 返回的商品类别表。
   */
  GoodsKind selectByPrimary(Integer id);

  /**
   * 查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回的商品类别表。
   */
  GoodsKind selectOne(GoodsKind goodsKind);

  /**
   * 查询商品类别表列表。返回所有符合条件的商品类别表，未分页。
   * @param goodsKind 商品类别表。
   * @return null无记录；非空为返回的商品类别表列表。
   */
  List<GoodsKind> select(GoodsKind goodsKind);

  /**
   * 查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回的商品类别表详情。
   */
  DetailGoodsKind selectDetail(GoodsKind goodsKind);

  /**
   * 根据主键查询一条商品类别表详情。
   * @param id 商品编号。
   * @return 返回的商品类别表详情。
   */
  DetailGoodsKind selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询商品类别表的记录数。
   * @param reqGoodsKind 查询的参数。
   * @return 返回商品类别表的记录数。
   */
  int selectTotal(ReqGoodsKind reqGoodsKind);

  /**
   * 综合查询商品类别表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqGoodsKind 查询的参数。
   * @return 返回的商品类别表列表。
   */
  List<RepGoodsKind.GoodsKindRow> selectRelative(ReqGoodsKind reqGoodsKind);

  /**
   * 综合查询商品类别表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqGoodsKind 查询的参数。
   * @return 返回的商品类别表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqGoodsKind reqGoodsKind);

  /**
   * 综合查询商品类别表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqGoodsKind} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询商品类别表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询商品类别表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的商品类别表列表。
   */
  List<GoodsKind> selectConditional(ParamQuery paramQuery);

  /**
   * 根据唯一键更新一条商品类别表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param goodsKind 商品类别表。
   * @return 返回更新的记录数。
   */
  int updateByName(GoodsKind goodsKind);

  /**
   * 根据唯一键删除一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  int deleteByName(GoodsKind goodsKind);

  /**
   * 根据唯一键查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回的商品类别表。
   */
  GoodsKind selectByName(GoodsKind goodsKind);

  /**
   * 根据唯一键查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回的商品类别表详情。
   */
  DetailGoodsKind selectDetailByName(GoodsKind goodsKind);

  /**
   * 统计商品类别表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}