package com.example.common.spring.base;

import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.Const;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.spring.base.BService;
import com.forsrc.common.tool.Tool;
import com.forsrc.security.model.UserDetail;
import com.forsrc.security.tool.ToolSecurity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class BaseService extends BService {

  // <<----------------------- public -----------------------

  protected void initService(HttpServletRequest request, BaseRequest baseRequest) {
    initValue(baseRequest);
    initPageSet(baseRequest, false);
  }

  protected void initServiceNest(HttpServletRequest request, BaseRequest baseRequest) {
    initValue(baseRequest);
    initPageSet(baseRequest, true);
  }

  protected boolean isQueryTotal(BaseRequest baseRequest) {
    int pageIndex = Tool.toInt(baseRequest.getPageIndex());
    return pageIndex <= 0;
  }

  protected Long getUserId() {
    UserDetail userDetail = getUserDetail();
    return userDetail == null ? 0 : Tool.toLong(userDetail.getUserId());
  }

  protected UserDetail getUserDetail() {
    UserDetail userDetail = ToolSecurity.getUserDetail();
    throwNull(userDetail, "userDetail");
    return userDetail;
  }

  protected String getUsername() {
    return ToolSecurity.getUsername();
  }

  protected void throwNull(HttpServletRequest request, HttpServletResponse response) {
    if (request == null) {
      throw new CommonException(Code.OBJECT_NULL, "request is null");
    }
    if (response == null) {
      throw new CommonException(Code.OBJECT_NULL, "response is null");
    }
  }

  protected void throwNull(Object object, String name) {
    if (object == null) {
      throw new CommonException(Code.OBJECT_NULL, name + " is null!");
    }
  }

  protected <T> void throwNull(List<T> list) {
    if (list == null || list.size() == 0) {
      throw new CommonException(Code.OBJECT_NULL, "list is null!");
    }
  }

  protected void throwNull(Object object) {
    if (object == null) {
      throw new CommonException(Code.OBJECT_NULL, "object is null!");
    }
  }

  protected void assertUnsigned(Integer value, String name) {
    if (value == null || value <= 0) {
      throw new CommonException(Code.OBJECT_NULL, name + " is invalid number!");
    }
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- common -----------------------

  private void initValue(BaseRequest baseRequest) {
    baseRequest.setInnerCondition(null);
  }

  private void initPageSet(BaseRequest baseRequest, boolean isDefaultAll) {
    int pageSize = Tool.toInt(baseRequest.getPageSize());
    int pageIndex = Tool.toInt(baseRequest.getPageIndex());
    if (pageIndex < 0) {
      pageIndex = 0;
    }
    if (isDefaultAll && pageSize <= 0) {  //嵌套查询默认不分页
      return;
    }
    if (pageSize < 0) {  //查询全部
      return;
    }
    if (pageSize == 0) {
      pageSize = Const.pageSize_def;
    }
    int startIndex = pageSize * pageIndex;

    baseRequest.setPageSize(pageSize);
    baseRequest.setStartIndex(startIndex);
  }

  // >>>----------------------- common -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}