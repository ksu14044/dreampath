package com.korit.dreampath_back.controller;

import com.korit.dreampath_back.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/file")
public class FilesController {
    @Autowired
    private FileService fileService;

    @Value("${user.dir}")
    private String rootPath;

    @Operation(summary = "파일 다운로드 API")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> fileDownloadApi(@PathVariable("fileName") String fileName) throws IOException {
//        파일 경로
        Path filePath = Paths.get(rootPath + "/upload/user/post/" + fileName);

//            실제 파일 데이터 가져옴
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filePath.toString()));

        //        String fileName = "<file_name_string>";
        //        logger.info("Success download input excel file : " + filePath);
//        응답 헤더 셋팅
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }
}
