package com.example.common.spring.base;

import com.forsrc.common.spring.bean.req.BReqPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel(value = "综合查询基础请求参数", description = "综合查询基础请求参数", parent = BReqPage.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseRequest extends BReqPage {

  @ApiModelProperty(value = "内部查询条件，外部设置无效", name = "innerCondition", dataType = "String", required = false, hidden = true)
  String innerCondition;

}