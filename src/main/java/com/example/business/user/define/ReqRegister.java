package com.example.business.user.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户注册请求参数", description = "用户注册请求参数。")
@Data
public class ReqRegister {

  /**
   * 用户名
   */
  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  /**
   * 用户密码
   */
  @ApiModelProperty(value = "用户密码", name = "password", dataType = "String", required = true)
  private String password;
  /**
   * 用户密码重复
   */
  @ApiModelProperty(value = "用户密码重复", name = "password_again", dataType = "String", required = true)
  private String password_again;

  /**
   * 电子邮件
   */
  @ApiModelProperty(value = "电子邮件", name = "email", dataType = "String", required = false)
  private String email;

  /**
   * 积分
   */
  @ApiModelProperty(value = "积分", name = "score", dataType = "Integer", required = false)
  private Integer score;

  /**
   * 头像
   */
  @ApiModelProperty(value = "头像", name = "headImgUrl", dataType = "String", required = false)
  private String headImgUrl;

  /**
   * 说明
   */
  @ApiModelProperty(value = "说明", name = "info", dataType = "String", required = false)
  private String info;

  /**
   * 验证码
   */
  @ApiModelProperty(value = "验证码", name = "verifyCode_", dataType = "String", required = true)
  private String verifyCode_;

}