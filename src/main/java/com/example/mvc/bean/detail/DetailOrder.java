package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单表详情", description = "订单表")
@Data
public class DetailOrder {

  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Byte", required = true)
  private Byte orderStatus;

  @ApiModelProperty(value = "订单状态", name = "orderStatus_", dataType = "String", required = true)
  private String orderStatus_;

  @ApiModelProperty(value = "支付状态", name = "payStatus", dataType = "Byte", required = true)
  private Byte payStatus;

  @ApiModelProperty(value = "支付状态", name = "payStatus_", dataType = "String", required = true)
  private String payStatus_;

  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer", required = true)
  private Integer amount;

  @ApiModelProperty(value = "成功金额", name = "amountSuccess", dataType = "Integer", required = false)
  private Integer amountSuccess;

  @ApiModelProperty(value = "第三方订单号", name = "thirdOrderNo", dataType = "String", required = false)
  private String thirdOrderNo;

  @ApiModelProperty(value = "发送时间", name = "sendTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date sendTime;

  @ApiModelProperty(value = "支付时间", name = "payTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date payTime;

  @ApiModelProperty(value = "通知时间", name = "notifyTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date notifyTime;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}