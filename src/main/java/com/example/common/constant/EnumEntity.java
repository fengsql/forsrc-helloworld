package com.example.common.constant;

import com.forsrc.common.base.IEnumField;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EnumEntity {

  public enum Table implements IEnumField {
    province_(1, "province", "省表"), //
    city_(2, "city", "市表"), //
    district_(3, "district", "县表"), //
    user_(4, "user", "用户"), //
    merchant_(5, "merchant", "商户表"), //
    goodsKind_(6, "goods_kind", "商品类别表"), //
    goods_(7, "goods", "商品表"), //
    order_(8, "order", "订单表"), //
    orderDetail_(9, "order_detail", "订单明细表"), //
    statDayOrder_(10, "stat_day_order", "每日订单统计"), //
    statDayGoods_(11, "stat_day_goods", "每日订单商品统计"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, Table> mapCode = new HashMap<>();
    private static final Map<String, Table> mapName = new HashMap<>();

    static {
      for (Table item : Table.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    Table(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static Table get(int code) {
      return mapCode.get(code);
    }

    public static Table get(String name) {
      return mapName.get(name);
    }
  }

}