package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailProvince;
import com.example.mvc.bean.rep.RepProvince;
import com.example.mvc.bean.req.ReqProvince;
import com.example.mvc.model.Province;

import java.util.List;
import java.util.Map;

public interface DaoProvince extends IDao<Province> {

  /**
   * 添加省表。空值将被忽略。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(Province province);

  /**
   * 添加省表。空值也会插入。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(Province province);

  /**
   * 更新省表。空值将被忽略。
   * @param province 省表。
   * @return 返回更新的记录数。
   */
  int update(Province province);

  /**
   * 更新省表。空值也会更新。
   * @param province 省表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(Province province);

  /**
   * 更新省表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除省表。可以设置主键、外键和常量字段可作为条件字段。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  int delete(Province province);
  /**
   * 删除省表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条省表。
   * @param id 省编号。
   * @return 返回的省表。
   */
  Province selectByPrimary(Integer id);

  /**
   * 查询一条省表。
   * @param province 省表。
   * @return 返回的省表。
   */
  Province selectOne(Province province);

  /**
   * 查询省表列表。返回所有符合条件的省表，未分页。
   * @param province 省表。
   * @return null无记录；非空为返回的省表列表。
   */
  List<Province> select(Province province);

  /**
   * 查询一条省表详情。
   * @param province 省表。
   * @return 返回的省表详情。
   */
  DetailProvince selectDetail(Province province);

  /**
   * 根据主键查询一条省表详情。
   * @param id 省编号。
   * @return 返回的省表详情。
   */
  DetailProvince selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询省表的记录数。
   * @param reqProvince 查询的参数。
   * @return 返回省表的记录数。
   */
  int selectTotal(ReqProvince reqProvince);

  /**
   * 综合查询省表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqProvince 查询的参数。
   * @return 返回的省表列表。
   */
  List<RepProvince.ProvinceRow> selectRelative(ReqProvince reqProvince);

  /**
   * 综合查询省表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqProvince 查询的参数。
   * @return 返回的省表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqProvince reqProvince);

  /**
   * 综合查询省表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqProvince} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询省表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的省表列表。
   */
  List<Province> selectConditional(ParamQuery paramQuery);

  /**
   * 根据唯一键更新一条省表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param province 省表。
   * @return 返回更新的记录数。
   */
  int updateByProvinceName(Province province);

  /**
   * 根据唯一键删除一条省表。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  int deleteByProvinceName(Province province);

  /**
   * 根据唯一键查询一条省表。
   * @param province 省表。
   * @return 返回的省表。
   */
  Province selectByProvinceName(Province province);

  /**
   * 根据唯一键查询一条省表详情。
   * @param province 省表。
   * @return 返回的省表详情。
   */
  DetailProvince selectDetailByProvinceName(Province province);

  /**
   * 统计省表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}