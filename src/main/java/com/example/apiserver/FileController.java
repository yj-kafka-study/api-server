package com.example.apiserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {
    private final CustomKafkaProducer customKafkaProducer;
    int a = 0;

    @GetMapping("/{fileUuid}")
    public FileGetResponse  getFile(@PathVariable String fileUuid) {
        log.info("Call api at {}", LocalDateTime.now());

        FileMeta fileMeta = new FileMeta(1L, "fileName", "fileType", "fileSize", "filePath", "fileHash");
        for(int i=0; i<5; i++){
            customKafkaProducer.create("testString" + a++);
        }
        return new FileGetResponse("test_filename.png", fileUuid, "test_download_url");
    }
}
