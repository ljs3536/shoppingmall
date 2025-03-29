package com.hertz.shoppingMall.utils.exception.image.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Image extends BaseDateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;

    // 연관된 엔티티를 구분하기 위한 필드
    @Enumerated(EnumType.STRING)
    private ImageType imageType; // PRODUCT, MEMBER 등 타입 구분

    @Column(name = "reference_id")
    private Long referenceId; // 해당 이미지가 속한 엔티티의 ID

    // ⭐ 메인 이미지 여부 (true이면 메인 이미지)
    private boolean isMain;
}