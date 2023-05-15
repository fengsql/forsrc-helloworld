package com.example.business.user.service;

import cn.hutool.core.lang.Validator;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.example.business.user.define.ReqRegister;
import com.example.common.constant.EnumField;
import com.example.common.tool.ToolUser;
import com.example.mvc.dao.DaoUser;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceRegister {
  private static final int size_username = 6;  //用户名最小长度
  private static final int size_password = 6;  //密码最小长度

  @Value("${security.enable-verify-code:false}")
  private Boolean enableVerifyCode;
  @Value("${security.enable-register:false}")
  private Boolean enableRegister;
  @Resource
  private PasswordEncoder passwordEncoder;
  @Resource
  private DaoUser daoUser;

  // <<----------------------- public -----------------------

  public String register(HttpServletRequest request, HttpServletResponse response, ReqRegister reqRegister) {
    checkRegister(reqRegister);
    String verifyCode = reqRegister.getVerifyCode_();
    checkVerifyCode(request, verifyCode);
    String username = reqRegister.getUsername();
    checkUsername(username);
    checkPassword(reqRegister);
    existUser(username);
    register(reqRegister);
    return "ok.";
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- register -----------------------

  private void register(ReqRegister reqRegister) {
    User user = newUser(reqRegister);
    daoUser.insert(user);
  }

  private User newUser(ReqRegister reqRegister) {
    User user = new User();
    if (user.getRoleType() == null) {
      user.setRoleType(EnumField.RoleType.def_.getCode());
    }
    if (user.getSexType() == null) {
      user.setSexType(EnumField.SexType.other_.getCode());
    }
    if (user.getUserStatus() == null) {
      user.setUserStatus(1);
    }
    user.setUsername(reqRegister.getUsername());
    user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
    user.setEmail(reqRegister.getEmail());
    user.setScore(reqRegister.getScore());
    user.setHeadImgUrl(reqRegister.getHeadImgUrl());
    user.setInfo(reqRegister.getInfo());
    return user;
  }

  // >>>----------------------- register -----------------------

  // <<<----------------------- normal -----------------------

  private void checkRegister(ReqRegister reqRegister) {
    if (!enableRegister) {
      throw new CommonException("未开启注册功能!");
    }
    if (enableVerifyCode) {
      if (Tool.isNull(reqRegister.getVerifyCode_())) {
        throw new CommonException("验证码为空!");
      }
    }
    if (Tool.isNull(reqRegister.getUsername())) {
      throw new CommonException("用户名为空!");
    }
    if (Tool.isNull(reqRegister.getPassword())) {
      throw new CommonException("密码为空!");
    }
    if (Tool.isNull(reqRegister.getPassword_again())) {
      throw new CommonException("重复密码为空!");
    }
  }

  private void checkVerifyCode(HttpServletRequest request, String verifyCode) {
    if (enableVerifyCode) {
      ToolUser.checkVerifyCode(request, verifyCode);
    }
  }

  private void checkPassword(ReqRegister reqRegister) {
    String password = reqRegister.getPassword();
    String password_again = reqRegister.getPassword_again();
    if (!password.equals(password_again)) {
      throw new CommonException("密码不一致!");
    }
    if (password.length() < size_password) {
      throw new CommonException("密码不能少于 " + size_password + " 位!");
    }
  }

  private void checkUsername(String username) {
    if (username.length() < size_username) {
      throw new CommonException("用户名不能少于 " + size_username + " 位!");
    }
    if (Validator.isGeneral(username)) {
      return;
    }
    throw new CommonException("用户名格式不正确!");
  }

  private void checkEmail(String email) {
    if (!Validator.isEmail(email)) {
      throw new CommonException("邮箱无效!");
    }
  }

  private void checkMobile(String mobile) {
    if (!Validator.isMobile(mobile)) {
      throw new CommonException("手机号无效!");
    }
  }

  private void existUser(String username) {
    User user = new User();
    user.setUsername(username);
    User user1 = daoUser.selectByUsername(user);
    if (user1 != null) {
      throw new CommonException("这个用户名已经注册!");
    }
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}