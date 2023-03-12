package com.example.business.user.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "更改密码请求参数", description = "更改密码请求参数")
@Data
public class ReqUpdatePassword {

  @ApiModelProperty(value = "旧的密码", name = "oldPassword", dataType = "String", required = true)
  private String oldPassword;

  @ApiModelProperty(value = "新的密码", name = "newPassword", dataType = "Long", required = true)
  private String newPassword;

}