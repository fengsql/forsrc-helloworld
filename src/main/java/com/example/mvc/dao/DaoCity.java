package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailCity;
import com.example.mvc.bean.rep.RepCity;
import com.example.mvc.bean.req.ReqCity;
import com.example.mvc.model.City;

import java.util.List;
import java.util.Map;

public interface DaoCity extends IDao<City> {

  /**
   * 添加市表。空值将被忽略。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(City city);

  /**
   * 添加市表。空值也会插入。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(City city);

  /**
   * 更新市表。空值将被忽略。
   * @param city 市表。
   * @return 返回更新的记录数。
   */
  int update(City city);

  /**
   * 更新市表。空值也会更新。
   * @param city 市表。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(City city);

  /**
   * 更新市表。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除市表。可以设置主键、外键和常量字段可作为条件字段。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  int delete(City city);

  /**
   * 删除市表。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条市表。
   * @param id 市编号。
   * @return 返回的市表。
   */
  City selectByPrimary(Integer id);

  /**
   * 查询一条市表。
   * @param city 市表。
   * @return 返回的市表。
   */
  City selectOne(City city);

  /**
   * 查询市表列表。返回所有符合条件的市表，未分页。
   * @param city 市表。
   * @return null无记录；非空为返回的市表列表。
   */
  List<City> select(City city);

  /**
   * 查询一条市表详情。
   * @param city 市表。
   * @return 返回的市表详情。
   */
  DetailCity selectDetail(City city);

  /**
   * 根据主键查询一条市表详情。
   * @param id 市编号。
   * @return 返回的市表详情。
   */
  DetailCity selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询市表的记录数。
   * @param reqCity 查询的参数。
   * @return 返回市表的记录数。
   */
  int selectTotal(ReqCity reqCity);

  /**
   * 综合查询市表列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqCity 查询的参数。
   * @return 返回的市表列表。
   */
  List<RepCity.CityRow> selectRelative(ReqCity reqCity);

  /**
   * 综合查询市表列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqCity 查询的参数。
   * @return 返回的市表列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqCity reqCity);

  /**
   * 综合查询市表列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqCity} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询市表列表。可以指定查询字段，查询条件，设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回列表信息。
   */
  List<Map<String, Object>> selectMap(ParamQuery paramQuery);

  /**
   * 查询市表列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的市表列表。
   */
  List<City> selectConditional(ParamQuery paramQuery);

  /**
   * 根据唯一键更新一条市表，此方法不适用根据唯一键更改唯一键的字段值。
   * @param city 市表。
   * @return 返回更新的记录数。
   */
  int updateByCityName(City city);

  /**
   * 根据唯一键删除一条市表。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  int deleteByCityName(City city);

  /**
   * 根据唯一键查询一条市表。
   * @param city 市表。
   * @return 返回的市表。
   */
  City selectByCityName(City city);

  /**
   * 根据唯一键查询一条市表详情。
   * @param city 市表。
   * @return 返回的市表详情。
   */
  DetailCity selectDetailByCityName(City city);

  /**
   * 统计市表列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}