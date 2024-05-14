package com.example.mvc.event.after;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.mvc.bean.detail.DetailGoods;
import com.example.mvc.bean.rep.RepGoods;
import com.example.mvc.bean.req.ReqGoods;
import com.example.mvc.event.base.BaseAfter;
import com.example.mvc.model.Goods;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

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