package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单表", description = "订单表")
@Data
public class Order {
  /**
   * 订单编号
   */
  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号", name = "userId", dataType = "Integer", required = true)
  private Integer userId;

  /**
   * 订单状态
   */
  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Integer", required = true)
  private Integer orderStatus;

  /**
   * 支付状态
   */
  @ApiModelProperty(value = "支付状态", name = "payStatus", dataType = "Integer", required = true)
  private Integer payStatus;

  /**
   * 金额数
   */
  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer", required = true)
  private Integer amount;

  /**
   * 成功金额
   */
  @ApiModelProperty(value = "成功金额", name = "amountSuccess", dataType = "Integer", required = false)
  private Integer amountSuccess;

  /**
   * 第三方订单号
   */
  @ApiModelProperty(value = "第三方订单号", name = "thirdOrderNo", dataType = "String", required = false)
  private String thirdOrderNo;

  /**
   * 发送时间
   */
  @ApiModelProperty(value = "发送时间", name = "sendTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date sendTime;

  /**
   * 支付时间
   */
  @ApiModelProperty(value = "支付时间", name = "payTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date payTime;

  /**
   * 通知时间
   */
  @ApiModelProperty(value = "通知时间", name = "notifyTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date notifyTime;

  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

  /**
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}