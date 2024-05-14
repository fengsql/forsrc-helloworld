package com.example.mvc.controller;

import com.forsrc.common.annotation.RequestSingle;
import com.forsrc.common.extend.bean.ParamExport;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.mvc.bean.detail.DetailUser;
import com.example.mvc.bean.rep.RepUser;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.model.User;
import com.example.mvc.service.ServiceUser;
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
import com.example.mvc.event.auth.AuthUser;

@Api(tags = "用户", description = "用户相关的 API", position = 1)
@RestController
@RequestMapping("/api/user")
@Slf4j
public class ControllerUser {

  @Resource
  private AuthUser authUser;
  @Resource
  private ServiceUser serviceUser;

  /**
   * 添加用户。空值将被忽略。可以返回插入后的主键值。
   * @param user 用户。
   * @return 返回添加的用户。
   */
  @ApiOperationSupport(order = 10)
  @ApiOperation(value = "添加用户", notes = "添加用户，空字段(null)将被忽略。", response = User.class)
  @RequestMapping(method = RequestMethod.POST, value = "insert")
  public User insert(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("insert: {}", user);
    authUser.onInsert(request, response, user);
    return serviceUser.insert(request, response, user);
  }

  /**
   * 同步批量添加用户，同步模式。空值将被忽略。
   * @param users 用户列表。
   * @return 返回添加的用户数。
   */
  @ApiOperationSupport(order = 20)
  @ApiOperation(value = "同步批量添加用户", notes = "同步批量添加用户，空字段(null)将被忽略。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertSync")
  public int insertSync(HttpServletRequest request, HttpServletResponse response, @RequestBody List<User> users) {
    log.info("insertSync: {}", users.size());
    authUser.onInsert(request, response, users);
    return serviceUser.insertSync(request, response, users);
  }

  /**
   * 异步批量添加用户，异步模式。空值将被忽略。
   * @param users 用户列表。
   * @return 无返回。
   */
  @ApiOperationSupport(order = 30)
  @ApiOperation(value = "异步批量添加用户", notes = "异步批量添加用户，空字段(null)将被忽略。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "insertAsyn")
  public String insertAsyn(HttpServletRequest request, HttpServletResponse response, @RequestBody List<User> users) {
    log.info("insertAsyn: {}", users.size());
    authUser.onInsert(request, response, users);
    serviceUser.insertAsyn(request, response, users);
    return "";
  }

  /**
   * 更新用户。空值将被忽略。
   * @param user 用户。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新用户", notes = "更新用户，不更新空字段(null)。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "update")
  public int update(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("update: {}", user);
    authUser.onUpdate(request, response, user);
    return serviceUser.update(request, response, user);
  }

  /**
   * 更新用户。空值将被更新为 null。
   * @param user 用户。
   * @return 返回更新的记录数。
   */
  @ApiOperationSupport(order = 40)
  @ApiOperation(value = "更新用户空值", notes = "更新用户，空值将被更新为 null。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateEvenNull")
  public int updateEvenNull(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("updateEvenNull: {}", user);
    authUser.onUpdate(request, response, user);
    return serviceUser.updateEvenNull(request, response, user);
  }


  /**
   * 根据主键删除一条用户。
   * @param id 用户编号。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 50)
  @ApiOperation(value = "删除一条用户", notes = "根据主键删除一条用户。", response = Integer.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "用户编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "deleteByPrimary")
  public int deleteByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("deleteByPrimary: {}", id);
    User user = new User();
    user.setId(id);
    authUser.onDelete(request, response, user);
    return serviceUser.delete(request, response, id);
  }

  /**
   * 根据条件删除用户。
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 60)
  @ApiOperation(value = "删除用户", notes = "根据条件删除用户。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "delete")
  public int delete(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("delete: {}", user);
    authUser.onDelete(request, response, user);
    return serviceUser.delete(request, response, user);
  }

  /**
   * 根据主键查询一条用户。
   * @param id 用户编号。
   * @return 返回用户。
   */
  @ApiOperationSupport(order = 70)
  @ApiOperation(value = "根据主键查询一条用户", notes = "根据主键查询一条用户。", response = User.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "用户编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectByPrimary")
  public User selectByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectByPrimary: {}", id);
    User user = new User();
    user.setId(id);
    authUser.onSelect(request, response, user);
    return serviceUser.selectByPrimary(request, response, id);
  }

