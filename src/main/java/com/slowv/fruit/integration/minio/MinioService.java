package com.slowv.fruit.integration.minio;

import com.slowv.fruit.config.ApplicationProperties;
import com.slowv.fruit.web.errors.BusinessException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class MinioService {
    private final MinioClient client;

    private final ApplicationProperties properties;

    public String upload(String bucket, long createdAt, List<MultipartFile> files) {
        var images = new StringBuilder();
        for (MultipartFile file : files) {
            try {
                var pathFile = buildImagePath(Long.toString(createdAt), file.getOriginalFilename());
                client.putObject(PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(pathFile)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(Objects.isNull(file.getContentType()) ? "image/png; image/jpg;" : file.getContentType())
                        .build());
                images.append(pathFile).append(";");
            } catch (Exception e) {
                throw new BusinessException("400", "Unable to upload file", e);
            }
        }

        if (images.length() == 0) {
            throw new BusinessException("400", "Files must be not null!");
        }

        return images.toString();
    }

    private String buildImagePath(String folder, String name) {
        return String.join("/", folder, properties.getSpringActive(), normalize(name));
    }

    private String normalize(String value) {
        var normalize = Normalizer.normalize(value, Normalizer.Form.NFD);
        var pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        var result = pattern.matcher(normalize).replaceAll("");
        result = result.toLowerCase();
        // Xóa ký tự đĐ
        result = result.replaceAll("[đĐ]", "d");
        // Xóa ký tự đặc biệt
        result = result.replaceAll("([^0-9a-z\\s])", "");
        // Xoá khoảng trắng
        result = result.replaceAll("[\\s]", "-");
        if (result.endsWith("-")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
