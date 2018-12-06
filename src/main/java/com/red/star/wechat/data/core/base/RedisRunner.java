package com.red.star.wechat.data.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.red.star.wechat.data.utils.CheckUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * Redis操作类，封装了 缓存、队列、计数 等相关方法
 */
public class RedisRunner {

    private static JedisPool jedisPool = null;

    /**
     * 初始化 系统会维护不要调用
     */
    public RedisRunner(String redisServers, String password) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(50);
        // 设置最小空闲数
        poolConfig.setMinIdle(8);
        poolConfig.setMaxWaitMillis(10000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        // Idle时进行连接扫描
        poolConfig.setTestWhileIdle(true);
        // 表示idle object evitor两次扫描之间要sleep的毫秒数
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        // 表示idle object evitor每次扫描的最多的对象数
        poolConfig.setNumTestsPerEvictionRun(10);
        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        if (!CheckUtil.isEmpty(redisServers)) {
            String[] ss = redisServers.split(":");
            if (CheckUtil.isEmpty(password)) {
                jedisPool = new JedisPool(poolConfig, ss[0], Integer.valueOf(ss[1]), 2000);
            } else {
                jedisPool = new JedisPool(poolConfig, ss[0], Integer.valueOf(ss[1]), 2000, password);
            }
        }
    }

    /**
     * 停止Redis连接池方法（需在容器销毁时调用）
     */
    public static void stop() {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

    /**
     * 获取Redis连接方法
     *
     * @param dbNumber 数据库编号
     * @return Jedis连接对象
     */
    public Jedis getResource(Integer dbNumber) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbNumber);
        return jedis;
    }

    /**
     * 归还Redis连接方法
     *
     * @param jedis Jedis连接对象
     */
    public void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }

    /**
     * 缓存 添加/更新方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @param value    值
     * @param expiry   过期时间，单位秒（-1 = 永久有效）
     */
    public void cacheAddUpdate(Integer dbNumber, String key, Object value, Integer expiry) {
        if (value == null) {
            return;
        }
        String v = getString(value);
        Jedis jedis = getResource(dbNumber);
        if (expiry.equals(-1)) {
            jedis.set(key, v);
        } else {
            jedis.setex(key, expiry, v);
        }
        returnResource(jedis);
    }

    /**
     * 缓存 获取方法（重载方法，使用json反序列化）
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @param type     对象类型引用
     * @return 内容
     */
    public <T> T cacheGet(Integer dbNumber, String key, TypeReference<T> type) {
        String s = cacheGet(dbNumber, key);
        return s == null ? null : JSON.parseObject(s, type);
    }

    /**
     * 缓存 获取方法（重载方法，使用json反序列化）
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @param type     对象类型
     * @return 内容
     */
    public <T> T cacheGet(Integer dbNumber, String key, Class<T> type) {
        String s = cacheGet(dbNumber, key);
        return s == null ? null : JSON.parseObject(s, type);
    }

    /**
     * 缓存 获取方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @return 内容
     */
    public String cacheGet(Integer dbNumber, String key) {
        return get(dbNumber, key);
    }

    /**
     * 缓存 更改过期时间方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @param expiry   过期时间，单位秒
     */
    public void cacheExpiry(Integer dbNumber, String key, Integer expiry) {
        Jedis jedis = getResource(dbNumber);
        jedis.expire(key, expiry);
        returnResource(jedis);
    }

    /**
     * 缓存 移除方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     */
    public void cacheRemove(Integer dbNumber, String key) {
        remove(dbNumber, key);
    }

    /**
     * 缓存 判断是否存在方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @return 是否存在
     */
    public Boolean cacheExists(Integer dbNumber, String key) {
        return exists(dbNumber, key);
    }

    /**
     * 计数 自增1方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @return 自增后数字
     */
    public Long incAdd(Integer dbNumber, String key) {
        Jedis jedis = getResource(dbNumber);
        Long result = jedis.incr(key);
        returnResource(jedis);
        return result;
    }

    /**
     * 计数 自增n方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @return 自增后数字
     */
    public Long incAdd(Integer dbNumber, String key, int number) {
        Jedis jedis = getResource(dbNumber);
        Long result = jedis.incrBy(key, number);
        returnResource(jedis);
        return result;
    }

    /**
     * 计数 获取方法
     *
     * @param dbNumber 数据库编号
     * @param key      键
     * @return 当前数字
     */
    public Long incGet(Integer dbNumber, String key) {
        String result = get(dbNumber, key);
        return result == null ? null : Long.valueOf(result);
    }

    private Boolean exists(Integer dbNumber, String key) {
        Jedis jedis = getResource(dbNumber);
        Boolean result = jedis.exists(key);
        returnResource(jedis);
        return result;
    }

    private void remove(Integer dbNumber, String key) {
        Jedis jedis = getResource(dbNumber);
        jedis.del(key);
        returnResource(jedis);
    }

    private String get(Integer dbNumber, String key) {
        Jedis jedis = getResource(dbNumber);
        String result = jedis.get(key);
        returnResource(jedis);
        return result;
    }

    private String getString(Object value) {
        if (value instanceof String) {
            return (String) value;
        } else {
            return JSON.toJSON(value).toString();
        }
    }

    /**
     * 订阅发布消息
     *
     * @param channel 频道
     * @param message 消息
     */
    public void publish(String channel, String message) {
        Jedis jedis = getResource(12);
        jedis.publish(channel, message);
        returnResource(jedis);
    }

    /**
     * 订阅注册方法
     *
     * @param listener 监听器
     * @param channel  频道
     */
    public void subscribe(final JedisPubSub listener, final String channel) {
        new Thread() {
            @Override
            public void run() {
                Jedis jedis = getResource(12);
                jedis.subscribe(listener, channel);
                returnResource(jedis);
            }
        }.start();
    }

}