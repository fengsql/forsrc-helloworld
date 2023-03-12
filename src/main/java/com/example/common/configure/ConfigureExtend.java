package com.example.common.configure;

import com.forsrc.common.extend.base.IFieldConvertor;
import com.forsrc.common.extend.work.ExportExcel;
import com.forsrc.common.extend.work.UploadFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ConfigureExtend {

  @Resource
  private IFieldConvertor fieldConvertor;

  @Bean
  public ExportExcel exportExcel() {
    return new ExportExcel(fieldConvertor);
  }

  @Bean
  public UploadFile uploadFile() {
    return new UploadFile();
  }

}