package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoUser;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheUser extends BCacheTable<User> {

  @Resource
  private DaoUser daoUser;

  @PostConstruct
  private void initialize() {
    super.initialize("user", User.class, daoUser);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条用户。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 用户编号。
   * @return null无记录；非空为返回的用户。
   */
  public User get(Long id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加用户到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param user 用户。
   */
  public void put(User user) {
    if (user == null) {
      log.warn("user is null!");
      return;
    }
    addCache(user);
    log.info("put user ok. id: " + user.getId());
  }

  /**
   * 更新用户到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param user 用户。
   */
  public boolean update(User user) {
    if (user == null) {
      log.warn("user is null!");
      return false;
    }
    boolean ok = updateCache(user);
    if (ok) {
      log.info("update user ok. id: " + user.getId());
    } else {
      log.warn("update user fail! id: " + user.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条用户。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 用户编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(Long id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(id));
    if (ok) {
      log.info("delete user ok. id: " + id);
    } else {
      log.warn("delete user fail! id: " + id);
    }
    return ok;
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据唯一键删除一条用户。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param user 用户。
   * @return true 成功。false 失败。
   */
  public boolean deleteByUsername(User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    User user1 = getByUsername(user);
    return deleteTable(Tool.toString(user1.getId()));
  }

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条用户。
   * @param user 用户。
   * @return null无记录。非空返回用户。
   */
  public User getByUsername(User user) {
    if (user == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(user.getUsername())) {
      throw new CommonException(Code.PARAM_EMPTY, "username is null!");
    }
    Map<String, String> map;
    String key = getIndexCache("username", Tool.toString(user.getUsername()));
    User user1;
    if (key != null) {
      user1 = getTable(key);
      if (user1 != null) {
        return user1;
      }
    }
    user1 = daoUser.selectByUsername(user);
    if (user1 != null) {
      addCache(user1);
    }
    return user1;
  }

  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param user 用户。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(User user) {
    return user == null || user.getId() == null ? null : Tool.toString(user.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param user 用户。
   * @param id 主键值。
   */
  @Override
  protected void setPrimaryId(User user, String id) {
    user.setId(Tool.toLong(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param user 用户。
   */
  @Override
  protected void putIndex(User user) {
    if (user == null) {
      return;
    }
    Map<String, String> map;
    setIndexCache(Tool.toString(getPrimaryId(user)), "username", Tool.toString(user.getUsername()));
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的用户。
   * @param old 旧的用户。
   */
  @Override
  protected void updateIndex(User now, User old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    User user = getCache(id);
    if (user == null) {
      return;
    }
    Map<String, String> map;
    deleteIndex("username", Tool.toString(user.getUsername()));
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param user 用户。
   */
  @Override
  protected void refreshIndex(User user) {
    String key;
    Map<String, String> map;
    key = getKeyIndex("username", Tool.toString(user.getUsername()));
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加用户到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param user 用户。
   */
  private void addCache(User user) {
    putCache(user);
    putIndex(user);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- protected -----------------------

}