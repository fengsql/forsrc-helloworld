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
import com.example.mvc.bean.detail.DetailOrderDetail;
import com.example.mvc.bean.rep.RepOrderDetail;
import com.example.mvc.bean.req.ReqOrderDetail;
import com.example.mvc.dao.DaoOrderDetail;
import com.example.mvc.model.OrderDetail;
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
public class ServiceOrderDetail extends BaseService implements IService<OrderDetail> {
  private static final String tableName = "OrderDetail";
  @Resource
  private DaoOrderDetail daoOrderDetail;
  @Resource
  private DbBatch<OrderDetail> dbBatch;

  /**
   * 添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 返回添加的订单明细表。
   */
  public OrderDetail insert(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrderDetail.insert(orderDetail);
    if (count <= 0) {
      throw new CommonException("insert fail!");
    }
    return orderDetail;
  }

  /**
   * 添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 返回添加的订单明细表。
   */
  public OrderDetail insert(OrderDetail orderDetail) {
    return insert(null, null, orderDetail);
  }

  /**
   * 同步批量添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetails 订单明细表。
   * @return 返回添加的订单明细表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(HttpServletRequest request, HttpServletResponse response, List<OrderDetail> orderDetails) {
    if (orderDetails == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int num = 0;
    for (OrderDetail orderDetail : orderDetails) {
      int count = daoOrderDetail.insert(orderDetail);
      if (count <= 0) {
        throw new CommonException("insertSync fail!");
      }
      num++;
    }
    return num;
  }

  /**
   * 同步批量添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 如果开启了插入缓存配置，插入成功后添加缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetails 订单明细表。
   * @return 返回添加的订单明细表数。
   */
  @Transactional(rollbackFor = Exception.class)
  public int insertSync(List<OrderDetail> orderDetails) {
    return insertSync(null, null, orderDetails);
  }

  /**
   * 异步批量添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param orderDetails 订单明细表。
   */
  public void insertAsyn(HttpServletRequest request, HttpServletResponse response, List<OrderDetail> orderDetails) {
    if (orderDetails == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    for (OrderDetail orderDetail : orderDetails) {
      dbBatch.insert(orderDetail, daoOrderDetail);
    }
  }

  /**
   * 异步批量添加订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 注意：异步模式不会添加到缓存中。
   * //#  }
   * @param orderDetails 订单明细表。
   */
  public void insertAsyn(List<OrderDetail> orderDetails) {
    insertAsyn(null, null, orderDetails);
  }

  /**
   * 更新订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrderDetail.update(orderDetail);
    return count;
  }

  /**
   * 更新订单明细表。空值将被忽略。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int update(OrderDetail orderDetail) {
    return update(null, null, orderDetail);
  }

  /**
   * 更新订单明细表。空值将被更新为 null。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrderDetail.updateEvenNull(orderDetail);
    return count;
  }

  /**
   * 更新订单明细表。空值将被更新为空。
   * //#  if (isCache(table)) {
   * 更新成功后，同时更新缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  public int updateEvenNull(OrderDetail orderDetail) {
    return updateEvenNull(null, null, orderDetail);
  }

  /**
   * 删除订单明细表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。仅可传入主键、外键、常量字段作为条件。
   * @return 返回删除的记录数。
   */
  public int delete(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    int count = daoOrderDetail.delete(orderDetail);
    return count;
  }

  /**
   * 删除订单明细表。
   * //#  if (isCache(table)) {
   * 删除成功后，同时删除缓存和缓存索引字段(唯一字段且未禁用缓存)信息。
   * //#  }
   * @param orderDetail 订单明细表。
   * @return 返回删除的记录数。
   */
  public int delete(OrderDetail orderDetail) {
    return delete(null, null, orderDetail);
  }

  /**
   * 删除订单明细表。
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
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setId(id);
    int count = daoOrderDetail.delete(orderDetail);
    return count;
  }

  /**
   * 删除订单明细表。
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
   * 根据主键查询一条订单明细表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 订单编号。
   * @return 返回订单明细表。
   */
  public OrderDetail selectByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    OrderDetail orderDetail = daoOrderDetail.selectByPrimary(id);
    return orderDetail;
  }

