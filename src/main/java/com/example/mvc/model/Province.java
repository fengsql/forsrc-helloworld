package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "省表", description = "省表")
@Data
public class Province {
  /**
   * 省编号
   */
  @ApiModelProperty(value = "省编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 省名称
   */
  @ApiModelProperty(value = "省名称", name = "provinceName", dataType = "String", required = true)
  private String provinceName;

}