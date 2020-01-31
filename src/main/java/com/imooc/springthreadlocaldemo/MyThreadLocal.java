package com.imooc.springthreadlocaldemo;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2020-01-31 22:41
 **/
public class MyThreadLocal<T> {

    static AtomicInteger atomic = new AtomicInteger();

    // 自增
    Integer threadLocalHash = atomic.addAndGet(0x61c88647);

    // 共享空间
    static HashMap<Thread, HashMap<Integer, Object>> threadLocalMap = new HashMap<>();

    // 临界区

    /**
     * 获取当前线程的数据
     * @return
     */
    synchronized static HashMap<Integer, Object> getMap() {
        // 获取当前线程
        Thread thread = Thread.currentThread();
        // 判断threadLocalMap是否包含当前线程，不包含就put进去
        if (!threadLocalMap.containsKey(thread)) {
            threadLocalMap.put(thread, new HashMap<Integer,Object>());
        }
        // 获取当前thread的map
        return threadLocalMap.get(thread);
    }

    protected T initialValue() {
        return null;
    }

    public T get() {
        HashMap<Integer, Object> map = getMap();
        if (!map.containsKey(this.threadLocalHash)) {
            map.put(this.threadLocalHash, initialValue());
        }
        return (T) map.get(this.threadLocalHash);
    }

    public void set(T v) {
        // 获取当前线程map
        HashMap<Integer, Object> map = getMap();
        map.put(this.threadLocalHash, v);
    }

}
