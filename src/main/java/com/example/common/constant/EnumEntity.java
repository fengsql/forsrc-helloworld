package com.example.common.constant;

import com.forsrc.common.base.IEnumField;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EnumEntity {

  public enum Table implements IEnumField {
    province_(1, "t_province", "省表"), //
    city_(2, "t_city", "市表"), //
    district_(3, "t_district", "县表"), //
    user_(4, "t_user", "用户"), //
    merchant_(5, "t_merchant", "商户表"), //
    statTest_(6, "t_stat_test", "统计信息"), //
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