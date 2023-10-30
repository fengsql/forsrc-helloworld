package com.example.business.common.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.common.spring.base.BaseService;
import com.example.common.tool.ToolUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceVerifyCode extends BaseService {

  // <<----------------------- public -----------------------

  @SneakyThrows
  public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
    LineCaptcha captcha = CaptchaUtil.createLineCaptcha(80, 30, 4, 50);
    captcha.write(response.getOutputStream());
    String verifyCode = captcha.getCode();
    ToolUser.setVerifyCode(request, verifyCode);
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}