package com.example.mvc.bean.req;

import com.forsrc.common.spring.base.BRequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询商品类别表请求的参数", description = "商品类别表", parent = BRequestPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqGoodsKind extends BRequestPage {

  @ApiModelProperty(value = "商品编号", name = "id", dataType = "Integer")
  private Integer id;

  @ApiModelProperty(value = "类别名称", name = "name", dataType = "String")
  private String name;

  @ApiModelProperty(value = "更新时间", name = "updateTime", dataType = "java.util.Date[]")
  private java.util.Date[] updateTime;

  @ApiModelProperty(value = "输入时间", name = "addTime", dataType = "java.util.Date[]")
  private java.util.Date[] addTime;

}