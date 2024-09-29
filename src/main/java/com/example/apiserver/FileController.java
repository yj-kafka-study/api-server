package com.example.apiserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @GetMapping("/{fileUuid}")
    public FileGetResponse  getFile(@PathVariable String fileUuid) {
        return new FileGetResponse("test_filename.png", fileUuid, "test_download_url");
    }
}
