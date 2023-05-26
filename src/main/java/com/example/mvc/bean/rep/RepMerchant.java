package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询商户表返回的数据", description = "商户表")
@Data
public class RepMerchant {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "商户表列表", name = "rows", dataType = "List", required = false)
  private List<MerchantRow> rows;

  @Data
  public static class MerchantRow {

    @ApiModelProperty(value = "商户编号", name = "merchantId", dataType = "Integer", required = true)
    private Integer merchantId;

    @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer", required = true)
    private Integer cityId;

    @ApiModelProperty(value = "市名称(市编号)", name = "cityName", dataType = "String", required = true)
    private String cityName;

    @ApiModelProperty(value = "用户编号", name = "userId", dataType = "Integer", required = true)
    private Integer userId;

    @ApiModelProperty(value = "角色类型(用户编号)", name = "roleType", dataType = "Byte", required = true)
    private Byte roleType;

    @ApiModelProperty(value = "角色类型(用户编号)名称", name = "roleType_", dataType = "String", required = true)
    private String roleType_;

    @ApiModelProperty(value = "性别(用户编号)", name = "sexType", dataType = "Byte", required = true)
    private Byte sexType;

    @ApiModelProperty(value = "性别(用户编号)名称", name = "sexType_", dataType = "String", required = true)
    private String sexType_;

    @ApiModelProperty(value = "用户状态(用户编号)", name = "userStatus", dataType = "Byte", required = true)
    private Byte userStatus;

    @ApiModelProperty(value = "用户状态(用户编号)名称", name = "userStatus_", dataType = "String", required = true)
    private String userStatus_;

    @ApiModelProperty(value = "用户名(用户编号)", name = "username", dataType = "String", required = true)
    private String username;

    @ApiModelProperty(value = "电子邮件(用户编号)", name = "email", dataType = "String", required = false)
    private String email;

    @ApiModelProperty(value = "积分(用户编号)", name = "score", dataType = "Long", required = false)
    private Long score;

    @ApiModelProperty(value = "次数(用户编号)", name = "times", dataType = "Integer", required = false)
    private Integer times;

    @ApiModelProperty(value = "头像(用户编号)", name = "headImgUrl", dataType = "String", required = false)
    private String headImgUrl;

    @ApiModelProperty(value = "商户类型", name = "merchantType", dataType = "Integer", required = false)
    private Integer merchantType;

    @ApiModelProperty(value = "商户类型名称", name = "merchantType_", dataType = "String", required = false)
    private String merchantType_;

    @ApiModelProperty(value = "商户名", name = "mchName", dataType = "String", required = false)
    private String mchName;

    @ApiModelProperty(value = "商户号", name = "mchNo", dataType = "String", required = false)
    private String mchNo;

    @ApiModelProperty(value = "Appid", name = "appid", dataType = "String", required = false)
    private String appid;

    @ApiModelProperty(value = "秘钥", name = "secret", dataType = "String", required = false)
    private String secret;

    @ApiModelProperty(value = "联系电话", name = "phone", dataType = "String", required = false)
    private String phone;

    @ApiModelProperty(value = "地址", name = "address", dataType = "String", required = false)
    private String address;

  }

}