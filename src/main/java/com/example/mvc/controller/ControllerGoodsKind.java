package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailGoodsKind;
import com.example.mvc.bean.rep.RepGoodsKind;
import com.example.mvc.bean.req.ReqGoodsKind;
import com.example.mvc.model.GoodsKind;
import com.example.mvc.service.ServiceGoodsKind;
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

@Api(tags = "商品类别表", description = "商品类别表相关的 API", position = 1)
@RestController
@RequestMapping("/api/goodsKind")
@Slf4j
public class ControllerGoodsKind {

  @Resource
  private ServiceGoodsKind serviceGoodsKind;

  /**
   * 添加商品类别表。空值将被忽略。可以返回插入后的主键值。
   * @param goodsKind 商品类别表。
   * @return 返回添加的商品类别表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加商品类别表", notes = "添加商品类别表，空字段(null)将被忽略。", response = GoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public GoodsKind insert(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("insert: {}", goodsKind);
    return serviceGoodsKind.insert(request, response, goodsKind);
  }

  /**
   * 同步批量添加商品类别表，同步模式。空值将被忽略。
   * @param goodsKinds 商品类别表列表。
   * @return 返回添加的商品类别表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加商品类别表", notes = "同步批量添加商品类别表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<GoodsKind> goodsKinds) {
    log.info("insertSync: {}", goodsKinds.size());
    return serviceGoodsKind.insertSync(request, response, goodsKinds);
  }

  /**
   * 异步批量添加商品类别表，异步模式。空值将被忽略。
   * @param goodsKinds 商品类别表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加商品类别表", notes = "异步批量添加商品类别表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<GoodsKind> goodsKinds) {
    log.info("insertAsyn: {}", goodsKinds.size());
    serviceGoodsKind.insertAsyn(request, response, goodsKinds);
    return "";
  }

  /**
   * 更新商品类别表。空值将被忽略。
   * @param goodsKind 商品类别表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新商品类别表", notes = "更新商品类别表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("update: {}", goodsKind);
    return serviceGoodsKind.update(request, response, goodsKind);
  }

  /**
   * 更新商品类别表。空值将被更新为 null。
   * @param goodsKind 商品类别表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新商品类别表空值", notes = "更新商品类别表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("updateEvenNull: {}", goodsKind);
    return serviceGoodsKind.updateEvenNull(request, response, goodsKind);
  }


  /**
   * 根据主键删除一条商品类别表。
   * @param id 商品编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条商品类别表", notes = "根据主键删除一条商品类别表。", response = Integer.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceGoodsKind.delete(request, response, id);
  }

  /**
   * 根据条件删除商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除商品类别表", notes = "根据条件删除商品类别表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("delete: {}", goodsKind);
    return serviceGoodsKind.delete(request, response, goodsKind);
  }

  /**
   * 根据主键查询一条商品类别表。
   * @param id 商品编号。
   * @return 返回商品类别表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条商品类别表", notes = "根据主键查询一条商品类别表。", response = GoodsKind.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public GoodsKind selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceGoodsKind.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条商品类别表", notes = "根据条件查询一条商品类别表。", response = GoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public GoodsKind selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("selectOne: {}", goodsKind);
    return serviceGoodsKind.selectOne(request, response, goodsKind);
  }

  /**
   * 根据条件查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条商品类别表详情", notes = "根据条件查询一条商品类别表详情。", response = DetailGoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailGoodsKind selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("selectDetail: {}", goodsKind);
    return serviceGoodsKind.selectDetail(request, response, goodsKind);
  }

  /**
   * 根据主键查询一条商品类别表详情。
   * @param id 商品编号。
   * @return 返回商品类别表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条商品类别表详情", notes = "根据主键查询一条商品类别表详情。", response = DetailGoodsKind.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailGoodsKind selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceGoodsKind.selectDetailByPrimary(request, response, id);
  }


  /**
   * 查询商品类别表列表。返回所有符合条件的商品类别表，未分页。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询商品类别表列表", notes = "查询商品类别表列表，返回所有符合条件的商品类别表，未分页。", response = GoodsKind.class, responseContainer = "List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<GoodsKind> select(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("select: {}", goodsKind);
    return serviceGoodsKind.select(request, response, goodsKind);
  }

  /**
   * 综合查询商品类别表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoodsKind 查询的参数。
   * @return 返回商品类别表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询商品类别表列表", notes = "综合查询商品类别表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepGoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepGoodsKind selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqGoodsKind reqGoodsKind) {
    log.info("selectRelative: {}", reqGoodsKind);
    return serviceGoodsKind.selectRelative(request, response, reqGoodsKind);
  }


  /**
   * 根据类别名称更新一条商品类别表，此方法不适用根据类别名称更改类别名称的字段值。
   * @param goodsKind 商品类别表。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "根据类别名称更新一条商品类别表", notes = "根据类别名称更新一条商品类别表，此方法不适用根据类别名称更改类别名称的字段值。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateByName")
  public int updateByName(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("updateByName. goodsKind: {}", goodsKind);
    return serviceGoodsKind.updateByName(request, response, goodsKind);
  }

  /**
   * 根据类别名称删除一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 121)
  @ApiOperation(value = "根据类别名称删除一条商品类别表", notes = "根据类别名称删除一条商品类别表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "deleteByName")
  public int deleteByName(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("deleteByName. goodsKind: {}", goodsKind);
    return serviceGoodsKind.deleteByName(request, response, goodsKind);
  }

  /**
   * 根据类别名称查询一条商品类别表。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  @ApiOperationSupport(order = 122)
  @ApiOperation(value = "根据类别名称查询一条商品类别表", notes = "根据类别名称查询一条商品类别表。", response = GoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectByName")
  public GoodsKind selectByName(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("selectByName: {}", goodsKind);
    return serviceGoodsKind.selectByName(request, response, goodsKind);
  }

  /**
   * 根据类别名称查询一条商品类别表详情。
   * @param goodsKind 商品类别表。
   * @return 返回商品类别表。
   */
  @ApiOperationSupport(order = 123)
  @ApiOperation(value = "根据类别名称查询一条商品类别表详情", notes = "根据类别名称查询一条商品类别表详情。", response = DetailGoodsKind.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByName")
  public DetailGoodsKind selectDetailByName(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsKind goodsKind) {
    log.info("selectDetailByName: {}", goodsKind);
    return serviceGoodsKind.selectDetailByName(request, response, goodsKind);
  }


  /**
   * 导出商品类别表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepGoodsKind.GoodsKindRow 中获取，reqParam 为 ReqGoodsKind 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出商品类别表", notes = "导出商品类别表到 excel。其中 fields 从 selectRelative 接口的 RepGoodsKind.GoodsKindRow 中获取，reqParam 为 ReqGoodsKind 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceGoodsKind.export(request, response, paramExport);
  }

}