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
    private final KafkaProducer kafkaProducer;

    @GetMapping("/{fileUuid}")
    public FileGetResponse  getFile(@PathVariable String fileUuid) {
        log.info("Call api at {}", LocalDateTime.now());
        kafkaProducer.create();
        return new FileGetResponse("test_filename.png", fileUuid, "test_download_url");
    }
}
