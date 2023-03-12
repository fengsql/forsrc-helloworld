package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoCity;
import com.example.mvc.model.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheCity extends BCacheTable<City> {

  @Resource
  private DaoCity daoCity;

  @PostConstruct
  private void initialize() {
    super.initialize("city", City.class, daoCity);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条市表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 市编号。
   * @return null无记录；非空为返回的市表。
   */
  public City get(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加市表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param city 市表。
   */
  public void put(City city) {
    if (city == null) {
      log.warn("city is null!");
      return;
    }
    addCache(city);
    log.info("put city ok. id: " + city.getId());
  }

  /**
   * 更新市表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param city 市表。
   */
  public boolean update(City city) {
    if (city == null) {
      log.warn("city is null!");
      return false;
    }
    boolean ok = updateCache(city);
    if (ok) {
      log.info("update city ok. id: " + city.getId());
    } else {
      log.warn("update city fail! id: " + city.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条市表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 市编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(id));
    if (ok) {
      log.info("delete city ok. id: " + id);
    } else {
      log.warn("delete city fail! id: " + id);
    }
    return ok;
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条市表。
   * @param provinceId 省编号。
   * @param cityName 市名称。
   * @return null无记录。非空返回市表。
   */
  public City getByCityName(Integer provinceId, String cityName) {
    City city;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("provinceId", Tool.toString(provinceId));
    map.put("cityName", Tool.toString(cityName));
    String key = getIndexCache(map);
    if (key != null) {
//      city = getTableByKey(key);
      city = getTable(key);
      if (city != null) {
        return city;
      }
    }
    city = selectByCityName(provinceId, cityName);
    if (city != null) {
      addCache(city);
    }
    return city;
  }
  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param city 市表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(City city) {
    return city == null || city.getId() == null ? null : Tool.toString(city.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param city 市表。
   * @param id 主键值。
   */
  @Override
  protected void setPrimaryId(City city, String id) {
    city.setId(Tool.toInteger(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param city 市表。
   */
  @Override
  protected void putIndex(City city) {
    if (city == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("provinceId", Tool.toString(city.getProvinceId()));
    map.put("cityName", Tool.toString(city.getCityName()));
    setIndexCache(Tool.toString(getPrimaryId(city)), map);
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的市表。
   * @param old 旧的市表。
   */
  @Override
  protected void updateIndex(City now, City old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    City city = getCache(id);
    if (city == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("provinceId", Tool.toString(city.getProvinceId()));
    map.put("cityName", Tool.toString(city.getCityName()));
    deleteIndex(map);
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param city 市表。
   */
  @Override
  protected void refreshIndex(City city) {
    String key;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("provinceId", Tool.toString(city.getProvinceId()));
    map.put("cityName", Tool.toString(city.getCityName()));
    key = getKeyIndex(map);
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加市表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param city 市表。
   */
  private void addCache(City city) {
    putCache(city);
    putIndex(city);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- select -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)从数据库中查询市表。
   * @param provinceId 省编号。
   * @param cityName 市名称。
   * @return null无记录；非空为返回的市表。
   */
  private City selectByCityName(Integer provinceId, String cityName) {
    City city = new City();
    city.setProvinceId(provinceId);
    city.setCityName(cityName);
    return daoCity.selectOne(city);
  }
  // >>>----------------------- select -----------------------

  // >>----------------------- protected -----------------------

}