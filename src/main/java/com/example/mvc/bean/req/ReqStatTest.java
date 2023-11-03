package com.example.mvc.bean.req;

import com.example.common.spring.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询统计信息请求的参数", description = "统计信息", parent = BaseRequest.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqStatTest extends BaseRequest {

  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "日期", name = "dayNo", dataType = "String")
  private String dayNo;

  @ApiModelProperty(value = "平均时间", name = "argTime", dataType = "Double[]")
  private Double[] argTime;

  @ApiModelProperty(value = "总体时间", name = "totalTime", dataType = "Double[]")
  private Double[] totalTime;

  @ApiModelProperty(value = "平均个数", name = "argNum", dataType = "Double[]")
  private Double[] argNum;

  @ApiModelProperty(value = "总共个数", name = "totalSum", dataType = "Double[]")
  private Double[] totalSum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}