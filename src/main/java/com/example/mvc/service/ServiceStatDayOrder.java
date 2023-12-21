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
import com.example.mvc.bean.detail.DetailStatDayOrder;
import com.example.mvc.bean.rep.RepStatDayOrder;
import com.example.mvc.bean.req.ReqStatDayOrder;
import com.example.mvc.dao.DaoStatDayOrder;
import com.example.mvc.model.StatDayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.statis.work.stat.StatStatDayOrder;

@Service
@Slf4j
public class ServiceStatDayOrder extends BaseService implements IService<StatDayOrder> {
  private static final String tableName = "StatDayOrder";
  @Resource
  private StatStatDayOrder statStatDayOrder;
  @Resource
  private DaoStatDayOrder daoStatDayOrder;
  @Resource
  private DbBatch<StatDayOrder> dbBatch;

  /**
   * 添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 返回添加的每日订单统计。
   */
  public StatDayOrder insert(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayOrder.insert(statDayOrder);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return statDayOrder;
  }

  /**
   * 添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 返回添加的每日订单统计。
   */
  public StatDayOrder insert(StatDayOrder statDayOrder) {
    return insert(null, null, statDayOrder);
  }

  /**
   * 同步批量添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrders 每日订单统计。
   * @return 返回添加的每日订单统计数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<StatDayOrder> statDayOrders) {
    if (statDayOrders == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (StatDayOrder statDayOrder : statDayOrders) {
      int count = daoStatDayOrder.insert(statDayOrder);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrders 每日订单统计。
   * @return 返回添加的每日订单统计数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<StatDayOrder> statDayOrders) {
    return insertSync(null, null, statDayOrders);
  }

  /**
   * 异步批量添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statDayOrders 每日订单统计。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<StatDayOrder> statDayOrders) {
    if (statDayOrders == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (StatDayOrder statDayOrder : statDayOrders) {
      dbBatch.insert(statDayOrder, daoStatDayOrder);
    }
  }

  /**
   * 异步批量添加每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param statDayOrders 每日订单统计。
   */
  public void insertAsyn(List<StatDayOrder> statDayOrders) {
    insertAsyn(null, null, statDayOrders);
  }

  /**
   * 更新每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayOrder.update(statDayOrder);
    return count;
  }

  /**
   * 更新每日订单统计。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(StatDayOrder statDayOrder) {
    return update(null, null, statDayOrder);
  }

  /**
   * 更新每日订单统计。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayOrder.updateEvenNull(statDayOrder);
    return count;
  }

  /**
   * 更新每日订单统计。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(StatDayOrder statDayOrder) {
    return updateEvenNull(null, null, statDayOrder);
  }

  /**
   * 删除每日订单统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoStatDayOrder.delete(statDayOrder);
    return count;
  }

  /**
   * 删除每日订单统计。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param statDayOrder 每日订单统计。
   * @return 返回删除的记录数。
   */
  public int delete(StatDayOrder statDayOrder) {
    return delete(null, null, statDayOrder);
  }

  /**
   * 删除每日订单统计。
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
    StatDayOrder statDayOrder = new StatDayOrder();
    statDayOrder.setId(id);
    int count = daoStatDayOrder.delete(statDayOrder);
    return count;
  }

  /**
   * 删除每日订单统计。
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
   * 根据主键查询一条每日订单统计。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回每日订单统计。
   */
  public StatDayOrder selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatDayOrder statDayOrder = daoStatDayOrder.selectByPrimary(id);
    return statDayOrder;
  }

  /**
   * 根据主键查询一条每日订单统计。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 编号。
   * @return 返回每日订单统计。
   */
  public StatDayOrder selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条每日订单统计。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计。
   */
  public StatDayOrder selectOne(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    StatDayOrder statDayOrder1 = daoStatDayOrder.selectOne(statDayOrder);
    return statDayOrder1;
  }

  /**
   * 根据条件查询一条每日订单统计。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计。
   */
  public StatDayOrder selectOne(StatDayOrder statDayOrder) {
    return selectOne(null, null, statDayOrder);
  }

  /**
   * 根据条件查询一条每日订单统计详情。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计详情。
   */
  public DetailStatDayOrder selectDetail(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatDayOrder detailStatDayOrder = daoStatDayOrder.selectDetail(statDayOrder);
    return detailStatDayOrder;
  }

  /**
   * 根据条件查询一条每日订单统计详情。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计详情。
   */
  public DetailStatDayOrder selectDetail(StatDayOrder statDayOrder) {
    return selectDetail(null, null, statDayOrder);
  }

  /**
   * 根据主键查询一条每日订单统计详情。
   * @param id 编号。
   * @return 返回每日订单统计详情。
   */
  public DetailStatDayOrder selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailStatDayOrder detailStatDayOrder = daoStatDayOrder.selectDetailByPrimary(id);
    return detailStatDayOrder;
  }

  /**
   * 根据主键查询一条每日订单统计详情。
   * @param id 编号。
   * @return 返回每日订单统计详情。
   */
  public DetailStatDayOrder selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询每日订单统计列表。返回所有符合条件的每日订单统计，未分页。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计列表。
   */
  public List<StatDayOrder> select(HttpServletRequest request, HttpServletResponse response, StatDayOrder statDayOrder) {
    if (statDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<StatDayOrder> list = daoStatDayOrder.select(statDayOrder);
    return list;
  }

  /**
   * 查询每日订单统计列表。返回所有符合条件的每日订单统计，未分页。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计列表。
   */
  public List<StatDayOrder> select(StatDayOrder statDayOrder) {
    return select(null, null, statDayOrder);
  }

  /**
   * 综合查询每日订单统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回每日订单统计列表。
   */
  public RepStatDayOrder selectRelative(HttpServletRequest request, HttpServletResponse response, ReqStatDayOrder reqStatDayOrder) {
    if (reqStatDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqStatDayOrder);
    RepStatDayOrder repStatDayOrder = new RepStatDayOrder();
    if (isQueryTotal(reqStatDayOrder)) {
      repStatDayOrder.setTotal(daoStatDayOrder.selectTotal(reqStatDayOrder));
    }
    repStatDayOrder.setRows(daoStatDayOrder.selectRelative(reqStatDayOrder));
    return repStatDayOrder;
  }

  /**
   * 综合查询每日订单统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回每日订单统计列表。
   */
  public RepStatDayOrder selectRelative(ReqStatDayOrder reqStatDayOrder) {
    return selectRelative(null, null, reqStatDayOrder);
  }

  /**
   * 导出每日订单统计到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqStatDayOrder reqStatDayOrder;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqStatDayOrder = ToolJson.toBean(reqParam, ReqStatDayOrder.class);
    } else {
      reqStatDayOrder = new ReqStatDayOrder();
    }
    List<Map<String, Object>> list = daoStatDayOrder.selectRelativeMap(reqStatDayOrder);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "每日订单统计";
    }
    List<Field> fields = getFields(paramExport.getFields());
    ToolExport.export(response, tableName, title, fields, list);
  }


  /**
   * 统计每日订单统计。返回所有统计结果，未分页。
   * @param reqStatDayOrder 统计参数。
   * @return 返回统计每日订单统计。
   */
  public List<StatDayOrder> stat(HttpServletRequest request, HttpServletResponse response, ReqStatDayOrder reqStatDayOrder) {
    if (reqStatDayOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    statStatDayOrder.setTime(reqStatDayOrder);
    List<StatDayOrder> list = daoStatDayOrder.stat(reqStatDayOrder);
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
      case "orderStatus":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "dayNo":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "amount":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "orderNum":
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