package com.example.app;

import com.forsrc.common.spring.named.NamedGenerator;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.example.*", "com.forsrc.*"}, nameGenerator = NamedGenerator.class)
@MapperScan({"com.example.**.dao"})
@Slf4j
public class LocalApplication {

  public static void main(String[] args) {
    log.info("application start.");
    SpringApplication springApplication = new SpringApplication(LocalApplication.class);
    ApplicationContext applicationContext = springApplication.run(args);
    try {
      Environment env = applicationContext.getEnvironment();
      String appName = env.getProperty("spring.application.name");
      String profile = Arrays.toString(env.getActiveProfiles());
      String host = InetAddress.getLocalHost().getHostAddress();
      String port = env.getProperty("server.port");
      String protocol = "http";
      if (env.getProperty("server.ssl.key-store") != null) {
        protocol = "https";
      }
      String msg = "\n";
      msg += "==================================================================================================================\n";
      msg += "                                                Application start ok.\n";
      msg += "    App:     " + appName + "\n";
      msg += "    Profile: " + profile + "\n";
      msg += "    Url:     " + protocol + "://" + host + ":" + port + "\n";
      msg += "==================================================================================================================";
      log.info(msg);
    } catch (Exception ignored) {
    }
  }

}