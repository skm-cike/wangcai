package com.wangcai.common.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by 陆华 on 15-12-25 下午1:19
 */
public class MongoUtil {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Object obj) {
        if (obj == null) {
            return;
        }
    }
}
