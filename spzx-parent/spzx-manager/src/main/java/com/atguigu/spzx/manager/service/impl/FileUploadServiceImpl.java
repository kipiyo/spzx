package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.properties.MinioProperties;
import com.atguigu.spzx.manager.service.FileUploadService;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.minio.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


/**
 * ClassName: FileUploadServiceImpl
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/13 14:50
 * @Version 1.0
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    private MinioProperties minioProperties;

    @Override
    public String fileupload(MultipartFile multipartFile) {
        //创建一个Minio的客户端对象
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                    .build();
            //创建bucket
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            //判断bucket是否存在
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println("bucket已存在");
            }
            //获取文件名
            //文件名存储唯一 uuid生成
            //根据上传文件时间 创建文件夹结构 2026/12/12/xxxxx/01.jpg
            String dateDir = DateUtil.format(new Date(), "yyyy/MM/dd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = dateDir + "/" + uuid + multipartFile.getOriginalFilename();


            //上传文件
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(minioProperties.getBucketName())
                            .object(fileName)
                            .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                            .build());

            //获取上传文件在minio路径
            //http://127.0.0.1:9000/spzx-b/01.jpg
            String url = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + fileName;

            return url;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
