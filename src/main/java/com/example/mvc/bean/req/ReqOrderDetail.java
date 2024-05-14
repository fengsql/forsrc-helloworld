package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询订单明细表请求的参数", description = "订单明细表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqOrderDetail extends BRequestPage {

  @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "订单编号", name = "orderId", dataType = "Integer")
  private Integer orderId;

  @ApiModelProperty(value = "订单状态(订单编号)", name = "orderStatus", dataType = "Integer[]")
  private Integer[] orderStatus;

  @ApiModelProperty(value = "支付状态(订单编号)", name = "payStatus", dataType = "Integer[]")
  private Integer[] payStatus;

  @ApiModelProperty(value = "金额数(订单编号)", name = "amount", dataType = "Integer[]")
  private Integer[] amount;

  @ApiModelProperty(value = "成功金额(订单编号)", name = "amountSuccess", dataType = "Integer[]")
  private Integer[] amountSuccess;

  @ApiModelProperty(value = "第三方订单号(订单编号)", name = "thirdOrderNo", dataType = "String")
  private String thirdOrderNo;

  @ApiModelProperty(value = "商品编号", name = "goodsId", dataType = "Integer")
  private Integer goodsId;

  @ApiModelProperty(value = "上架状态(商品编号)", name = "onlineStatus", dataType = "Integer[]")
  private Integer[] onlineStatus;

  @ApiModelProperty(value = "商品名称(商品编号)", name = "name", dataType = "String")
  private String name;

  @ApiModelProperty(value = "商品价格(商品编号)", name = "price", dataType = "Integer[]")
  private Integer[] price;

  @ApiModelProperty(value = "商品数量", name = "goodsNum", dataType = "Integer[]")
  private Integer[] goodsNum;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}