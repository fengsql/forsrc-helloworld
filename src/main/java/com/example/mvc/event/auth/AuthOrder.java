package com.example.mvc.event.auth;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.mvc.bean.req.ReqOrder;
import com.example.mvc.event.base.BaseAuth;
import com.example.mvc.model.Order;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthOrder extends BaseAuth {

  /**
   * 添加订单表之前的授权处理。
   * @param order 传入的订单表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, Order order) {
    needNormal();
  }

  /**
   * 添加订单表之前的授权处理。
   * @param orders 传入的订单表列表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, List<Order> orders) {
    needNormal();
  }

  /**
   * 更新订单表之前的授权处理。
   * @param order 传入的订单表。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, Order order) {
    needNormal();
  }

  /**
   * 删除订单表之前的授权处理。
   * @param order 传入的订单表。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, Order order) {
    needService();
  }

  /**
   * 根据主键查询一条订单表之前的授权处理。
   * @param order 传入的订单表。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, Order order) {
    needNormal();
  }

  /**
   * 根据主键查询一条订单表详情之前的授权处理。
   * @param order 传入的订单表。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, Order order) {
    needNormal();
  }

  /**
   * 综合查询订单表列表之前的授权处理。
   * @param reqOrder 查询的参数。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqOrder reqOrder) {
    needNormal();
  }

  /**
   * 导出订单表到 excel 之前的授权处理。
   * @param paramExport 导出的参数。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    needNormal();
  }

}