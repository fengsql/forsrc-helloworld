package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询商品表返回的数据", description = "商品表")
@Data
public class RepGoods {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;

  @ApiModelProperty(value = "商品表列表", name = "rows", dataType = "List", required = false)
  private List<GoodsRow> rows;

  @ApiModel(value = "商品表", description = "商品表")
  @Data
  public static class GoodsRow {

    @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "类别编号", name = "goodsKindId", dataType = "Integer", required = true)
    private Integer goodsKindId;

    @ApiModelProperty(value = "类别名称(类别编号)", name = "goodsKindName", dataType = "String", required = true)
    private String goodsKindName;

    @ApiModelProperty(value = "上架状态", name = "onlineStatus", dataType = "Integer", required = true)
    private Integer onlineStatus;

    @ApiModelProperty(value = "上架状态名称", name = "onlineStatus_", dataType = "String", required = true)
    private String onlineStatus_;

    @ApiModelProperty(value = "商品名称", name = "name", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "商品价格", name = "price", dataType = "Integer", required = false)
    private Integer price;

    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}