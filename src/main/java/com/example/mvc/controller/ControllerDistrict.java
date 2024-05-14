package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailDistrict;
import com.example.mvc.bean.rep.RepDistrict;
import com.example.mvc.bean.req.ReqDistrict;
import com.example.mvc.model.District;
import com.example.mvc.service.ServiceDistrict;
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

@Api(tags = "县表", description = "县表相关的 API", position = 1)
@RestController
@RequestMapping("/api/district")
@Slf4j
public class ControllerDistrict {

  @Resource
  private ServiceDistrict serviceDistrict;

  /**
   * 添加县表。空值将被忽略。可以返回插入后的主键值。
   * @param district 县表。
   * @return 返回添加的县表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加县表", notes = "添加县表，空字段(null)将被忽略。", response = District.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public District insert(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("insert: {}", district);
    return serviceDistrict.insert(request, response, district);
  }

  /**
   * 同步批量添加县表，同步模式。空值将被忽略。
   * @param districts 县表列表。
   * @return 返回添加的县表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加县表", notes = "同步批量添加县表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<District> districts) {
    log.info("insertSync: {}", districts.size());
    return serviceDistrict.insertSync(request, response, districts);
  }

  /**
   * 异步批量添加县表，异步模式。空值将被忽略。
   * @param districts 县表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加县表", notes = "异步批量添加县表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<District> districts) {
    log.info("insertAsyn: {}", districts.size());
    serviceDistrict.insertAsyn(request, response, districts);
    return "";
  }

  /**
   * 更新县表。空值将被忽略。
   * @param district 县表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新县表", notes = "更新县表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("update: {}", district);
    return serviceDistrict.update(request, response, district);
  }

  /**
   * 更新县表。空值将被更新为 null。
   * @param district 县表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新县表空值", notes = "更新县表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("updateEvenNull: {}", district);
    return serviceDistrict.updateEvenNull(request, response, district);
  }


  /**
   * 根据主键删除一条县表。
   * @param id 县编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条县表", notes = "根据主键删除一条县表。", response = Integer.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "县编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceDistrict.delete(request, response, id);
  }

  /**
   * 根据条件删除县表。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除县表", notes = "根据条件删除县表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("delete: {}", district);
    return serviceDistrict.delete(request, response, district);
  }

  /**
   * 根据主键查询一条县表。
   * @param id 县编号。
   * @return 返回县表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条县表", notes = "根据主键查询一条县表。", response = District.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "县编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public District selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceDistrict.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条县表。
   * @param district 县表。
   * @return 返回县表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条县表", notes = "根据条件查询一条县表。", response = District.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public District selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("selectOne: {}", district);
    return serviceDistrict.selectOne(request, response, district);
  }

  /**
   * 根据条件查询一条县表详情。
   * @param district 县表。
   * @return 返回县表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条县表详情", notes = "根据条件查询一条县表详情。", response = DetailDistrict.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailDistrict selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("selectDetail: {}", district);
    return serviceDistrict.selectDetail(request, response, district);
  }

  /**
   * 根据主键查询一条县表详情。
   * @param id 县编号。
   * @return 返回县表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条县表详情", notes = "根据主键查询一条县表详情。", response = DetailDistrict.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "县编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailDistrict selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceDistrict.selectDetailByPrimary(request, response, id);
  }


  /**
   * 查询县表列表。返回所有符合条件的县表，未分页。
   * @param district 县表。
   * @return 返回县表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询县表列表", notes = "查询县表列表，返回所有符合条件的县表，未分页。", response = District.class, responseContainer = "List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<District> select(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("select: {}", district);
    return serviceDistrict.select(request, response, district);
  }

  /**
   * 综合查询县表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqDistrict 查询的参数。
   * @return 返回县表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询县表列表", notes = "综合查询县表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepDistrict.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepDistrict selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqDistrict reqDistrict) {
    log.info("selectRelative: {}", reqDistrict);
    return serviceDistrict.selectRelative(request, response, reqDistrict);
  }


  /**
   * 根据县名称更新一条县表，此方法不适用根据县名称更改县名称的字段值。
   * @param district 县表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "根据县名称更新一条县表", notes = "根据县名称更新一条县表，此方法不适用根据县名称更改县名称的字段值。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateByDistrictName")
  public int updateByDistrictName(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("updateByDistrictName. district: {}", district);
    return serviceDistrict.updateByDistrictName(request, response, district);
  }

  /**
   * 根据县名称删除一条县表。
   * @param district 县表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 121)
  @ApiOperation(value = "根据县名称删除一条县表", notes = "根据县名称删除一条县表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "deleteByDistrictName")
  public int deleteByDistrictName(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("deleteByDistrictName. district: {}", district);
    return serviceDistrict.deleteByDistrictName(request, response, district);
  }

  /**
   * 根据县名称查询一条县表。
   * @param district 县表。
   * @return 返回县表。
   */
  @ApiOperationSupport(order = 122)
  @ApiOperation(value = "根据县名称查询一条县表", notes = "根据县名称查询一条县表。", response = District.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectByDistrictName")
  public District selectByDistrictName(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("selectByDistrictName: {}", district);
    return serviceDistrict.selectByDistrictName(request, response, district);
  }

  /**
   * 根据县名称查询一条县表详情。
   * @param district 县表。
   * @return 返回县表。
   */
  @ApiOperationSupport(order = 123)
  @ApiOperation(value = "根据县名称查询一条县表详情", notes = "根据县名称查询一条县表详情。", response = DetailDistrict.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByDistrictName")
  public DetailDistrict selectDetailByDistrictName(HttpServletRequest request, HttpServletResponse response, @RequestBody District district) {
    log.info("selectDetailByDistrictName: {}", district);
    return serviceDistrict.selectDetailByDistrictName(request, response, district);
  }


  /**
   * 导出县表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepDistrict.DistrictRow 中获取，reqParam 为 ReqDistrict 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出县表", notes = "导出县表到 excel。其中 fields 从 selectRelative 接口的 RepDistrict.DistrictRow 中获取，reqParam 为 ReqDistrict 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceDistrict.export(request, response, paramExport);
  }

}