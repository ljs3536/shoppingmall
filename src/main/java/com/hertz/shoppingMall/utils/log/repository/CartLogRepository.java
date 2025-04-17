package com.hertz.shoppingMall.utils.log.repository;

import com.hertz.shoppingMall.utils.log.model.CartLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CartLogRepository extends ElasticsearchRepository<CartLog, String> {
}
