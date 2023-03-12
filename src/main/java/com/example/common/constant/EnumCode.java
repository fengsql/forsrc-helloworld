package com.example.common.constant;

public enum EnumCode {
  //custom 2000
  custom_exception(2000, "异常"),
  ;

  Integer code;
  String msg;

  EnumCode(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}