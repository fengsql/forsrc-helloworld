package com.example.workspace.demo.mvc.controller;

import com.example.workspace.demo.mvc.define.RepHelloWorld;
import com.example.workspace.demo.mvc.define.ReqHelloWorld;
import com.example.workspace.demo.mvc.service.ServiceHelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "演示业务", description = "这是一个演示业务的 API", position = 999)
@RestController
@RequestMapping("/api/demo")
@Slf4j
public class ControllerHelloWorld {

  @Resource
  private ServiceHelloWorld serviceHelloWorld;

  /**
   * 这是一个演示接口。。
   * @param reqHelloWorld 请求数据。
   * @return 返回数据。
   */
  @ApiOperation(value = "演示接口", notes = "这是一个演示接口。", response = ReqHelloWorld.class)
  @RequestMapping(method = RequestMethod.POST, value = "helloWorld")
  public RepHelloWorld helloWorld(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqHelloWorld reqHelloWorld) {
    log.info("helloWorld: {}", reqHelloWorld);
    return serviceHelloWorld.helloWorld(request, response, reqHelloWorld);
  }

}