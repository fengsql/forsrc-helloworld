package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailStatTest;
import com.example.mvc.bean.rep.RepStatTest;
import com.example.mvc.bean.req.ReqStatTest;
import com.example.mvc.model.StatTest;
import com.example.mvc.service.ServiceStatTest;
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

@Api(tags = "统计信息", description = "统计信息相关的 API", position = 5)
@RestController
@RequestMapping("/api/statTest")
@Slf4j
public class ControllerStatTest {

  @Resource
  private ServiceStatTest serviceStatTest;

  /**
   * 添加统计信息。空值将被忽略。可以返回插入后的主键值。
   * @param statTest 统计信息。
   * @return 返回添加的统计信息。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加统计信息", notes = "添加统计信息，空字段(null)将被忽略。", response = StatTest.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public StatTest insert(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("insert: {}", statTest);
    return serviceStatTest.insert(request, response, statTest);
  }

  /**
   * 同步批量添加统计信息，同步模式。空值将被忽略。
   * @param statTests 统计信息列表。
   * @return 返回添加的统计信息数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加统计信息", notes = "同步批量添加统计信息，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatTest> statTests) {
    log.info("insertSync: {}", statTests.size());
    return serviceStatTest.insertSync(request, response, statTests);
  }

  /**
   * 异步批量添加统计信息，异步模式。空值将被忽略。
   * @param statTests 统计信息列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加统计信息", notes = "异步批量添加统计信息，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<StatTest> statTests) {
    log.info("insertAsyn: {}", statTests.size());
    serviceStatTest.insertAsyn(request, response, statTests);
    return "";
  }

  /**
   * 更新统计信息。空值将被忽略。
   * @param statTest 统计信息。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新统计信息", notes = "更新统计信息，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("update: {}", statTest);
    return serviceStatTest.update(request, response, statTest);
  }

  /**
   * 根据主键删除一条统计信息。
   * @param id 编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条统计信息", notes = "根据主键删除一条统计信息。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceStatTest.delete(request, response, id);
  }

  /**
   * 根据条件删除统计信息。
   * @param statTest 统计信息。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除统计信息", notes = "根据条件删除统计信息。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("delete: {}", statTest);
    return serviceStatTest.delete(request, response, statTest);
  }

  /**
   * 根据主键查询一条统计信息。
   * @param id 编号。
   * @return 返回统计信息。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条统计信息", notes = "根据主键查询一条统计信息。", response = StatTest.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public StatTest selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceStatTest.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条统计信息。
   * @param statTest 统计信息。
   * @return 返回统计信息。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条统计信息", notes = "根据条件查询一条统计信息。", response = StatTest.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public StatTest selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("selectOne: {}", statTest);
    return serviceStatTest.selectOne(request, response, statTest);
  }

  /**
   * 根据条件查询一条统计信息详情。
   * @param statTest 统计信息。
   * @return 返回统计信息详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条统计信息详情", notes = "根据条件查询一条统计信息详情。", response = DetailStatTest.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailStatTest selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("selectDetail: {}", statTest);
    return serviceStatTest.selectDetail(request, response, statTest);
  }

  /**
   * 根据主键查询一条统计信息详情。
   * @param id 编号。
   * @return 返回统计信息详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条统计信息详情", notes = "根据主键查询一条统计信息详情。", response = DetailStatTest.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailStatTest selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceStatTest.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询统计信息列表。返回所有符合条件的统计信息，未分页。
   * @param statTest 统计信息。
   * @return 返回统计信息列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询统计信息列表", notes = "查询统计信息列表，返回所有符合条件的统计信息，未分页。", response = StatTest.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<StatTest> select(HttpServletRequest request, HttpServletResponse response, @RequestBody StatTest statTest) {
    log.info("select: {}", statTest);
    return serviceStatTest.select(request, response, statTest);
  }

  /**
   * 综合查询统计信息列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqStatTest 查询的参数。
   * @return 返回统计信息列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询统计信息列表", notes = "综合查询统计信息列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepStatTest.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepStatTest selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqStatTest reqStatTest) {
    log.info("selectRelative: {}", reqStatTest);
    return serviceStatTest.selectRelative(request, response, reqStatTest);
  }

  /**
   * 导出统计信息到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepStatTest.StatTestRow 中获取，reqParam 为 ReqStatTest 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出统计信息", notes = "导出统计信息到 excel。其中 fields 从 selectRelative 接口的 RepStatTest.StatTestRow 中获取，reqParam 为 ReqStatTest 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceStatTest.export(request, response, paramExport);
  }

}