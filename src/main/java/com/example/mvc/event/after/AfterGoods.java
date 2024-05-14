package com.example.mvc.event.after;

import com.example.mvc.event.base.BaseAfter;
import com.example.mvc.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class AfterGoods extends BaseAfter {

  /**
   * 更新商品表之后的处理。
   * @param goods 传入的商品表。
   * @param count 更新成功记录数。
   */
  public void onUpdate(HttpServletRequest request, HttpServletResponse response, Goods goods, int count) {

  }

}