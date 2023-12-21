package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoProvince;
import com.example.mvc.model.Province;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheProvince extends BCacheTable<Province> {

  @Resource
  private DaoProvince daoProvince;

  @PostConstruct
  private void initialize() {
    super.initialize("province", Province.class, daoProvince);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条省表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 省编号。
   * @return null无记录；非空为返回的省表。
   */
  public Province get(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加省表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param province 省表。
   */
  public void put(Province province) {
    if (province == null) {
      log.warn("province is null!");
      return;
    }
    addCache(province);
    log.info("put province ok. id: " + province.getId());
  }

  /**
   * 更新省表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param province 省表。
   */
  public boolean update(Province province) {
    if (province == null) {
      log.warn("province is null!");
      return false;
    }
    boolean ok = updateCache(province);
    if (ok) {
      log.debug("update province ok. id: " + province.getId());
    } else {
      log.warn("update province fail! id: " + province.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条省表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 省编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(id));
    if (ok) {
      log.info("delete province ok. id: " + id);
    } else {
      log.warn("delete province fail! id: " + id);
    }
    return ok;
  }

  /**
   * 根据主键清除一条缓存的省表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。仅清除缓存，不清除数据库。
   * @param id 省编号。
   */
  public void remove(Integer id) {
    removeTable(Tool.toString(id));
  }

  /**
   * 清除所有缓存的省表。仅清除缓存，不清除数据库。
   */
  public void clear() {
    removeAll();
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据唯一键删除一条省表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param province 省表。
   * @return true 成功。false 失败。
   */
  public boolean deleteByProvinceName(Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    Province province1 = getByProvinceName(province);
    return deleteTable(Tool.toString(province1.getId()));
  }

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条省表。
   * @param province 省表。
   * @return null无记录。非空返回省表。
   */
  public Province getByProvinceName(Province province) {
    if (province == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(province.getProvinceName())) {
      throw new CommonException(Code.PARAM_EMPTY, "provinceName is null!");
    }
    Map<String, String> map;
    String key = getIndexCache("provinceName", Tool.toString(province.getProvinceName()));
    Province province1;
    if (key != null) {
      province1 = getTable(key);
      if (province1 != null) {
        return province1;
      }
    }
    province1 = daoProvince.selectByProvinceName(province);
    if (province1 != null) {
      addCache(province1);
    }
    return province1;
  }

  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param province 省表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(Province province) {
    return province == null || province.getId() == null ? null : Tool.toString(province.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param province 省表。
   * @param id      主键值。
   */
  @Override
  protected void setPrimaryId(Province province, String id) {
    province.setId(Tool.toInteger(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param province 省表。
   */
  @Override
  protected void putIndex(Province province) {
    if (province == null) {
      return;
    }
    Map<String, String> map;
    setIndexCache(Tool.toString(getPrimaryId(province)), "provinceName", Tool.toString(province.getProvinceName()));
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的省表。
   * @param old 旧的省表。
   */
  @Override
  protected void updateIndex(Province now, Province old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    Province province = getCache(id);
    if (province == null) {
      return;
    }
    Map<String, String> map;
    deleteIndex("provinceName", Tool.toString(province.getProvinceName()));
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param province 省表。
   */
  @Override
  protected void refreshIndex(Province province) {
    String key;
    Map<String, String> map;
    key = getKeyIndex("provinceName", Tool.toString(province.getProvinceName()));
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加省表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param province 省表。
   */
  private void addCache(Province province) {
    putCache(province);
    putIndex(province);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- protected -----------------------

}