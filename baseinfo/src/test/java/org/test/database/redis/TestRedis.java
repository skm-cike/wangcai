package org.test.database.redis;

import com.wangcai.common.db.cache.mybatisredis.SerializeUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by 陆华 on 15-12-21 上午11:23
 */
public class TestRedis {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
        RedisTemplate redisTemplate = (RedisTemplate)context.getBean("redisTemplate");
        final User user = new User();
        user.setId("123");
        user.setName("刺客");
        user.setPwd("12345");
        while (true) {
            Object isSuccess = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.setNX(SerializeUtil.serialize(user.getId()), SerializeUtil.serialize(user));
                }
            });
            System.out.println((Boolean) isSuccess);
//        Object count = redisTemplate.execute(new RedisCallback<Object>() {
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                return connection.del(SerializeUtil.serialize(user.getId()));
//            }
//        });
            final String key = user.getId();

            User _user = (User) redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] val = connection.get(SerializeUtil.serialize(key));
                    if (val != null) {
                        return SerializeUtil.unserialize(val);
                    }
                    return null;
                }
            });
            System.out.println(_user);
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.del(SerializeUtil.serialize(key));
                }
            });
            redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.flushDb();
                    return null;
                }
            });
        }
    }

}
