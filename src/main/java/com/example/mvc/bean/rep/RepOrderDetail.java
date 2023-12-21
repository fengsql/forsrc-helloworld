package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询订单明细表返回的数据", description = "订单明细表")
@Data
public class RepOrderDetail {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "订单明细表列表", name = "rows", dataType = "List", required = false)
  private List<OrderDetailRow> rows;

  @Data
  public static class OrderDetailRow {

    @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "订单编号", name = "orderId", dataType = "Integer", required = true)
    private Integer orderId;

    @ApiModelProperty(value = "订单状态(订单编号)", name = "orderStatus", dataType = "Byte", required = true)
    private Byte orderStatus;

    @ApiModelProperty(value = "订单状态(订单编号)名称", name = "orderStatus_", dataType = "String", required = true)
    private String orderStatus_;

    @ApiModelProperty(value = "支付状态(订单编号)", name = "payStatus", dataType = "Byte", required = true)
    private Byte payStatus;

    @ApiModelProperty(value = "支付状态(订单编号)名称", name = "payStatus_", dataType = "String", required = true)
    private String payStatus_;

    @ApiModelProperty(value = "金额数(订单编号)", name = "amount", dataType = "Integer", required = true)
    private Integer amount;

    @ApiModelProperty(value = "成功金额(订单编号)", name = "amountSuccess", dataType = "Integer", required = false)
    private Integer amountSuccess;

    @ApiModelProperty(value = "第三方订单号(订单编号)", name = "thirdOrderNo", dataType = "String", required = false)
    private String thirdOrderNo;

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

    @ApiModelProperty(value = "商品数量", name = "goodsNum", dataType = "Integer", required = true)
    private Integer goodsNum;

    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}