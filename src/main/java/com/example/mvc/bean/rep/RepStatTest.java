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

    @ApiModelProperty(value = "平均时间", name = "argTime", dataType = "Double", required = false)
    private Double argTime;

    @ApiModelProperty(value = "总体时间", name = "totalTime", dataType = "Double", required = false)
    private Double totalTime;

    @ApiModelProperty(value = "平均个数", name = "argNum", dataType = "Double", required = false)
    private Double argNum;

    @ApiModelProperty(value = "总共个数", name = "totalSum", dataType = "Double", required = false)
    private Double totalSum;

    @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date addTime;

  }

}