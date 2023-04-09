package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "县表", description = "县表")
@Data
public class District {
  /**
   * 县编号
   */
  @ApiModelProperty(value = "县编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 市编号
   */
  @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer", required = true)
  private Integer cityId;

  /**
   * 县名称
   */
  @ApiModelProperty(value = "县名称", name = "districtName", dataType = "String", required = true)
  private String districtName;

}