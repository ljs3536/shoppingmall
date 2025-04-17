package com.hertz.shoppingMall.utils.exception.image.component;

import com.hertz.shoppingMall.utils.exception.image.model.Image;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import com.hertz.shoppingMall.utils.exception.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveImageUtil {

    @Value("${file.upload-path}") // yml에서 가져옴
    private String uploadPath;

    @Value("${file.access-url}")
    private String accessUrl;

    public Image saveImage(Image image,MultipartFile file, ImageType imageType, Long referenceId, boolean isMain) throws IOException {
        if(file.isEmpty()){
            throw new IllegalArgumentException("빈 파일은 저장할 수 없습니다.");
        }
        String filepath = uploadPath + imageType.name() + "/" + referenceId.toString() + "/";
        // 최종 디렉토리 경로 생성 (폴더 자동 생성 포함)
        Path uploadDir = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // 고유한 파일명 생성
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = uploadDir.resolve(fileName);

        // 파일 저장
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        image.setFileName(fileName);
        image.setFilePath(uploadDir+"");
        image.setFileType(file.getContentType());
        image.setImageType(imageType);
        image.setReferenceId(referenceId);
        image.setMain(isMain);

        return image;
    }
    public void deleteImageFile(Image image){
        try {
            if(image != null) {
                Path filePath = Paths.get(image.getFilePath() + "/" + image.getFileName());
                if (Files.exists(filePath)) {
                    Files.deleteIfExists(filePath);
                }
            }
        } catch (IOException e) {
            // 로그 기록하고 계속 진행
            log.error("이미지 파일 삭제 실패: " + e.getMessage());
        }
    }
}
