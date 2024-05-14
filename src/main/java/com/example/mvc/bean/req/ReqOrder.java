package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询订单表请求的参数", description = "订单表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqOrder extends BRequestPage {

  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer")
  private Integer id;

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

  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Integer[]")
  private Integer[] orderStatus;

  @ApiModelProperty(value = "支付状态", name = "payStatus", dataType = "Integer[]")
  private Integer[] payStatus;

  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer[]")
  private Integer[] amount;

  @ApiModelProperty(value = "成功金额", name = "amountSuccess", dataType = "Integer[]")
  private Integer[] amountSuccess;

  @ApiModelProperty(value = "第三方订单号", name = "thirdOrderNo", dataType = "String")
  private String thirdOrderNo;

  @ApiModelProperty(value = "发送时间", name = "sendTime", dataType = "java.util.Date[]")
  private java.util.Date[] sendTime;

  @ApiModelProperty(value = "支付时间", name = "payTime", dataType = "java.util.Date[]")
  private java.util.Date[] payTime;

  @ApiModelProperty(value = "通知时间", name = "notifyTime", dataType = "java.util.Date[]")
  private java.util.Date[] notifyTime;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}