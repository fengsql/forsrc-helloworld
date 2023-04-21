package com.example.business.common.controller;

import com.forsrc.common.extend.bean.ReqExport;
import com.forsrc.common.extend.work.ExportExcel;
import com.forsrc.common.extend.work.UploadFile;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "扩展功能-通用", description = "扩展功能通用 API", position = 10000)
@RestController
@RequestMapping("/business/file")
@Slf4j
public class ControllerFile {

  @Resource
  private UploadFile uploadFile;
  @Resource
  private ExportExcel exportExcel;

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

  /**
   * 输出数据到 excel。
   * @param reqExport 导出数据的参数，table 指定导出的表名；fields 指定导出的字段；condition 为查询数据的条件，json格式。
   */
  @ApiOperationSupport(order = 2)
  @ApiOperation(value = "导出数据", notes = "导出数据到 excel。")
  @RequestMapping(method = RequestMethod.POST, value = "export")
  public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody ReqExport reqExport) {
    exportExcel.work(request, response, reqExport);
  }

}