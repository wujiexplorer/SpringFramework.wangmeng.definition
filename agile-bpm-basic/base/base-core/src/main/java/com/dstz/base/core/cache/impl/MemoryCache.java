package com.dstz.base.core.cache.impl;

import com.dstz.base.core.cache.ICache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存的cache实现。
 */
public class MemoryCache<T> implements ICache<T> {

    private Map<String, T> map = new ConcurrentHashMap<String, T>();

    public void add(String key, T obj) {
    	if(key == null) return;
        map.put(key, obj);
    }

    public void delByKey(String key) {
    	if(key == null) return;
        map.remove(key);
    }

    public void clearAll() {
        map.clear();
    }

    public T getByKey(String key) {
    	if(key == null) return null;
        return map.get(key);
    }
    
    
    public boolean containKey(String key) {
    	if(key == null) return false;
        return map.containsKey(key);
    }

    public void add(String key, T obj, int timeout) {
    	
    }

}
