package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailMerchant;
import com.example.mvc.bean.rep.RepMerchant;
import com.example.mvc.bean.req.ReqMerchant;
import com.example.mvc.model.Merchant;
import com.example.mvc.service.ServiceMerchant;
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
import java.util.Map;

@Api(tags = "商户表", description = "商户表相关的 API", position = 4)
@RestController
@RequestMapping("/api/merchant")
@Slf4j
public class ControllerMerchant {

  @Resource
  private ServiceMerchant serviceMerchant;

  /**
   * 添加商户表。空值将被忽略。可以返回插入后的主键值。
   * @param merchant 商户表。
   * @return 返回添加的商户表。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "添加商户表", notes = "添加商户表，空字段(null)将被忽略。", response = Merchant.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public Merchant insert(HttpServletRequest request, HttpServletResponse response, @RequestBody Merchant merchant) {
    log.info("insert: {}", merchant);
    return serviceMerchant.insert(request, response, merchant);
  }

  /**
   * 同步批量添加商户表，同步模式。空值将被忽略。
   * @param merchants 商户表列表。
   * @return 返回添加的商户表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加商户表", notes = "同步批量添加商户表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Merchant> merchants) {
    log.info("insertSync: {}", merchants.size());
    return serviceMerchant.insertSync(request, response, merchants);
  }

  /**
   * 异步批量添加商户表，异步模式。空值将被忽略。
   * @param merchants 商户表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "异步批量添加商户表", notes = "异步批量添加商户表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Merchant> merchants) {
    log.info("insertAsyn: {}", merchants.size());
    serviceMerchant.insertAsyn(request, response, merchants);
    return "";
  }

  /**
   * 更新商户表。空值将被忽略。
   * @param merchant 商户表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "更新商户表", notes = "更新商户表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody Merchant merchant) {
    log.info("update: {}", merchant);
    return serviceMerchant.update(request, response, merchant);
  }

  /**
   * 根据主键删除一条商户表。
   * @param merchantId 商户编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "删除一条商户表", notes = "根据主键删除一条商户表。", response = Integer.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "merchantId", value = "商户编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "merchantId") String merchantId) {
    log.info("deleteByPrimary: {}", merchantId);
    return serviceMerchant.delete(request, response, merchantId);
  }

  /**
   * 根据条件删除商户表。
   * @param merchant 商户表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除商户表", notes = "根据条件删除商户表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody Merchant merchant) {
    log.info("delete: {}", merchant);
    return serviceMerchant.delete(request, response, merchant);
  }

  /**
   * 根据主键查询一条商户表。
   * @param merchantId 商户编号。
   * @return 返回商户表。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "根据主键查询一条商户表", notes = "根据主键查询一条商户表。", response = Merchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "merchantId", value = "商户编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public Merchant selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "merchantId") String merchantId) {
    log.info("selectByPrimary: {}", merchantId);
    return serviceMerchant.selectByPrimary(request, response, merchantId);
  }

  /**
   * 根据条件查询一条商户表详情。
   * @param merchant 商户表。
   * @return 返回商户表详情。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据条件查询一条商户表详情", notes = "根据条件查询一条商户表详情。", response = DetailMerchant.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailMerchant selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody Merchant merchant) {
    log.info("selectDetail: {}", merchant);
    return serviceMerchant.selectDetail(request, response, merchant);
  }

  /**
   * 根据主键查询一条商户表详情。
   * @param merchantId 商户编号。
   * @return 返回商户表详情。
   */
  @ApiOperationSupport(order = 71)
  @ApiOperation(value = "根据主键查询一条商户表详情", notes = "根据主键查询一条商户表详情。", response = DetailMerchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "merchantId", value = "商户编号", required = true)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailMerchant selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "merchantId") String merchantId) {
    log.info("selectDetailByPrimary: {}", merchantId);
    return serviceMerchant.selectDetailByPrimary(request, response, merchantId);
  }

  /**
   * 根据商户号查询一条商户表。
   * @param map 请求参数。mchName: 商户名; mchNo: 商户号。
   * @return 返回商户表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据商户号查询一条商户表", notes = "根据商户号查询一条商户表。", response = Merchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "mchName", value = "商户名", required = false),
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "mchNo", value = "商户号", required = false)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByMchNo")
  public Merchant selectByMchNo(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
    log.info("selectByMchNo. map: {}", map);
    String mchName = (String)map.get("mchName");
    String mchNo = (String)map.get("mchNo");
    return serviceMerchant.selectByMchNo(request, response, mchName, mchNo);
  }

  /**
   * 根据商户号查询一条商户表详情。
   * @param map 请求参数。mchName: 商户名; mchNo: 商户号。
   * @return 返回商户表。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据商户号查询一条商户表详情", notes = "根据商户号查询一条商户表详情。", response = DetailMerchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "mchName", value = "商户名", required = false),
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "mchNo", value = "商户号", required = false)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByMchNo")
  public DetailMerchant selectDetailByMchNo(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
    log.info("selectDetailByMchNo. map: {}", map);
    String mchName = (String)map.get("mchName");
    String mchNo = (String)map.get("mchNo");
    return serviceMerchant.selectDetailByMchNo(request, response, mchName, mchNo);
  }

  /**
   * 根据Appid查询一条商户表。
   * @param map 请求参数。appid: Appid。
   * @return 返回商户表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据Appid查询一条商户表", notes = "根据Appid查询一条商户表。", response = Merchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "appid", value = "Appid", required = false)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectByAppid")
  public Merchant selectByAppid(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
    log.info("selectByAppid. map: {}", map);
    String appid = (String)map.get("appid");
    return serviceMerchant.selectByAppid(request, response, appid);
  }

  /**
   * 根据Appid查询一条商户表详情。
   * @param map 请求参数。appid: Appid。
   * @return 返回商户表。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据Appid查询一条商户表详情", notes = "根据Appid查询一条商户表详情。", response = DetailMerchant.class)
  @ApiImplicitParams({
    @ApiImplicitParam(paramType = "body", dataType = "String", name = "appid", value = "Appid", required = false)
  })
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByAppid")
  public DetailMerchant selectDetailByAppid(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> map) {
    log.info("selectDetailByAppid. map: {}", map);
    String appid = (String)map.get("appid");
    return serviceMerchant.selectDetailByAppid(request, response, appid);
  }

  /**
   * 查询商户表列表。返回所有符合条件的商户表，未分页。
   * @param merchant 商户表。
   * @return 返回商户表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询商户表列表", notes = "查询商户表列表，返回所有符合条件的商户表，未分页。", response = Merchant.class, responseContainer="List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<Merchant> select(HttpServletRequest request, HttpServletResponse response, @RequestBody Merchant merchant) {
    log.info("select: {}", merchant);
    return serviceMerchant.select(request, response, merchant);
  }

  /**
   * 综合查询商户表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqMerchant 查询的参数。
   * @return 返回商户表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询商户表列表", notes = "综合查询商户表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepMerchant.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepMerchant selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqMerchant reqMerchant) {
    log.info("selectRelative: {}", reqMerchant);
    return serviceMerchant.selectRelative(request, response, reqMerchant);
  }

  /**
   * 导出商户表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepMerchant.MerchantRow 中获取，reqParam 为 ReqMerchant 对象。
   */
  @ApiOperationSupport(order = 2)
  @ApiOperation(value = "导出商户表", notes = "导出商户表到 excel。其中 fields 从 selectRelative 接口的 RepMerchant.MerchantRow 中获取，reqParam 为 ReqMerchant 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceMerchant.export(request, response, paramExport);
  }

}