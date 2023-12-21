package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商品类别表", description = "商品类别表")
@Data
public class GoodsKind {
  /**
   * 商品编号
   */
  @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 类别名称
   */
  @ApiModelProperty(value = "类别名称", name = "name", dataType = "String", required = true)
  private String name;

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