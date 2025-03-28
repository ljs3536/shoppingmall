package com.hertz.shoppingMall.utils.exception.image.component;

import com.hertz.shoppingMall.utils.exception.image.model.Image;
import com.hertz.shoppingMall.utils.exception.image.model.ImageType;
import com.hertz.shoppingMall.utils.exception.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
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
public class SaveImageUtil {

    @Value("${file.upload-path}") // yml에서 가져옴
    private String uploadPath;

    private final ImageRepository imageRepository;

    public Image saveImage(MultipartFile file, ImageType imageType, Long referenceId, boolean isMain) throws IOException {
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

        Image image = new Image();
        image.setFileName(fileName);
        image.setFilePath(uploadDir + fileName);
        image.setFileType(file.getContentType());
        image.setImageType(imageType);
        image.setReferenceId(referenceId);
        image.setMain(isMain);

        return imageRepository.save(image);
    }

}
