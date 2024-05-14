package com.example.mvc.event.base;

import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.forsrc.security.model.UserDetail;
import com.forsrc.security.tool.ToolSecurity;
import com.example.common.constant.EnumField;
import com.example.common.spring.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseAuth extends BaseService {

  // <<----------------------- public -----------------------

  // <<<----------------------- common -----------------------

  // >>>----------------------- common -----------------------

  // <<<----------------------- auth -----------------------

  //need
  protected void needDef() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.def_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  protected void needNormal() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.normal_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  protected void needTest() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.test_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  protected void needService() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.service_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  protected void needMaint() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.maint_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  protected void needAdmin() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    if (userDetail == null || userDetail.getRoleType() < EnumField.RoleType.admin_.getCode()) {
      throw new CommonException(Code.AUTHENTICATION_DENY, "没有权限!");
    }
  }

  //is
  protected boolean isDef(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.def_.getCode();
  }

  protected boolean isNormal(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.normal_.getCode();
  }

  protected boolean isTest(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.test_.getCode();
  }

  protected boolean isService(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.service_.getCode();
  }

  protected boolean isMaint(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.maint_.getCode();
  }

  protected boolean isAdmin(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() == EnumField.RoleType.admin_.getCode();
  }

  //isUp
  protected boolean isUpDef(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.def_.getCode();
  }

  protected boolean isUpNormal(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.normal_.getCode();
  }

  protected boolean isUpTest(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.test_.getCode();
  }

  protected boolean isUpService(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.service_.getCode();
  }

  protected boolean isUpMaint(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.maint_.getCode();
  }

  protected boolean isUpAdmin(UserDetail userDetail) {
    return userDetail != null && userDetail.getRoleType() >= EnumField.RoleType.admin_.getCode();
  }

  // >>>----------------------- auth -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- common -----------------------

  // >>>----------------------- common -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}