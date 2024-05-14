package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询商户表请求的参数", description = "商户表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqMerchant extends BRequestPage {

  @ApiModelProperty(value = "商户编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer")
  private Integer cityId;

  @ApiModelProperty(value = "市名称(市编号)", name = "cityName", dataType = "String")
  private String cityName;

  @ApiModelProperty(value = "用户编号", name = "userId", dataType = "Integer")
  private Integer userId;

  @ApiModelProperty(value = "角色类型(用户编号)", name = "roleType", dataType = "Integer[]")
  private Integer[] roleType;

  @ApiModelProperty(value = "性别(用户编号)", name = "sexType", dataType = "Integer[]")
  private Integer[] sexType;

  @ApiModelProperty(value = "用户状态(用户编号)", name = "userStatus", dataType = "Integer[]")
  private Integer[] userStatus;

  @ApiModelProperty(value = "用户名(用户编号)", name = "username", dataType = "String")
  private String username;

  @ApiModelProperty(value = "电子邮件(用户编号)", name = "email", dataType = "String")
  private String email;

  @ApiModelProperty(value = "积分(用户编号)", name = "score", dataType = "Long[]")
  private Long[] score;

  @ApiModelProperty(value = "次数(用户编号)", name = "times", dataType = "Integer[]")
  private Integer[] times;

  @ApiModelProperty(value = "头像(用户编号)", name = "headImgUrl", dataType = "String")
  private String headImgUrl;

  @ApiModelProperty(value = "商户类型", name = "merchantType", dataType = "Integer[]")
  private Integer[] merchantType;

  @ApiModelProperty(value = "商户名", name = "mchName", dataType = "String")
  private String mchName;

  @ApiModelProperty(value = "商户号", name = "mchNo", dataType = "String")
  private String mchNo;

  @ApiModelProperty(value = "Appid", name = "appid", dataType = "String")
  private String appid;

  @ApiModelProperty(value = "秘钥", name = "secret", dataType = "String")
  private String secret;

  @ApiModelProperty(value = "联系电话", name = "phone", dataType = "String")
  private String phone;

  @ApiModelProperty(value = "地址", name = "address", dataType = "String")
  private String address;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}