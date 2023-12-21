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
  private static final String password_default = "123456";
  @Resource
  private ServiceUser serviceUser;
  @Resource
  private SecurityPasswordEncoder passwordEncoder;

  public void updatePassword(HttpServletRequest request, HttpServletResponse response, ReqUpdatePassword reqUpdatePassword) {
    throwNull(request, response);
    throwNull(reqUpdatePassword, "param");
    throwNull(reqUpdatePassword.getOldPassword(), "old password");
    throwNull(reqUpdatePassword.getNewPassword(), "new password");
    User user = getUser();

    checkPassword(user, reqUpdatePassword.getOldPassword());
    updatePassword(user, reqUpdatePassword.getNewPassword());
  }

  private void updatePassword(User user, String newPassword) {
    user.setPassword(passwordEncoder.encode(newPassword));
    serviceUser.update(user);
  }

  private void checkPassword(User user, String oldPassword) {
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new CommonException(Code.USER_CREDENTIALS_ERROR);
    }
  }

}