package com.qxst.e.meatballstartup.service;

public interface RedisService {
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    void remove(final String... keys);
    /**
     * 批量删除key
     *
     * @param pattern
     */
    void removePattern(final String pattern);
    /**
     * 删除对应的value
     *
     * @param key
     */
    void remove(final String key);
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    boolean exists(final String key);
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    Object get(final String key);
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(final String key, Object value);
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(final String key, Object value, Long expireTime);
}
