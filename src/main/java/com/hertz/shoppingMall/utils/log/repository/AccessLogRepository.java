package com.hertz.shoppingMall.utils.log.repository;

import com.hertz.shoppingMall.utils.log.model.AccessLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccessLogRepository extends ElasticsearchRepository<AccessLog, String> {
}
