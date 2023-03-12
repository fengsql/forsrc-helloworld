package com.example.tpi.base;

import com.forsrc.common.configure.okhttp.BeanOkHttp;
import com.forsrc.common.tool.Tool;
import com.forsrc.common.tool.ToolDateTime;
import com.forsrc.common.tool.ToolFile;
import com.forsrc.common.tool.ToolPath;
import com.example.common.spring.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public abstract class BTpiSend extends BaseService {

  private static final int min_seq = 100000;
  private static final int max_seq = 1000000;

  private static final String upload_folder_default = "upload";
  private static final String base64_file_ext = ".base64";

  private static int seqIndex = 0;

  @Value("${website.upload.folder:upload}")
  private String uploadFolder;

  @Resource
  private BeanOkHttp beanOkHttp;

  // <<----------------------- public -----------------------

  // >>----------------------- public -----------------------

  // <<----------------------- protected -----------------------

  protected String sendPost(String url, String data, Map<String, String> headers) {
    return beanOkHttp.postJson(url, data, headers);
  }

  protected String sendPost(String url, String data) {
    return beanOkHttp.postJson(url, data);
  }

  protected String saveFile(String base64) {
    String webRootPath = ToolPath.getClassPath();
    String folder = !Tool.isNull(uploadFolder) ? uploadFolder : upload_folder_default;

    String uploadPath = ToolFile.joinFileName(webRootPath, folder);
    ToolFile.forcePath(uploadPath);
    String fileName = ToolFile.getRandomFile(uploadPath, base64_file_ext);
    boolean ok = ToolFile.writeFile(fileName, base64);
    if (!ok) {
      return null;
    }
    fileName = Tool.subString(fileName, webRootPath.length());
    return fileName;
  }

  // >>----------------------- protected -----------------------

  // <<----------------------- private -----------------------

  // <<<----------------------- common -----------------------

  // >>>----------------------- common -----------------------

  // <<<----------------------- tool -----------------------

  private synchronized static int getNextSeq() {
    seqIndex++;
    if (seqIndex >= max_seq) {
      seqIndex = 0;
    }
    return seqIndex;
  }

  private String getNextMessageId() {
    int seq = getNextSeq() + min_seq;
    long ms = ToolDateTime.now().getTime();
    return Tool.toString(ms) + Tool.toString(seq);
  }

  // >>>----------------------- tool -----------------------

  // >>----------------------- private -----------------------

}