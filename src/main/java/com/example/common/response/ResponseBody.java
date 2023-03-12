package com.example.common.response;

import com.forsrc.common.spring.base.BResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回数据，可以定制返回数据格式，实现 IResponseHandler 接口即可。
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseBody extends BResponse {

  private Boolean success;

  private Integer code;

  private String message;

  private Object data;

}