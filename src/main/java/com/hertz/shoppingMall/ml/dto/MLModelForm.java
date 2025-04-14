package com.hertz.shoppingMall.ml.dto;

import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MLModelForm implements Serializable {
    @Serial //클래스 버전 관리를 위해
    private static final long serialVersionUID = 1L;    //고유 식별자 필드

    private Long id;
    private String name;
    private ModelType type;
    private boolean active;
    private String description;

    public MLModelForm convertToForm(MLModel model){
        MLModelForm form = new MLModelForm();
        form.setId(model.getId());
        form.setName(model.getName());
        form.setType(model.getType());
        form.setActive(model.isActive());
        form.setDescription(model.getDescription());
        return form;
    }

    public List<MLModelForm> convertToFormList(List<MLModel> models){
        return models.stream()
                .map(this::convertToForm)
                .collect(Collectors.toList());
    }
}
