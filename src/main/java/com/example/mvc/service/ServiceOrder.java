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
import com.example.mvc.bean.detail.DetailOrder;
import com.example.mvc.bean.rep.RepOrder;
import com.example.mvc.bean.req.ReqOrder;
import com.example.mvc.dao.DaoOrder;
import com.example.mvc.model.Order;
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
public class ServiceOrder extends BaseService implements IService<Order> {
  private static final String tableName = "Order";

  @Resource
  private DaoOrder daoOrder;
  @Resource
  private DbBatch<Order> dbBatch;

  /**
   * 添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 返回添加的订单表。
   */
  public Order insert(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrder.insert(order);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return order;
  }

  /**
   * 添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 返回添加的订单表。
   */
  public Order insert(Order order) {
    return insert(null, null, order);
  }

  /**
   * 同步批量添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orders 订单表。
   * @return 返回添加的订单表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<Order> orders) {
    if (orders == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (Order order : orders) {
      int count = daoOrder.insert(order);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orders 订单表。
   * @return 返回添加的订单表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<Order> orders) {
    return insertSync(null, null, orders);
  }

  /**
   * 异步批量添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param orders 订单表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<Order> orders) {
    if (orders == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (Order order : orders) {
      dbBatch.insert(order, daoOrder);
    }
  }

  /**
   * 异步批量添加订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param orders 订单表。
   */
  public void insertAsyn(List<Order> orders) {
    insertAsyn(null, null, orders);
  }

  /**
   * 更新订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrder.update(order);
    return count;
  }

  /**
   * 更新订单表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(Order order) {
    return update(null, null, order);
  }

  /**
   * 更新订单表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrder.updateEvenNull(order);
    return count;
  }

  /**
   * 更新订单表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(Order order) {
    return updateEvenNull(null, null, order);
  }

  /**
   * 删除订单表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrder.delete(order);
    return count;
  }

  /**
   * 删除订单表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param order 订单表。
   * @return 返回删除的记录数。
   */
  public int delete(Order order) {
    return delete(null, null, order);
  }

  /**
   * 删除订单表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 订单编号。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Order order = new Order();
    order.setId(id);
    int count = daoOrder.delete(order);
    return count;
  }

  /**
   * 删除订单表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param id 订单编号。
   * @return 返回删除的记录数。
   */
  public int delete(Integer id) {
    return delete(null, null, id);
  }

  /**
   * 根据主键查询一条订单表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 订单编号。
   * @return 返回订单表。
   */
  public Order selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Order order = daoOrder.selectByPrimary(id);
    return order;
  }

  /**
   * 根据主键查询一条订单表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 订单编号。
   * @return 返回订单表。
   */
  public Order selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条订单表。
   * @param order 订单表。
   * @return 返回订单表。
   */
  public Order selectOne(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    Order order1 = daoOrder.selectOne(order);
    return order1;
  }

  /**
   * 根据条件查询一条订单表。
   * @param order 订单表。
   * @return 返回订单表。
   */
  public Order selectOne(Order order) {
    return selectOne(null, null, order);
  }

  /**
   * 根据条件查询一条订单表详情。
   * @param order 订单表。
   * @return 返回订单表详情。
   */
  public DetailOrder selectDetail(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailOrder detailOrder = daoOrder.selectDetail(order);
    return detailOrder;
  }

  /**
   * 根据条件查询一条订单表详情。
   * @param order 订单表。
   * @return 返回订单表详情。
   */
  public DetailOrder selectDetail(Order order) {
    return selectDetail(null, null, order);
  }

  /**
   * 根据主键查询一条订单表详情。
   * @param id 订单编号。
   * @return 返回订单表详情。
   */
  public DetailOrder selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailOrder detailOrder = daoOrder.selectDetailByPrimary(id);
    return detailOrder;
  }

  /**
   * 根据主键查询一条订单表详情。
   * @param id 订单编号。
   * @return 返回订单表详情。
   */
  public DetailOrder selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询订单表列表。返回所有符合条件的订单表，未分页。
   * @param order 订单表。
   * @return 返回订单表列表。
   */
  public List<Order> select(HttpServletRequest request, HttpServletResponse response, Order order) {
    if (order == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<Order> list = daoOrder.select(order);
    return list;
  }

  /**
   * 查询订单表列表。返回所有符合条件的订单表，未分页。
   * @param order 订单表。
   * @return 返回订单表列表。
   */
  public List<Order> select(Order order) {
    return select(null, null, order);
  }

  /**
   * 综合查询订单表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrder 查询的参数。
   * @return 返回订单表列表。
   */
  public RepOrder selectRelative(HttpServletRequest request, HttpServletResponse response, ReqOrder reqOrder) {
    if (reqOrder == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqOrder);
    RepOrder repOrder = new RepOrder();
    if (isQueryTotal(reqOrder)) {
      repOrder.setTotal(daoOrder.selectTotal(reqOrder));
    }
    repOrder.setRows(daoOrder.selectRelative(reqOrder));
    return repOrder;
  }

  /**
   * 综合查询订单表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrder 查询的参数。
   * @return 返回订单表列表。
   */
  public RepOrder selectRelative(ReqOrder reqOrder) {
    return selectRelative(null, null, reqOrder);
  }

  /**
   * 导出订单表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqOrder reqOrder;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqOrder = ToolJson.toBean(reqParam, ReqOrder.class);
    } else {
      reqOrder = new ReqOrder();
    }
    List<Map<String, Object>> list = daoOrder.selectRelativeMap(reqOrder);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "订单表";
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
      case "orderStatus":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "payStatus":
        field.setExportFieldType(Enum.ExportFieldType.constant_);
        break;
      case "amount":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "amountSuccess":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
        break;
      case "thirdOrderNo":
        field.setLength(64);
        field.setExportFieldType(Enum.ExportFieldType.string_);
        break;
      case "sendTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      case "payTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      case "notifyTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      case "updateTime":
        field.setExportFieldType(Enum.ExportFieldType.datetime_);
        break;
      default:
        throw new CommonException(Code.PARAM_EMPTY, "unknow fileName: " + name);

    }
  }
}