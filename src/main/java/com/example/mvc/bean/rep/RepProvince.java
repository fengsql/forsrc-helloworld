package com.example.mvc.bean.rep;

import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "综合查询省表返回的数据", description = "省表")
@Data
public class RepProvince {

  @ApiModelProperty(value = "总记录数", name = "total", dataType = "int", required = false)
  private int total;
  @ApiModelProperty(value = "省表列表", name = "rows", dataType = "List", required = false)
  private List<ProvinceRow> rows;

  @Data
  public static class ProvinceRow {

    @ApiModelProperty(value = "省编号", name = "id", dataType = "Integer", required = true)
    private Integer id;

    @ApiModelProperty(value = "省名称", name = "provinceName", dataType = "String", required = true)
    private String provinceName;

  }

}