package com.hertz.shoppingMall.ml.repository;

import com.hertz.shoppingMall.ml.model.ModelTrainLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTrainLogRepository extends JpaRepository<ModelTrainLog, Long> {
}
