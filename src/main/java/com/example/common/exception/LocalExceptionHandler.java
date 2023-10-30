package com.example.common.exception;

import com.forsrc.common.exception.BaseExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class LocalExceptionHandler extends BaseExceptionHandler {

  /**
   * 可以定制异常处理逻辑。
   * @param exception 异常。分为以下几种情况：
   *                  1、CommonException，HttpStatus 为 200，code 为自定义状态码；
   *                  2、RawException，HttpStatus 和 code 为保持一致，HttpStatus 由系统定义；
   *                  3、其它异常，如果能够获取 HttpStatus，HttpStatus 和 code 为保持一致，否则都为 500。
   * @return 返回数据。
   */
  @ExceptionHandler(Exception.class)
  @org.springframework.web.bind.annotation.ResponseBody
  public Object handleException(Exception exception) throws Exception {
    if (exception instanceof SQLException) {  //可以实现自己的异常处理逻辑
      log.error(ExceptionUtils.getStackTrace(exception));
      return handleSQLException(exception);
    }
    return super.handleException(exception);
  }

  private Object handleSQLException(Exception exception) {
    return createResponseBody("SQL error!");
  }

}