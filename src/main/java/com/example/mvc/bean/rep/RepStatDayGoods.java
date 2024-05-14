package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询每日订单商品统计返回的数据", description = "每日订单商品统计")
@Data
public class RepStatDayGoods {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;

  @ApiModelProperty(value = "每日订单商品统计列表", name = "rows", dataType = "List", required = false)
  private List<StatDayGoodsRow> rows;

  @ApiModel(value = "每日订单商品统计", description = "每日订单商品统计")
  @Data
  public static class StatDayGoodsRow {

    @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "商品编号", name = "goodsId", dataType = "Integer", required = true)
    private Integer goodsId;

    @ApiModelProperty(value = "上架状态(商品编号)", name = "onlineStatus", dataType = "Integer", required = true)
    private Integer onlineStatus;

    @ApiModelProperty(value = "上架状态(商品编号)名称", name = "onlineStatus_", dataType = "String", required = true)
    private String onlineStatus_;

    @ApiModelProperty(value = "商品名称(商品编号)", name = "name", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "商品价格(商品编号)", name = "price", dataType = "Integer", required = false)
    private Integer price;

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

}