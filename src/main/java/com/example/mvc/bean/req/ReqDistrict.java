package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询县表请求的参数", description = "县表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqDistrict extends BRequestPage {

  @ApiModelProperty(value = "县编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer")
  private Integer cityId;

  @ApiModelProperty(value = "市名称(市编号)", name = "cityName", dataType = "String")
  private String cityName;

  @ApiModelProperty(value = "县名称", name = "districtName", dataType = "String")
  private String districtName;

}