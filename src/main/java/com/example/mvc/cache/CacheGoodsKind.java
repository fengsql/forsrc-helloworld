package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoGoodsKind;
import com.example.mvc.model.GoodsKind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheGoodsKind extends BCacheTable<GoodsKind> {

  @Resource
  private DaoGoodsKind daoGoodsKind;

  @PostConstruct
  private void initialize() {
    super.initialize("goodsKind", GoodsKind.class, daoGoodsKind);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条商品类别表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 商品编号。
   * @return null无记录；非空为返回的商品类别表。
   */
  public GoodsKind get(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加商品类别表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goodsKind 商品类别表。
   */
  public void put(GoodsKind goodsKind) {
    if (goodsKind == null) {
      log.warn("goodsKind is null!");
      return;
    }
    addCache(goodsKind);
    log.info("put goodsKind ok. id: " + goodsKind.getId());
  }

  /**
   * 更新商品类别表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goodsKind 商品类别表。
   */
  public boolean update(GoodsKind goodsKind) {
    if (goodsKind == null) {
      log.warn("goodsKind is null!");
      return false;
    }
    boolean ok = updateCache(goodsKind);
    if (ok) {
      log.debug("update goodsKind ok. id: " + goodsKind.getId());
    } else {
      log.warn("update goodsKind fail! id: " + goodsKind.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条商品类别表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param id 商品编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(id));
    if (ok) {
      log.info("delete goodsKind ok. id: " + id);
    } else {
      log.warn("delete goodsKind fail! id: " + id);
    }
    return ok;
  }

  /**
   * 根据主键清除一条缓存的商品类别表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。仅清除缓存，不清除数据库。
   * @param id 商品编号。
   */
  public void remove(Integer id) {
    removeTable(Tool.toString(id));
  }

  /**
   * 清除所有缓存的商品类别表。仅清除缓存，不清除数据库。
   */
  public void clear() {
    removeAll();
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据唯一键删除一条商品类别表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param goodsKind 商品类别表。
   * @return true 成功。false 失败。
   */
  public boolean deleteByName(GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    GoodsKind goodsKind1 = getByName(goodsKind);
    return deleteTable(Tool.toString(goodsKind1.getId()));
  }

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return null无记录。非空返回商品类别表。
   */
  public GoodsKind getByName(GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    Map<String, String> map;
    String key = getIndexCache("name", Tool.toString(goodsKind.getName()));
    GoodsKind goodsKind1;
    if (key != null) {
      goodsKind1 = getTable(key);
      if (goodsKind1 != null) {
        return goodsKind1;
      }
    }
    goodsKind1 = daoGoodsKind.selectByName(goodsKind);
    if (goodsKind1 != null) {
      addCache(goodsKind1);
    }
    return goodsKind1;
  }

  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param goodsKind 商品类别表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(GoodsKind goodsKind) {
    return goodsKind == null || goodsKind.getId() == null ? null : Tool.toString(goodsKind.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param goodsKind 商品类别表。
   * @param id      主键值。
   */
  @Override
  protected void setPrimaryId(GoodsKind goodsKind, String id) {
    goodsKind.setId(Tool.toInteger(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goodsKind 商品类别表。
   */
  @Override
  protected void putIndex(GoodsKind goodsKind) {
    if (goodsKind == null) {
      return;
    }
    Map<String, String> map;
    setIndexCache(Tool.toString(getPrimaryId(goodsKind)), "name", Tool.toString(goodsKind.getName()));
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的商品类别表。
   * @param old 旧的商品类别表。
   */
  @Override
  protected void updateIndex(GoodsKind now, GoodsKind old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    GoodsKind goodsKind = getCache(id);
    if (goodsKind == null) {
      return;
    }
    Map<String, String> map;
    deleteIndex("name", Tool.toString(goodsKind.getName()));
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param goodsKind 商品类别表。
   */
  @Override
  protected void refreshIndex(GoodsKind goodsKind) {
    String key;
    Map<String, String> map;
    key = getKeyIndex("name", Tool.toString(goodsKind.getName()));
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加商品类别表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goodsKind 商品类别表。
   */
  private void addCache(GoodsKind goodsKind) {
    putCache(goodsKind);
    putIndex(goodsKind);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- protected -----------------------

}