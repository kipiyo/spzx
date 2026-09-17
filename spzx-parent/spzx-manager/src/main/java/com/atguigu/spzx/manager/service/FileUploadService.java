package com.atguigu.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadService
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 14:49
 * @Version 1.0
 */
public interface FileUploadService {
     String fileupload(MultipartFile multipartFile);
}
