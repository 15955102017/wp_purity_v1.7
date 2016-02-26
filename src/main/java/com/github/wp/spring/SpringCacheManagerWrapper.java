package com.github.wp.spring;

import net.sf.ehcache.Ehcache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.*;

/**
 * 包装Spring cache抽象
 * <p>User: wangping
 * <p>Date: 13-3-23 上午8:26
 * <p>Version: 1.0
 */
public class SpringCacheManagerWrapper implements CacheManager {

    private org.springframework.cache.CacheManager cacheManager;

    /**
     * 设置spring cache manager
     *
     * @param cacheManager
     */
    public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        org.springframework.cache.Cache springCache = cacheManager.getCache(name);
        return new SpringCacheWrapper<K, V>(springCache);
    }

    static class SpringCacheWrapper<K,V> implements Cache<K,V> {
        private org.springframework.cache.Cache springCache;

        SpringCacheWrapper(org.springframework.cache.Cache springCache) {
            this.springCache = springCache;
        }

        @SuppressWarnings("unchecked")
		public V get(K key) throws CacheException {
            V value = (V) springCache.get(key);
            if (value instanceof SimpleValueWrapper) {
                return (V) ((SimpleValueWrapper) value).get();
            }
            return value;
        }

        public V put(K key, V value) throws CacheException {
            springCache.put(key, value);
            return value;
        }

        public V remove(K key) throws CacheException {
            springCache.evict(key);
            return null;
        }

        public void clear() throws CacheException {
            springCache.clear();
        }

        public int size() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                return ehcache.getSize();
            }
            throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
        }

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Set<K> keys() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
                return new HashSet(ehcache.getKeys());
            }
            throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
        }

        public Collection<V> values() {
            if(springCache.getNativeCache() instanceof Ehcache) {
                Ehcache ehcache = (Ehcache) springCache.getNativeCache();
				@SuppressWarnings("unchecked")
				List<K> keys = ehcache.getKeys();
                if (!CollectionUtils.isEmpty(keys)) {
                    List<V> values = new ArrayList<V>(keys.size());
                    for (K key : keys) {
                        V value = get(key);
                        if (value != null) {
                            values.add(value);
                        }
                    }
                    return Collections.unmodifiableList(values);
                } else {
                    return Collections.emptyList();
                }
            }
            throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
        }
    }
}
