package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailMerchant;
import com.example.mvc.bean.rep.RepMerchant;
import com.example.mvc.bean.req.ReqMerchant;
import com.example.mvc.model.Merchant;

import java.util.List;
import java.util.Map;

public interface DaoMerchant extends IDao<Merchant> {

  /**
   * 添加商户表。空值将被忽略。
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(Merchant merchant);

  /**
   * 添加商户表。空值也会插入。
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(Merchant merchant);

  /**
   * 更新商户表。空值将被忽略。
   * @param merchant 商户表。
   * @return 返回更新的记录数。
   */
  int update(Merchant merchant);

  /**
   * 更新商户表。空值也会更新。
   * @param merchant 商户表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(Merchant merchant);

  /**
   * 更新商户表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除商户表。可以设置主键、外键和常量字段可作为条件字段。
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  int delete(Merchant merchant);

  /**
   * 删除商户表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条商户表。
   * @param id 商户编号。
   * @return 返回的商户表。
   */
  Merchant selectByPrimary(Integer id);

  /**
   * 查询一条商户表。
   * @param merchant 商户表。
   * @return 返回的商户表。
   */
  Merchant selectOne(Merchant merchant);

  /**
   * 查询商户表列表。返回所有符合条件的商户表，未分页。
   * @param merchant 商户表。
   * @return null无记录；非空为返回的商户表列表。
   */
  List<Merchant> select(Merchant merchant);

  /**
   * 查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回的商户表详情。
   */
  DetailMerchant selectDetail(Merchant merchant);

  /**
   * 根据主键查询一条商户表详情。
   * @param id 商户编号。
   * @return 返回的商户表详情。
   */
  DetailMerchant selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询商户表的记录数。
   * @param reqMerchant 查询的参数。
   * @return 返回商户表的记录数。
   */
  int selectTotal(ReqMerchant reqMerchant);

  /**
   * 综合查询商户表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqMerchant 查询的参数。
   * @return 返回的商户表列表。
   */
  List<RepMerchant.MerchantRow> selectRelative(ReqMerchant reqMerchant);

  /**
   * 综合查询商户表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqMerchant 查询的参数。
   * @return 返回的商户表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqMerchant reqMerchant);

  /**
   * 综合查询商户表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqMerchant} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询商户表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询商户表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的商户表列表。
   */
  List<Merchant> selectConditional(ParamQuery paramQuery);

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param merchant 商户表。
   * @return 返回更新的记录数。
   */
  int updateByMchNo(Merchant merchant);

  /**
   * 根据唯一键删除一条商户表。
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  int deleteByMchNo(Merchant merchant);

  /**
   * 根据唯一键查询一条商户表。
   * @param merchant 商户表。
   * @return 返回的商户表。
   */
  Merchant selectByMchNo(Merchant merchant);

  /**
   * 根据唯一键查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回的商户表详情。
   */
  DetailMerchant selectDetailByMchNo(Merchant merchant);

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param merchant 商户表。
   * @return 返回更新的记录数。
   */
  int updateByAppid(Merchant merchant);

  /**
   * 根据唯一键删除一条商户表。
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  int deleteByAppid(Merchant merchant);

  /**
   * 根据唯一键查询一条商户表。
   * @param merchant 商户表。
   * @return 返回的商户表。
   */
  Merchant selectByAppid(Merchant merchant);

  /**
   * 根据唯一键查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回的商户表详情。
   */
  DetailMerchant selectDetailByAppid(Merchant merchant);

  /**
   * 统计商户表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}