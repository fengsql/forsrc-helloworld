package com.example.business.user.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.business.user.define.ReqUpdatePassword;
import com.example.business.user.service.ServiceUpdatePassword;
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

@Api(tags = "扩展功能-用户", description = "扩展功能用户 API", position = 20000)
@RestController
@RequestMapping("/business/account")
@Slf4j
public class ControllerAccount {

  @Resource
  private ServiceUpdatePassword serviceUpdatePassword;

  /**
   * 用户更改密码。
   * @param reqUpdatePassword 更改密码的参数。
   */
  @ApiOperationSupport(order = 1)
  @ApiOperation(value = "更改密码", notes = "用户更改密码。")
  @RequestMapping(method = RequestMethod.POST, value = "updatePassword")
  public Boolean updatePassword(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqUpdatePassword reqUpdatePassword) {
    serviceUpdatePassword.updatePassword(request, response, reqUpdatePassword);
    return true;
  }

}