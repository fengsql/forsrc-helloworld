package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询用户返回的数据", description = "用户")
@Data
public class RepUser {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "用户列表", name = "rows", dataType = "List", required = false)
  private List<UserRow> rows;

  @Data
  public static class UserRow {

    @ApiModelProperty(value = "用户编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "角色类型", name = "roleType", dataType = "Byte", required = true)
    private Byte roleType;

    @ApiModelProperty(value = "角色类型名称", name = "roleType_", dataType = "String", required = true)
    private String roleType_;

    @ApiModelProperty(value = "性别", name = "sexType", dataType = "Byte", required = true)
    private Byte sexType;

    @ApiModelProperty(value = "性别名称", name = "sexType_", dataType = "String", required = true)
    private String sexType_;

    @ApiModelProperty(value = "用户状态", name = "userStatus", dataType = "Byte", required = true)
    private Byte userStatus;

    @ApiModelProperty(value = "用户状态名称", name = "userStatus_", dataType = "String", required = true)
    private String userStatus_;

    @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
    private String username;

    @ApiModelProperty(value = "电子邮件", name = "email", dataType = "String", required = false)
    private String email;

    @ApiModelProperty(value = "积分", name = "score", dataType = "Long", required = false)
    private Long score;

    @ApiModelProperty(value = "次数", name = "times", dataType = "Integer", required = false)
    private Integer times;

    @ApiModelProperty(value = "出生日期", name = "birthDate", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date birthDate;

    @ApiModelProperty(value = "头像", name = "headImgUrl", dataType = "String", required = false)
    private String headImgUrl;

    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}