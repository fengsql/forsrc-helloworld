package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailStatDayOrder;
import com.example.mvc.bean.rep.RepStatDayOrder;
import com.example.mvc.bean.req.ReqStatDayOrder;
import com.example.mvc.model.StatDayOrder;
import com.example.mvc.service.ServiceStatDayOrder;
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

@Api(tags = "每日订单统计", description = "每日订单统计相关的 API", position = 1)
@RestController
@RequestMapping("/api/statDayOrder")
@Slf4j
public class ControllerStatDayOrder {

  @Resource
  private ServiceStatDayOrder serviceStatDayOrder;

  /**
   * 添加每日订单统计。空值将被忽略。可以返回插入后的主键值。
   * @param statDayOrder 每日订单统计。
   * @return 返回添加的每日订单统计。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加每日订单统计", notes = "添加每日订单统计，空字段(null)将被忽略。", response = StatDayOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public StatDayOrder insert(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("insert: {}", statDayOrder);
    return serviceStatDayOrder.insert(request, response, statDayOrder);
  }

  /**
   * 同步批量添加每日订单统计，同步模式。空值将被忽略。
   * @param statDayOrders 每日订单统计列表。
   * @return 返回添加的每日订单统计数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加每日订单统计", notes = "同步批量添加每日订单统计，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatDayOrder> statDayOrders) {
    log.info("insertSync: {}", statDayOrders.size());
    return serviceStatDayOrder.insertSync(request, response, statDayOrders);
  }

  /**
   * 异步批量添加每日订单统计，异步模式。空值将被忽略。
   * @param statDayOrders 每日订单统计列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加每日订单统计", notes = "异步批量添加每日订单统计，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatDayOrder> statDayOrders) {
    log.info("insertAsyn: {}", statDayOrders.size());
    serviceStatDayOrder.insertAsyn(request, response, statDayOrders);
    return "";
  }

  /**
   * 更新每日订单统计。空值将被忽略。
   * @param statDayOrder 每日订单统计。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新每日订单统计", notes = "更新每日订单统计，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("update: {}", statDayOrder);
    return serviceStatDayOrder.update(request, response, statDayOrder);
  }

  /**
   * 更新每日订单统计。空值将被更新为 null。
   * @param statDayOrder 每日订单统计。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新每日订单统计空值", notes = "更新每日订单统计，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("updateEvenNull: {}", statDayOrder);
    return serviceStatDayOrder.updateEvenNull(request, response, statDayOrder);
  }

  /**
   * 根据主键删除一条每日订单统计。
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条每日订单统计", notes = "根据主键删除一条每日订单统计。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceStatDayOrder.delete(request, response, id);
  }

  /**
   * 根据条件删除每日订单统计。
   * @param statDayOrder 每日订单统计。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除每日订单统计", notes = "根据条件删除每日订单统计。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("delete: {}", statDayOrder);
    return serviceStatDayOrder.delete(request, response, statDayOrder);
  }

  /**
   * 根据主键查询一条每日订单统计。
   * @param id 编号。
   * @return 返回每日订单统计。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条每日订单统计", notes = "根据主键查询一条每日订单统计。", response = StatDayOrder.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public StatDayOrder selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceStatDayOrder.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条每日订单统计。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条每日订单统计", notes = "根据条件查询一条每日订单统计。", response = StatDayOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public StatDayOrder selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("selectOne: {}", statDayOrder);
    return serviceStatDayOrder.selectOne(request, response, statDayOrder);
  }

  /**
   * 根据条件查询一条每日订单统计详情。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条每日订单统计详情", notes = "根据条件查询一条每日订单统计详情。", response = DetailStatDayOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailStatDayOrder selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("selectDetail: {}", statDayOrder);
    return serviceStatDayOrder.selectDetail(request, response, statDayOrder);
  }

  /**
   * 根据主键查询一条每日订单统计详情。
   * @param id 编号。
   * @return 返回每日订单统计详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条每日订单统计详情", notes = "根据主键查询一条每日订单统计详情。", response = DetailStatDayOrder.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailStatDayOrder selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceStatDayOrder.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询每日订单统计列表。返回所有符合条件的每日订单统计，未分页。
   * @param statDayOrder 每日订单统计。
   * @return 返回每日订单统计列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询每日订单统计列表", notes = "查询每日订单统计列表，返回所有符合条件的每日订单统计，未分页。", response = StatDayOrder.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<StatDayOrder> select(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayOrder statDayOrder) {
    log.info("select: {}", statDayOrder);
    return serviceStatDayOrder.select(request, response, statDayOrder);
  }

  /**
   * 综合查询每日订单统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayOrder 查询的参数。
   * @return 返回每日订单统计列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询每日订单统计列表", notes = "综合查询每日订单统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepStatDayOrder.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepStatDayOrder selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqStatDayOrder reqStatDayOrder) {
    log.info("selectRelative: {}", reqStatDayOrder);
    return serviceStatDayOrder.selectRelative(request, response, reqStatDayOrder);
  }

  /**
   * 导出每日订单统计到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepStatDayOrder.StatDayOrderRow 中获取，reqParam 为 ReqStatDayOrder 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出每日订单统计", notes = "导出每日订单统计到 excel。其中 fields 从 selectRelative 接口的 RepStatDayOrder.StatDayOrderRow 中获取，reqParam 为 ReqStatDayOrder 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceStatDayOrder.export(request, response, paramExport);
  }

  /**
   * 实时统计每日订单统计。返回所有统计结果，未分页。
   * @param reqStatDayOrder 统计参数。
   * @return 返回实时统计每日订单统计。
   */
  @ApiOperationSupport(order = 210)
  @ApiOperation(value = "实时统计每日订单统计", notes = "实时统计每日订单统计，返回所有统计结果，未分页。仅分组字段可以作为查询条件。", response = StatDayOrder.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "stat")
  public List<StatDayOrder> stat(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqStatDayOrder reqStatDayOrder) {
    log.info("stat: {}", reqStatDayOrder);
    return serviceStatDayOrder.stat(request, response, reqStatDayOrder);
  }

}