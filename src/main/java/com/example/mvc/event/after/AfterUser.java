package com.example.mvc.event.after;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.mvc.bean.detail.DetailUser;
import com.example.mvc.bean.rep.RepUser;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.event.base.BaseAfter;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AfterUser extends BaseAfter {

  /**
   * 添加用户之后的处理。
   * @param user 添加后的用户。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, User user) {

  }

  /**
   * 更新用户之后的处理。
   * @param user 传入的用户。
   * @param count 更新成功记录数。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, User user, int count) {

  }

  /**
   * 删除用户之后的处理。
   * @param user 传入的用户。
   * @param users_ 已经删除的用户。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, User user, List<User> users_) {

  }

  /**
   * 根据主键查询一条用户之后的处理。
   * @param user 传入的用户。
   * @param user1 查询到的用户。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, User user, User user1) {

  }

  /**
   * 根据主键查询一条用户详情之后的处理。
   * @param user 传入的用户。
   * @param detailUser 查询到的用户详情。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, User user, DetailUser detailUser) {

  }

  /**
   * 查询用户列表之后的处理。
   * @param user 传入的用户。
   * @param list 查询到的用户列表。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, User user, List<User> list) {

  }

  /**
   * 综合查询用户列表之后的处理。
   * @param reqUser 查询的参数。
   * @param repUser 综合查询到的用户。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqUser reqUser, RepUser repUser) {

  }

  /**
   * 导出用户到 excel 之后的处理。
   * @param paramExport 导出的参数。
   * @param list 查询到的用户列表。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport, List<Map<String, Object>> list) {

  }

}