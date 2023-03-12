package com.example.mvc.bean.detail;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "市表详情", description = "市表")
@Data
public class DetailCity {

  @ApiModelProperty(value = "市编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "省名称", name = "provinceName", dataType = "String", required = true)
  private String provinceName;

  @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String", required = true)
  private String cityName;

}