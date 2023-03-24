package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "省表详情", description = "省表")
@Data
public class DetailProvince {

  @ApiModelProperty(value = "省编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  @ApiModelProperty(value = "省名称", name = "provinceName", dataType = "String", required = true)
  private String provinceName;

}