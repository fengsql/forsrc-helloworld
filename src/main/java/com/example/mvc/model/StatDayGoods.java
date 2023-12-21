package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "每日订单商品统计", description = "每日订单商品统计")
@Data
public class StatDayGoods {
  /**
   * 编号
   */
  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 商品编号
   */
  @ApiModelProperty(value = "商品编号", name = "goodsId", dataType = "Integer", required = true)
  private Integer goodsId;

  /**
   * 天数
   */
  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer", required = true)
  private Integer dayNo;

  /**
   * 订单数
   */
  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer", required = false)
  private Integer orderNum;

  /**
   * 商品数
   */
  @ApiModelProperty(value = "商品数", name = "goodsNum", dataType = "Integer", required = false)
  private Integer goodsNum;

  /**
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}