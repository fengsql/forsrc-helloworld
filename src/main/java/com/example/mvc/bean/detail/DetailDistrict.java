package com.example.mvc.bean.detail;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "县表详情", description = "县表")
@Data
public class DetailDistrict {

  @ApiModelProperty(value = "县编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String", required = true)
  private String cityName;

  @ApiModelProperty(value = "县名称", name = "districtName", dataType = "String", required = true)
  private String districtName;

}