package com.example.business.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.business.admin.define.ReqResetPassword;
import com.example.business.admin.service.ServiceResetPassword;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "业务-基础-管理员", description = "业务-基础-管理员 API", position = 20000)
@RestController
@RequestMapping("/business/admin")
@Slf4j
public class ControllerAdmin {

  @Resource
  private ServiceResetPassword serviceResetPassword;

  /**
   * 重置用户密码。
   * @param reqResetPassword 重置用户密码的参数。
   */
  @ApiOperationSupport(order = 1)
  @ApiOperation(value = "重置用户密码", notes = "重置用户密码。")
  @RequestMapping(method = RequestMethod.POST, value = "resetPassword")
  public Boolean resetPassword(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqResetPassword reqResetPassword) {
    log.info("resetPassword: {}", reqResetPassword);
    serviceResetPassword.resetPassword(request, response, reqResetPassword);
    return true;
  }

}