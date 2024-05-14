package com.example.mvc.event.auth;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.mvc.bean.req.ReqOrderDetail;
import com.example.mvc.event.base.BaseAuth;
import com.example.mvc.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthOrderDetail extends BaseAuth {

  /**
   * 添加订单明细表之前的授权处理。
   * @param orderDetail 传入的订单明细表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    needNormal();
  }

  /**
   * 添加订单明细表之前的授权处理。
   * @param orderDetails 传入的订单明细表列表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, List<OrderDetail> orderDetails) {
    needNormal();
  }

  /**
   * 更新订单明细表之前的授权处理。
   * @param orderDetail 传入的订单明细表。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    needNormal();
  }

  /**
   * 删除订单明细表之前的授权处理。
   * @param orderDetail 传入的订单明细表。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    needService();
  }

  /**
   * 根据主键查询一条订单明细表之前的授权处理。
   * @param orderDetail 传入的订单明细表。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    needNormal();
  }

  /**
   * 根据主键查询一条订单明细表详情之前的授权处理。
   * @param orderDetail 传入的订单明细表。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, OrderDetail orderDetail) {
    needNormal();
  }

  /**
   * 综合查询订单明细表列表之前的授权处理。
   * @param reqOrderDetail 查询的参数。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqOrderDetail reqOrderDetail) {
    needNormal();
  }

  /**
   * 导出订单明细表到 excel 之前的授权处理。
   * @param paramExport 导出的参数。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    needNormal();
  }

}