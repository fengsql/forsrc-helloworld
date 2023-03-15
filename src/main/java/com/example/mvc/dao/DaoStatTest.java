package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailStatTest;
import com.example.mvc.bean.rep.RepStatTest;
import com.example.mvc.bean.req.ReqStatTest;
import com.example.mvc.model.StatTest;

import java.util.List;
import java.util.Map;

public interface DaoStatTest extends IDao<StatTest> {

  /**
   * 添加统计信息。空值将被忽略。
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(StatTest statTest);

  /**
   * 添加统计信息。空值也会插入。
   * @param statTest 统计信息。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(StatTest statTest);

  /**
   * 更新统计信息。空值将被忽略。
   * @param statTest 统计信息。
   * @return 返回更新的记录数。
   */
  int update(StatTest statTest);

  /**
   * 更新统计信息。空值也会更新。
   * @param statTest 统计信息。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(StatTest statTest);

  /**
   * 更新统计信息。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除统计信息。可以设置主键、外键和常量字段可作为条件字段。
   * @param statTest 统计信息。
   * @return 返回删除的记录数。
   */
  int delete(StatTest statTest);
  /**
   * 删除统计信息。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 根据主键查询一条统计信息。
   * @param id 编号。
   * @return 返回的统计信息。
   */
  StatTest selectByPrimary(Integer id);

  /**
   * 查询一条统计信息。
   * @param statTest 统计信息。
   * @return 返回的统计信息。
   */
  StatTest selectOne(StatTest statTest);

  /**
   * 查询统计信息列表。返回所有符合条件的统计信息，未分页。
   * @param statTest 统计信息。
   * @return null无记录；非空为返回的统计信息列表。
   */
  List<StatTest> select(StatTest statTest);

  /**
   * 查询一条统计信息详情。
   * @param statTest 统计信息。
   * @return 返回的统计信息详情。
   */
  DetailStatTest selectDetail(StatTest statTest);

  /**
   * 根据主键查询一条统计信息详情。
   * @param id 编号。
   * @return 返回的统计信息详情。
   */
  DetailStatTest selectDetailByPrimary(Integer id);

  /**
   * 根据条件查询统计信息的记录数。
   * @param reqStatTest 查询的参数。
   * @return 返回统计信息的记录数。
   */
  int selectTotal(ReqStatTest reqStatTest);

  /**
   * 综合查询统计信息列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqStatTest 查询的参数。
   * @return 返回的统计信息列表。
   */
  List<RepStatTest.StatTestRow> selectRelative(ReqStatTest reqStatTest);

  /**
   * 综合查询统计信息列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqStatTest 查询的参数。
   * @return 返回的统计信息列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqStatTest reqStatTest);

  /**
   * 综合查询统计信息列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqStatTest} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询统计信息列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的统计信息列表。
   */
  List<StatTest> selectConditional(ParamQuery paramQuery);

  /**
   * 统计统计信息列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}