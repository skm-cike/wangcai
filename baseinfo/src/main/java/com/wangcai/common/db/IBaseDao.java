package com.wangcai.common.db;

import java.util.List;

/**
 * Created by 陆华 on 15-12-18 上午9:45
 */
public interface IBaseDao<K, V> {
    /**
     * 新增
     * <br>------------------------------<br>
     * @param v 实体
     * @return
     */
    boolean add(V v);

    /**
     * 批量新增 使用pipeline方式
     * <br>------------------------------<br>
     * @param list
     * @return
     */
    boolean add(List<V> list);

    /**
     * 删除
     * <br>------------------------------<br>
     * @param key 主键
     */
    void delete(K key);

    /**
     * 删除多个
     * <br>------------------------------<br>
     * @param keys
     */
    void delete(List<K> keys);

    /**
     * 修改
     * <br>------------------------------<br>
     * @param user
     * @return
     */
    boolean update(V user);

    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param keyId
     * @return
     */
    V get(K keyId);
}
