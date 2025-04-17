package com.hertz.shoppingMall.ml.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "model_train_log")
public class ModelTrainLog {

    @Id
    @GeneratedValue
    private Long id;

    private String modelName;

    @Enumerated(EnumType.STRING)
    private ModelType type;

    private boolean success;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime executedAt;
}
