package com.wangcai.common.redis;

import com.wangcai.common.db.NSID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陆华 on 15-12-17 下午2:22
 */
public class AbstractBaseRedisDao<K, V> {
    @Autowired
    protected RedisTemplate<K, V> redisTemplate;

    /**
     * 设置redisTemplate
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisSerializer
     */
    protected RedisSerializer<?> getRedisSerializer() {
        return redisTemplate.getDefaultSerializer();
    }

    protected List<String> getObjList(Object obj) {
        if (obj == null) {
            return null;
        }
        List<String> rst = new ArrayList();
        Field[] fields = obj.getClass().getDeclaredFields();
        String keyMethod = null;
        for (Field f: fields) {
            Annotation[] as = f.getAnnotations();
            if (as == null ||as.length == 0) {
                continue;
            }
            boolean isId = false;
            for (Annotation a: as) {
                if (a instanceof NSID) {
                    isId = true;
                    break;
                }
            }
            if (isId) {
                keyMethod = "get" + StringUtils.capitalize(f.getName());
                break;
            }
        }
        if (keyMethod == null) {
            throw new RuntimeException("没有设置主键注解!");
        }
        Method[] methods = obj.getClass().getMethods();
        for (Method m: methods) {
            if (m.getName().equals(keyMethod)) {
                try {
                    rst.add(m.invoke(obj).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Method m: methods) {
            if (!m.getName().equals(keyMethod) && m.getName().startsWith("get")) {
                try {
                    rst.add(m.invoke(obj)==null?null:m.invoke(obj).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return rst;
    }
}
