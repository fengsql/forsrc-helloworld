package com.example.business.admin.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "重置用户密码请求参数", description = "重置用户密码请求参数")
@Data
public class ReqResetPassword {

  @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

}