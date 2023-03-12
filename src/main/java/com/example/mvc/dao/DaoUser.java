package com.example.mvc.dao;

import com.forsrc.common.spring.base.IDao;
import com.forsrc.common.spring.bean.param.ParamDelete;
import com.forsrc.common.spring.bean.param.ParamQuery;
import com.forsrc.common.spring.bean.param.ParamUpdate;
import com.example.mvc.bean.detail.DetailUser;
import com.example.mvc.bean.rep.RepUser;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.model.User;

import java.util.List;
import java.util.Map;

public interface DaoUser extends IDao<User> {

  /**
   * 添加用户。空值将被忽略。
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insert(User user);

  /**
   * 添加用户。空值也会插入。
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回插入的记录数。
   */
  int insertEvenNull(User user);

  /**
   * 更新用户。空值将被忽略。
   * @param user 用户。
   * @return 返回更新的记录数。
   */
  int update(User user);

  /**
   * 更新用户。空值也会更新。
   * @param user 用户。
   * @return 返回更新的记录数。
   */
  int updateEvenNull(User user);

  /**
   * 更新用户。此操作有 sql 注入风险。
   * @param paramUpdate 更新参数。
   * @return 返回更新的记录数。
   */
  int updateConditional(ParamUpdate paramUpdate);

  /**
   * 删除用户。可以设置主键、外键和常量字段可作为条件字段。
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  int delete(User user);

  /**
   * 删除用户。此操作有 sql 注入风险。
   * @param paramDelete 删除条件，可以设置主键或条件。
   * @return 返回删除的记录数。
   */
  int deleteConditional(ParamDelete paramDelete);

  /**
   * 查询一条用户。
   * @param user 用户。
   * @return 返回的用户。
   */
  User selectOne(User user);

  /**
   * 查询用户列表。返回所有符合条件的用户，未分页。
   * @param user 用户。
   * @return null无记录；非空为返回的用户列表。
   */
  List<User> select(User user);

  /**
   * 查询一条用户详情。
   * @param user 用户。
   * @return 返回的用户。
   */
  DetailUser selectDetail(User user);

  /**
   * 根据条件查询用户的记录数。
   * @param reqUser 查询的参数。
   * @return 返回用户的记录数。
   */
  int selectTotal(ReqUser reqUser);

  /**
   * 综合查询用户列表，综合查询将查询出关联表的字段。可以设置分页。
   * @param reqUser 查询的参数。
   * @return 返回的用户列表。
   */
  List<RepUser.UserRow> selectRelative(ReqUser reqUser);

  /**
   * 综合查询用户列表，综合查询将查询出关联表的字段。设置分页无效。导出数据时使用此方法。
   * @param reqUser 查询的参数。
   * @return 返回的用户列表。
   */
  List<Map<String, Object>> selectRelativeMap(ReqUser reqUser);

  /**
   * 综合查询用户列表，综合查询会查询出关联表的字段。可以设置分页。可以设置查询的字段(驼峰命名)，设置排序等。
   * @param map 查询条件、字段、排序、分页等，查询条件与{@link ReqUser} 一致。查询字段有注入风险。
   * @return 返回查询出的数据集合。
   */
  List<Map<String, Object>> selectList(Map<String, Object> map);

  /**
   * 查询用户列表。可以设置分页。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 返回的用户列表。
   */
  List<User> selectConditional(ParamQuery paramQuery);

  /**
   * 统计用户列表。此查询有 sql 注入风险。
   * @param paramQuery 查询的参数。
   * @return 统计值。
   */
  Double selectSum(ParamQuery paramQuery);

}