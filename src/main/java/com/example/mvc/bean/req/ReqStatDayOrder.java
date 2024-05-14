package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询每日订单统计请求的参数", description = "每日订单统计", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqStatDayOrder extends BRequestPage {

  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Integer[]")
  private Integer[] orderStatus;

  @ApiModelProperty(value = "天数", name = "dayNo", dataType = "Integer[]")
  private Integer[] dayNo;

  @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer[]")
  private Integer[] amount;

  @ApiModelProperty(value = "订单数", name = "orderNum", dataType = "Integer[]")
  private Integer[] orderNum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}