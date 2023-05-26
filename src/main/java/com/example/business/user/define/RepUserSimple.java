package com.example.business.user.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "获取用户简单信息返回参数", description = "获取用户简单信息返回参数。")
@Data
public class RepUserSimple {

  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer", required = true)
  private int id;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Integer", required = true)
  private int roleType;


}