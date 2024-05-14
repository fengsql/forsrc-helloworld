package com.example.mvc.event.auth;

import com.example.mvc.bean.req.ReqMerchant;
import com.example.mvc.event.base.BaseAuth;
import com.example.mvc.model.Merchant;
import com.forsrc.common.extend.bean.ParamExport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class AuthMerchant extends BaseAuth {

  /**
   * 添加商户表之前的授权处理。
   * @param merchant 传入的商户表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    needAdmin();
  }

  /**
   * 添加商户表之前的授权处理。
   * @param merchants 传入的商户表列表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, List<Merchant> merchants) {
    needAdmin();
  }

  /**
   * 更新商户表之前的授权处理。
   * @param merchant 传入的商户表。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    needAdmin();
  }

  /**
   * 删除商户表之前的授权处理。
   * @param merchant 传入的商户表。
   */
  public void onDelete(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    needAdmin();
  }

  /**
   * 根据主键查询一条商户表之前的授权处理。
   * @param merchant 传入的商户表。
   */
  public void onSelect(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    needAdmin();
  }

  /**
   * 根据主键查询一条商户表详情之前的授权处理。
   * @param merchant 传入的商户表。
   */
  public void onSelectDetail(HttpServletRequest request, HttpServletResponse response, Merchant merchant) {
    needAdmin();
  }

  /**
   * 综合查询商户表列表之前的授权处理。
   * @param reqMerchant 查询的参数。
   */
  public void onSelectRelative(HttpServletRequest request, HttpServletResponse response, ReqMerchant reqMerchant) {
    needAdmin();
  }

  /**
   * 导出商户表到 excel 之前的授权处理。
   * @param paramExport 导出的参数。
   */
  public void onExport(HttpServletRequest request, HttpServletResponse response, ParamExport paramExport) {
    needAdmin();
  }

}