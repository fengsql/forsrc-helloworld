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
import com.example.mvc.bean.detail.DetailGoodsKind;
import com.example.mvc.bean.rep.RepGoodsKind;
import com.example.mvc.bean.req.ReqGoodsKind;
import com.example.mvc.dao.DaoGoodsKind;
import com.example.mvc.model.GoodsKind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.mvc.cache.CacheGoodsKind;

@Service
@Slf4j
public class ServiceGoodsKind extends BaseService implements IService<GoodsKind> {
  private static final String tableName = "GoodsKind";

  @Resource
  private CacheGoodsKind cacheGoodsKind;
  @Resource
  private DaoGoodsKind daoGoodsKind;
  @Resource
  private DbBatch<GoodsKind> dbBatch;

  /**
   * 添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回添加的商品类别表。
   */
  public GoodsKind insert(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoodsKind.insert(goodsKind);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheGoodsKind.put(goodsKind);
    }
    return goodsKind;
  }

  /**
   * 添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回添加的商品类别表。
   */
  public GoodsKind insert(GoodsKind goodsKind) {
    return insert(null, null, goodsKind);
  }

  /**
   * 同步批量添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKinds 商品类别表。
   * @return 返回添加的商品类别表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<GoodsKind> goodsKinds) {
    if (goodsKinds == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (GoodsKind goodsKind : goodsKinds) {
      int count = daoGoodsKind.insert(goodsKind);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheGoodsKind.put(goodsKind);
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKinds 商品类别表。
   * @return 返回添加的商品类别表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<GoodsKind> goodsKinds) {
    return insertSync(null, null, goodsKinds);
  }

  /**
   * 异步批量添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param goodsKinds 商品类别表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<GoodsKind> goodsKinds) {
    if (goodsKinds == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (GoodsKind goodsKind : goodsKinds) {
      dbBatch.insert(goodsKind, daoGoodsKind);
    }
  }

  /**
   * 异步批量添加商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param goodsKinds 商品类别表。
   */
  public void insertAsyn(List<GoodsKind> goodsKinds) {
    insertAsyn(null, null, goodsKinds);
  }

  /**
   * 更新商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoodsKind.update(goodsKind);
    if (count > 0) {
      cacheGoodsKind.update(daoGoodsKind.selectOne(goodsKind));
    }
    return count;
  }

  /**
   * 更新商品类别表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(GoodsKind goodsKind) {
    return update(null, null, goodsKind);
  }

  /**
   * 更新商品类别表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoodsKind.updateEvenNull(goodsKind);
    if (count > 0) {
      cacheGoodsKind.update(daoGoodsKind.selectOne(goodsKind));
    }
    return count;
  }

  /**
   * 更新商品类别表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(GoodsKind goodsKind) {
    return updateEvenNull(null, null, goodsKind);
  }

  /**
   * 删除商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<GoodsKind> goodsKinds_ = daoGoodsKind.select(goodsKind);
    if (Tool.isNull(goodsKinds_)) {
      return 0;
    }
    for (GoodsKind goodsKind1 : goodsKinds_) {
      cacheGoodsKind.delete(goodsKind1.getId());
    }
    return goodsKinds_.size();
  }

  /**
   * 删除商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  public int delete(GoodsKind goodsKind) {
    return delete(null, null, goodsKind);
  }

  /**
   * 删除商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 商品编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    GoodsKind goodsKind = new GoodsKind();
    goodsKind.setId(id);
    GoodsKind goodsKind1 = cacheGoodsKind.get(id);
    if (goodsKind1 == null) {
      return 0;
    }
    List<GoodsKind> goodsKinds_ = new ArrayList<>();
    goodsKinds_.add(goodsKind1);
    cacheGoodsKind.delete(id);
    return 1;
  }

  /**
   * 删除商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 商品编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条商品类别表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商品编号。
   * @return 返回商品类别表。
   */
  public GoodsKind selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    GoodsKind goodsKind = cacheGoodsKind.get(id);
    return goodsKind;
  }

  /**
   * 根据主键查询一条商品类别表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商品编号。
   * @return 返回商品类别表。
   */
  public GoodsKind selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public GoodsKind selectOne(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    GoodsKind goodsKind1 = daoGoodsKind.selectOne(goodsKind);
    return goodsKind1;
  }

  /**
   * 根据条件查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public GoodsKind selectOne(GoodsKind goodsKind) {
    return selectOne(null, null, goodsKind);
  }

  /**
   * 根据条件查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表详情。
   */
  public DetailGoodsKind selectDetail(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailGoodsKind detailGoodsKind = daoGoodsKind.selectDetail(goodsKind);
    return detailGoodsKind;
  }

  /**
   * 根据条件查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表详情。
   */
  public DetailGoodsKind selectDetail(GoodsKind goodsKind) {
    return selectDetail(null, null, goodsKind);
  }

  /**
   * 根据主键查询一条商品类别表详情。
   * @param id 商品编号。
   * @return 返回商品类别表详情。
   */
  public DetailGoodsKind selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailGoodsKind detailGoodsKind = daoGoodsKind.selectDetailByPrimary(id);
    return detailGoodsKind;
  }

  /**
   * 根据主键查询一条商品类别表详情。
   * @param id 商品编号。
   * @return 返回商品类别表详情。
   */
  public DetailGoodsKind selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询商品类别表列表。返回所有符合条件的商品类别表，未分页。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表列表。
   */
  public List<GoodsKind> select(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<GoodsKind> list = daoGoodsKind.select(goodsKind);
    return list;
  }

  /**
   * 查询商品类别表列表。返回所有符合条件的商品类别表，未分页。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表列表。
   */
  public List<GoodsKind> select(GoodsKind goodsKind) {
    return select(null, null, goodsKind);
  }

  /**
   * 综合查询商品类别表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoodsKind 查询的参数。
   * @return 返回商品类别表列表。
   */
  public RepGoodsKind selectRelative(HttpServletRequest request, HttpServletResponse response, ReqGoodsKind reqGoodsKind) {
    if (reqGoodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqGoodsKind);
    RepGoodsKind repGoodsKind = new RepGoodsKind();
    if (isQueryTotal(reqGoodsKind)) {
      repGoodsKind.setTotal(daoGoodsKind.selectTotal(reqGoodsKind));
    }
    repGoodsKind.setRows(daoGoodsKind.selectRelative(reqGoodsKind));
    return repGoodsKind;
  }

  /**
   * 综合查询商品类别表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoodsKind 查询的参数。
   * @return 返回商品类别表列表。
   */
  public RepGoodsKind selectRelative(ReqGoodsKind reqGoodsKind) {
    return selectRelative(null, null, reqGoodsKind);
  }

  /**
   * 根据唯一键更新一条商品类别表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByName(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    int count = daoGoodsKind.updateByName(goodsKind);
    if (count > 0) {
      cacheGoodsKind.update(daoGoodsKind.selectOne(goodsKind));
    }
    return count;
  }

  /**
   * 根据唯一键更新一条商品类别表，此方法不适用根据唯一键更改唯一键的字段值。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateByName(GoodsKind goodsKind) {
    return updateByName(null, null, goodsKind);
  }

  /**
   * 根据唯一键删除一条商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  public int deleteByName(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    GoodsKind goodsKind1 = new GoodsKind();
    goodsKind1.setName(goodsKind.getName());
    GoodsKind goodsKind2 = cacheGoodsKind.getByName(goodsKind1);
    if (goodsKind2 == null) {
      return 0;
    }
    List<GoodsKind> goodsKinds_ = new ArrayList<>();
    goodsKinds_.add(goodsKind2);
    cacheGoodsKind.deleteByName(goodsKind2);
    return 1;
  }

  /**
   * 根据唯一键删除一条商品类别表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  public int deleteByName(GoodsKind goodsKind) {
    return deleteByName(null, null, goodsKind);
  }

  /**
   * 根据唯一键查询一条商品类别表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public GoodsKind selectByName(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    GoodsKind goodsKind0 = cacheGoodsKind.getByName(goodsKind);
    return goodsKind0;
  }

  /**
   * 根据唯一键查询一条商品类别表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public GoodsKind selectByName(GoodsKind goodsKind) {
    return selectByName(null, null, goodsKind);
  }

  /**
   * 根据唯一键查询一条商品类别表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public DetailGoodsKind selectDetailByName(HttpServletRequest request, HttpServletResponse response, GoodsKind goodsKind) {
    if (goodsKind == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(goodsKind.getName())) {
      throw new CommonException(Code.PARAM_EMPTY, "name is null!");
    }
    DetailGoodsKind detailGoodsKind = daoGoodsKind.selectDetailByName(goodsKind);
    return detailGoodsKind;
  }

  /**
   * 根据唯一键查询一条商品类别表详情。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  public DetailGoodsKind selectDetailByName(GoodsKind goodsKind) {
    return selectDetailByName(null, null, goodsKind);
  }

  /**
   * 导出商品类别表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqGoodsKind reqGoodsKind;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqGoodsKind = ToolJson.toBean(reqParam, ReqGoodsKind.class);
    } else {
      reqGoodsKind = new ReqGoodsKind();
    }
    List<Map<String, Object>> list = daoGoodsKind.selectRelativeMap(reqGoodsKind);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "商品类别表";
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
      case "name":
        field.setLength(128);
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