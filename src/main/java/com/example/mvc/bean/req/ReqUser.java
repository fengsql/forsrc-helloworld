package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询用户请求的参数", description = "用户", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqUser extends BRequestPage {

  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Integer[]")
  private Integer[] roleType;

  @ApiModelProperty(value = "性别", name = "sexType", dataType = "Integer[]")
  private Integer[] sexType;

  @ApiModelProperty(value = "用户状态", name = "userStatus", dataType = "Integer[]")
  private Integer[] userStatus;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String")
  private String username;

  @ApiModelProperty(value = "电子邮件", name = "email", dataType = "String")
  private String email;

  @ApiModelProperty(value = "积分", name = "score", dataType = "Long[]")
  private Long[] score;

  @ApiModelProperty(value = "次数", name = "times", dataType = "Integer[]")
  private Integer[] times;

  @ApiModelProperty(value = "出生日期", name = "birthDate", dataType = "java.util.Date[]")
  private java.util.Date[] birthDate;

  @ApiModelProperty(value = "头像", name = "headImgUrl", dataType = "String")
  private String headImgUrl;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}