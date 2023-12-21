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
import com.example.mvc.bean.detail.DetailStatDayGoods;
import com.example.mvc.bean.rep.RepStatDayGoods;
import com.example.mvc.bean.req.ReqStatDayGoods;
import com.example.mvc.dao.DaoStatDayGoods;
import com.example.mvc.model.StatDayGoods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.statis.work.stat.StatStatDayGoods;

@Service
@Slf4j
public class ServiceStatDayGoods extends BaseService implements IService<StatDayGoods> {
  private static final String tableName = "StatDayGoods";
  @Resource
  private StatStatDayGoods statStatDayGoods;
  @Resource
  private DaoStatDayGoods daoStatDayGoods;
  @Resource
  private DbBatch<StatDayGoods> dbBatch;

  /**
   * 添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 返回添加的每日订单商品统计。
   */
  public StatDayGoods insert(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayGoods.insert(statDayGoods);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return statDayGoods;
  }

  /**
   * 添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 返回添加的每日订单商品统计。
   */
  public StatDayGoods insert(StatDayGoods statDayGoods) {
    return insert(null, null, statDayGoods);
  }

  /**
   * 同步批量添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoodss 每日订单商品统计。
   * @return 返回添加的每日订单商品统计数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<StatDayGoods> statDayGoodss) {
    if (statDayGoodss == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (StatDayGoods statDayGoods : statDayGoodss) {
      int count = daoStatDayGoods.insert(statDayGoods);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoodss 每日订单商品统计。
   * @return 返回添加的每日订单商品统计数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<StatDayGoods> statDayGoodss) {
    return insertSync(null, null, statDayGoodss);
  }

  /**
   * 异步批量添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statDayGoodss 每日订单商品统计。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<StatDayGoods> statDayGoodss) {
    if (statDayGoodss == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (StatDayGoods statDayGoods : statDayGoodss) {
      dbBatch.insert(statDayGoods, daoStatDayGoods);
    }
  }

  /**
   * 异步批量添加每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statDayGoodss 每日订单商品统计。
   */
  public void insertAsyn(List<StatDayGoods> statDayGoodss) {
    insertAsyn(null, null, statDayGoodss);
  }

  /**
   * 更新每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayGoods.update(statDayGoods);
    return count;
  }

  /**
   * 更新每日订单商品统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(StatDayGoods statDayGoods) {
    return update(null, null, statDayGoods);
  }

  /**
   * 更新每日订单商品统计。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayGoods.updateEvenNull(statDayGoods);
    return count;
  }

  /**
   * 更新每日订单商品统计。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(StatDayGoods statDayGoods) {
    return updateEvenNull(null, null, statDayGoods);
  }

  /**
   * 删除每日订单商品统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayGoods.delete(statDayGoods);
    return count;
  }

  /**
   * 删除每日订单商品统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayGoods 每日订单商品统计。
   * @return 返回删除的记录数。
   */
  public int delete(StatDayGoods statDayGoods) {
    return delete(null, null, statDayGoods);
  }

  /**
   * 删除每日订单商品统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatDayGoods statDayGoods = new StatDayGoods();
    statDayGoods.setId(id);
    int count = daoStatDayGoods.delete(statDayGoods);
    return count;
  }

  /**
   * 删除每日订单商品统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条每日订单商品统计。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回每日订单商品统计。
   */
  public StatDayGoods selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatDayGoods statDayGoods = daoStatDayGoods.selectByPrimary(id);
    return statDayGoods;
  }

  /**
   * 根据主键查询一条每日订单商品统计。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回每日订单商品统计。
   */
  public StatDayGoods selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条每日订单商品统计。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计。
   */
  public StatDayGoods selectOne(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatDayGoods statDayGoods1 = daoStatDayGoods.selectOne(statDayGoods);
    return statDayGoods1;
  }

  /**
   * 根据条件查询一条每日订单商品统计。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计。
   */
  public StatDayGoods selectOne(StatDayGoods statDayGoods) {
    return selectOne(null, null, statDayGoods);
  }

  /**
   * 根据条件查询一条每日订单商品统计详情。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计详情。
   */
  public DetailStatDayGoods selectDetail(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatDayGoods detailStatDayGoods = daoStatDayGoods.selectDetail(statDayGoods);
    return detailStatDayGoods;
  }

  /**
   * 根据条件查询一条每日订单商品统计详情。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计详情。
   */
  public DetailStatDayGoods selectDetail(StatDayGoods statDayGoods) {
    return selectDetail(null, null, statDayGoods);
  }

  /**
   * 根据主键查询一条每日订单商品统计详情。
   * @param id 编号。
   * @return 返回每日订单商品统计详情。
   */
  public DetailStatDayGoods selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatDayGoods detailStatDayGoods = daoStatDayGoods.selectDetailByPrimary(id);
    return detailStatDayGoods;
  }

  /**
   * 根据主键查询一条每日订单商品统计详情。
   * @param id 编号。
   * @return 返回每日订单商品统计详情。
   */
  public DetailStatDayGoods selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询每日订单商品统计列表。返回所有符合条件的每日订单商品统计，未分页。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计列表。
   */
  public List<StatDayGoods> select(HttpServletRequest request, HttpServletResponse response, StatDayGoods statDayGoods) {
    if (statDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<StatDayGoods> list = daoStatDayGoods.select(statDayGoods);
    return list;
  }

  /**
   * 查询每日订单商品统计列表。返回所有符合条件的每日订单商品统计，未分页。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计列表。
   */
  public List<StatDayGoods> select(StatDayGoods statDayGoods) {
    return select(null, null, statDayGoods);
  }

  /**
   * 综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回每日订单商品统计列表。
   */
  public RepStatDayGoods selectRelative(HttpServletRequest request, HttpServletResponse response, ReqStatDayGoods reqStatDayGoods) {
    if (reqStatDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqStatDayGoods);
    RepStatDayGoods repStatDayGoods = new RepStatDayGoods();
    if (isQueryTotal(reqStatDayGoods)) {
      repStatDayGoods.setTotal(daoStatDayGoods.selectTotal(reqStatDayGoods));
    }
    repStatDayGoods.setRows(daoStatDayGoods.selectRelative(reqStatDayGoods));
    return repStatDayGoods;
  }

  /**
   * 综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回每日订单商品统计列表。
   */
  public RepStatDayGoods selectRelative(ReqStatDayGoods reqStatDayGoods) {
    return selectRelative(null, null, reqStatDayGoods);
  }

  /**
   * 导出每日订单商品统计到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqStatDayGoods reqStatDayGoods;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqStatDayGoods = ToolJson.toBean(reqParam, ReqStatDayGoods.class);
    } else {
      reqStatDayGoods = new ReqStatDayGoods();
    }
    List<Map<String, Object>> list = daoStatDayGoods.selectRelativeMap(reqStatDayGoods);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "每日订单商品统计";
    }
    List<Field> fields = getFields(paramExport.getFields());
    ToolExport.export(response, tableName, title, fields, list);
  }


  /**
   * 统计每日订单商品统计。返回所有统计结果，未分页。
   * @param reqStatDayGoods 统计参数。
   * @return 返回统计每日订单商品统计。
   */
  public List<StatDayGoods> stat(HttpServletRequest request, HttpServletResponse response, ReqStatDayGoods reqStatDayGoods) {
    if (reqStatDayGoods == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    statStatDayGoods.setTime(reqStatDayGoods);
    List<StatDayGoods> list = daoStatDayGoods.stat(reqStatDayGoods);
    return list;
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
      case "goodsId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
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
      case "dayNo":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "orderNum":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "goodsNum":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "addTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}