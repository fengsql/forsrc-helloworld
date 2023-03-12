package com.example.mvc.bean.rep;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询县表返回的数据", description = "县表")
@Data
public class RepDistrict {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "县表列表", name = "rows", dataType = "List", required = false)
  private List<DistrictRow> rows;

  @Data
  public static class DistrictRow {

    @ApiModelProperty(value = "县编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer", required = true)
    private Integer cityId;

    @ApiModelProperty(value = "市名称(市编号)", name = "cityName", dataType = "String", required = true)
    private String cityName;

    @ApiModelProperty(value = "县名称", name = "districtName", dataType = "String", required = true)
    private String districtName;

  }

}