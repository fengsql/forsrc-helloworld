package com.example.mvc.bean.req;

import com.example.common.spring.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询商品表请求的参数", description = "商品表", parent = BaseRequest.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqGoods extends BaseRequest {

  @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "类别编号", name = "goodsKindId", dataType = "Integer")
  private Integer goodsKindId;

  @ApiModelProperty(value = "类别名称(类别编号)", name = "goodsKindName", dataType = "String")
  private String goodsKindName;

  @ApiModelProperty(value = "上架状态", name = "onlineStatus", dataType = "Integer[]")
  private Integer[] onlineStatus;

  @ApiModelProperty(value = "商品名称", name = "name", dataType = "String")
  private String name;

  @ApiModelProperty(value = "商品价格", name = "price", dataType = "Integer[]")
  private Integer[] price;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}