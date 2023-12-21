package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询商品类别表返回的数据", description = "商品类别表")
@Data
public class RepGoodsKind {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "商品类别表列表", name = "rows", dataType = "List", required = false)
  private List<GoodsKindRow> rows;

  @Data
  public static class GoodsKindRow {

    @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "类别名称", name = "name", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}