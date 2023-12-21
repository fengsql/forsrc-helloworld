package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询订单表返回的数据", description = "订单表")
@Data
public class RepOrder {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "订单表列表", name = "rows", dataType = "List", required = false)
  private List<OrderRow> rows;

  @Data
  public static class OrderRow {

    @ApiModelProperty(value = "订单编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

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

    @ApiModelProperty(value = "订单状态", name = "orderStatus", dataType = "Byte", required = true)
    private Byte orderStatus;

    @ApiModelProperty(value = "订单状态名称", name = "orderStatus_", dataType = "String", required = true)
    private String orderStatus_;

    @ApiModelProperty(value = "支付状态", name = "payStatus", dataType = "Byte", required = true)
    private Byte payStatus;

    @ApiModelProperty(value = "支付状态名称", name = "payStatus_", dataType = "String", required = true)
    private String payStatus_;

    @ApiModelProperty(value = "金额数", name = "amount", dataType = "Integer", required = true)
    private Integer amount;

    @ApiModelProperty(value = "成功金额", name = "amountSuccess", dataType = "Integer", required = false)
    private Integer amountSuccess;

    @ApiModelProperty(value = "第三方订单号", name = "thirdOrderNo", dataType = "String", required = false)
    private String thirdOrderNo;

    @ApiModelProperty(value = "发送时间", name = "sendTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date sendTime;

    @ApiModelProperty(value = "支付时间", name = "payTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date payTime;

    @ApiModelProperty(value = "通知时间", name = "notifyTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date notifyTime;

    @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

  }

}