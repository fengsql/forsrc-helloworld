package com.example.common.constant;

import com.forsrc.common.base.IEnumField;
import com.forsrc.common.tool.ToolEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EnumField {

  static {
    try {
      ToolEnum.loadEnum("roleType", RoleType.class);
      ToolEnum.loadEnum("sexType", SexType.class);
      ToolEnum.loadEnum("userStatus", UserStatus.class);
      ToolEnum.loadEnum("merchantType", MerchantType.class);
      ToolEnum.loadEnum("onlineStatus", OnlineStatus.class);
      ToolEnum.loadEnum("orderStatus", OrderStatus.class);
      ToolEnum.loadEnum("payStatus", PayStatus.class);
    } catch (Exception e) {
      log.error("init ToolEnum Exception.", e);
    }
  }

  public enum RoleType implements IEnumField {
    def_(0, "def", "缺省"), //
    normal_(1, "normal", "普通"), //
    test_(9, "test", "测试用户"), //
    service_(90, "service", "客服"), //
    maint_(91, "maint", "维护"), //
    admin_(99, "admin", "管理员"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, RoleType> mapCode = new HashMap<>();
    private static final Map<String, RoleType> mapName = new HashMap<>();

    static {
      for (RoleType item : RoleType.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    RoleType(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static RoleType get(int code) {
      return mapCode.get(code);
    }

    public static RoleType get(String name) {
      return mapName.get(name);
    }
  }

  public enum SexType implements IEnumField {
    other_(0, "other", "保密"), //
    man_(1, "man", "男"), //
    female_(2, "female", "女"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, SexType> mapCode = new HashMap<>();
    private static final Map<String, SexType> mapName = new HashMap<>();

    static {
      for (SexType item : SexType.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    SexType(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static SexType get(int code) {
      return mapCode.get(code);
    }

    public static SexType get(String name) {
      return mapName.get(name);
    }
  }

  public enum UserStatus implements IEnumField {
    def_(0, "def", "缺省"), //
    normal_(1, "normal", "正常"), //
    pause_(2, "pause", "暂停"), //
    stop_(3, "stop", "停止"), //
    invalId_(4, "invalId", "无效"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, UserStatus> mapCode = new HashMap<>();
    private static final Map<String, UserStatus> mapName = new HashMap<>();

    static {
      for (UserStatus item : UserStatus.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    UserStatus(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static UserStatus get(int code) {
      return mapCode.get(code);
    }

    public static UserStatus get(String name) {
      return mapName.get(name);
    }
  }

  public enum MerchantType implements IEnumField {
    def_(0, "def", "缺省"), //
    normal_(1, "normal", "普通"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, MerchantType> mapCode = new HashMap<>();
    private static final Map<String, MerchantType> mapName = new HashMap<>();

    static {
      for (MerchantType item : MerchantType.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    MerchantType(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static MerchantType get(int code) {
      return mapCode.get(code);
    }

    public static MerchantType get(String name) {
      return mapName.get(name);
    }
  }

  public enum OnlineStatus implements IEnumField {
    def_(0, "def", "缺省"), //
    on_(1, "on", "上架"), //
    off_(2, "off", "下架"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, OnlineStatus> mapCode = new HashMap<>();
    private static final Map<String, OnlineStatus> mapName = new HashMap<>();

    static {
      for (OnlineStatus item : OnlineStatus.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    OnlineStatus(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static OnlineStatus get(int code) {
      return mapCode.get(code);
    }

    public static OnlineStatus get(String name) {
      return mapName.get(name);
    }
  }

  public enum OrderStatus implements IEnumField {
    def_(0, "def", "缺省"), //
    prepare_(1, "prepare", "已准备"), //
    send_(2, "send", "已发送"), //
    recv_(3, "recv", "收到通知"), //
    pay_(4, "pay", "已支付"), //
    refund_(5, "refund", "已退款"), //
    closed_(6, "closed", "已关闭"), //
    timeout_(7, "timeout", "已超时"), //
    cancel_(8, "cancel", "已取消"), //
    finish_(9, "finish", "已结束"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, OrderStatus> mapCode = new HashMap<>();
    private static final Map<String, OrderStatus> mapName = new HashMap<>();

    static {
      for (OrderStatus item : OrderStatus.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    OrderStatus(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static OrderStatus get(int code) {
      return mapCode.get(code);
    }

    public static OrderStatus get(String name) {
      return mapName.get(name);
    }
  }

  public enum PayStatus implements IEnumField {
    default_(0, "default", "未支付"), //
    success_(1, "success", "成功"), //
    fail_(2, "fail", "失败"), //
    ;

    @Getter
    private final int code;
    @Getter
    private final String name;
    @Getter
    private final String title;
    @Getter
    private static final Map<Integer, PayStatus> mapCode = new HashMap<>();
    private static final Map<String, PayStatus> mapName = new HashMap<>();

    static {
      for (PayStatus item : PayStatus.values()) {
        mapCode.put(item.getCode(), item);
        mapName.put(item.getName(), item);
      }
    }

    PayStatus(int code, String name, String title) {
      this.code = code;
      this.name = name;
      this.title = title;
    }

    public static PayStatus get(int code) {
      return mapCode.get(code);
    }

    public static PayStatus get(String name) {
      return mapName.get(name);
    }
  }

}