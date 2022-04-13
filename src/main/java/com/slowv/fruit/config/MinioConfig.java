package com.slowv.fruit.config;

import com.slowv.fruit.web.errors.BusinessException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.Data;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MinioConfig {

    @Value("${app.services.minio.url}")
    private String url;
    @Value("${app.services.minio.accessKey}")
    private String accessKey;
    @Value("${app.services.minio.secretKey}")
    private String secretKey;
    @Value("${app.services.minio.bucket}")
    private String bucket;

    @Bean
    public MinioClient minioClient() {
        final var minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        makeBucket(bucket, minioClient);
        return minioClient;
    }

    private void makeBucket(String bucket, MinioClient client) {
        try {
            boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!exists) client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            throw new BusinessException(400, "Unable to make bucket");
        }
    }
}
