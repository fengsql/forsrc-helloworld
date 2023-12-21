package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailStatDayGoods;
import com.example.mvc.bean.rep.RepStatDayGoods;
import com.example.mvc.bean.req.ReqStatDayGoods;
import com.example.mvc.model.StatDayGoods;
import com.example.mvc.service.ServiceStatDayGoods;
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

@Api(tags = "每日订单商品统计", description = "每日订单商品统计相关的 API", position = 1)
@RestController
@RequestMapping("/api/statDayGoods")
@Slf4j
public class ControllerStatDayGoods {

  @Resource
  private ServiceStatDayGoods serviceStatDayGoods;

  /**
   * 添加每日订单商品统计。空值将被忽略。可以返回插入后的主键值。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回添加的每日订单商品统计。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加每日订单商品统计", notes = "添加每日订单商品统计，空字段(null)将被忽略。", response = StatDayGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public StatDayGoods insert(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("insert: {}", statDayGoods);
    return serviceStatDayGoods.insert(request, response, statDayGoods);
  }

  /**
   * 同步批量添加每日订单商品统计，同步模式。空值将被忽略。
   * @param statDayGoodss 每日订单商品统计列表。
   * @return 返回添加的每日订单商品统计数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加每日订单商品统计", notes = "同步批量添加每日订单商品统计，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatDayGoods> statDayGoodss) {
    log.info("insertSync: {}", statDayGoodss.size());
    return serviceStatDayGoods.insertSync(request, response, statDayGoodss);
  }

  /**
   * 异步批量添加每日订单商品统计，异步模式。空值将被忽略。
   * @param statDayGoodss 每日订单商品统计列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加每日订单商品统计", notes = "异步批量添加每日订单商品统计，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatDayGoods> statDayGoodss) {
    log.info("insertAsyn: {}", statDayGoodss.size());
    serviceStatDayGoods.insertAsyn(request, response, statDayGoodss);
    return "";
  }

  /**
   * 更新每日订单商品统计。空值将被忽略。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新每日订单商品统计", notes = "更新每日订单商品统计，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("update: {}", statDayGoods);
    return serviceStatDayGoods.update(request, response, statDayGoods);
  }

  /**
   * 更新每日订单商品统计。空值将被更新为 null。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新每日订单商品统计空值", notes = "更新每日订单商品统计，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("updateEvenNull: {}", statDayGoods);
    return serviceStatDayGoods.updateEvenNull(request, response, statDayGoods);
  }

  /**
   * 根据主键删除一条每日订单商品统计。
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条每日订单商品统计", notes = "根据主键删除一条每日订单商品统计。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceStatDayGoods.delete(request, response, id);
  }

  /**
   * 根据条件删除每日订单商品统计。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除每日订单商品统计", notes = "根据条件删除每日订单商品统计。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("delete: {}", statDayGoods);
    return serviceStatDayGoods.delete(request, response, statDayGoods);
  }

  /**
   * 根据主键查询一条每日订单商品统计。
   * @param id 编号。
   * @return 返回每日订单商品统计。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条每日订单商品统计", notes = "根据主键查询一条每日订单商品统计。", response = StatDayGoods.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public StatDayGoods selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceStatDayGoods.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条每日订单商品统计。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条每日订单商品统计", notes = "根据条件查询一条每日订单商品统计。", response = StatDayGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public StatDayGoods selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("selectOne: {}", statDayGoods);
    return serviceStatDayGoods.selectOne(request, response, statDayGoods);
  }

  /**
   * 根据条件查询一条每日订单商品统计详情。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条每日订单商品统计详情", notes = "根据条件查询一条每日订单商品统计详情。", response = DetailStatDayGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailStatDayGoods selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("selectDetail: {}", statDayGoods);
    return serviceStatDayGoods.selectDetail(request, response, statDayGoods);
  }

  /**
   * 根据主键查询一条每日订单商品统计详情。
   * @param id 编号。
   * @return 返回每日订单商品统计详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条每日订单商品统计详情", notes = "根据主键查询一条每日订单商品统计详情。", response = DetailStatDayGoods.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailStatDayGoods selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceStatDayGoods.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询每日订单商品统计列表。返回所有符合条件的每日订单商品统计，未分页。
   * @param statDayGoods 每日订单商品统计。
   * @return 返回每日订单商品统计列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询每日订单商品统计列表", notes = "查询每日订单商品统计列表，返回所有符合条件的每日订单商品统计，未分页。", response = StatDayGoods.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<StatDayGoods> select(HttpServletRequest request, HttpServletResponse response, @RequestBody StatDayGoods statDayGoods) {
    log.info("select: {}", statDayGoods);
    return serviceStatDayGoods.select(request, response, statDayGoods);
  }

  /**
   * 综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatDayGoods 查询的参数。
   * @return 返回每日订单商品统计列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询每日订单商品统计列表", notes = "综合查询每日订单商品统计列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepStatDayGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepStatDayGoods selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqStatDayGoods reqStatDayGoods) {
    log.info("selectRelative: {}", reqStatDayGoods);
    return serviceStatDayGoods.selectRelative(request, response, reqStatDayGoods);
  }

  /**
   * 导出每日订单商品统计到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepStatDayGoods.StatDayGoodsRow 中获取，reqParam 为 ReqStatDayGoods 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出每日订单商品统计", notes = "导出每日订单商品统计到 excel。其中 fields 从 selectRelative 接口的 RepStatDayGoods.StatDayGoodsRow 中获取，reqParam 为 ReqStatDayGoods 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceStatDayGoods.export(request, response, paramExport);
  }

  /**
   * 实时统计每日订单商品统计。返回所有统计结果，未分页。
   * @param reqStatDayGoods 统计参数。
   * @return 返回实时统计每日订单商品统计。
   */
  @ApiOperationSupport(order = 210)
  @ApiOperation(value = "实时统计每日订单商品统计", notes = "实时统计每日订单商品统计，返回所有统计结果，未分页。仅分组字段可以作为查询条件。", response = StatDayGoods.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "stat")
  public List<StatDayGoods> stat(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqStatDayGoods reqStatDayGoods) {
    log.info("stat: {}", reqStatDayGoods);
    return serviceStatDayGoods.stat(request, response, reqStatDayGoods);
  }

}