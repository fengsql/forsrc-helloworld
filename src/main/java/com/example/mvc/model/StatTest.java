package com.example.mvc.model;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "统计信息", description = "统计信息")
@Data
public class StatTest {
  /**
   * 编号
   */
  @ApiModelProperty(value = "编号", name = "id", dataType = "Integer", required = true)
  private Integer id;

  /**
   * 日期
   */
  @ApiModelProperty(value = "日期", name = "dayNo", dataType = "String", required = false)
  private String dayNo;

  /**
   * 平均时间
   */
  @ApiModelProperty(value = "平均时间", name = "argTime", dataType = "Double", required = false)
  private Double argTime;

  /**
   * 总体时间
   */
  @ApiModelProperty(value = "总体时间", name = "totalTime", dataType = "Double", required = false)
  private Double totalTime;

  /**
   * 平均个数
   */
  @ApiModelProperty(value = "平均个数", name = "argNum", dataType = "Double", required = false)
  private Double argNum;

  /**
   * 总共个数
   */
  @ApiModelProperty(value = "总共个数", name = "totalSum", dataType = "Double", required = false)
  private Double totalSum;

  /**
   * 输入时间
   */
  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date", required = false)
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date addTime;

}