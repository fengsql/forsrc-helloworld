package com.example.common.tool;

import com.forsrc.common.constant.Const;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ToolUser {
  private static final int length_secret = 32;

  // <<----------------------- public -----------------------

  public static String getSecret() {
    return Tool.getRandomString(length_secret);
  }

  public static void checkVerifyCode(HttpServletRequest request, String verifyCode) {
    if (Tool.isNull(verifyCode)) {
      throw new CommonException("验证码为空!");
    }
    String code = (String) request.getSession().getAttribute(Const.param_verifyCode);
    if (Tool.isNull(code)) {
      throw new CommonException("没有验证码!");
    }
    if (!verifyCode.trim().toLowerCase().equals(code.toLowerCase())) {
      throw new CommonException("验证码不匹配!");
    }
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}