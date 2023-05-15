package com.example.security.entity;

import com.forsrc.security.base.BLoginResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录的返回信息。可以自定义返回数据。
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BLoginResponse {

  private Integer id;

  private String username;

  private int roleType;

  private String roleName_;

}