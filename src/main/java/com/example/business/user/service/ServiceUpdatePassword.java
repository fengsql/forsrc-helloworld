package com.example.business.user.service;

import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.example.business.user.define.ReqUpdatePassword;
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
public class ServiceUpdatePassword extends BaseService {

  @Resource
  private ServiceUser serviceUser;
  @Resource
  private SecurityPasswordEncoder passwordEncoder;

  public void updatePassword(HttpServletRequest request, HttpServletResponse response, ReqUpdatePassword reqUpdatePassword) {
    throwNull(request, response);
    throwNull(reqUpdatePassword, "param");
    throwNull(reqUpdatePassword.getOldPassword(), "old password");
    throwNull(reqUpdatePassword.getNewPassword(), "new password");

    User user = getUser(request, response);
    throwNull(user, "user");

    checkPassword(user, reqUpdatePassword.getOldPassword());
    updatePassword(request, response, user, reqUpdatePassword.getNewPassword());
  }

  private void updatePassword(HttpServletRequest request, HttpServletResponse response, User user, String newPassword) {
    user.setPassword(newPassword);
    serviceUser.update(request, response, user);
  }

  private void checkPassword(User user, String oldPassword) {
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new CommonException(Code.USER_CREDENTIALS_ERROR);
    }
  }

  private User getUser(HttpServletRequest request, HttpServletResponse response) {
    Integer userId = getUserId();
    return serviceUser.selectByPrimary(request, response, userId);
  }

}