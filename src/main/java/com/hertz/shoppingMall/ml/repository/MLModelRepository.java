package com.hertz.shoppingMall.ml.repository;

import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MLModelRepository extends JpaRepository<MLModel, Long> {

    @Modifying
    @Query("UPDATE MLModel m SET m.active = false WHERE m.type = :type")
    void deactivateAllByType(@Param("type") ModelType type);
}
