package org.ricone.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
@Configuration("CacheConfig")
@EnableCaching
public class CacheConfig {
   private final Logger logger = LogManager.getLogger(this.getClass());
   final static String CACHE_TOKEN = "tokenCache";
   final static String CACHE_APP = "appCache";
   final static String CACHE_ACL = "dataXMLCache";
   final static String CACHE_DISTRICT = "districtCache";
   final static String CACHE_DISTRICT_KV = "districtKVCache";
   final static String CACHE_SCHOOL = "schoolCache";
   final static String CACHE_SCHOOL_KV = "schoolKVCache";
   final static String CACHE_FILTER = "filterCache";

   @Bean
   public CaffeineCacheManager caffeineCacheManager() {
      return new CaffeineCacheManager();
   }

   @Bean
   public Cache tokenCache() {
      return new CaffeineCache(CACHE_TOKEN, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.DAYS)
              .build());
   }

   @Bean
   public Cache appCache() {
      return new CaffeineCache(CACHE_APP, Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build());
   }

   @Bean
   public Cache dataXMLCache() {
      return new CaffeineCache(CACHE_ACL, Caffeine.newBuilder()
              .expireAfterWrite(1, TimeUnit.HOURS)
              .build());
   }

   @Bean
   public Cache districtCache() {
      return new CaffeineCache(CACHE_DISTRICT, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.MINUTES)
              .build());
   }

   @Bean
   public Cache districtKVCache() {
      return new CaffeineCache(CACHE_DISTRICT_KV, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.MINUTES)
              .build());
   }

   @Bean
   public Cache schoolCache() {
      return new CaffeineCache(CACHE_SCHOOL, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.MINUTES)
              .build());
   }

   @Bean
   public Cache schoolKVCache() {
      return new CaffeineCache(CACHE_SCHOOL_KV, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.MINUTES)
              .build());
   }

   @Bean
   public Cache filterCache() {
      return new CaffeineCache(CACHE_FILTER, Caffeine.newBuilder()
              .expireAfterWrite(10, TimeUnit.MINUTES)
              .build());
   }
}