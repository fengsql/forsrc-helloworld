package com.example.business.user.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.example.business.common.service.ServiceVerifyCode;
import com.example.business.user.define.ReqRegister;
import com.example.business.user.service.ServiceRegister;
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
import java.io.IOException;

@Api(tags = "扩展功能-用户注册", description = "扩展功能-用户注册 API", position = 10000)
@RestController
@RequestMapping("/business/user/register")
@Slf4j
public class ControllerRegister {

  @Resource
  private ServiceRegister serviceRegister;

  @Resource
  private ServiceVerifyCode serviceVerifyCode;

  /**
   * 获取图片验证码。
   */
  @ApiOperationSupport(order = 2)
  @ApiOperation(value = "获取图片验证码", notes = "获取图片验证码。")
  @RequestMapping(method = RequestMethod.GET, value = "getVerifyCode")
  public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
    serviceVerifyCode.getVerifyCode(request, response);
  }

  /**
   * 用户注册。
   * @param reqRegister 参数。
   */
  @ApiOperationSupport(order = 1)
  @ApiOperation(value = "用户注册", notes = "用户注册。")
  @RequestMapping(method = RequestMethod.POST, value = "register")
  public String register(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqRegister reqRegister) {
    log.info("register: {}", reqRegister);
    return serviceRegister.register(request, response, reqRegister);
  }

}