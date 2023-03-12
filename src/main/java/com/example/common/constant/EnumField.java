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

}