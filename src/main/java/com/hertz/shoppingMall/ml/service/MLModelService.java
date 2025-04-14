package com.hertz.shoppingMall.ml.service;

import com.hertz.shoppingMall.ml.dto.MLModelForm;
import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.repository.MLModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MLModelService {

    private final MLModelRepository mlModelRepository;

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
