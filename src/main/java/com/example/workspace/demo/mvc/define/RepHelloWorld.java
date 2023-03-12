package com.example.workspace.demo.mvc.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "演示返回数据", description = "演示返回数据")
@Data
public class RepHelloWorld {

  @ApiModelProperty(value = "演示返回消息", name = "msg", dataType = "String")
  private String msg;

}