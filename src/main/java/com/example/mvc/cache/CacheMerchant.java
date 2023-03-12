package com.example.mvc.cache;

import com.forsrc.common.cache.base.BCacheTable;
import com.forsrc.common.tool.Tool;
import com.example.mvc.dao.DaoMerchant;
import com.example.mvc.model.Merchant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheMerchant extends BCacheTable<Merchant> {

  @Resource
  private DaoMerchant daoMerchant;

  @PostConstruct
  private void initialize() {
    super.initialize("merchant", Merchant.class, daoMerchant);
  }

  // <<----------------------- public -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 根据主键查询一条商户表。
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * @param merchantId 商户编号。
   * @return null无记录；非空为返回的商户表。
   */
  public Merchant get(String merchantId) {
    if (Tool.isNull(merchantId)) {
      log.warn("merchantId is null!");
      return null;
    }
    return getTable(Tool.toString(merchantId));
  }

  /**
   * 添加商户表到缓存，同时添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param merchant 商户表。
   */
  public void put(Merchant merchant) {
    if (merchant == null) {
      log.warn("merchant is null!");
      return;
    }
    addCache(merchant);
    log.info("put merchant ok. merchantId: " + merchant.getMerchantId());
  }

  /**
   * 更新商户表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param merchant 商户表。
   */
  public boolean update(Merchant merchant) {
    if (merchant == null) {
      log.warn("merchant is null!");
      return false;
    }
    boolean ok = updateCache(merchant);
    if (ok) {
      log.info("update merchant ok. merchantId: " + merchant.getMerchantId());
    } else {
      log.warn("update merchant fail! merchantId: " + merchant.getMerchantId());
    }
    return ok;
  }

  /**
   * 根据主键删除一条商户表。同时删除缓存索引字段(唯一字段且未禁用缓存)信息。
   * @param merchantId 商户编号。
   * @return true 成功。false 失败。
   */
  public boolean delete(String merchantId) {
    if (Tool.isNull(merchantId)) {
      log.warn("merchantId is null!");
      return false;
    }
    boolean ok = deleteTable(Tool.toString(merchantId));
    if (ok) {
      log.info("delete merchant ok. merchantId: " + merchantId);
    } else {
      log.warn("delete merchant fail! merchantId: " + merchantId);
    }
    return ok;
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条商户表。
   * @param mchName 商户名。
   * @param mchNo 商户号。
   * @return null无记录。非空返回商户表。
   */
  public Merchant getByMchNo(String mchName, String mchNo) {
    Merchant merchant;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("mchName", Tool.toString(mchName));
    map.put("mchNo", Tool.toString(mchNo));
    String key = getIndexCache(map);
    if (key != null) {
//      merchant = getTableByKey(key);
      merchant = getTable(key);
      if (merchant != null) {
        return merchant;
      }
    }
    merchant = selectByMchNo(mchName, mchNo);
    if (merchant != null) {
      addCache(merchant);
    }
    return merchant;
  }
  // >>>----------------------- index -----------------------

  // <<<----------------------- index -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)查询一条商户表。
   * @param appid Appid。
   * @return null无记录。非空返回商户表。
   */
  public Merchant getByAppid(String appid) {
    Merchant merchant;
    Map<String, String> map;
    String key = getIndexCache("appid", Tool.toString(appid));
    if (key != null) {
//      merchant = getTableByKey(key);
      merchant = getTable(key);
      if (merchant != null) {
        return merchant;
      }
    }
    merchant = selectByAppid(appid);
    if (merchant != null) {
      addCache(merchant);
    }
    return merchant;
  }
  // >>>----------------------- index -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  /**
   * 实现抽象方法。获取主键值。
   * @param merchant 商户表。
   * @return 返回主键值。
   */
  @Override
  protected String getPrimaryId(Merchant merchant) {
    return merchant == null || merchant.getMerchantId() == null ? null : Tool.toString(merchant.getMerchantId());
  }

  /**
   * 实现抽象方法。设置主键值。
   * @param merchant 商户表。
   * @param id 主键值。
   */
  @Override
  protected void setPrimaryId(Merchant merchant, String id) {
    merchant.setMerchantId(Tool.toString(id));
  }

  /**
   * 添加缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param merchant 商户表。
   */
  @Override
  protected void putIndex(Merchant merchant) {
    if (merchant == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("mchName", Tool.toString(merchant.getMchName()));
    map.put("mchNo", Tool.toString(merchant.getMchNo()));
    setIndexCache(Tool.toString(getPrimaryId(merchant)), map);
    setIndexCache(Tool.toString(getPrimaryId(merchant)), "appid", Tool.toString(merchant.getAppid()));
  }

  /**
   * 实现抽象方法。更新缓存索引字段(唯一字段且未禁用缓存)。
   * @param now 新的商户表。
   * @param old 旧的商户表。
   */
  @Override
  protected void updateIndex(Merchant now, Merchant old) {
    deleteIndex(Tool.toString(old.getMerchantId()));
    putIndex(now);
  }

  /**
   * 实现抽象方法。删除缓存索引字段(唯一字段且未禁用缓存)。
   * @param id 主键值。
   */
  @Override
  protected void deleteIndex(String id) {
    Merchant merchant = getCache(id);
    if (merchant == null) {
      return;
    }
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("mchName", Tool.toString(merchant.getMchName()));
    map.put("mchNo", Tool.toString(merchant.getMchNo()));
    deleteIndex(map);
    deleteIndex("appid", Tool.toString(merchant.getAppid()));
  }

  /**
   * 实现抽象方法。刷新缓存索引字段时间(唯一字段且未禁用缓存)。
   * @param merchant 商户表。
   */
  @Override
  protected void refreshIndex(Merchant merchant) {
    String key;
    Map<String, String> map;
    map = new LinkedHashMap<>();
    map.put("mchName", Tool.toString(merchant.getMchName()));
    map.put("mchNo", Tool.toString(merchant.getMchNo()));
    key = getKeyIndex(map);
    refreshExpire(key);
    key = getKeyIndex("appid", Tool.toString(merchant.getAppid()));
    refreshExpire(key);
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 添加商户表到缓存，同时更新缓存索引字段(唯一字段且未禁用缓存)到缓存。
   * @param merchant 商户表。
   */
  private void addCache(Merchant merchant) {
    putCache(merchant);
    putIndex(merchant);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- select -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)从数据库中查询商户表。
   * @param mchName 商户名。
   * @param mchNo 商户号。
   * @return null无记录；非空为返回的商户表。
   */
  private Merchant selectByMchNo(String mchName, String mchNo) {
    Merchant merchant = new Merchant();
    merchant.setMchName(mchName);
    merchant.setMchNo(mchNo);
    return daoMerchant.selectOne(merchant);
  }
  // >>>----------------------- select -----------------------

  // <<<----------------------- select -----------------------

  /**
   * 根据缓存索引字段(唯一字段且未禁用缓存)从数据库中查询商户表。
   * @param appid Appid。
   * @return null无记录；非空为返回的商户表。
   */
  private Merchant selectByAppid(String appid) {
    Merchant merchant = new Merchant();
    merchant.setAppid(appid);
    return daoMerchant.selectOne(merchant);
  }
  // >>>----------------------- select -----------------------

  // >>----------------------- protected -----------------------

}