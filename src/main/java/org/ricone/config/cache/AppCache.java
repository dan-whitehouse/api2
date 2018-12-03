package org.ricone.config.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.ConfigService;
import org.ricone.config.model.App;
import org.ricone.config.model.District;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AppCache {
    private static AppCache instance = null;
    private static final Integer EXPIRE = 2;
    private final LoadingCache<String, App> cache;

    public static AppCache getInstance() {
        if(instance == null) {
            instance = new AppCache();
        }
        return instance;
    }

    private AppCache() {
        cache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE, TimeUnit.HOURS).build(new CacheLoader<String, App>() {
            @Override
            public App load(String appId) throws Exception {
                return loadCache(appId);
            }
        });
    }

    public App get(String appId) {
        return cache.getUnchecked(appId);
    }

    public void put(String appId, App app) {
        cache.put(appId, app);
    }

    public void clearCache() {
        cache.invalidateAll();
    }

    private App loadCache(String appId) {
        App app = ConfigService.getInstance().getApp(appId);
        if (app != null) {
            List<District> districts = ConfigService.getInstance().getDistrictsByApp(app.getId());
            if (CollectionUtils.isNotEmpty(districts)) {
                List<District> filtered = districts.stream().filter(district -> (district.getEnabled() && StringUtils.equalsIgnoreCase(district.getProviderId(), System.getenv("provider_id")))).collect(Collectors.toList());
                filtered.forEach(district -> {
                    try {
                        HashMap<String, String> kv = ConfigService.getInstance().getDistrictAPIKV(district.getId());
                        district.setKv(kv);
                    }
                    catch (Exception ignored) {

                    }
                });
                app.setDistricts(filtered);
            }
        }
        return app;
    }
}