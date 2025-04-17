package com.hertz.shoppingMall.statistics.controller;

import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.service.MLModelService;
import com.hertz.shoppingMall.statistics.service.StatisticsInfoService;
import com.hertz.shoppingMall.statistics.service.StatisticsPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/statistics")
@RequiredArgsConstructor
public class AdminStatisticsController {

    private final StatisticsInfoService statisticsInfoService;

    private final StatisticsPredictionService statisticsPredictionService;

    private final MLModelService mlModelService;

    @GetMapping("/info")
    public String getStatisticsPage() {
        return "statistics/info";
    }

    @GetMapping("/stats/yearly/{year}")
    @ResponseBody
    public Mono<Object> getYearlySales(@PathVariable("year") String year) {
        return statisticsInfoService.getYearlySales(year);
    }

    @GetMapping("/stats/age")
    @ResponseBody
    public List<Map<String, Object>> getAgeStats() {
        return (List<Map<String, Object>>) statisticsInfoService.getAgeGroupFavorites().block();
    }

    @GetMapping("/stats/region")
    @ResponseBody
    public List<Map<String, Object>> getRegionStats() {
        return (List<Map<String, Object>>) statisticsInfoService.getRegionFavorites().block();
    }

    @GetMapping("/stats/trend")
    @ResponseBody
    public List<Map<String, Object>> getTrendStats() {
        return (List<Map<String, Object>>) statisticsInfoService.getMonthlyTrends().block();
    }

    @GetMapping("/prediction")
    public String predictionForm(Model model) {
        List<MLModel> models = mlModelService.getMLModelAllList();
        List<String> algorithms = models.stream().filter(m -> m.getType() == ModelType.PREDICT).map(MLModel::getName).toList();
        model.addAttribute("algorithms", algorithms);
        return "statistics/predict";
    }

    @PostMapping("/prediction")
    @ResponseBody
    public Map predictResult(@RequestParam(name = "productName") String productName,
                             @RequestParam(name = "algorithm") String algorithm) {
        return statisticsPredictionService.predictQuantity(productName, algorithm).block();
    }

}
