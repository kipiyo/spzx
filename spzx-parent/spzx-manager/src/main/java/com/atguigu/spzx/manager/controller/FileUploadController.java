package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileUploadController
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 13:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/fileUpload")
    public Result<String> fileUploadService(@RequestParam("file") MultipartFile multipartFile) {
        String fileUrl = fileUploadService.fileupload(multipartFile);
        return Result.build(fileUrl, ResultCodeEnum.SUCCESS);
    }
}
