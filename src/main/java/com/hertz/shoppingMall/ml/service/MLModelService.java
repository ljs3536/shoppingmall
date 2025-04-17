package com.hertz.shoppingMall.ml.service;

import com.hertz.shoppingMall.ml.dto.MLModelForm;
import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelTrainLog;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.repository.MLModelRepository;
import com.hertz.shoppingMall.ml.repository.ModelTrainLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MLModelService {

    private final MLModelRepository mlModelRepository;

    private final ModelTrainLogRepository modelTrainLogRepository;

    private final WebClient webClient;

    public String trainRecommendModel(String algo) {
        return trainModel(algo, ModelType.RECOMMEND, "/recommend/train");
    }

    public String trainPredictModel(String algo) {
        return trainModel(algo, ModelType.PREDICT, "/predict/train");
    }

    private String trainModel(String algo, ModelType type, String uri) {
        String result;
        boolean success = true;
        try {
            result = webClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Map.of("algo_name", algo))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("{} 모델 학습 성공: {}", type, algo);
        } catch (Exception e) {
            success = false;
            result = e.getMessage();
            log.error("{} 모델 학습 실패: {}", type, result, e);
        }

        // 로그 저장
        modelTrainLogRepository.save(ModelTrainLog.builder()
                .modelName(algo)
                .type(type)
                .success(success)
                .message(result)
                .executedAt(LocalDateTime.now())
                .build());

        return result;
    }

    public List<MLModel> getMLModelAllList(){
        return mlModelRepository.findAll();
    }

    @Transactional
    public void addNewModel(MLModelForm form) {
        MLModel model = MLModel.builder()
                .name(form.getName())
                .active(false)
                .description(form.getDescription())
                .type(form.getType())
                .build();
        mlModelRepository.save(model);
    }


    @Transactional
    public void activateOnlyThisModel(Long modelId) {
        MLModel target = mlModelRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("모델을 찾을 수 없습니다."));
        mlModelRepository.deactivateAllByType(target.getType()); // 같은 타입 비활성화
        target.setActive(true);
        mlModelRepository.save(target);
    }

    @Transactional
    public void updateModelInfo(Long id, String name, String desc) {
        MLModel model = mlModelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("모델을 찾을 수 없습니다."));
        model.setName(name);
        model.setDescription(desc);
    }

    public String getActiveModel(ModelType type) {
        return mlModelRepository.findByModelTypeAndIsActiveTrue(type);
    }
}
