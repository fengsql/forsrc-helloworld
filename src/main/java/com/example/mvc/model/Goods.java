package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商品表", description = "商品表")
@Data
public class Goods {
  /**
   * 商品编号
   */
  @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 类别编号
   */
  @ApiModelProperty(value = "类别编号", name = "goodsKindId", dataType = "Integer", required = true)
  private Integer goodsKindId;

  /**
   * 上架状态
   */
  @ApiModelProperty(value = "上架状态", name = "onlineStatus", dataType = "Integer", required = true)
  private Integer onlineStatus;

  /**
   * 商品名称
   */
  @ApiModelProperty(value = "商品名称", name = "name", dataType = "String", required = true)
  private String name;

  /**
   * 商品价格
   */
  @ApiModelProperty(value = "商品价格", name = "price", dataType = "Integer", required = false)
  private Integer price;

  /**
   * 说明
   */
  @ApiModelProperty(value = "说明", name = "info", dataType = "String", required = false)
  private String info;

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