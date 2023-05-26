package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailProvince;
import com.example.mvc.bean.rep.RepProvince;
import com.example.mvc.bean.req.ReqProvince;
import com.example.mvc.model.Province;
import com.example.mvc.service.ServiceProvince;
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

@Api(tags = "省表", description = "省表相关的 API", position = 1)
@RestController
@RequestMapping("/api/province")
@Slf4j
public class ControllerProvince {

  @Resource
  private ServiceProvince serviceProvince;

  /**
   * 添加省表。空值将被忽略。可以返回插入后的主键值。
   * @param province 省表。
   * @return 返回添加的省表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加省表", notes = "添加省表，空字段(null)将被忽略。", response = Province.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public Province insert(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("insert: {}", province);
    return serviceProvince.insert(request, response, province);
  }

  /**
   * 同步批量添加省表，同步模式。空值将被忽略。
   * @param provinces 省表列表。
   * @return 返回添加的省表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加省表", notes = "同步批量添加省表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Province> provinces) {
    log.info("insertSync: {}", provinces.size());
    return serviceProvince.insertSync(request, response, provinces);
  }

  /**
   * 异步批量添加省表，异步模式。空值将被忽略。
   * @param provinces 省表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加省表", notes = "异步批量添加省表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Province> provinces) {
    log.info("insertAsyn: {}", provinces.size());
    serviceProvince.insertAsyn(request, response, provinces);
    return "";
  }

  /**
   * 更新省表。空值将被忽略。
   * @param province 省表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新省表", notes = "更新省表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("update: {}", province);
    return serviceProvince.update(request, response, province);
  }

  /**
   * 更新省表。空值将被更新为 null。
   * @param province 省表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新省表空值", notes = "更新省表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("updateEvenNull: {}", province);
    return serviceProvince.updateEvenNull(request, response, province);
  }

  /**
   * 根据主键删除一条省表。
   * @param id 省编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条省表", notes = "根据主键删除一条省表。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "省编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceProvince.delete(request, response, id);
  }

  /**
   * 根据条件删除省表。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除省表", notes = "根据条件删除省表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("delete: {}", province);
    return serviceProvince.delete(request, response, province);
  }

  /**
   * 根据主键查询一条省表。
   * @param id 省编号。
   * @return 返回省表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条省表", notes = "根据主键查询一条省表。", response = Province.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "省编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public Province selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceProvince.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条省表。
   * @param province 省表。
   * @return 返回省表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条省表", notes = "根据条件查询一条省表。", response = Province.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public Province selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("selectOne: {}", province);
    return serviceProvince.selectOne(request, response, province);
  }

  /**
   * 根据条件查询一条省表详情。
   * @param province 省表。
   * @return 返回省表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条省表详情", notes = "根据条件查询一条省表详情。", response = DetailProvince.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailProvince selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("selectDetail: {}", province);
    return serviceProvince.selectDetail(request, response, province);
  }

  /**
   * 根据主键查询一条省表详情。
   * @param id 省编号。
   * @return 返回省表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条省表详情", notes = "根据主键查询一条省表详情。", response = DetailProvince.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "省编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailProvince selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceProvince.selectDetailByPrimary(request, response, id);
  }

  /**
   * 查询省表列表。返回所有符合条件的省表，未分页。
   * @param province 省表。
   * @return 返回省表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询省表列表", notes = "查询省表列表，返回所有符合条件的省表，未分页。", response = Province.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<Province> select(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("select: {}", province);
    return serviceProvince.select(request, response, province);
  }

  /**
   * 综合查询省表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqProvince 查询的参数。
   * @return 返回省表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询省表列表", notes = "综合查询省表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepProvince.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepProvince selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqProvince reqProvince) {
    log.info("selectRelative: {}", reqProvince);
    return serviceProvince.selectRelative(request, response, reqProvince);
  }

  /**
   * 根据省名称更新一条省表，此方法不适用根据省名称更改省名称的字段值。
   * @param province 省表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "根据省名称更新一条省表", notes = "根据省名称更新一条省表，此方法不适用根据省名称更改省名称的字段值。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateByProvinceName")
  public int updateByProvinceName(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("updateByProvinceName. province: {}", province);
    return serviceProvince.updateByProvinceName(request, response, province);
  }

  /**
   * 根据省名称删除一条省表。
   * @param province 省表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 121)
  @ApiOperation(value = "根据省名称删除一条省表", notes = "根据省名称删除一条省表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "deleteByProvinceName")
  public int deleteByProvinceName(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("deleteByProvinceName. province: {}", province);
    return serviceProvince.deleteByProvinceName(request, response, province);
  }

  /**
   * 根据省名称查询一条省表。
   * @param province 省表。
   * @return 返回省表。
   */
  @ApiOperationSupport(order = 122)
  @ApiOperation(value = "根据省名称查询一条省表", notes = "根据省名称查询一条省表。", response = Province.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectByProvinceName")
  public Province selectByProvinceName(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("selectByProvinceName: {}", province);
    return serviceProvince.selectByProvinceName(request, response, province);
  }

  /**
   * 根据省名称查询一条省表详情。
   * @param province 省表。
   * @return 返回省表。
   */
  @ApiOperationSupport(order = 123)
  @ApiOperation(value = "根据省名称查询一条省表详情", notes = "根据省名称查询一条省表详情。", response = DetailProvince.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByProvinceName")
  public DetailProvince selectDetailByProvinceName(HttpServletRequest request, HttpServletResponse response, @RequestBody Province province) {
    log.info("selectDetailByProvinceName: {}", province);
    return serviceProvince.selectDetailByProvinceName(request, response, province);
  }

  /**
   * 导出省表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepProvince.ProvinceRow 中获取，reqParam 为 ReqProvince 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出省表", notes = "导出省表到 excel。其中 fields 从 selectRelative 接口的 RepProvince.ProvinceRow 中获取，reqParam 为 ReqProvince 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceProvince.export(request, response, paramExport);
  }

}