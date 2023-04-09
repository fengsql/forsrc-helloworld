package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailCity;
import com.example.mvc.bean.rep.RepCity;
import com.example.mvc.bean.req.ReqCity;
import com.example.mvc.model.City;
import com.example.mvc.service.ServiceCity;
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

@Api(tags = "市表", description = "市表相关的 API", position = 1)
@RestController
@RequestMapping("/api/city")
@Slf4j
public class ControllerCity {

  @Resource
  private ServiceCity serviceCity;

  /**
   * 添加市表。空值将被忽略。可以返回插入后的主键值。
   * @param city 市表。
   * @return 返回添加的市表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加市表", notes = "添加市表，空字段(null)将被忽略。", response = City.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public City insert(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("insert: {}", city);
    return serviceCity.insert(request, response, city);
  }

  /**
   * 同步批量添加市表，同步模式。空值将被忽略。
   * @param citys 市表列表。
   * @return 返回添加的市表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加市表", notes = "同步批量添加市表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<City> citys) {
    log.info("insertSync: {}", citys.size());
    return serviceCity.insertSync(request, response, citys);
  }

  /**
   * 异步批量添加市表，异步模式。空值将被忽略。
   * @param citys 市表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加市表", notes = "异步批量添加市表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<City> citys) {
    log.info("insertAsyn: {}", citys.size());
    serviceCity.insertAsyn(request, response, citys);
    return "";
  }

  /**
   * 更新市表。空值将被忽略。
   * @param city 市表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新市表", notes = "更新市表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("update: {}", city);
    return serviceCity.update(request, response, city);
  }

  /**
   * 根据主键删除一条市表。
   * @param id 市编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条市表", notes = "根据主键删除一条市表。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "市编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceCity.delete(request, response, id);
  }

  /**
   * 根据条件删除市表。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除市表", notes = "根据条件删除市表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("delete: {}", city);
    return serviceCity.delete(request, response, city);
  }

  /**
   * 根据主键查询一条市表。
   * @param id 市编号。
   * @return 返回市表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条市表", notes = "根据主键查询一条市表。", response = City.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "市编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public City selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceCity.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条市表。
   * @param city 市表。
   * @return 返回市表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条市表", notes = "根据条件查询一条市表。", response = City.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public City selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("selectOne: {}", city);
    return serviceCity.selectOne(request, response, city);
  }

  /**
   * 根据条件查询一条市表详情。
   * @param city 市表。
   * @return 返回市表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条市表详情", notes = "根据条件查询一条市表详情。", response = DetailCity.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailCity selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("selectDetail: {}", city);
    return serviceCity.selectDetail(request, response, city);
  }

  /**
   * 根据主键查询一条市表详情。
   * @param id 市编号。
   * @return 返回市表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条市表详情", notes = "根据主键查询一条市表详情。", response = DetailCity.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "市编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailCity selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceCity.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询市表列表。返回所有符合条件的市表，未分页。
   * @param city 市表。
   * @return 返回市表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询市表列表", notes = "查询市表列表，返回所有符合条件的市表，未分页。", response = City.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<City> select(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("select: {}", city);
    return serviceCity.select(request, response, city);
  }

  /**
   * 综合查询市表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqCity 查询的参数。
   * @return 返回市表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询市表列表", notes = "综合查询市表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepCity.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepCity selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqCity reqCity) {
    log.info("selectRelative: {}", reqCity);
    return serviceCity.selectRelative(request, response, reqCity);
  }

  /**
   * 根据市名称更新一条市表，此方法不适用根据市名称更改市名称的字段值。
   * @param city 市表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "根据市名称更新一条市表", notes = "根据市名称更新一条市表，此方法不适用根据市名称更改市名称的字段值。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateByCityName")
  public int updateByCityName(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("updateByCityName. city: {}", city);
    return serviceCity.updateByCityName(request, response, city);
  }

  /**
   * 根据市名称删除一条市表。
   * @param city 市表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 121)
  @ApiOperation(value = "根据市名称删除一条市表", notes = "根据市名称删除一条市表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "deleteByCityName")
  public int deleteByCityName(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("deleteByCityName. city: {}", city);
    return serviceCity.deleteByCityName(request, response, city);
  }

  /**
   * 根据市名称查询一条市表。
   * @param city 市表。
   * @return 返回市表。
   */
  @ApiOperationSupport(order = 122)
  @ApiOperation(value = "根据市名称查询一条市表", notes = "根据市名称查询一条市表。", response = City.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectByCityName")
  public City selectByCityName(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("selectByCityName: {}", city);
    return serviceCity.selectByCityName(request, response, city);
  }

  /**
   * 根据市名称查询一条市表详情。
   * @param city 市表。
   * @return 返回市表。
   */
  @ApiOperationSupport(order = 123)
  @ApiOperation(value = "根据市名称查询一条市表详情", notes = "根据市名称查询一条市表详情。", response = DetailCity.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByCityName")
  public DetailCity selectDetailByCityName(HttpServletRequest request, HttpServletResponse response, @RequestBody City city) {
    log.info("selectDetailByCityName: {}", city);
    return serviceCity.selectDetailByCityName(request, response, city);
  }

  /**
   * 导出市表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepCity.CityRow 中获取，reqParam 为 ReqCity 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出市表", notes = "导出市表到 excel。其中 fields 从 selectRelative 接口的 RepCity.CityRow 中获取，reqParam 为 ReqCity 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceCity.export(request, response, paramExport);
  }

}