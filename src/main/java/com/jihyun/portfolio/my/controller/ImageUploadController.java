package com.jihyun.portfolio.my.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageUploadController {

    @Value("${file.upload.path}")
    private String uploadDir;

    @PostMapping("/uploadImage")
    @ResponseBody
    public List<String> uploadImages(@RequestParam("files") List<MultipartFile> files) {
        List<String> uploadedPaths = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
                throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = Paths.get(uploadDir, fileName).toFile();

            // 중복 파일 확인
            if (destinationFile.exists()) {
                // 이미 동일한 파일이 존재하면 경로만 추가하고 저장하지 않음
                uploadedPaths.add("/uploaded-images/" + fileName);
                continue;
            }

            // 디렉토리 확인 및 생성
            if (!destinationFile.getParentFile().exists()) {
                destinationFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(destinationFile);
                uploadedPaths.add("/uploaded-images/" + fileName); // 업로드된 파일 경로 저장
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
            }
        }

        return uploadedPaths; // 업로드된 파일 경로 반환
    }
}
