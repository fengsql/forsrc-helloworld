package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "每日订单统计详情", description = "每日订单统计")
@Data
public class DetailStatDayOrder {

  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Byte", required = true)
  private Byte orderStatus;

  @ApiModelProperty(value = "订单状态", name = "orderStatus_", dataType = "String", required = true)
  private String orderStatus_;

  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer", required = true)
  private Integer dayNo;

  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer", required = false)
  private Integer amount;

  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer", required = false)
  private Integer orderNum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}