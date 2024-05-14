package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailOrderDetail;
import com.example.mvc.bean.rep.RepOrderDetail;
import com.example.mvc.bean.req.ReqOrderDetail;
import com.example.mvc.model.OrderDetail;
import com.example.mvc.service.ServiceOrderDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.example.mvc.event.auth.AuthOrderDetail;

@Api(tags = "订单明细表", description = "订单明细表相关的 API", position = 1)
@RestController
@RequestMapping("/api/orderDetail")
@Slf4j
public class ControllerOrderDetail {

  @Resource
  private AuthOrderDetail authOrderDetail;
  @Resource
  private ServiceOrderDetail serviceOrderDetail;

  /**
   * 添加订单明细表。空值将被忽略。可以返回插入后的主键值。
   * @param orderDetail 订单明细表。
   * @return 返回添加的订单明细表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加订单明细表", notes = "添加订单明细表，空字段(null)将被忽略。", response = OrderDetail.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public OrderDetail insert(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("insert: {}", orderDetail);
    authOrderDetail.onInsert(request, response, orderDetail);
    return serviceOrderDetail.insert(request, response, orderDetail);
  }

  /**
   * 同步批量添加订单明细表，同步模式。空值将被忽略。
   * @param orderDetails 订单明细表列表。
   * @return 返回添加的订单明细表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加订单明细表", notes = "同步批量添加订单明细表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<OrderDetail> orderDetails) {
    log.info("insertSync: {}", orderDetails.size());
    authOrderDetail.onInsert(request, response, orderDetails);
    return serviceOrderDetail.insertSync(request, response, orderDetails);
  }

  /**
   * 异步批量添加订单明细表，异步模式。空值将被忽略。
   * @param orderDetails 订单明细表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加订单明细表", notes = "异步批量添加订单明细表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<OrderDetail> orderDetails) {
    log.info("insertAsyn: {}", orderDetails.size());
    authOrderDetail.onInsert(request, response, orderDetails);
    serviceOrderDetail.insertAsyn(request, response, orderDetails);
    return "";
  }

  /**
   * 更新订单明细表。空值将被忽略。
   * @param orderDetail 订单明细表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新订单明细表", notes = "更新订单明细表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("update: {}", orderDetail);
    authOrderDetail.onUpdate(request, response, orderDetail);
    return serviceOrderDetail.update(request, response, orderDetail);
  }

  /**
   * 更新订单明细表。空值将被更新为 null。
   * @param orderDetail 订单明细表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新订单明细表空值", notes = "更新订单明细表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("updateEvenNull: {}", orderDetail);
    authOrderDetail.onUpdate(request, response, orderDetail);
    return serviceOrderDetail.updateEvenNull(request, response, orderDetail);
  }


  /**
   * 根据主键删除一条订单明细表。
   * @param id 订单编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条订单明细表", notes = "根据主键删除一条订单明细表。", response = Integer.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setId(id);
    authOrderDetail.onDelete(request, response, orderDetail);
    return serviceOrderDetail.delete(request, response, id);
  }

  /**
   * 根据条件删除订单明细表。
   * @param orderDetail 订单明细表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除订单明细表", notes = "根据条件删除订单明细表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("delete: {}", orderDetail);
    authOrderDetail.onDelete(request, response, orderDetail);
    return serviceOrderDetail.delete(request, response, orderDetail);
  }

  /**
   * 根据主键查询一条订单明细表。
   * @param id 订单编号。
   * @return 返回订单明细表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条订单明细表", notes = "根据主键查询一条订单明细表。", response = OrderDetail.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public OrderDetail selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setId(id);
    authOrderDetail.onSelect(request, response, orderDetail);
    return serviceOrderDetail.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条订单明细表。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条订单明细表", notes = "根据条件查询一条订单明细表。", response = OrderDetail.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public OrderDetail selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("selectOne: {}", orderDetail);
    authOrderDetail.onSelect(request, response, orderDetail);
    return serviceOrderDetail.selectOne(request, response, orderDetail);
  }

  /**
   * 根据条件查询一条订单明细表详情。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条订单明细表详情", notes = "根据条件查询一条订单明细表详情。", response = DetailOrderDetail.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailOrderDetail selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("selectDetail: {}", orderDetail);
    authOrderDetail.onSelectDetail(request, response, orderDetail);
    return serviceOrderDetail.selectDetail(request, response, orderDetail);
  }

  /**
   * 根据主键查询一条订单明细表详情。
   * @param id 订单编号。
   * @return 返回订单明细表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条订单明细表详情", notes = "根据主键查询一条订单明细表详情。", response = DetailOrderDetail.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailOrderDetail selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setId(id);
    authOrderDetail.onSelectDetail(request, response, orderDetail);
    return serviceOrderDetail.selectDetailByPrimary(request, response, id);
  }


  /**
   * 查询订单明细表列表。返回所有符合条件的订单明细表，未分页。
   * @param orderDetail 订单明细表。
   * @return 返回订单明细表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询订单明细表列表", notes = "查询订单明细表列表，返回所有符合条件的订单明细表，未分页。", response = OrderDetail.class, responseContainer = "List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<OrderDetail> select(HttpServletRequest request, HttpServletResponse response, @RequestBody OrderDetail orderDetail) {
    log.info("select: {}", orderDetail);
    authOrderDetail.onSelect(request, response, orderDetail);
    return serviceOrderDetail.select(request, response, orderDetail);
  }

  /**
   * 综合查询订单明细表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrderDetail 查询的参数。
   * @return 返回订单明细表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询订单明细表列表", notes = "综合查询订单明细表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepOrderDetail.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepOrderDetail selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqOrderDetail reqOrderDetail) {
    log.info("selectRelative: {}", reqOrderDetail);
    authOrderDetail.onSelectRelative(request, response, reqOrderDetail);
    return serviceOrderDetail.selectRelative(request, response, reqOrderDetail);
  }


  /**
   * 导出订单明细表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepOrderDetail.OrderDetailRow 中获取，reqParam 为 ReqOrderDetail 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出订单明细表", notes = "导出订单明细表到 excel。其中 fields 从 selectRelative 接口的 RepOrderDetail.OrderDetailRow 中获取，reqParam 为 ReqOrderDetail 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    authOrderDetail.onExport(request, response, paramExport);
    serviceOrderDetail.export(request, response, paramExport);
  }

}