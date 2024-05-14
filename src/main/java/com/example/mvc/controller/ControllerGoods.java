package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailGoods;
import com.example.mvc.bean.rep.RepGoods;
import com.example.mvc.bean.req.ReqGoods;
import com.example.mvc.model.Goods;
import com.example.mvc.service.ServiceGoods;
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

@Api(tags = "商品表", description = "商品表相关的 API", position = 1)
@RestController
@RequestMapping("/api/goods")
@Slf4j
public class ControllerGoods {

  @Resource
  private ServiceGoods serviceGoods;

  /**
   * 添加商品表。空值将被忽略。可以返回插入后的主键值。
   * @param goods 商品表。
   * @return 返回添加的商品表。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加商品表", notes = "添加商品表，空字段(null)将被忽略。", response = Goods.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public Goods insert(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("insert: {}", goods);
    return serviceGoods.insert(request, response, goods);
  }

  /**
   * 同步批量添加商品表，同步模式。空值将被忽略。
   * @param goodss 商品表列表。
   * @return 返回添加的商品表数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加商品表", notes = "同步批量添加商品表，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Goods> goodss) {
    log.info("insertSync: {}", goodss.size());
    return serviceGoods.insertSync(request, response, goodss);
  }

  /**
   * 异步批量添加商品表，异步模式。空值将被忽略。
   * @param goodss 商品表列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加商品表", notes = "异步批量添加商品表，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<Goods> goodss) {
    log.info("insertAsyn: {}", goodss.size());
    serviceGoods.insertAsyn(request, response, goodss);
    return "";
  }

  /**
   * 更新商品表。空值将被忽略。
   * @param goods 商品表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新商品表", notes = "更新商品表，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("update: {}", goods);
    return serviceGoods.update(request, response, goods);
  }

  /**
   * 更新商品表。空值将被更新为 null。
   * @param goods 商品表。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新商品表空值", notes = "更新商品表，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("updateEvenNull: {}", goods);
    return serviceGoods.updateEvenNull(request, response, goods);
  }


  /**
   * 根据主键删除一条商品表。
   * @param id 商品编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条商品表", notes = "根据主键删除一条商品表。", response = Integer.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    return serviceGoods.delete(request, response, id);
  }

  /**
   * 根据条件删除商品表。
   * @param goods 商品表。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除商品表", notes = "根据条件删除商品表。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("delete: {}", goods);
    return serviceGoods.delete(request, response, goods);
  }

  /**
   * 根据主键查询一条商品表。
   * @param id 商品编号。
   * @return 返回商品表。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条商品表", notes = "根据主键查询一条商品表。", response = Goods.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public Goods selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    return serviceGoods.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条商品表。
   * @param goods 商品表。
   * @return 返回商品表。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条商品表", notes = "根据条件查询一条商品表。", response = Goods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public Goods selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("selectOne: {}", goods);
    return serviceGoods.selectOne(request, response, goods);
  }

  /**
   * 根据条件查询一条商品表详情。
   * @param goods 商品表。
   * @return 返回商品表详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条商品表详情", notes = "根据条件查询一条商品表详情。", response = DetailGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailGoods selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("selectDetail: {}", goods);
    return serviceGoods.selectDetail(request, response, goods);
  }

  /**
   * 根据主键查询一条商品表详情。
   * @param id 商品编号。
   * @return 返回商品表详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条商品表详情", notes = "根据主键查询一条商品表详情。", response = DetailGoods.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "商品编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailGoods selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    return serviceGoods.selectDetailByPrimary(request, response, id);
  }


  /**
   * 查询商品表列表。返回所有符合条件的商品表，未分页。
   * @param goods 商品表。
   * @return 返回商品表列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询商品表列表", notes = "查询商品表列表，返回所有符合条件的商品表，未分页。", response = Goods.class, responseContainer = "List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<Goods> select(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) {
    log.info("select: {}", goods);
    return serviceGoods.select(request, response, goods);
  }

  /**
   * 综合查询商品表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqGoods 查询的参数。
   * @return 返回商品表列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询商品表列表", notes = "综合查询商品表列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepGoods.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepGoods selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqGoods reqGoods) {
    log.info("selectRelative: {}", reqGoods);
    return serviceGoods.selectRelative(request, response, reqGoods);
  }


  /**
   * 导出商品表到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepGoods.GoodsRow 中获取，reqParam 为 ReqGoods 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出商品表", notes = "导出商品表到 excel。其中 fields 从 selectRelative 接口的 RepGoods.GoodsRow 中获取，reqParam 为 ReqGoods 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    serviceGoods.export(request, response, paramExport);
  }

}