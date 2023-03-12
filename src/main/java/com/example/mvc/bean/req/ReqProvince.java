package com.example.mvc.bean.req;

import com.example.common.spring.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询省表请求的参数", description = "省表", parent = BaseRequest.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqProvince extends BaseRequest {

  @ApiModelProperty(value = "省编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "省名称", name = "provinceName", dataType = "String")
  private String provinceName;

}