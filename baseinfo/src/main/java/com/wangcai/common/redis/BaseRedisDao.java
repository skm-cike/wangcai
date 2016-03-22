package com.wangcai.common.redis;

import com.wangcai.common.db.IBaseDao;
import com.wangcai.common.db.cache.mybatisredis.SerializeUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陆华 on 15-12-18 上午9:32
 */
public class BaseRedisDao<K, V> extends AbstractBaseRedisDao<K, V> implements IBaseDao<K, V>{
    public boolean add(V v) {
        return false;
    }

    public boolean add(List<V> list) {
        return false;
    }

    public void delete(K key) {

    }

    public void delete(List<K> keys) {

    }

    public boolean update(V user) {
        return false;
    }

    public V get(K keyId) {
        return null;
    }
//    public boolean add(final V v) {
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<?> serializer = redisTemplate.getKeySerializer();
//                byte[] key  = serializer.serialize();
//                byte[] name = SerializeUtil.serialize(v);
//                return connection.setNX(key, name);
//            }
//        });
//        return result;
//    }
//
//    public boolean add(final List<V> list) {
//        Assert.notEmpty(list);
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<?> serializer = getRedisSerializer();
//                for (V v : list) {
//                    byte[] key  = serializer.serialize(user.getId());
//                    byte[] name = serializer.serialize(user.getName());
//                    connection.setNX(key, name);
//                }
//                return true;
//            }
//        }, false, true);
//        return result;
//    }
//
//    public void delete(K key) {
//        List<K> list = new ArrayList<K>();
//        list.add(key);
//        delete(list);
//    }
//
//    public void delete(List<K> keys) {
//        redisTemplate.delete(keys);
//    }
//
//    public boolean update(final V obj) {
//        String key = user.getId();
//        if (get(key) == null) {
//            throw new NullPointerException("数据行不存在, key = " + key);
//        }
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<String> serializer = getRedisSerializer();
//                byte[] key  = serializer.serialize(user.getId());
//                byte[] name = serializer.serialize(user.getName());
//                connection.set(key, name);
//                return true;
//            }
//        });
//        return result;
//    }
//
//    public V get(final K keyId) {
//        V result = redisTemplate.execute(new RedisCallback<V>() {
//            public V doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<K> serializer = getRedisSerializer();
//                byte[] key = serializer.serialize(keyId);
//                byte[] value = connection.get(key);
//                if (value == null) {
//                    return null;
//                }
//                String name = serializer.deserialize(value);
//                return new User(keyId, name, null);
//            }
//        });
//        return result;
//    }
}
