package org.ricone.init;

import org.ricone.config.model.App;
import org.ricone.config.model.DataXML;
import org.ricone.config.model.District;
import org.ricone.config.model.School;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static org.ricone.init.CacheConfig.*;

@Component
public class CacheService {
	private final CacheRepository cacheRepository;

	public CacheService(CacheRepository cacheRepository) {this.cacheRepository = cacheRepository;}

	@Cacheable(value = CACHE_APP, key = "#appId", unless = "#result != null")
	public App getAppById(String appId) {
		return cacheRepository.getAppByAppId(appId);
	}

	@Cacheable(value = CACHE_ACL, key = "#appId", unless = "#result != null")
	public DataXML getDataXMLByAppId(String appId) {
		return cacheRepository.getDataXMLByAppId(appId);
	}

	@Cacheable(value = CACHE_DISTRICT, key = "#appId", unless = "#result != null")
	public List<District> getDistrictsByAppId(String appId) {
		return cacheRepository.getDistrictsByAppId(appId);
	}

	@Cacheable(value = CACHE_DISTRICT_KV, key = "#districtId", unless = "#result != null")
	public HashMap<String, String> getDistrictKVsByDistrictId(String districtId) {
		return cacheRepository.getDistrictKVsByDistrictId(districtId);
	}

	@Cacheable(value = CACHE_SCHOOL, key = "#districtId", unless = "#result != null")
	public List<School> getSchoolsByDistrictId(String districtId) {
		return cacheRepository.getSchoolsByDistrictId(districtId);
	}

	@Cacheable(value = CACHE_SCHOOL_KV, key = "#schoolId", unless = "#result != null")
	public HashMap<String, String> getSchoolKVsBySchoolId(String schoolId) {
		return cacheRepository.getSchoolKVsBySchoolId(schoolId);
	}
}
