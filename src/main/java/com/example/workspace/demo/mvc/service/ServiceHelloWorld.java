package com.example.workspace.demo.mvc.service;

import com.example.common.spring.base.BaseService;
import com.example.workspace.demo.mvc.define.RepHelloWorld;
import com.example.workspace.demo.mvc.define.ReqHelloWorld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class ServiceHelloWorld extends BaseService {

  // <<----------------------- public -----------------------

  /**
   * 这是一个演示接口。。
   * @param reqHelloWorld 请求数据。
   * @return 返回数据。
   */
  public RepHelloWorld helloWorld(HttpServletRequest request, HttpServletResponse response, ReqHelloWorld reqHelloWorld) {
    checkParam(reqHelloWorld);
    return doHelloWorld(reqHelloWorld);
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  private void checkParam(ReqHelloWorld reqHelloWorld) {
    throwNull(reqHelloWorld);
  }

  private RepHelloWorld doHelloWorld(ReqHelloWorld reqHelloWorld) {
    RepHelloWorld repHelloWorld = new RepHelloWorld();
    repHelloWorld.setMsg("Hello world!");
    return repHelloWorld;
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}