package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailGoods;
import com.example.mvc.bean.rep.RepGoods;
import com.example.mvc.bean.req.ReqGoods;
import com.example.mvc.model.Goods;

import java.util.List;
import java.util.Map;

public interface DaoGoods extends IDao<Goods> {

  /**
   * 添加商品表。空值将被忽略。
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(Goods goods);

  /**
   * 添加商品表。空值也会插入。
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(Goods goods);

  /**
   * 更新商品表。空值将被忽略。
   * @param goods 商品表。
   * @return 返回更新的记录数。
   */
  int update(Goods goods);

  /**
   * 更新商品表。空值也会更新。
   * @param goods 商品表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(Goods goods);

  /**
   * 更新商品表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除商品表。可以设置主键、外键和常量字段可作为条件字段。
   * @param goods 商品表。
   * @return 返回删除的记录数。
   */
  int delete(Goods goods);

  /**
   * 删除商品表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条商品表。
   * @param id 商品编号。
   * @return 返回的商品表。
   */
  Goods selectByPrimary(Integer id);

  /**
   * 查询一条商品表。
   * @param goods 商品表。
   * @return 返回的商品表。
   */
  Goods selectOne(Goods goods);

  /**
   * 查询商品表列表。返回所有符合条件的商品表，未分页。
   * @param goods 商品表。
   * @return null无记录；非空为返回的商品表列表。
   */
  List<Goods> select(Goods goods);

  /**
   * 查询一条商品表详情。
   * @param goods 商品表。
   * @return 返回的商品表详情。
   */
  DetailGoods selectDetail(Goods goods);

  /**
   * 根据主键查询一条商品表详情。
   * @param id 商品编号。
   * @return 返回的商品表详情。
   */
  DetailGoods selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询商品表的记录数。
   * @param reqGoods 查询的参数。
   * @return 返回商品表的记录数。
   */
  int selectTotal(ReqGoods reqGoods);

  /**
   * 综合查询商品表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqGoods 查询的参数。
   * @return 返回的商品表列表。
   */
  List<RepGoods.GoodsRow> selectRelative(ReqGoods reqGoods);

  /**
   * 综合查询商品表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqGoods 查询的参数。
   * @return 返回的商品表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqGoods reqGoods);

  /**
   * 综合查询商品表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqGoods} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询商品表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询商品表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的商品表列表。
   */
  List<Goods> selectConditional(ParamQuery paramQuery);

  /**
   * 统计商品表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}