package com.example.business.common.service;

import com.forsrc.common.tool.Tool;
import com.example.business.common.define.ReqSendMail;
import com.example.common.spring.base.BaseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class ServiceSendMail extends BaseService {
  private static final String sep_email = ",";

  @Value("${spring.mail.username:null}")
  private String username;

  @Autowired
  private JavaMailSender mailSender;

  // <<----------------------- public -----------------------

  @SneakyThrows
  public void sendEmail(ReqSendMail reqSendMail) {
    throwNull(reqSendMail);
    String email = reqSendMail.getEmail();
    String subject = reqSendMail.getSubject();
    String content = reqSendMail.getContent();
    throwNull(email);
    throwNull(subject);

    String[] emails = Tool.split(email, sep_email);
    String filePath = reqSendMail.getFilePath();
    if (Tool.isNull(filePath)) {
      sendHtml(emails, subject, content);
    } else {
      sendFile(emails, subject, content, filePath);
    }
  }

  // >>----------------------- public -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- normal -----------------------

  /**
   * 简单文本内容发送，无附件。
   * @param emails   接收方 emails。
   * @param subject 邮件主题。
   * @param text    邮件内容。
   */
  private void sendText(String[] emails, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(username);  //这里指的是发送者的账号
    message.setTo(emails);
    message.setSubject(subject);
    message.setText(text);
    //发送邮件
    mailSender.send(message);
    log.info("sendText ok. emails: {}.", (Object) emails);
  }

  /**
   * html 内容发送，无附件。
   * @param emails   接收方 emails。
   * @param subject 邮件主题。
   * @param html    html格式邮件内容。
   */
  @SneakyThrows
  private void sendHtml(String[] emails, String subject, String html) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(username);
    helper.setTo(emails);
    helper.setSubject(subject);
    helper.setText(html, true);
    mailSender.send(message);
    log.info("sendHtml ok. emails: {}.", (Object) emails);
  }

  /**
   * 发送带附件的邮件。
   * @param emails    接收方 emails。
   * @param subject  邮件主题。
   * @param html     邮件内容。
   * @param filePath 附件文件路径。
   */
  private void sendFile(String[] emails, String subject, String html, String filePath) throws MessagingException, UnsupportedEncodingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");  // 设置utf-8或GBK编码，否则邮件会有乱码
    messageHelper.setFrom(username);
    messageHelper.setTo(emails);
    messageHelper.setSubject(subject);
    messageHelper.setText(html, true);
    FileSystemResource file = new FileSystemResource(new File(filePath));
    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
    messageHelper.addAttachment(fileName, file); //可以添加多个
    mailSender.send(mimeMessage);
    log.info("sendFile ok. emails: {}.", (Object) emails);
  }

  // >>>----------------------- normal -----------------------

  // <<<----------------------- tool -----------------------

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}