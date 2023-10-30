package com.example.business.user.service;

import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.forsrc.security.model.UserDetail;
import com.forsrc.security.tool.ToolToken;
import com.example.business.user.define.RepUserSimple;
import com.example.common.spring.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceUser extends BaseService {

  // <<----------------------- public -----------------------

  public RepUserSimple getSimple(HttpServletRequest request, HttpServletResponse response) {
    UserDetail userDetail = getUserDetail();
    return getRepUserSimple(userDetail);
  }

  public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
    String token = ToolToken.refreshToken(request);
    if (Tool.isNull(token)) {
      throw new CommonException(Code.AUTHENTICATION_EXCEPTION);
    }
    return token;
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- getSimple -----------------------

  private RepUserSimple getRepUserSimple(UserDetail userDetail) {
    RepUserSimple repUserSimple = new RepUserSimple();
    repUserSimple.setId(Tool.toInteger(userDetail.getUserId()));
    repUserSimple.setUsername(userDetail.getUsername());
    repUserSimple.setRoleType(userDetail.getRoleType());
    return repUserSimple;
  }

  // >>>----------------------- getSimple -----------------------

  // <<<----------------------- check -----------------------

  // >>>----------------------- check -----------------------

  // <<<----------------------- normal -----------------------

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}