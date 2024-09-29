package com.example.apiserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileGetResponse {
    private String filename;
    private String uuid;
    private String downloadUrl;
}
