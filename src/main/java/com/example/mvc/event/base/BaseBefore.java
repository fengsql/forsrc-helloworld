package com.example.mvc.event.base;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.common.spring.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BaseBefore extends BaseService {

  // <<----------------------- public -----------------------

  // <<<----------------------- common -----------------------

  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport, List<Map<String, Object>> list) {

  }

  // >>>----------------------- common -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- common -----------------------

  // >>>----------------------- common -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}