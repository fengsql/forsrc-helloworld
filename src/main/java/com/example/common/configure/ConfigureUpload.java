package com.example.common.configure;

import com.forsrc.common.extend.work.UploadFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureUpload {

  @Bean
  public UploadFile uploadFile() {
    return new UploadFile();
  }

}