  /**
   * 根据条件查询一条用户。
   * @param user 用户。
   * @return 返回用户。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条用户", notes = "根据条件查询一条用户。", response = User.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectOne")
  public User selectOne(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("selectOne: {}", user);
    authUser.onSelect(request, response, user);
    return serviceUser.selectOne(request, response, user);
  }

  /**
   * 根据条件查询一条用户详情。
   * @param user 用户。
   * @return 返回用户详情。
   */
  @ApiOperationSupport(order = 80)
  @ApiOperation(value = "根据条件查询一条用户详情", notes = "根据条件查询一条用户详情。", response = DetailUser.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetail")
  public DetailUser selectDetail(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("selectDetail: {}", user);
    authUser.onSelectDetail(request, response, user);
    return serviceUser.selectDetail(request, response, user);
  }

  /**
   * 根据主键查询一条用户详情。
   * @param id 用户编号。
   * @return 返回用户详情。
   */
  @ApiOperationSupport(order = 90)
  @ApiOperation(value = "根据主键查询一条用户详情", notes = "根据主键查询一条用户详情。", response = DetailUser.class)
  @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "Integer", name = "id", value = "用户编号", required = true)})
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByPrimary")
  public DetailUser selectDetailByPrimary(HttpServletRequest request, HttpServletResponse response, @RequestSingle(value = "id") Integer id) {
    log.info("selectDetailByPrimary: {}", id);
    User user = new User();
    user.setId(id);
    authUser.onSelectDetail(request, response, user);
    return serviceUser.selectDetailByPrimary(request, response, id);
  }


  /**
   * 查询用户列表。返回所有符合条件的用户，未分页。
   * @param user 用户。
   * @return 返回用户列表。
   */
  @ApiOperationSupport(order = 100)
  @ApiOperation(value = "查询用户列表", notes = "查询用户列表，返回所有符合条件的用户，未分页。", response = User.class, responseContainer = "List")
  @RequestMapping(method = RequestMethod.POST, value = "select")
  public List<User> select(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("select: {}", user);
    authUser.onSelect(request, response, user);
    return serviceUser.select(request, response, user);
  }

  /**
   * 综合查询用户列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。
   * @param reqUser 查询的参数。
   * @return 返回用户列表。
   */
  @ApiOperationSupport(order = 110)
  @ApiOperation(value = "综合查询用户列表", notes = "综合查询用户列表，综合查询将查询出关联表的字段。可以设置分页，查询第一页将返回符合条件的总记录数。", response = RepUser.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectRelative")
  public RepUser selectRelative(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqUser reqUser) {
    log.info("selectRelative: {}", reqUser);
    authUser.onSelectRelative(request, response, reqUser);
    return serviceUser.selectRelative(request, response, reqUser);
  }


  /**
   * 根据用户名更新一条用户，此方法不适用根据用户名更改用户名的字段值。
   * @param user 用户。
   * @return 0为失败；大于0为成功，返回更新的记录数。
   */
  @ApiOperationSupport(order = 120)
  @ApiOperation(value = "根据用户名更新一条用户", notes = "根据用户名更新一条用户，此方法不适用根据用户名更改用户名的字段值。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "updateByUsername")
  public int updateByUsername(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("updateByUsername. user: {}", user);
    authUser.onUpdate(request, response, user);
    return serviceUser.updateByUsername(request, response, user);
  }

  /**
   * 根据用户名删除一条用户。
   * @param user 用户。
   * @return 返回删除的记录数。
   */
  @ApiOperationSupport(order = 121)
  @ApiOperation(value = "根据用户名删除一条用户", notes = "根据用户名删除一条用户。", response = Integer.class)
  @RequestMapping(method = RequestMethod.POST, value = "deleteByUsername")
  public int deleteByUsername(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("deleteByUsername. user: {}", user);
    authUser.onDelete(request, response, user);
    return serviceUser.deleteByUsername(request, response, user);
  }

  /**
   * 根据用户名查询一条用户。
   * @param user 用户。
   * @return 返回用户。
   */
  @ApiOperationSupport(order = 122)
  @ApiOperation(value = "根据用户名查询一条用户", notes = "根据用户名查询一条用户。", response = User.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectByUsername")
  public User selectByUsername(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("selectByUsername: {}", user);
    authUser.onSelect(request, response, user);
    return serviceUser.selectByUsername(request, response, user);
  }

  /**
   * 根据用户名查询一条用户详情。
   * @param user 用户。
   * @return 返回用户。
   */
  @ApiOperationSupport(order = 123)
  @ApiOperation(value = "根据用户名查询一条用户详情", notes = "根据用户名查询一条用户详情。", response = DetailUser.class)
  @RequestMapping(method = RequestMethod.POST, value = "selectDetailByUsername")
  public DetailUser selectDetailByUsername(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
    log.info("selectDetailByUsername: {}", user);
    authUser.onSelectDetail(request, response, user);
    return serviceUser.selectDetailByUsername(request, response, user);
  }


  /**
   * 导出用户到 excel。
   * @param paramExport 导出的参数。其中 fields 从 selectRelative 接口的 RepUser.UserRow 中获取，reqParam 为 ReqUser 对象。
   */
  @ApiOperationSupport(order = 200)
  @ApiOperation(value = "导出用户", notes = "导出用户到 excel。其中 fields 从 selectRelative 接口的 RepUser.UserRow 中获取，reqParam 为 ReqUser 对象。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ParamExport paramExport) {
    log.info("export: {}", paramExport);
    authUser.onExport(request, response, paramExport);
    serviceUser.export(request, response, paramExport);
  }

}