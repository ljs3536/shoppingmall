package com.hertz.shoppingMall.ml.model;

import com.hertz.shoppingMall.config.jpa.BaseDateEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "model_config")
public class MLModel extends BaseDateEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_config_id")
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_type", nullable = false)
    private ModelType type; // recommend or predict

    @Column(name = "active", nullable = false)
    private boolean active = true;

    private String description;
}
