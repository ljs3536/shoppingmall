package com.hertz.shoppingMall.ml.controller;

import com.hertz.shoppingMall.ml.dto.MLModelForm;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.service.MLModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/models")
public class MLModelController {

    private final MLModelService mlModelService;

    @GetMapping("/list")
    public String getModelManagementPage(Model model) {
        List<MLModelForm> allModels = mlModelService.getMLModelAllList();
        model.addAttribute("recommendModels", allModels.stream()
                .filter(m -> m.getType() == ModelType.RECOMMEND)
                .toList());
        model.addAttribute("predictModels", allModels.stream()
                .filter(m -> m.getType() == ModelType.PREDICT)
                .toList());
        return "model/list"; // 위 HTML 이름
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Void> addModel(@RequestBody MLModelForm form) {
        mlModelService.addNewModel(form);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<Void> updateModel(@PathVariable("id") Long id, @RequestBody MLModelForm form) {
        mlModelService.updateModelInfo(id, form.getName(), form.getDescription());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    @ResponseBody
    public ResponseEntity<Void> activateModel(@PathVariable("id") Long id) {
        mlModelService.activateOnlyThisModel(id); // 같은 타입 전체 비활성화 후 이 모델만 활성화
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recommend/train")
    @ResponseBody
    public ResponseEntity<String> trainRecommendModel(@RequestBody Map<String, String> body) {
        String algo = body.get("algo");
        String result = mlModelService.trainRecommendModel(algo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/predict/train")
    @ResponseBody
    public ResponseEntity<String> trainPredictModel(@RequestBody Map<String, String> body) {
        String algo = body.get("algo");
        String result = mlModelService.trainPredictModel(algo);
        return ResponseEntity.ok(result);
    }
}
