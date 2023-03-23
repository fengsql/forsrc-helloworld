package com.example.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户", description = "用户")
@Data
public class User {
  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 角色类型
   */
  @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Integer", required = true)
  private Integer roleType;

  /**
   * 性别
   */
  @ApiModelProperty(value = "性别", name = "sexType", dataType = "Integer", required = true)
  private Integer sexType;

  /**
   * 用户状态
   */
  @ApiModelProperty(value = "用户状态", name = "userStatus", dataType = "Integer", required = true)
  private Integer userStatus;

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
   * 出生日期
   */
  @ApiModelProperty(value = "出生日期", name = "birthDate", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date birthDate;

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
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

}