package com.hertz.shoppingMall.utils.log.repository;

import com.hertz.shoppingMall.utils.log.model.OrderLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderLogRepository extends ElasticsearchRepository<OrderLog,String> {
}
