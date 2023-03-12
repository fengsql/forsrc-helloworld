package com.example.mvc.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商户表", description = "商户表")
@Data
public class Merchant {
  /**
   * 商户编号
   */
  @ApiModelProperty(value = "商户编号", name = "merchantId", dataType = "String", required = true)
  private String merchantId;

  /**
   * 市编号
   */
  @ApiModelProperty(value = "市编号", name = "cityId", dataType = "Integer", required = true)
  private Integer cityId;

  /**
   * 用户编号
   */
  @ApiModelProperty(value = "用户编号", name = "userId", dataType = "Long", required = true)
  private Long userId;

  /**
   * 商户类型
   */
  @ApiModelProperty(value = "商户类型", name = "merchantType", dataType = "Integer", required = false)
  private Integer merchantType;

  /**
   * 商户名
   */
  @ApiModelProperty(value = "商户名", name = "mchName", dataType = "String", required = false)
  private String mchName;

  /**
   * 商户号
   */
  @ApiModelProperty(value = "商户号", name = "mchNo", dataType = "String", required = false)
  private String mchNo;

  /**
   * Appid
   */
  @ApiModelProperty(value = "Appid", name = "appid", dataType = "String", required = false)
  private String appid;

  /**
   * 秘钥
   */
  @ApiModelProperty(value = "秘钥", name = "secret", dataType = "String", required = false)
  private String secret;

  /**
   * 联系电话
   */
  @ApiModelProperty(value = "联系电话", name = "phone", dataType = "String", required = false)
  private String phone;

  /**
   * 地址
   */
  @ApiModelProperty(value = "地址", name = "address", dataType = "String", required = false)
  private String address;

  /**
   * 说明
   */
  @ApiModelProperty(value = "说明", name = "info", dataType = "String", required = false)
  private String info;

  /**
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

  /**
   * 更新时间
   */
  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

}