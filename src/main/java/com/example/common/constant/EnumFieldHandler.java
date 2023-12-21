package com.example.common.constant;

import com.forsrc.common.spring.type.BaseEnumTypeHandler;
import com.forsrc.common.tool.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumFieldHandler {

  public static class HandleRoleType extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.RoleType type = EnumField.RoleType.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandleSexType extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.SexType type = EnumField.SexType.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandleUserStatus extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.UserStatus type = EnumField.UserStatus.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandleMerchantType extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.MerchantType type = EnumField.MerchantType.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandleOnlineStatus extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.OnlineStatus type = EnumField.OnlineStatus.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandleOrderStatus extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.OrderStatus type = EnumField.OrderStatus.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

  public static class HandlePayStatus extends BaseEnumTypeHandler {
    @Override
    public String getNullableResult(ResultSet resultSet, String fieldName) throws SQLException {
      if (resultSet.getString(fieldName) == null) {
        return null;
      }
      EnumField.PayStatus type = EnumField.PayStatus.get(resultSet.getInt(fieldName));
      return type == null ? null : type.getTitle();
    }
  }

}