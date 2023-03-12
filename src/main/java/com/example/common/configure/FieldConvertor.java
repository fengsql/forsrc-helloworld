package com.example.common.configure;

import com.forsrc.common.extend.base.IFieldConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FieldConvertor implements IFieldConvertor {

  @Override
  public String formatDatetime(String fieldName) {
    return "date_format(" + fieldName + ", '%Y-%m-%d %T')";
  }

}