package com.example.mvc.event.before;

import com.forsrc.common.extend.bean.ParamExport;
import com.example.common.constant.EnumField;
import com.example.mvc.bean.req.ReqGoods;
import com.example.mvc.event.base.BaseBefore;
import com.example.mvc.model.Goods;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeforeGoods extends BaseBefore {

  /**
   * 添加商品表之前的处理。
   * @param goods 传入的商品表。
   */
  public void onInsert(HttpServletRequest request, HttpServletResponse response, Goods goods) {
    if (goods.getOnlineStatus() == null) {
      goods.setOnlineStatus(EnumField.OnlineStatus.def_.getCode());
    }
  }

}