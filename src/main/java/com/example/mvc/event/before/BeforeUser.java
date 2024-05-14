package com.example.mvc.event.before;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.common.constant.EnumField;
import com.example.mvc.bean.req.ReqUser;
import com.example.mvc.event.base.BaseBefore;
import com.example.mvc.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeforeUser extends BaseBefore {

  /**
   * 添加用户之前的处理。
   * @param user 传入的用户。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, User user) {
    if (user.getRoleType() == null) {
      user.setRoleType(EnumField.RoleType.def_.getCode());
    }
    if (user.getSexType() == null) {
      user.setSexType(EnumField.SexType.other_.getCode());
    }
    if (user.getUserStatus() == null) {
      user.setUserStatus(1);
    }
  }

  /**
   * 更新用户之前的处理。
   * @param user 传入的用户。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, User user) {

  }

  /**
   * 删除用户之前的处理。
   * @param user 传入的用户。
   * @param users_ 准备删除的用户。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, User user, List<User> users_) {

  }

  /**
   * 根据主键查询一条用户之前的处理。
   * @param user 传入的用户。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, User user) {

  }

  /**
   * 根据主键查询一条用户详情之前的处理。
   * @param user 传入的用户。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, User user) {

  }

  /**
   * 综合查询用户列表之前的处理。
   * @param reqUser 查询的参数。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqUser reqUser) {

  }

  /**
   * 导出用户到 excel 之前的处理。
   * @param paramExport 导出的参数。
   * @param list 查询到的用户列表。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport, List<Map<String, Object>> list) {

  }

}