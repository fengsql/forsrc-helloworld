package com.example.business.user.define;

import com.example.mvc.model.User;
import com.example.mvc.service.ServiceUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户注册请求参数", description = "用户注册请求参数。")
@Data
public class ReqRegister {

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "验证码", name = "verifyCode", dataType = "String", required = true)
  private String verifyCode;

  @ApiModelProperty(value = "密码", name = "password", dataType = "String", required = true)
  private String password;

  @ApiModelProperty(value = "重复密码", name = "passwordAgain", dataType = "String", required = true)
  private String passwordAgain;


}