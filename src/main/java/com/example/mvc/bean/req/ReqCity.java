package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询市表请求的参数", description = "市表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqCity extends BRequestPage {

  @ApiModelProperty(value = "市编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "省编号", name = "provinceId", dataType = "Integer")
  private Integer provinceId;

  @ApiModelProperty(value = "省名称(省编号)", name = "provinceName", dataType = "String")
  private String provinceName;

  @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String")
  private String cityName;

}