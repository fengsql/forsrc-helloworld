package com.example.mvc.bean.detail;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "商户表详情", description = "商户表")
@Data
public class DetailMerchant {

  @ApiModelProperty(value = "商户编号", name = "merchantId", dataType = "Integer", required = true)
  private Integer merchantId;

  @ApiModelProperty(value = "市名称", name = "cityName", dataType = "String", required = true)
  private String cityName;

  @ApiModelProperty(value = "用户名", name = "username", dataType = "String", required = true)
  private String username;

  @ApiModelProperty(value = "商户类型", name = "merchantType", dataType = "Integer", required = false)
  private Integer merchantType;

  @ApiModelProperty(value = "商户类型", name = "merchantType_", dataType = "String", required = false)
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

  @ApiModelProperty(value = "说明", name = "info", dataType = "String", required = false)
  private String info;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date updateTime;

}