package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单明细表详情", description = "订单明细表")
@Data
public class DetailOrderDetail {

  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "第三方订单号", name = "thirdOrderNo", dataType = "String", required = false)
  private String thirdOrderNo;

  @ApiModelProperty(value = "商品名称", name = "name", dataType = "String", required = true)
  private String name;

  @ApiModelProperty(value = "商品数量", name = "goodsNum", dataType = "Integer", required = true)
  private Integer goodsNum;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}