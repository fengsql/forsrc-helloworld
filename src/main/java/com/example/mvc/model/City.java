package com.example.mvc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "市表", description = "市表")
@Data
public class City {
  /**
   * 市编号
   */
  @ApiModelProperty(value = "市编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 省编号
   */
  @ApiModelProperty(value = "省编号", name = "provinceId", dataType = "Integer", required = true)
  private Integer provinceId;

  /**
   * 市名称
   */
  @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String", required = true)
  private String cityName;

}