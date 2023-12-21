package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "每日订单商品统计详情", description = "每日订单商品统计")
@Data
public class DetailStatDayGoods {

  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "商品名称", name = "name", dataType = "String", required = true)
  private String name;

  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer", required = true)
  private Integer dayNo;

  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer", required = false)
  private Integer orderNum;

  @ApiModelProperty(value = "商品数", name = "goodsNum", dataType = "Integer", required = false)
  private Integer goodsNum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}