package com.example.mvc.event.auth;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.event.base.BaseAuth;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUser extends BaseAuth {

  /**
   * 添加用户之前的授权处理。
   * @param user 传入的用户。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, User user) {
    needAdmin();
  }

  /**
   * 添加用户之前的授权处理。
   * @param users 传入的用户列表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, List<User> users) {
    needAdmin();
  }

  /**
   * 更新用户之前的授权处理。
   * @param user 传入的用户。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, User user) {
    needAdmin();
  }

  /**
   * 删除用户之前的授权处理。
   * @param user 传入的用户。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, User user) {
    needAdmin();
  }

  /**
   * 根据主键查询一条用户之前的授权处理。
   * @param user 传入的用户。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, User user) {
    needNormal();
  }

  /**
   * 根据主键查询一条用户详情之前的授权处理。
   * @param user 传入的用户。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, User user) {
    needNormal();
  }

  /**
   * 综合查询用户列表之前的授权处理。
   * @param reqUser 查询的参数。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqUser reqUser) {
    needNormal();
  }

  /**
   * 导出用户到 excel 之前的授权处理。
   * @param paramExport 导出的参数。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    needAdmin();
  }

}