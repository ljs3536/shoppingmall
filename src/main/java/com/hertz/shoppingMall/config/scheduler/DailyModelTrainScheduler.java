package com.hertz.shoppingMall.config.scheduler;

import com.hertz.shoppingMall.ml.model.MLModel;
import com.hertz.shoppingMall.ml.model.ModelType;
import com.hertz.shoppingMall.ml.service.MLModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyModelTrainScheduler {

    private final MLModelService mlModelService;

    //매일 오전 3시 실행
    @Scheduled(cron = "0 0 3 * * ?")
    public void trainDailyRecommendationModel(){
        log.info("추천 모델 자동 학습 시작");
        List<MLModel> models = mlModelService.getMLModelAllList();
        models.stream().filter(m->m.getType() == ModelType.RECOMMEND).toList();
        for(MLModel ml : models){
            String result = mlModelService.trainRecommendModel(ml.getName());
            log.info("추천 모델 학습 완료 : {}",result);
        }

    }
}
