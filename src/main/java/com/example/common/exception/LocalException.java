package com.example.common.exception;

import com.forsrc.common.constant.Code;
import com.forsrc.common.exception.CommonException;
import com.example.common.constant.EnumCode;

public class LocalException extends CommonException {

  public LocalException(EnumCode enumCode) {
    super(enumCode.getCode(), enumCode.getMsg());
  }

  public LocalException(EnumCode enumCode, String message) {
    super(enumCode.getCode(), message);
  }

  public LocalException(int code, String message) {
    super(code, message);
  }

  public LocalException(String message) {
    super(Code.FAIL, message);
  }

}