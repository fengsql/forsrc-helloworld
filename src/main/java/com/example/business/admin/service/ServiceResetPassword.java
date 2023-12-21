package com.example.business.admin.service;

import com.forsrc.common.exception.CommonException;
import com.example.business.admin.define.ReqResetPassword;
import com.example.common.spring.base.BaseService;
import com.example.mvc.model.User;
import com.example.mvc.service.ServiceUser;
import com.example.security.service.SecurityPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceResetPassword extends BaseService {
  private static final String password_default = "123456";

  @Resource
  private ServiceUser serviceUser;
  @Resource
  private SecurityPasswordEncoder passwordEncoder;

  public void resetPassword(HttpServletRequest request, HttpServletResponse response, ReqResetPassword reqResetPassword) {
    Integer id = reqResetPassword.getId();
    if (id == null || id <= 0) {
      throw new CommonException("用户编号无效!");
    }
    User user = new User();
    user.setId(id);
    user.setPassword(passwordEncoder.encode(password_default));
    serviceUser.update(user);
  }

}