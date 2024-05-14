package com.example.common.tool;

import com.forsrc.common.tool.ToolFile;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ToolDownload {
  private static final int buffer_size = 8192;

  /**
   * 文件分块下载，断点续传。
   * @param request  请求
   * @param response 响应
   * @param filePath 完整路径文件名
   */
  @SneakyThrows
  public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) {
    String range = request.getHeader("Range");
    File file = new File(filePath);
    long startByte = 0;
    long endByte = file.length() - 1;
    log.info("download file length: {}. range: {}. file: {}", file.length(), range, filePath);
    if (range != null && range.contains("bytes=") && range.contains("-")) {
      range = range.substring(range.lastIndexOf("=") + 1).trim();
      String[] ranges = range.split("-");
      try {
        if (ranges.length == 1) { //判断range的类型
          if (range.startsWith("-")) {  //类型一：bytes=-2343
            endByte = Long.parseLong(ranges[0]);
          } else if (range.endsWith("-")) {  //类型二：bytes=2343-
            startByte = Long.parseLong(ranges[0]);
          }
        } else if (ranges.length == 2) {  //类型三：bytes=22-2343
          startByte = Long.parseLong(ranges[0]);
          endByte = Long.parseLong(ranges[1]);
        }
      } catch (NumberFormatException e) {
        startByte = 0;
        endByte = file.length() - 1;
        log.error("Range occur error! Message: {}", e.getLocalizedMessage());
      }
    }

    long contentLength = endByte - startByte + 1;
    String fileName = file.getName();
    String contentType = request.getServletContext().getMimeType(fileName);
    byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);  //解决下载文件时文件名乱码问题
    String downloadFileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

    response.setHeader("Accept-Ranges", "bytes");  //
    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);  //http状态码要为206
    response.setContentType(contentType);
    response.setHeader("Content-Type", contentType);
    response.setHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
    response.setHeader("Content-Length", String.valueOf(contentLength));
    response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());  // [要下载的开始位置]-[结束位置]/[文件总大小]

    BufferedOutputStream outputStream;
    RandomAccessFile randomAccessFile = null;
    long transmitted = 0;
    try {
      randomAccessFile = new RandomAccessFile(file, "r");
      outputStream = new BufferedOutputStream(response.getOutputStream());
      byte[] buff = new byte[buffer_size];
      int len = 0;
      randomAccessFile.seek(startByte);
      while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
        outputStream.write(buff, 0, len);
        transmitted += len;
      }
      if (transmitted < contentLength) {  //处理不足buff.length部分
        len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
        outputStream.write(buff, 0, len);
        transmitted += len;
      }
      outputStream.flush();
      response.flushBuffer();
      log.info("download ok. file: {}", filePath);
    } finally {
      try {
        if (randomAccessFile != null) {
          randomAccessFile.close();
        }
      } catch (IOException e) {
      }
    }
  }

  /**
   * 下载文件，直接下载，无断点续传
   * @param response 响应
   * @param filePath 完整路径文件名
   */
  @SneakyThrows
  public static void download(HttpServletResponse response, String filePath) {
    if (!ToolFile.existFile(filePath)) {
      log.warn("file not exist. file: " + filePath);
      return;
    }
    String name = ToolFile.getFileName(filePath);
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/octet-stream");
    try {
      response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(name, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      log.error("downFile UnsupportedEncodingException.", e);
    }
    byte[] buff = new byte[buffer_size];
    BufferedInputStream inputStream = null;
    OutputStream outputStream = null;
    try {
      outputStream = response.getOutputStream();
      File file = new File(filePath);
      inputStream = new BufferedInputStream(new FileInputStream(file));
      int count = 0;
      int len;
      while ((len = inputStream.read(buff)) != -1) {
        outputStream.write(buff, 0, len);
        outputStream.flush();
        count += len;
      }
      log.info("downFile ok. file: {}. size: {}", filePath, ToolFile.getFileSizeText(count));
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
      }
    }
  }

  /**
   * 下载文件，直接下载，无断点续传
   * @param response 响应
   * @param content  文件内容
   */
  @SneakyThrows
  public static void download(HttpServletResponse response, byte[] content) {
    long contentLength = content.length;
    //文件类型
    String contentType = "application/octet-stream";
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType(contentType);
    response.setHeader("Content-Type", contentType);
    response.setHeader("Content-Length", String.valueOf(contentLength));

    BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
    outputStream.write(content);
    outputStream.flush();
    response.flushBuffer();
  }
}