package com.example.mvc.bean.detail;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户详情", description = "用户")
@Data
public class DetailUser {

  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Long", required = true)
  private Long id;

  @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Byte", required = true)
  private Byte roleType;

  @ApiModelProperty(value = "角色类型", name = "roleType_", dataType = "String", required = true)
  private String roleType_;

  @ApiModelProperty(value = "性别", name = "sexType", dataType = "Byte", required = true)
  private Byte sexType;

  @ApiModelProperty(value = "性别", name = "sexType_", dataType = "String", required = true)
  private String sexType_;

  @ApiModelProperty(value = "用户状态", name = "userStatus", dataType = "Byte", required = true)
  private Byte userStatus;

  @ApiModelProperty(value = "用户状态", name = "userStatus_", dataType = "String", required = true)
  private String userStatus_;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "电子邮件", name = "email", dataType = "String", required = false)
  private String email;

  @ApiModelProperty(value = "积分", name = "score", dataType = "Integer", required = false)
  private Integer score;

  @ApiModelProperty(value = "出生日期", name = "birthDate", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date birthDate;

  @ApiModelProperty(value = "头像", name = "headImgUrl", dataType = "String", required = false)
  private String headImgUrl;

  @ApiModelProperty(value = "说明", name = "info", dataType = "String", required = false)
  private String info;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

}