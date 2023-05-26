package com.example.security.entity;

import com.forsrc.security.base.BLoginResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录的返回信息。可以自定义返回数据。
 */
@ApiModel(value = "用户登录的返回信息", description = "用户登录的返回信息。")
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BLoginResponse {

  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer", required = true)
  private int id;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Integer", required = true)
  private int roleType;

  @ApiModelProperty(value = "角色类型名称", name = "roleName_", dataType = "String", required = true)
  private String roleName_;

}