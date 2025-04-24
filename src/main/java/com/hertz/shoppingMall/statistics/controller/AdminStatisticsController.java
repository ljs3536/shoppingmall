package com.hertz.shoppingMall.statistics.controller;

import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.service.MLModelService;
import com.hertz.shoppingMall.statistics.service.StatisticsInfoService;
import com.hertz.shoppingMall.statistics.service.StatisticsPredictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/statistics")
@RequiredArgsConstructor
@Slf4j
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
    public Object getYearlySales(@PathVariable("year") String year) {
        try {
            return statisticsInfoService.getYearlySales(year).block();
        } catch (Exception e){
            log.error("Error while getting yearly stats", e);
            return new Object();
        }
    }

    @GetMapping("/stats/age")
    @ResponseBody
    public List<Map<String, Object>> getAgeStats() {
        try {
            return (List<Map<String, Object>>) statisticsInfoService.getAgeGroupFavorites().block();
        } catch (Exception e){
            log.error("Error while getting age stats", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/stats/region")
    @ResponseBody
    public List<Map<String, Object>> getRegionStats() {
        try {
            return (List<Map<String, Object>>) statisticsInfoService.getRegionFavorites().block();
        } catch (Exception e){
            log.error("Error while getting region stats", e);
            return Collections.emptyList();
        }
    }

    @GetMapping("/stats/trend")
    @ResponseBody
    public List<Map<String, Object>> getTrendStats() {
        try {
            return (List<Map<String, Object>>) statisticsInfoService.getMonthlyTrends().block();
        } catch (Exception e){
            log.error("Error while getting trend stats", e);
            return Collections.emptyList();
        }
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
