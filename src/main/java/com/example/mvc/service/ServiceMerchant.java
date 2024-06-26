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
import com.example.mvc.bean.detail.DetailMerchant;
import com.example.mvc.bean.rep.RepMerchant;
import com.example.mvc.bean.req.ReqMerchant;
import com.example.mvc.dao.DaoMerchant;
import com.example.mvc.model.Merchant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ServiceMerchant extends BaseService implements IService<Merchant> {
  private static final String tableName = "Merchant";

  @Resource
  private DaoMerchant daoMerchant;
  @Resource
  private DbBatch<Merchant> dbBatch;

  /**
   * 添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回添加的商户表。
   */
  public Merchant insert(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoMerchant.insert(merchant);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return merchant;
  }

  /**
   * 添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回添加的商户表。
   */
  public Merchant insert(Merchant merchant) {
    return insert(null, null, merchant);
  }

  /**
   * 同步批量添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchants 商户表。
   * @return 返回添加的商户表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<Merchant> merchants) {
    if (merchants == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (Merchant merchant : merchants) {
      int count = daoMerchant.insert(merchant);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchants 商户表。
   * @return 返回添加的商户表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<Merchant> merchants) {
    return insertSync(null, null, merchants);
  }

  /**
   * 异步批量添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param merchants 商户表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<Merchant> merchants) {
    if (merchants == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (Merchant merchant : merchants) {
      dbBatch.insert(merchant, daoMerchant);
    }
  }

  /**
   * 异步批量添加商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param merchants 商户表。
   */
  public void insertAsyn(List<Merchant> merchants) {
    insertAsyn(null, null, merchants);
  }

  /**
   * 更新商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoMerchant.update(merchant);
    return count;
  }

  /**
   * 更新商户表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(Merchant merchant) {
    return update(null, null, merchant);
  }

  /**
   * 更新商户表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoMerchant.updateEvenNull(merchant);
    return count;
  }

  /**
   * 更新商户表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(Merchant merchant) {
    return updateEvenNull(null, null, merchant);
  }

  /**
   * 删除商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoMerchant.delete(merchant);
    return count;
  }

  /**
   * 删除商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  public int delete(Merchant merchant) {
    return delete(null, null, merchant);
  }

  /**
   * 删除商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 商户编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Merchant merchant = new Merchant();
    merchant.setId(id);
    int count = daoMerchant.delete(merchant);
    return count;
  }

  /**
   * 删除商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 商户编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商户编号。
   * @return 返回商户表。
   */
  public Merchant selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Merchant merchant = daoMerchant.selectByPrimary(id);
    return merchant;
  }

  /**
   * 根据主键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商户编号。
   * @return 返回商户表。
   */
  public Merchant selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条商户表。
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectOne(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Merchant merchant1 = daoMerchant.selectOne(merchant);
    return merchant1;
  }

  /**
   * 根据条件查询一条商户表。
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectOne(Merchant merchant) {
    return selectOne(null, null, merchant);
  }

  /**
   * 根据条件查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回商户表详情。
   */
  public DetailMerchant selectDetail(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailMerchant detailMerchant = daoMerchant.selectDetail(merchant);
    return detailMerchant;
  }

  /**
   * 根据条件查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回商户表详情。
   */
  public DetailMerchant selectDetail(Merchant merchant) {
    return selectDetail(null, null, merchant);
  }

  /**
   * 根据主键查询一条商户表详情。
   * @param id 商户编号。
   * @return 返回商户表详情。
   */
  public DetailMerchant selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailMerchant detailMerchant = daoMerchant.selectDetailByPrimary(id);
    return detailMerchant;
  }

  /**
   * 根据主键查询一条商户表详情。
   * @param id 商户编号。
   * @return 返回商户表详情。
   */
  public DetailMerchant selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询商户表列表。返回所有符合条件的商户表，未分页。
   * @param merchant 商户表。
   * @return 返回商户表列表。
   */
  public List<Merchant> select(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<Merchant> list = daoMerchant.select(merchant);
    return list;
  }

  /**
   * 查询商户表列表。返回所有符合条件的商户表，未分页。
   * @param merchant 商户表。
   * @return 返回商户表列表。
   */
  public List<Merchant> select(Merchant merchant) {
    return select(null, null, merchant);
  }

  /**
   * 综合查询商户表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqMerchant 查询的参数。
   * @return 返回商户表列表。
   */
  public RepMerchant selectRelative(HttpServletRequest request, HttpServletResponse response, ReqMerchant reqMerchant) {
    if (reqMerchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqMerchant);
    RepMerchant repMerchant = new RepMerchant();
    if (isQueryTotal(reqMerchant)) {
      repMerchant.setTotal(daoMerchant.selectTotal(reqMerchant));
    }
    repMerchant.setRows(daoMerchant.selectRelative(reqMerchant));
    return repMerchant;
  }

  /**
   * 综合查询商户表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqMerchant 查询的参数。
   * @return 返回商户表列表。
   */
  public RepMerchant selectRelative(ReqMerchant reqMerchant) {
    return selectRelative(null, null, reqMerchant);
  }

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByMchNo(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getMchName())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchName is null!");
    }
    if (Tool.isNull(merchant.getMchNo())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchNo is null!");
    }
    int count = daoMerchant.updateByMchNo(merchant);
    return count;
  }

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByMchNo(Merchant merchant) {
    return updateByMchNo(null, null, merchant);
  }

  /**
   * 根据唯一键删除一条商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  public int deleteByMchNo(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getMchName())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchName is null!");
    }
    if (Tool.isNull(merchant.getMchNo())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchNo is null!");
    }
    Merchant merchant1 = new Merchant();
    merchant1.setMchName(merchant.getMchName());
    merchant1.setMchNo(merchant.getMchNo());
    int count = daoMerchant.deleteByMchNo(merchant1);
    return count;
  }

  /**
   * 根据唯一键删除一条商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  public int deleteByMchNo(Merchant merchant) {
    return deleteByMchNo(null, null, merchant);
  }

  /**
   * 根据唯一键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectByMchNo(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getMchName())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchName is null!");
    }
    if (Tool.isNull(merchant.getMchNo())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchNo is null!");
    }
    Merchant merchant0 = daoMerchant.selectByMchNo(merchant);
    return merchant0;
  }

  /**
   * 根据唯一键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectByMchNo(Merchant merchant) {
    return selectByMchNo(null, null, merchant);
  }

  /**
   * 根据唯一键查询一条商户表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public DetailMerchant selectDetailByMchNo(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getMchName())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchName is null!");
    }
    if (Tool.isNull(merchant.getMchNo())) {
      throw new CommonException(Code.PARAM_EMPTY, "mchNo is null!");
    }
    DetailMerchant detailMerchant = daoMerchant.selectDetailByMchNo(merchant);
    return detailMerchant;
  }

  /**
   * 根据唯一键查询一条商户表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public DetailMerchant selectDetailByMchNo(Merchant merchant) {
    return selectDetailByMchNo(null, null, merchant);
  }

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByAppid(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getAppid())) {
      throw new CommonException(Code.PARAM_EMPTY, "appid is null!");
    }
    int count = daoMerchant.updateByAppid(merchant);
    return count;
  }

  /**
   * 根据唯一键更新一条商户表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByAppid(Merchant merchant) {
    return updateByAppid(null, null, merchant);
  }

  /**
   * 根据唯一键删除一条商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  public int deleteByAppid(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getAppid())) {
      throw new CommonException(Code.PARAM_EMPTY, "appid is null!");
    }
    Merchant merchant1 = new Merchant();
    merchant1.setAppid(merchant.getAppid());
    int count = daoMerchant.deleteByAppid(merchant1);
    return count;
  }

  /**
   * 根据唯一键删除一条商户表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  public int deleteByAppid(Merchant merchant) {
    return deleteByAppid(null, null, merchant);
  }

  /**
   * 根据唯一键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectByAppid(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getAppid())) {
      throw new CommonException(Code.PARAM_EMPTY, "appid is null!");
    }
    Merchant merchant0 = daoMerchant.selectByAppid(merchant);
    return merchant0;
  }

  /**
   * 根据唯一键查询一条商户表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public Merchant selectByAppid(Merchant merchant) {
    return selectByAppid(null, null, merchant);
  }

  /**
   * 根据唯一键查询一条商户表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public DetailMerchant selectDetailByAppid(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    if (merchant == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(merchant.getAppid())) {
      throw new CommonException(Code.PARAM_EMPTY, "appid is null!");
    }
    DetailMerchant detailMerchant = daoMerchant.selectDetailByAppid(merchant);
    return detailMerchant;
  }

  /**
   * 根据唯一键查询一条商户表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param merchant 商户表。
   * @return 返回商户表。
   */
  public DetailMerchant selectDetailByAppid(Merchant merchant) {
    return selectDetailByAppid(null, null, merchant);
  }

  /**
   * 导出商户表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqMerchant reqMerchant;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqMerchant = ToolJson.toBean(reqParam, ReqMerchant.class);
    } else {
      reqMerchant = new ReqMerchant();
    }
    List<Map<String, Object>> list = daoMerchant.selectRelativeMap(reqMerchant);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "商户表";
    }
    List<Field> fields = getFields(paramExport.getFields());
    ToolExport.export(response, tableName, title, fields, list);
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
      case "cityId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "cityName":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "userId":
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
      case "headImgUrl":
        field.setLength(256);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "merchantType":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "mchName":
        field.setLength(128);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "mchNo":
        field.setLength(32);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "appid":
        field.setLength(32);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "secret":
        field.setLength(128);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "phone":
        field.setLength(32);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "address":
        field.setLength(256);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}