package com.example.security.service;

import com.forsrc.common.exception.CommonException;
import com.forsrc.common.tool.Tool;
import com.forsrc.security.base.BLoginResponse;
import com.forsrc.security.base.BServiceUserDetails;
import com.forsrc.security.base.IUserDetails;
import com.forsrc.security.model.UserDetail;
import com.example.common.constant.EnumField;
import com.example.mvc.model.User;
import com.example.mvc.service.ServiceUser;
import com.example.security.entity.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SecurityServiceUserDetails extends BServiceUserDetails<User> {

  @Resource
  private ServiceUser serviceUser;

  @Override
  public IUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = new User();
    user.setUsername(Tool.toString(username));
    user = serviceUser.selectByUsername(user);
    if (user == null) {
      throw new UsernameNotFoundException("not find username '" + username + "'");
    }
    return getUserDetails(user);
  }

  /**
   * 创建用户信息，默认使用 UserDetail，可以使用自定义用户对象覆盖此方法。
   * @return 返回用户对象。
   */
  @Override
  protected IUserDetails createUserDetails() {
    return new UserDetail();
  }

  /**
   * 使用用户信息 user 填充用户对象 userDetails。注意：
   * 1、必须设置用户密码以验证用户登录。
   * 2、如果设置了 userId, username, roleType，则 token 中包含这些信息，否则从 token 中获取不了这些信息。
   * @param userDetails 用户对象。
   * @param user        用户信息。
   */
  @Override
  protected void setUserDetails(IUserDetails userDetails, User user) {
    UserDetail userDetail = (UserDetail) userDetails;
    userDetail.setUserId(user.getId());
    userDetail.setRoleType(user.getRoleType());
    userDetail.setUsername(user.getUsername());
    userDetail.setPassword(user.getPassword());
  }

  /**
   * 返回登录信息，可以使用自定义登录信息覆盖此方法。
   * @return 返回用户对象。
   */
  @Override
  protected BLoginResponse getLoginResponse(User user) {
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setId(user.getId());
    loginResponse.setUsername(user.getUsername());
    loginResponse.setRoleType(user.getRoleType());
    loginResponse.setRoleName_(getRoleName(user));
    return loginResponse;
  }

  /**
   * 获取用户角色，注意角色名称必须与 security.role 中配置的名称保持一致，可以为数字。
   * @param user 用户信息。
   * @return 返回用户角色列表。
   */
  @Override
  protected List<String> getRoles(User user) {
    List<String> roles = new ArrayList<>();
    roles.add(getRoleName(user));
    return roles;
  }

  /**
   * 获取用户角色，注意角色名称必须与 security.role 中配置的名称保持一致，可以为数字。
   * @param user 用户信息。
   * @return 返回角色名称。
   */
  private String getRoleName(User user) {
    int value = Tool.toInt(user.getRoleType());
    EnumField.RoleType roleType = EnumField.RoleType.get(value);
    return roleType == null ? null : roleType.getName();
  }

}