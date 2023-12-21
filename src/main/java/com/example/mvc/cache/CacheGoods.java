package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoGoods;
import com.example.mvc.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheGoods extends BCacheTable<Goods> {

  @Resource
  private DaoGoods daoGoods;

  @PostConstruct
  private void initialize() {
    super.initialize("goods", Goods.class, daoGoods);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条商品表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param id 商品编号。
   * @return null无记录；非空为返回的商品表。
   */
  public Goods get(Integer id) {
    if (Tool.isNull(id)) {
      log.warn("id is null!");
      return null;
    }
    return getTable(Tool.toString(id));
  }

  /**
   * 添加商品表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goods 商品表。
   */
  public void put(Goods goods) {
    if (goods == null) {
      log.warn("goods is null!");
      return;
    }
    addCache(goods);
    log.info("put goods ok. id: " + goods.getId());
  }

  /**
   * 更新商品表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goods 商品表。
   */
  public boolean update(Goods goods) {
    if (goods == null) {
      log.warn("goods is null!");
      return false;
    }
    boolean ok = updateCache(goods);
    if (ok) {
      log.debug("update goods ok. id: " + goods.getId());
    } else {
      log.warn("update goods fail! id: " + goods.getId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条商品表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
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
      log.info("delete goods ok. id: " + id);
    } else {
      log.warn("delete goods fail! id: " + id);
    }
    return ok;
  }

  /**
   * 根据主键清除一条缓存的商品表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。仅清除缓存，不清除数据库。
   * @param id 商品编号。
   */
  public void remove(Integer id) {
    removeTable(Tool.toString(id));
  }

  /**
   * 清除所有缓存的商品表。仅清除缓存，不清除数据库。
   */
  public void clear() {
    removeAll();
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param goods 商品表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(Goods goods) {
    return goods == null || goods.getId() == null ? null : Tool.toString(goods.getId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param goods 商品表。
   * @param id      主键值。
   */
  @Override
  protected void setPrimaryId(Goods goods, String id) {
    goods.setId(Tool.toInteger(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goods 商品表。
   */
  @Override
  protected void putIndex(Goods goods) {
    Map<String, String> map;
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的商品表。
   * @param old 旧的商品表。
   */
  @Override
  protected void updateIndex(Goods now, Goods old) {
    deleteIndex(Tool.toString(old.getId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    Map<String, String> map;
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param goods 商品表。
   */
  @Override
  protected void refreshIndex(Goods goods) {
    String key;
    Map<String, String> map;
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加商品表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param goods 商品表。
   */
  private void addCache(Goods goods) {
    putCache(goods);
    putIndex(goods);
  }

  // >>>----------------------- normal -----------------------

  // >>----------------------- protected -----------------------

}