  /**
   * 根据主键查询一条订单明细表。
   * //#  if (isCache(table)) {
   * 先从缓存查询，没有找到再从数据库查询，查询成功后添加到缓存。
   * //#  }
   * @param id 订单编号。
   * @return 返回订单明细表。
   */
  public OrderDetail selectByPrimary(Integer id) {
    return selectByPrimary(null, null, id);
  }

  /**
   * 根据条件查询一条订单明细表。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表。
   */
  public OrderDetail selectOne(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    OrderDetail orderDetail1 = daoOrderDetail.selectOne(orderDetail);
    return orderDetail1;
  }

  /**
   * 根据条件查询一条订单明细表。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表。
   */
  public OrderDetail selectOne(OrderDetail orderDetail) {
    return selectOne(null, null, orderDetail);
  }

  /**
   * 根据条件查询一条订单明细表详情。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表详情。
   */
  public DetailOrderDetail selectDetail(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailOrderDetail detailOrderDetail = daoOrderDetail.selectDetail(orderDetail);
    return detailOrderDetail;
  }

  /**
   * 根据条件查询一条订单明细表详情。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表详情。
   */
  public DetailOrderDetail selectDetail(OrderDetail orderDetail) {
    return selectDetail(null, null, orderDetail);
  }

  /**
   * 根据主键查询一条订单明细表详情。
   * @param id 订单编号。
   * @return 返回订单明细表详情。
   */
  public DetailOrderDetail selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, Integer id) {
    if (id == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    DetailOrderDetail detailOrderDetail = daoOrderDetail.selectDetailByPrimary(id);
    return detailOrderDetail;
  }

  /**
   * 根据主键查询一条订单明细表详情。
   * @param id 订单编号。
   * @return 返回订单明细表详情。
   */
  public DetailOrderDetail selectDetailByPrimary(Integer id) {
    return selectDetailByPrimary(null, null, id);
  }

  /**
   * 查询订单明细表列表。返回所有符合条件的订单明细表，未分页。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表列表。
   */
  public List<OrderDetail> select(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    if (orderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    List<OrderDetail> list = daoOrderDetail.select(orderDetail);
    return list;
  }

  /**
   * 查询订单明细表列表。返回所有符合条件的订单明细表，未分页。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表列表。
   */
  public List<OrderDetail> select(OrderDetail orderDetail) {
    return select(null, null, orderDetail);
  }

  /**
   * 综合查询订单明细表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrderDetail 查询的参数。
   * @return 返回订单明细表列表。
   */
  public RepOrderDetail selectRelative(HttpServletRequest request, HttpServletResponse response, ReqOrderDetail reqOrderDetail) {
    if (reqOrderDetail == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    initService(request, reqOrderDetail);
    RepOrderDetail repOrderDetail = new RepOrderDetail();
    if (isQueryTotal(reqOrderDetail)) {
      repOrderDetail.setTotal(daoOrderDetail.selectTotal(reqOrderDetail));
    }
    repOrderDetail.setRows(daoOrderDetail.selectRelative(reqOrderDetail));
    return repOrderDetail;
  }

  /**
   * 综合查询订单明细表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrderDetail 查询的参数。
   * @return 返回订单明细表列表。
   */
  public RepOrderDetail selectRelative(ReqOrderDetail reqOrderDetail) {
    return selectRelative(null, null, reqOrderDetail);
  }

  /**
   * 导出订单明细表到 excel。
   * @param paramExport 导出的参数。
   */
  public void export(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    if (paramExport == null) {
      throw new CommonException(Code.PARAM_EMPTY);
    }
    if (Tool.isNull(paramExport.getFields())) {
      throw new CommonException(Code.PARAM_EMPTY, "fields 不能为空！");
    }
    ReqOrderDetail reqOrderDetail;
    String reqParam = paramExport.getReqParam();
    if (!Tool.isNull(reqParam)) {
      reqOrderDetail = ToolJson.toBean(reqParam, ReqOrderDetail.class);
    } else {
      reqOrderDetail = new ReqOrderDetail();
    }
    List<Map<String, Object>> list = daoOrderDetail.selectRelativeMap(reqOrderDetail);
    String title = paramExport.getTitle();
    if (Tool.isNull(title)) {
      title = "订单明细表";
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
      case "orderId":
        field.setExportFieldType(Enum.ExportFieldType.integer_);
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
      case "goodsNum":
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