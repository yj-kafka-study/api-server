package com.example.apiserver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FileMeta implements Serializable {
    private Long fileId;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String filePath;
    private String fileHash;
}
