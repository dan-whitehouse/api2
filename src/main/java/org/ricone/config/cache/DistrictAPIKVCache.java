package org.ricone.config.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.ricone.config.ConfigService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DistrictAPIKVCache {
    private static DistrictAPIKVCache instance = null;
    private static final Integer EXPIRE = 2;

    private final LoadingCache<String, HashMap<String, String>> cache;

    public static DistrictAPIKVCache getInstance() {
        if(instance == null) {
            instance = new DistrictAPIKVCache();
        }
        return instance;
    }

    public DistrictAPIKVCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE, TimeUnit.HOURS).build(new CacheLoader<String, HashMap<String, String>>() {
            @Override
            public HashMap<String, String> load(String districtId) throws Exception {
                return loadCache(districtId);
            }
        });
    }

    public HashMap<String, String> get(String districtId) {
        return cache.getUnchecked(districtId);
    }

    public void put(String districtId, HashMap<String, String> kv) {
        cache.put(districtId, kv);
    }

    private HashMap<String, String> loadCache(String districtId) {
        HashMap<String, String> profile = ConfigService.getInstance().getDistrictAPIKV(districtId);
        return profile;
    }
}