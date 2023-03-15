package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailDistrict;
import com.example.mvc.bean.rep.RepDistrict;
import com.example.mvc.bean.req.ReqDistrict;
import com.example.mvc.model.District;

import java.util.List;
import java.util.Map;

public interface DaoDistrict extends IDao<District> {

  /**
   * 添加县表。空值将被忽略。
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(District district);

  /**
   * 添加县表。空值也会插入。
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(District district);

  /**
   * 更新县表。空值将被忽略。
   * @param district 县表。
   * @return 返回更新的记录数。
   */
  int update(District district);

  /**
   * 更新县表。空值也会更新。
   * @param district 县表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(District district);

  /**
   * 更新县表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除县表。可以设置主键、外键和常量字段可作为条件字段。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  int delete(District district);
  /**
   * 删除县表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条县表。
   * @param id 县编号。
   * @return 返回的县表。
   */
  District selectByPrimary(Integer id);

  /**
   * 查询一条县表。
   * @param district 县表。
   * @return 返回的县表。
   */
  District selectOne(District district);

  /**
   * 查询县表列表。返回所有符合条件的县表，未分页。
   * @param district 县表。
   * @return null无记录；非空为返回的县表列表。
   */
  List<District> select(District district);

  /**
   * 查询一条县表详情。
   * @param district 县表。
   * @return 返回的县表详情。
   */
  DetailDistrict selectDetail(District district);

  /**
   * 根据主键查询一条县表详情。
   * @param id 县编号。
   * @return 返回的县表详情。
   */
  DetailDistrict selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询县表的记录数。
   * @param reqDistrict 查询的参数。
   * @return 返回县表的记录数。
   */
  int selectTotal(ReqDistrict reqDistrict);

  /**
   * 综合查询县表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqDistrict 查询的参数。
   * @return 返回的县表列表。
   */
  List<RepDistrict.DistrictRow> selectRelative(ReqDistrict reqDistrict);

  /**
   * 综合查询县表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqDistrict 查询的参数。
   * @return 返回的县表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqDistrict reqDistrict);

  /**
   * 综合查询县表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqDistrict} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询县表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的县表列表。
   */
  List<District> selectConditional(ParamQuery paramQuery);

  /**
   * 根据唯一键更新一条县表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param district 县表。
   * @return 返回更新的记录数。
   */
  int updateByDistrictName(District district);

  /**
   * 根据唯一键删除一条县表。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  int deleteByDistrictName(District district);

  /**
   * 根据唯一键查询一条县表。
   * @param district 县表。
   * @return 返回的县表。
   */
  District selectByDistrictName(District district);

  /**
   * 根据唯一键查询一条县表详情。
   * @param district 县表。
   * @return 返回的县表详情。
   */
  DetailDistrict selectDetailByDistrictName(District district);

  /**
   * 统计县表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}