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

  @ApiModelProperty(value = "平均Dep时间", name = "crsDepTime", dataType = "Double[]")
  private Double[] crsDepTime;

  @ApiModelProperty(value = "平均Arr时间", name = "crsArrTime", dataType = "Double[]")
  private Double[] crsArrTime;

  @ApiModelProperty(value = "平均Flight个数", name = "flightNum", dataType = "Double[]")
  private Double[] flightNum;

  @ApiModelProperty(value = "总共Flight个数", name = "flightSum", dataType = "Double[]")
  private Double[] flightSum;

  @ApiModelProperty(value = "平均Elapsed时间", name = "crsElapsedTime", dataType = "Double[]")
  private Double[] crsElapsedTime;

  @ApiModelProperty(value = "平均距离", name = "distance", dataType = "Double[]")
  private Double[] distance;

  @ApiModelProperty(value = "总共距离", name = "distanceSum", dataType = "Double[]")
  private Double[] distanceSum;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}