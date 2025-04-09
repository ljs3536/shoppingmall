package com.hertz.shoppingMall.utils.log.repository;

import com.hertz.shoppingMall.utils.log.model.ReviewLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewLogRepository extends ElasticsearchRepository<ReviewLog,String> {
}
