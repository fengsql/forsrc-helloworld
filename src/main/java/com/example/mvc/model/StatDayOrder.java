package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "每日订单统计", description = "每日订单统计")
@Data
public class StatDayOrder {
  /**
   * 编号
   */
  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 订单状态
   */
  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Integer", required = true)
  private Integer orderStatus;

  /**
   * 天数
   */
  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer", required = true)
  private Integer dayNo;

  /**
   * 金额数
   */
  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer", required = false)
  private Integer amount;

  /**
   * 订单数
   */
  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer", required = false)
  private Integer orderNum;

  /**
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}