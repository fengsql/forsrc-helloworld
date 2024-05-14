package com.example.common.response;

import com.forsrc.common.spring.base.BResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回数据，可以定制返回数据格式，实现 IResponseHandler 接口即可。
 */
@ApiModel(value = "返回数据", description = "返回数据，所有请求统一返回这个格式。")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseBody extends BResponse {

  @ApiModelProperty(value = "是否成功", name = "success", dataType = "Boolean", required = true)
  private Boolean success;

  @ApiModelProperty(value = "返回码", name = "code", dataType = "Integer", required = true)
  private Integer code;

  @ApiModelProperty(value = "返回消息", name = "code", dataType = "String", required = false)
  private String message;

  @ApiModelProperty(value = "返回数据", name = "data", dataType = "Object", required = false)
  private Object data;

}