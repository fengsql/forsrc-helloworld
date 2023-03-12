package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoDistrict;
import com.example.mvc.model.District;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheDistrict extends BCacheTable<District> {

  @Resource
  private DaoDistrict daoDistrict;

  @PostConstruct
  private void initialize() {
    super.initialize("district", District.class, daoDistrict);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条县表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 县编号。
   * @return null无记录；非空为返回的县表。
   */
  public District get(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加县表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param district 县表。
   */
  public void put(District district) {
    if (district == null) {
      log.warn("district is null!");
      return;
    }
    addCache(district);
    log.info("put district ok. id: " + district.getId());
  }

  /**
   * 更新县表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param district 县表。
   */
  public boolean update(District district) {
    if (district == null) {
      log.warn("district is null!");
      return false;
    }
    boolean ok = updateCache(district);
    if (ok) {
      log.info("update district ok. id: " + district.getId());
    } else {
      log.warn("update district fail! id: " + district.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条县表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 县编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(id));
    if (ok) {
      log.info("delete district ok. id: " + id);
    } else {
      log.warn("delete district fail! id: " + id);
    }
    return ok;
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条县表。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return null无记录。非空返回县表。
   */
  public District getByDistrictName(Integer cityId, String districtName) {
    District district;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("cityId", Tool.toString(cityId));
    map.put("districtName", Tool.toString(districtName));
    String key = getIndexCache(map);
    if (key != null) {
//      district = getTableByKey(key);
      district = getTable(key);
      if (district != null) {
        return district;
      }
    }
    district = selectByDistrictName(cityId, districtName);
    if (district != null) {
      addCache(district);
    }
    return district;
  }
  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param district 县表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(District district) {
    return district == null || district.getId() == null ? null : Tool.toString(district.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param district 县表。
   * @param id 主键值。
   */
  @Override
  protected void setPrimaryId(District district, String id) {
    district.setId(Tool.toInteger(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param district 县表。
   */
  @Override
  protected void putIndex(District district) {
    if (district == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("cityId", Tool.toString(district.getCityId()));
    map.put("districtName", Tool.toString(district.getDistrictName()));
    setIndexCache(Tool.toString(getPrimaryId(district)), map);
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的县表。
   * @param old 旧的县表。
   */
  @Override
  protected void updateIndex(District now, District old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    District district = getCache(id);
    if (district == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("cityId", Tool.toString(district.getCityId()));
    map.put("districtName", Tool.toString(district.getDistrictName()));
    deleteIndex(map);
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param district 县表。
   */
  @Override
  protected void refreshIndex(District district) {
    String key;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("cityId", Tool.toString(district.getCityId()));
    map.put("districtName", Tool.toString(district.getDistrictName()));
    key = getKeyIndex(map);
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加县表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param district 县表。
   */
  private void addCache(District district) {
    putCache(district);
    putIndex(district);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- select -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)从数据库中查询县表。
   * @param cityId 市编号。
   * @param districtName 县名称。
   * @return null无记录；非空为返回的县表。
   */
  private District selectByDistrictName(Integer cityId, String districtName) {
    District district = new District();
    district.setCityId(cityId);
    district.setDistrictName(districtName);
    return daoDistrict.selectOne(district);
  }
  // >>>----------------------- select -----------------------

  // >>----------------------- protected -----------------------

}