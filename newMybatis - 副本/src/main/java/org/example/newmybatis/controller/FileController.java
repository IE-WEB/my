package org.example.newmybatis.controller;

import cn.hutool.core.io.FileUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.example.newmybatis.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件相关接口
 */
@RestController
@RequestMapping("/files")
public class FileController {
    // 不将文件地址写死
    private static final String filePath = System.getProperty("user.dir") + "/files/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        try {
            // 拿到文件名字
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                return Result.error();
            }
            // 判断保存目录是否存在，不存在则创建
            if (!FileUtil.isDirectory(filePath)) {
                FileUtil.mkdir(filePath);
            }
            // 生成新的文件名，使用时间戳和原始文件名拼接
            String fileName = System.currentTimeMillis() + "_" + originalFilename;
            // 设置文件完整保存路径
            String realPath = filePath + fileName;
            // 写入文件
            FileUtil.writeBytes(file.getBytes(), realPath);
            String url="http://localhost:8080/files/download/"+fileName;

            return Result.success(url);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error();
        }

    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) {
        String realpath = filePath + fileName;
        if (!FileUtil.exist(realpath)) {
            // 处理文件不存在的情况
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");
            byte[] bytes = FileUtil.readBytes(realpath);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}