package com.jihyun.portfolio.my.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class ImageUploadController {

    private static final String IMAGE_UPLOAD_DIR = "/Users/jihyun/Documents/project/portfolioSave/save";

    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destinationFile = Paths.get("/Users/jihyun/Documents/project/portfolioSave/save", fileName).toFile();

        // 디렉토리 존재 확인 및 생성
        if (!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace(); // 로그에 에러 출력
            throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
        }

        // 업로드된 파일 URL 반환
        return "/uploaded-images/" + fileName;
    }
}
