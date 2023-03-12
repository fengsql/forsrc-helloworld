package com.example.common.response;

import com.forsrc.common.reponse.IResponseHandler;
import org.springframework.stereotype.Service;

@Service
public class ResponseHandler implements IResponseHandler<ResponseBody> {

  /**
   * 可以定制返回数据格式，实现此方法即可。
   * @param success 处理结果，true 为成功，false 为失败。
   * @param code 返回状态码。可以为自定义状态码。当正常返回时，与 HttpStatus 的状态码一致为 200，当出现异常时，参见 LocalExceptionHandler 说明。
   * @param message 提示消息，一般为错误消息。正常返回时为 null。
   * @param data 返回数据。当出现错误时为 null。
   * @return 返回的数据。json 格式。
   */
  @Override
  public ResponseBody createResponse(Boolean success, Integer code, String message, Object data) {
    ResponseBody responseBody = new ResponseBody();
    responseBody.setSuccess(success);
    responseBody.setCode(code);
    responseBody.setMessage(message);
    responseBody.setData(data);
    return responseBody;
  }
}