package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单明细表", description = "订单明细表")
@Data
public class OrderDetail {
  /**
   * 订单编号
   */
  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 订单编号
   */
  @ApiModelProperty(value = "订单编号", name = "orderId", dataType = "Integer", required = true)
  private Integer orderId;

  /**
   * 商品编号
   */
  @ApiModelProperty(value = "商品编号", name = "goodsId", dataType = "Integer", required = true)
  private Integer goodsId;

  /**
   * 商品数量
   */
  @ApiModelProperty(value = "商品数量", name = "goodsNum", dataType = "Integer", required = true)
  private Integer goodsNum;

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