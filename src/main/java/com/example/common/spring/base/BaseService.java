package com.example.common.spring.base;

import com.forsrc.common.constant.Code;
import com.forsrc.common.constant.Const;
import com.forsrc.common.exception.CommonException;
import com.forsrc.common.spring.base.BRequestPage;
import com.forsrc.common.spring.base.BService;
import com.forsrc.common.tool.Tool;
import com.forsrc.security.model.UserDetail;
import com.forsrc.security.tool.ToolSecurity;
import com.example.mvc.model.User;
import com.example.mvc.service.ServiceUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BaseService extends BService {

  @Resource
  private ServiceUser serviceUser;

  // <<----------------------- public -----------------------

  // <<<----------------------- common -----------------------

  protected void initService(HttpServletRequest request, BRequestPage requestPage) {
    initValue(requestPage);
    initPageSet(requestPage, false);
  }

  protected void initServiceNest(HttpServletRequest request, BRequestPage requestPage) {
    initValue(requestPage);
    initPageSet(requestPage, true);
  }

  protected boolean isQueryTotal(BRequestPage requestPage) {
    int pageSize = Tool.toInt(requestPage.getPageSize());
    int pageIndex = Tool.toInt(requestPage.getPageIndex());
    return pageSize > 0 && pageIndex <= 0;
  }

  protected Integer getUserId() {
    UserDetail userDetail = getUserDetail();
    return getUserId(userDetail);
  }

  protected Integer getUserId(UserDetail userDetail) {
    return userDetail == null ? 0 : Tool.toInteger(userDetail.getUserId());
  }

  protected Integer getRoleType(UserDetail userDetail) {
    return userDetail == null ? 0 : Tool.toInteger(userDetail.getRoleType());
  }

  protected String getUsername() {
    return ToolSecurity.getUsername();
  }

  protected String getUsername(UserDetail userDetail) {
    return userDetail == null ? null : Tool.toString(userDetail.getUsername());
  }

  protected UserDetail getUserDetail() {
    return ToolSecurity.getUserDetail();
  }

  protected User getUser() {
    User user = selectUser();
    if (user == null) {
      throw new CommonException("用户不存在!");
    }
    return user;
  }

  protected User selectUser() {
    Integer userId = getUserId();
    if (userId == null || userId == 0) {
      return null;
    }
    return serviceUser.selectByPrimary(userId);
  }

  protected void throwNull(HttpServletRequest request, HttpServletResponse response) {
    if (request == null) {
      throw new CommonException(Code.OBJECT_NULL, "request 对象不能为空!");
    }
    if (response == null) {
      throw new CommonException(Code.OBJECT_NULL, "response 对象不能为空!");
    }
  }

  protected void throwNull(Integer value, String name) {
    if (value == null || value <= 0) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不是一个有效整数!");
    }
  }

  protected void throwNull(Double value, String name) {
    if (value == null || value <= 0) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不是一个有效小数!");
    }
  }

  protected void throwNull(String value, String name) {
    if (Tool.isNull(value)) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  protected <T> void throwNull(List<T> list, String name) {
    if (list == null || list.size() == 0) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  protected <K, V> void throwNull(Map<K, V> map, String name) {
    if (map == null || map.size() == 0) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  protected void throwNull(Object object, String name) {
    if (object == null) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  protected void assertNumber(Integer value, String name) {
    if (value == null) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  protected void assertNumber(Double value, String name) {
    if (value == null) {
      throw new CommonException(Code.OBJECT_NULL, name + " 不能为空!");
    }
  }

  // >>>----------------------- common -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- common -----------------------

  private void initValue(BRequestPage requestPage) {
    requestPage.setInnerCondition(null);
    if (requestPage.getDesc() == null) {
      requestPage.setDesc(true);
    }
  }

  private void initPageSet(BRequestPage requestPage, boolean isDefaultAll) {
    int pageSize = Tool.toInt(requestPage.getPageSize());
    int pageIndex = Tool.toInt(requestPage.getPageIndex());
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

    requestPage.setPageSize(pageSize);
    requestPage.setStartIndex(startIndex);
  }

  // >>>----------------------- common -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}