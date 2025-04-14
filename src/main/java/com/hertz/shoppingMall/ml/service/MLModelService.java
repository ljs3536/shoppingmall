package com.hertz.shoppingMall.ml.service;

import com.hertz.shoppingMall.ml.dto.MLModelForm;
import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.repository.MLModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MLModelService {

    private final MLModelRepository mlModelRepository;

    private final WebClient webClient;

    public String trainRecommendModel(String algo){
        return webClient.post()
                .uri("/recommend/train")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("algo_name", algo))
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 블로킹 방식 사용
    }

    public String trainPredictModel(String algo) {
        return webClient.post()
                .uri("/predict/train")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("algo_name", algo))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public List<MLModelForm> getMLModelAllList(){
        MLModelForm modelForm = new MLModelForm();
        return modelForm.convertToFormList(mlModelRepository.findAll());
    }

    @Transactional
    public void addNewModel(MLModelForm form) {
        MLModel model = new MLModel();
        model.setName(form.getName());
        model.setDescription(form.getDescription());
        model.setType(form.getType());
        model.setActive(false); // 기본값
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

}
