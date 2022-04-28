package com.slowv.fruit.web.rest;

import com.slowv.fruit.config.MinioConfig;
import com.slowv.fruit.integration.minio.MinioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/image")
public class ImageResource {

    private final MinioService minioService;

    private final MinioConfig minioConfig;

    @GetMapping
    public ResponseEntity<ByteArrayResource> downloadImage(@RequestParam String name) {
        final var bytes = minioService.download(minioConfig.getBucket(), name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + name);
        headers.add("Content-type", "application/octet-stream");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ByteArrayResource(bytes));
    }
}
