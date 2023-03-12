package com.example.business.common.define;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "发送邮件请求参数", description = "发送邮件请求参数。")
@Data
public class ReqSendMail {

  @ApiModelProperty(value = "邮箱地址", name = "email", dataType = "String", required = true)
  private String email;

  @ApiModelProperty(value = "主题", name = "subject", dataType = "String", required = true)
  private String subject;

  @ApiModelProperty(value = "内容", name = "content", dataType = "String", required = false)
  private String content;

  @ApiModelProperty(value = "附件文件路径", name = "filePath", dataType = "String", required = false)
  private String filePath;

}