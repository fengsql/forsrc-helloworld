package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询市表返回的数据", description = "市表")
@Data
public class RepCity {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "市表列表", name = "rows", dataType = "List", required = false)
  private List<CityRow> rows;

  @Data
  public static class CityRow {

    @ApiModelProperty(value = "市编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "省编号", name = "provinceId", dataType = "Integer", required = true)
    private Integer provinceId;

    @ApiModelProperty(value = "省名称(省编号)", name = "provinceName", dataType = "String", required = true)
    private String provinceName;

    @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String", required = true)
    private String cityName;

  }

}