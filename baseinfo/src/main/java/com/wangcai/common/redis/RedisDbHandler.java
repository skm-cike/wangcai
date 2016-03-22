package com.wangcai.common.redis;

import com.wangcai.common.db.cache.mybatisredis.SerializeUtil;
import com.wangcai.common.util.SpringContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 陆华 on 15-12-23 上午11:05
 */
public class RedisDbHandler {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Log logger = LogFactory.getLog(RedisDbHandler.class);
    protected static RedisTemplate redisTemplate;
    private static void initRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) SpringContextHolder.getBean("redisTemplate");
        }
    }
    public static void putObject(final Object key, final Object value) {
        initRedisTemplate();
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>putObject:" + key + "=" + value);
        Object succ = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
            }
        });
        if (!(Boolean)succ) {
            throw new RuntimeException("保存数据失败!");
        }
    }

    public Object getObject(final Object key) {
        initRedisTemplate();
        Object val = null;
        val = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] valbyte = connection.get(SerializeUtil.serialize(key.toString()));
                Object value = SerializeUtil.unserialize(valbyte);
                logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>getObject:" + key + "=" + value);
                return value;
            }
        });
        return val;
    }

    public Object removeObject(final Object key) {
        initRedisTemplate();
        Object val = null;
        val = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.expire(SerializeUtil.serialize(key.toString()),0);
            }
        });
        return val;
    }

    public void clear() {
        initRedisTemplate();
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    public int getSize() {
        initRedisTemplate();
        Object obj = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return Integer.valueOf(connection.dbSize().toString());
            }
        });
        return (Integer)obj;
    }

    public ReadWriteLock getReadWriteLock() {
        initRedisTemplate();
        return readWriteLock;
    }
}
