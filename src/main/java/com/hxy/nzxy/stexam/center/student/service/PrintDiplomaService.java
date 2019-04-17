package com.hxy.nzxy.stexam.center.student.service;

import org.springframework.web.multipart.MultipartFile;

public interface PrintDiplomaService {

    String batchImport(String fileName, MultipartFile file);
}
