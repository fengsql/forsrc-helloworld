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
import com.example.mvc.bean.detail.DetailGoods;
import com.example.mvc.bean.rep.RepGoods;
import com.example.mvc.bean.req.ReqGoods;
import com.example.mvc.dao.DaoGoods;
import com.example.mvc.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.mvc.cache.CacheGoods;

@Service
@Slf4j
public class ServiceGoods extends BaseService implements IService<Goods> {
  private static final String tableName = "Goods";

  @Resource
  private CacheGoods cacheGoods;
  @Resource
  private DaoGoods daoGoods;
  @Resource
  private DbBatch<Goods> dbBatch;

  /**
   * 添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 返回添加的商品表。
   */
  public Goods insert(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoods.insert(goods);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    if (ConfigCommon.redis.cacheInsert) {
      cacheGoods.put(goods);
    }
    return goods;
  }

  /**
   * 添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 返回添加的商品表。
   */
  public Goods insert(Goods goods) {
    return insert(null, null, goods);
  }

  /**
   * 同步批量添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodss 商品表。
   * @return 返回添加的商品表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<Goods> goodss) {
    if (goodss == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (Goods goods : goodss) {
      int count = daoGoods.insert(goods);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      if (ConfigCommon.redis.cacheInsert) {
        cacheGoods.put(goods);
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goodss 商品表。
   * @return 返回添加的商品表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<Goods> goodss) {
    return insertSync(null, null, goodss);
  }

  /**
   * 异步批量添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param goodss 商品表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<Goods> goodss) {
    if (goodss == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (Goods goods : goodss) {
      dbBatch.insert(goods, daoGoods);
    }
  }

  /**
   * 异步批量添加商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param goodss 商品表。
   */
  public void insertAsyn(List<Goods> goodss) {
    insertAsyn(null, null, goodss);
  }

  /**
   * 更新商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoods.update(goods);
    if (count > 0) {
      cacheGoods.update(daoGoods.selectOne(goods));
    }
    return count;
  }

  /**
   * 更新商品表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(Goods goods) {
    return update(null, null, goods);
  }

  /**
   * 更新商品表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoGoods.updateEvenNull(goods);
    if (count > 0) {
      cacheGoods.update(daoGoods.selectOne(goods));
    }
    return count;
  }

  /**
   * 更新商品表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(Goods goods) {
    return updateEvenNull(null, null, goods);
  }

  /**
   * 删除商品表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<Goods> goodss_ = daoGoods.select(goods);
    if (Tool.isNull(goodss_)) {
      return 0;
    }
    for (Goods goods1 : goodss_) {
      cacheGoods.delete(goods1.getId());
    }
    return goodss_.size();
  }

  /**
   * 删除商品表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param goods 商品表。
   * @return 返回删除的记录数。
   */
  public int delete(Goods goods) {
    return delete(null, null, goods);
  }

  /**
   * 删除商品表。
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
    Goods goods = new Goods();
    goods.setId(id);
    Goods goods1 = cacheGoods.get(id);
    if (goods1 == null) {
      return 0;
    }
    List<Goods> goodss_ = new ArrayList<>();
    goodss_.add(goods1);
    cacheGoods.delete(id);
    return 1;
  }

  /**
   * 删除商品表。
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
   * 根据主键查询一条商品表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商品编号。
   * @return 返回商品表。
   */
  public Goods selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Goods goods = cacheGoods.get(id);
    return goods;
  }

  /**
   * 根据主键查询一条商品表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 商品编号。
   * @return 返回商品表。
   */
  public Goods selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条商品表。
   * @param goods 商品表。
   * @return 返回商品表。
   */
  public Goods selectOne(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Goods goods1 = daoGoods.selectOne(goods);
    return goods1;
  }

  /**
   * 根据条件查询一条商品表。
   * @param goods 商品表。
   * @return 返回商品表。
   */
  public Goods selectOne(Goods goods) {
    return selectOne(null, null, goods);
  }

  /**
   * 根据条件查询一条商品表详情。
   * @param goods 商品表。
   * @return 返回商品表详情。
   */
  public DetailGoods selectDetail(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailGoods detailGoods = daoGoods.selectDetail(goods);
    return detailGoods;
  }

  /**
   * 根据条件查询一条商品表详情。
   * @param goods 商品表。
   * @return 返回商品表详情。
   */
  public DetailGoods selectDetail(Goods goods) {
    return selectDetail(null, null, goods);
  }

  /**
   * 根据主键查询一条商品表详情。
   * @param id 商品编号。
   * @return 返回商品表详情。
   */
  public DetailGoods selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailGoods detailGoods = daoGoods.selectDetailByPrimary(id);
    return detailGoods;
  }

  /**
   * 根据主键查询一条商品表详情。
   * @param id 商品编号。
   * @return 返回商品表详情。
   */
  public DetailGoods selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询商品表列表。返回所有符合条件的商品表，未分页。
   * @param goods 商品表。
   * @return 返回商品表列表。
   */
  public List<Goods> select(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<Goods> list = daoGoods.select(goods);
    return list;
  }

  /**
   * 查询商品表列表。返回所有符合条件的商品表，未分页。
   * @param goods 商品表。
   * @return 返回商品表列表。
   */
  public List<Goods> select(Goods goods) {
    return select(null, null, goods);
  }

  /**
   * 综合查询商品表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoods 查询的参数。
   * @return 返回商品表列表。
   */
  public RepGoods selectRelative(HttpServletRequest request, HttpServletResponse response, ReqGoods reqGoods) {
    if (reqGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqGoods);
    RepGoods repGoods = new RepGoods();
    if (isQueryTotal(reqGoods)) {
      repGoods.setTotal(daoGoods.selectTotal(reqGoods));
    }
    repGoods.setRows(daoGoods.selectRelative(reqGoods));
    return repGoods;
  }

  /**
   * 综合查询商品表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoods 查询的参数。
   * @return 返回商品表列表。
   */
  public RepGoods selectRelative(ReqGoods reqGoods) {
    return selectRelative(null, null, reqGoods);
  }

  /**
   * 导出商品表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqGoods reqGoods;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqGoods = ToolJson.toBean(reqParam, ReqGoods.class);
    } else {
      reqGoods = new ReqGoods();
    }
    List<Map<String, Object>> list = daoGoods.selectRelativeMap(reqGoods);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "商品表";
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
      case "goodsKindId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "goodsKindName":
        field.setLength(128);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "onlineStatus":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "name":
        field.setLength(128);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "price":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
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