package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询每日订单商品统计请求的参数", description = "每日订单商品统计", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqStatDayGoods extends BRequestPage {

  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "商品编号", name = "goodsId", dataType = "Integer")
  private Integer goodsId;

  @ApiModelProperty(value = "上架状态(商品编号)", name = "onlineStatus", dataType = "Integer[]")
  private Integer[] onlineStatus;

  @ApiModelProperty(value = "商品名称(商品编号)", name = "name", dataType = "String")
  private String name;

  @ApiModelProperty(value = "商品价格(商品编号)", name = "price", dataType = "Integer[]")
  private Integer[] price;

  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer[]")
  private Integer[] dayNo;

  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer[]")
  private Integer[] orderNum;

  @ApiModelProperty(value = "商品数", name = "goodsNum", dataType = "Integer[]")
  private Integer[] goodsNum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}