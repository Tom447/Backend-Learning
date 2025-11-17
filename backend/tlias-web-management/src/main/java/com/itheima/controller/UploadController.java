package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //文件本地存储
   /* @PostMapping("/upload")
    public Result upload(String username , Integer age , MultipartFile image) throws Exception {
        log.info("文件上传....{}, {}", username, age);
        //接收到文件之后, 需要将文件存储起来 , 比如: E:/images/
        //image.transferTo(new File("E:\\images\\1.txt"));

        //1. 构建新的文件名 (不能重复)
        //1.1 生成不重复的文件名
        String uuid = UUID.randomUUID().toString(); //4a780e89-722a-4b09-a1d2-3f7a792308c6

        //1.2 获取原始文件名
        String originalFilename = image.getOriginalFilename(); //3.jpg

        //1.3 组装新的文件名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".")); //.jpg
        String newFileName = uuid + extName;

        //2. 将文件转存到磁盘目录中
        image.transferTo(new File("E:\\images\\"+newFileName));

        return Result.success();
    }
    */

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
        log.info("进行文件上传, 将文件上传到阿里云OSS");
        String url = aliyunOSSUtils.upload(image);
        return Result.success(url);
    }

}
