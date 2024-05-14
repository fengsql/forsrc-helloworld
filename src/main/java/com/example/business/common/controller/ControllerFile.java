package com.example.business.common.controller;

import com.forsrc.common.extend.work.UploadFile;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "业务-基础-文件上传", description = "业务-基础-文件上传 API", position = 10000)
@RestController
@RequestMapping("/business/file")
@Slf4j
public class ControllerFile {

  @Resource
  private UploadFile uploadFile;

  /**
   * 上传文件。
   * @return 保存成功后返回带有相对路径的文件名。
   */
  @ApiOperationSupport(order = 1)
  @ApiOperation(value = "上传文件或图片", notes = "上传文件或图片，保存成功后返回带有相对路径的文件名。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "upload")
  public String upload(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "file") MultipartFile file) {
    return uploadFile.save(request, response, file);
  }

  /**
   * 上传文件，多个文件。
   * @return 保存成功后返回带有相对路径的文件名。
   */
  @ApiOperationSupport(order = 1)
  @ApiOperation(value = "上传多个文件或图片", notes = "上传多个文件或图片，保存成功后返回带有相对路径的文件名。", response = String.class)
  @RequestMapping(method = RequestMethod.POST, value = "uploads")
  public List<String> uploads(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "files") MultipartFile[] files) {
    return uploadFile.save(request, response, files);
  }

}