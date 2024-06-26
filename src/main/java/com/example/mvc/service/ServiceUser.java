package com.example.mvc.service;

import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.ConfigCommon;
import com.forsrc.common.constant.Enum;
import com.forsrc.common.db.batch.DbBatch;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.extend.bean.Field;
import com.forsrc.common.extend.bean.ParamExport;
import com.forsrc.common.extend.tool.ToolExport;
import com.forsrc.common.spring.base.IService;
import com.forsrc.common.tool.Tool;
import com.forsrc.common.tool.ToolJson;
import com.example.common.spring.base.BaseService;
import com.example.mvc.bean.detail.DetailUser;
import com.example.mvc.bean.rep.RepUser;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.dao.DaoUser;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.mvc.cache.CacheUser;
import com.example.mvc.event.before.BeforeUser;
import com.example.mvc.event.after.AfterUser;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class ServiceUser extends BaseService implements IService<User> {
  private static final String tableName = "User";

  @Resource
  private CacheUser cacheUser;
  @Resource
  private BeforeUser beforeUser;
  @Resource
  private AfterUser afterUser;
  @Resource
  private PasswordEncoder passwordEncoder;
  @Resource
  private DaoUser daoUser;
  @Resource
  private DbBatch<User> dbBatch;

  /**
   * 添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 返回添加的用户。
   */
  public User insert(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (user.getPassword() == null) {
      throw new CommonException("invalid password!");
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    beforeUser.onInsert(request, response, user);
    int count = daoUser.insert(user);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheUser.put(user);
    }
    afterUser.onInsert(request, response, user);
    return user;
  }

  /**
   * 添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 返回添加的用户。
   */
  public User insert(User user) {
    return insert(null, null, user);
  }

  /**
   * 同步批量添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param users 用户。
   * @return 返回添加的用户数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<User> users) {
    if (users == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (User user : users) {
      if (user.getPassword() == null) {
        throw new CommonException("invalid password!");
      } else {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      beforeUser.onInsert(request, response, user);
      int count = daoUser.insert(user);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheUser.put(user);
      }
      afterUser.onInsert(request, response, user);
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param users 用户。
   * @return 返回添加的用户数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<User> users) {
    return insertSync(null, null, users);
  }

  /**
   * 异步批量添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param users 用户。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<User> users) {
    if (users == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (User user : users) {
      if (user.getPassword() == null) {
        throw new CommonException("invalid password!");
      } else {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      }
      beforeUser.onInsert(request, response, user);
      dbBatch.insert(user, daoUser);
      afterUser.onInsert(request, response, user);
    }
  }

  /**
   * 异步批量添加用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param users 用户。
   */
  public void insertAsyn(List<User> users) {
    insertAsyn(null, null, users);
  }

  /**
   * 更新用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    beforeUser.onUpdate(request, response, user);
    int count = daoUser.update(user);
    if (count > 0) {
      cacheUser.update(daoUser.selectOne(user));
    }
    afterUser.onUpdate(request, response, user, count);
    return count;
  }

  /**
   * 更新用户。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(User user) {
    return update(null, null, user);
  }

  /**
   * 更新用户。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    beforeUser.onUpdate(request, response, user);
    int count = daoUser.updateEvenNull(user);
    if (count > 0) {
      cacheUser.update(daoUser.selectOne(user));
    }
    afterUser.onUpdate(request, response, user, count);
    return count;
  }

  /**
   * 更新用户。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(User user) {
    return updateEvenNull(null, null, user);
  }

  /**
   * 删除用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<User> users_ = daoUser.select(user);
    if (Tool.isNull(users_)) {
      return 0;
    }
    beforeUser.onDelete(request, response, user, users_);
    for (User user1 : users_) {
      cacheUser.delete(user1.getId());
    }
    afterUser.onDelete(request, response, user, users_);
    return users_.size();
  }

  /**
   * 删除用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  public int delete(User user) {
    return delete(null, null, user);
  }

  /**
   * 删除用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 用户编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    User user = new User();
    user.setId(id);
    User user1 = cacheUser.get(id);
    if (user1 == null) {
      return 0;
    }
    List<User> users_ = new ArrayList<>();
    users_.add(user1);
    beforeUser.onDelete(request, response, user1, users_);
    cacheUser.delete(id);
    afterUser.onDelete(request, response, user1, users_);
    return 1;
  }

  /**
   * 删除用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 用户编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条用户。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 用户编号。
   * @return 返回用户。
   */
  public User selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    User user1 = new User();
    user1.setId(id);
    beforeUser.onSelect(request, response, user1);
    User user = cacheUser.get(id);
    User user2 = new User();
    user2.setId(id);
    afterUser.onSelect(request, response, user2, user);
    return user;
  }

  /**
   * 根据主键查询一条用户。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 用户编号。
   * @return 返回用户。
   */
  public User selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条用户。
   * @param user 用户。
   * @return 返回用户。
   */
  public User selectOne(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    beforeUser.onSelect(request, response, user);
    User user1 = daoUser.selectOne(user);
    afterUser.onSelect(request, response, user, user1);
    return user1;
  }

  /**
   * 根据条件查询一条用户。
   * @param user 用户。
   * @return 返回用户。
   */
  public User selectOne(User user) {
    return selectOne(null, null, user);
  }

  /**
   * 根据条件查询一条用户详情。
   * @param user 用户。
   * @return 返回用户详情。
   */
  public DetailUser selectDetail(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    beforeUser.onSelectDetail(request, response, user);
    DetailUser detailUser = daoUser.selectDetail(user);
    afterUser.onSelectDetail(request, response, user, detailUser);
    return detailUser;
  }

  /**
   * 根据条件查询一条用户详情。
   * @param user 用户。
   * @return 返回用户详情。
   */
  public DetailUser selectDetail(User user) {
    return selectDetail(null, null, user);
  }

  /**
   * 根据主键查询一条用户详情。
   * @param id 用户编号。
   * @return 返回用户详情。
   */
  public DetailUser selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    User user1 = new User();
    user1.setId(id);
    beforeUser.onSelectDetail(request, response, user1);
    DetailUser detailUser = daoUser.selectDetailByPrimary(id);
    User user2 = new User();
    user2.setId(id);
    afterUser.onSelectDetail(request, response, user2, detailUser);
    return detailUser;
  }

  /**
   * 根据主键查询一条用户详情。
   * @param id 用户编号。
   * @return 返回用户详情。
   */
  public DetailUser selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询用户列表。返回所有符合条件的用户，未分页。
   * @param user 用户。
   * @return 返回用户列表。
   */
  public List<User> select(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    beforeUser.onSelect(request, response, user);
    List<User> list = daoUser.select(user);
    afterUser.onSelect(request, response, user, list);
    return list;
  }

  /**
   * 查询用户列表。返回所有符合条件的用户，未分页。
   * @param user 用户。
   * @return 返回用户列表。
   */
  public List<User> select(User user) {
    return select(null, null, user);
  }

  /**
   * 综合查询用户列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqUser 查询的参数。
   * @return 返回用户列表。
   */
  public RepUser selectRelative(HttpServletRequest request, HttpServletResponse response, ReqUser reqUser) {
    if (reqUser == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqUser);
    beforeUser.onSelectRelative(request, response, reqUser);
    RepUser repUser = new RepUser();
    if (isQueryTotal(reqUser)) {
      repUser.setTotal(daoUser.selectTotal(reqUser));
    }
    repUser.setRows(daoUser.selectRelative(reqUser));
    afterUser.onSelectRelative(request, response, reqUser, repUser);
    return repUser;
  }

  /**
   * 综合查询用户列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqUser 查询的参数。
   * @return 返回用户列表。
   */
  public RepUser selectRelative(ReqUser reqUser) {
    return selectRelative(null, null, reqUser);
  }

  /**
   * 根据唯一键更新一条用户，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByUsername(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    beforeUser.onUpdate(request, response, user);
    int count = daoUser.updateByUsername(user);
    if (count > 0) {
      cacheUser.update(daoUser.selectOne(user));
    }
    afterUser.onUpdate(request, response, user, count);
    return count;
  }

  /**
   * 根据唯一键更新一条用户，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByUsername(User user) {
    return updateByUsername(null, null, user);
  }

  /**
   * 根据唯一键删除一条用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  public int deleteByUsername(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    User user1 = new User();
    user1.setUsername(user.getUsername());
    User user2 = cacheUser.getByUsername(user1);
    if (user2 == null) {
      return 0;
    }
    List<User> users_ = new ArrayList<>();
    users_.add(user2);
    beforeUser.onDelete(request, response, user2, users_);
    cacheUser.deleteByUsername(user2);
    afterUser.onDelete(request, response, user2, users_);
    return 1;
  }

  /**
   * 根据唯一键删除一条用户。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  public int deleteByUsername(User user) {
    return deleteByUsername(null, null, user);
  }

  /**
   * 根据唯一键查询一条用户。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param user 用户。
   * @return 返回用户。
   */
  public User selectByUsername(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    beforeUser.onSelect(request, response, user);
    User user0 = cacheUser.getByUsername(user);
    afterUser.onSelect(request, response, user, user0);
    return user0;
  }

  /**
   * 根据唯一键查询一条用户。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param user 用户。
   * @return 返回用户。
   */
  public User selectByUsername(User user) {
    return selectByUsername(null, null, user);
  }

  /**
   * 根据唯一键查询一条用户详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param user 用户。
   * @return 返回用户。
   */
  public DetailUser selectDetailByUsername(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    beforeUser.onSelectDetail(request, response, user);
    DetailUser detailUser = daoUser.selectDetailByUsername(user);
    afterUser.onSelectDetail(request, response, user, detailUser);
    return detailUser;
  }

  /**
   * 根据唯一键查询一条用户详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param user 用户。
   * @return 返回用户。
   */
  public DetailUser selectDetailByUsername(User user) {
    return selectDetailByUsername(null, null, user);
  }

  /**
   * 导出用户到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqUser reqUser;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqUser = ToolJson.toBean(reqParam, ReqUser.class);
    } else {
      reqUser = new ReqUser();
    }
    beforeUser.onSelectRelative(request, response, reqUser);
    List<Map<String, Object>> list = daoUser.selectRelativeMap(reqUser);
    beforeUser.onExport(request, response, paramExport, list);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "用户";
    }
    List<Field> fields = getFields(paramExport.getFields());
    ToolExport.export(response, tableName, title, fields, list);
    afterUser.onExport(request, response, paramExport, list);
  }

  private List<Field> getFields(List<Field> fields) {
    for (Field field : fields) {
      setExportFieldType(field);
    }
    return fields;
  }

  private void setExportFieldType(Field field) {
    if (field.getExportFieldType() != null) {
      return;
    }
    String name = field.getName();
    switch (name) {
      case "id":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "roleType":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "sexType":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "userStatus":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "username":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "email":
        field.setLength(128);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "score":
        field.setExportFieldType(Enum.ExportFieldType.long_);
        break;
      case "times":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "birthDate":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      case "headImgUrl":
        field.setLength(256);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "updateTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      case "addTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}