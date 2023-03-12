package com.example.common.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ToolDownload {

  /**
   * 文件支持分块下载和断点续传
   * @param request  请求
   * @param response 响应
   * @param filePath 文件完整路径
   */
  public static void download(HttpServletRequest request, HttpServletResponse response, String filePath) {
    String range = request.getHeader("Range");
    File file = new File(filePath);
    long startByte = 0;
    long endByte = file.length() - 1;
    //    log.info("文件开始位置：{}，文件结束位置：{}，文件总长度：{}", startByte, endByte, file.length());
    log.info("download file length: {}. file: {}", file.length(), filePath);
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
        log.error("Range Occur Error,Message:{}", e.getLocalizedMessage());
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
      byte[] buff = new byte[4096];
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
      randomAccessFile.close();
      //      log.info("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);
      log.info("download ok. file: {}", filePath);
    } catch (ClientAbortException e) {
      log.info("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
    } catch (IOException e) {
      log.error("用户下载IO异常!", e);
    } finally {
      try {
        if (randomAccessFile != null) {
          randomAccessFile.close();
        }
      } catch (IOException e) {
      }
    }
  }

  public static void download(HttpServletResponse response, byte[] content) {
    long contentLength = content.length;
    //文件类型
    String contentType = "application/octet-stream";
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType(contentType);
    response.setHeader("Content-Type", contentType);
    response.setHeader("Content-Length", String.valueOf(contentLength));

    BufferedOutputStream outputStream;
    try {
      outputStream = new BufferedOutputStream(response.getOutputStream());
      outputStream.write(content);
      outputStream.flush();
      response.flushBuffer();
    } catch (IOException e) {
      log.error("用户下载IO异常，Message：{}", e.getLocalizedMessage());
    }
  }
}