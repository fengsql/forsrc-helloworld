package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailOrder;
import com.example.mvc.bean.rep.RepOrder;
import com.example.mvc.bean.req.ReqOrder;
import com.example.mvc.model.Order;
import com.example.mvc.service.ServiceOrder;
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

@Api(tags = "订单表", description = "订单表相关的 API", position = 1)
@RestController
@RequestMapping("/api/order")
@Slf4j
public class ControllerOrder {

  @Resource
  private ServiceOrder serviceOrder;

  /**
   * 添加订单表。空值将被忽略。可以返回插入后的主键值。
   * @param order 订单表。
   * @return 返回添加的订单表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加订单表", notes = "添加订单表，空字段(null)将被忽略。", response = Order.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public Order insert(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("insert: {}", order);
    return serviceOrder.insert(request, response, order);
  }

  /**
   * 同步批量添加订单表，同步模式。空值将被忽略。
   * @param orders 订单表列表。
   * @return 返回添加的订单表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加订单表", notes = "同步批量添加订单表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Order> orders) {
    log.info("insertSync: {}", orders.size());
    return serviceOrder.insertSync(request, response, orders);
  }

  /**
   * 异步批量添加订单表，异步模式。空值将被忽略。
   * @param orders 订单表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加订单表", notes = "异步批量添加订单表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Order> orders) {
    log.info("insertAsyn: {}", orders.size());
    serviceOrder.insertAsyn(request, response, orders);
    return "";
  }

  /**
   * 更新订单表。空值将被忽略。
   * @param order 订单表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新订单表", notes = "更新订单表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("update: {}", order);
    return serviceOrder.update(request, response, order);
  }

  /**
   * 更新订单表。空值将被更新为 null。
   * @param order 订单表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新订单表空值", notes = "更新订单表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("updateEvenNull: {}", order);
    return serviceOrder.updateEvenNull(request, response, order);
  }

  /**
   * 根据主键删除一条订单表。
   * @param id 订单编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条订单表", notes = "根据主键删除一条订单表。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceOrder.delete(request, response, id);
  }

  /**
   * 根据条件删除订单表。
   * @param order 订单表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除订单表", notes = "根据条件删除订单表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("delete: {}", order);
    return serviceOrder.delete(request, response, order);
  }

  /**
   * 根据主键查询一条订单表。
   * @param id 订单编号。
   * @return 返回订单表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条订单表", notes = "根据主键查询一条订单表。", response = Order.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public Order selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceOrder.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条订单表。
   * @param order 订单表。
   * @return 返回订单表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条订单表", notes = "根据条件查询一条订单表。", response = Order.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public Order selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("selectOne: {}", order);
    return serviceOrder.selectOne(request, response, order);
  }

  /**
   * 根据条件查询一条订单表详情。
   * @param order 订单表。
   * @return 返回订单表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条订单表详情", notes = "根据条件查询一条订单表详情。", response = DetailOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailOrder selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("selectDetail: {}", order);
    return serviceOrder.selectDetail(request, response, order);
  }

  /**
   * 根据主键查询一条订单表详情。
   * @param id 订单编号。
   * @return 返回订单表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条订单表详情", notes = "根据主键查询一条订单表详情。", response = DetailOrder.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "订单编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailOrder selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceOrder.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询订单表列表。返回所有符合条件的订单表，未分页。
   * @param order 订单表。
   * @return 返回订单表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询订单表列表", notes = "查询订单表列表，返回所有符合条件的订单表，未分页。", response = Order.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<Order> select(HttpServletRequest request, HttpServletResponse response, @RequestBody Order order) {
    log.info("select: {}", order);
    return serviceOrder.select(request, response, order);
  }

  /**
   * 综合查询订单表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqOrder 查询的参数。
   * @return 返回订单表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询订单表列表", notes = "综合查询订单表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepOrder selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqOrder reqOrder) {
    log.info("selectRelative: {}", reqOrder);
    return serviceOrder.selectRelative(request, response, reqOrder);
  }

  /**
   * 导出订单表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepOrder.OrderRow 中获取，reqParam 为 ReqOrder 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出订单表", notes = "导出订单表到 excel。其中 fields 从 selectRelative 接口的 RepOrder.OrderRow 中获取，reqParam 为 ReqOrder 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceOrder.export(request, response, paramExport);
  }

}