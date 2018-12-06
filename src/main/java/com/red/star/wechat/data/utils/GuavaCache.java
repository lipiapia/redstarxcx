package com.red.star.wechat.data.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 参考：http://blog.csdn.net/michaelwubo/article/details/50865185
 * https://www.cnblogs.com/gongxijun/p/5781108.html
 * http://blog.csdn.net/dgeek/article/details/76221746
 * java ConcurrentHashMap 和 Guava cache对比
 */
public class GuavaCache {

    private static Cache<Object, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(100)//设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                .expireAfterWrite(30, TimeUnit.SECONDS)//设置写缓存后30秒钟过期
                .recordStats()
                .build();

    private static LoadingCache<String,String> loadingCache= CacheBuilder.newBuilder()
            .maximumSize(100) //最大缓存数目
            .expireAfterAccess(1, TimeUnit.SECONDS) //缓存1秒后过期
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {//没有值的情况下返回 key
                    return key;
                }
            });


    public static Object get(Object key) throws ExecutionException {
        Object var = cache.get(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("如果没有值,就执行其他方式去获取值");
                return "fail";
            }
        });
        return var;
    }

    public static void put(Object key, Object value) {
        cache.put(key, value);
    }

    public static void main(String args[]) throws ExecutionException {
        String key = "test-key";
        String value = "test-value";
        put(key,value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(get(key));
//        loadingCache.put(key,value);
//        System.out.println(loadingCache.get(key));
    }
}
