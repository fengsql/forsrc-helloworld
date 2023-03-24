package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询统计信息返回的数据", description = "统计信息")
@Data
public class RepStatTest {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "统计信息列表", name = "rows", dataType = "List", required = false)
  private List<StatTestRow> rows;

  @Data
  public static class StatTestRow {

    @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "日期", name = "dayNo", dataType = "String", required = false)
    private String dayNo;

    @ApiModelProperty(value = "平均Dep时间", name = "crsDepTime", dataType = "Double", required = false)
    private Double crsDepTime;

    @ApiModelProperty(value = "平均Arr时间", name = "crsArrTime", dataType = "Double", required = false)
    private Double crsArrTime;

    @ApiModelProperty(value = "平均Flight个数", name = "flightNum", dataType = "Double", required = false)
    private Double flightNum;

    @ApiModelProperty(value = "总共Flight个数", name = "flightSum", dataType = "Double", required = false)
    private Double flightSum;

    @ApiModelProperty(value = "平均Elapsed时间", name = "crsElapsedTime", dataType = "Double", required = false)
    private Double crsElapsedTime;

    @ApiModelProperty(value = "平均距离", name = "distance", dataType = "Double", required = false)
    private Double distance;

    @ApiModelProperty(value = "总共距离", name = "distanceSum", dataType = "Double", required = false)
    private Double distanceSum;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}