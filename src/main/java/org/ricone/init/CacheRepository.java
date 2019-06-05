package org.ricone.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static org.ricone.init.CacheConfig.*;

@Repository("CacheRepository")
public class CacheRepository {
	private final static String AUTHORIZATION = "Authorization";
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Caching(put = {@CachePut(value = CACHE_CONFIG, key = "#result", condition = "#result != null")})
	public String getAccessToken() throws RestClientException {
		logger.debug("Getting Config Token From Config");
		try {
			RestTemplate rt = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Login> request = new HttpEntity<>(new Login(getUsername(), getPassword()), headers);

			Credential credential = rt.postForObject(getUrl() + "users/login", request, Credential.class);
			if(credential != null) {
				return credential.getId();
			}
			return null;
		}
		catch (RestClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Caching(put = {@CachePut(value = CACHE_APP, key = "#result.id", condition = "#result != null")})
	public App getAppByAppId(String appId) {
		logger.debug("Getting App From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<App> response = rt.exchange((getUrl() + "app/" + appId), HttpMethod.GET, entity, App.class);
			return response.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_ACL, key = "#result.xml.appId", condition = "#result != null")})
	public DataXML getDataXMLByAppId(String appId) {
		logger.debug("Getting DataXML From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<DataXML> response = rt.exchange((getUrl() + "app/" + appId + "/dataxml"), HttpMethod.GET, entity, DataXML.class);
			return response.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_DISTRICT, key = "#appId", condition = "#result != null")})
	public List<District> getDistrictsByAppId(String appId) {
		logger.debug("Getting Districts From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<List<District>> response;
		try {
			headers.set(AUTHORIZATION, getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			response = rt.exchange((getUrl() + "app/" + appId + "/district"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<District>>() {});
			return response.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_DISTRICT_KV, key = "#districtId", condition = "#result != null")})
	public HashMap<String, String> getDistrictKVsByDistrictId(String districtId) throws RestClientException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<DistrictKV[]> response = rt.exchange((getUrl() + "district/" + districtId + "/apikv"), HttpMethod.GET, entity, DistrictKV[].class);

			HashMap<String, String> infoMap = new HashMap<String, String>();
			if(response.getBody() != null) {
				for (DistrictKV info : response.getBody()) {
					infoMap.put(info.getField(), info.getValue());
				}
			}
			return infoMap;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_SCHOOL, key = "#districtId", condition = "#result != null")})
	public List<School> getSchoolsByDistrictId(String districtId) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<List<School>> response;
		try {
			headers.set(AUTHORIZATION, getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			response = rt.exchange((getUrl() + "/district/" + districtId + "/school"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<School>>() {});
			return response.getBody();
		}
		catch (Exception ignored) {
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_SCHOOL_KV, key = "#schoolId", condition = "#result != null")})
	public HashMap<String, String> getSchoolKVsBySchoolId(String schoolId) throws RestClientException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, this.getAccessToken());
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<SchoolAPIKV[]> response = rt.exchange((getUrl() + "/school/" + schoolId + "/apikv"), HttpMethod.GET, entity, SchoolAPIKV[].class);

			HashMap<String, String> infoMap = new HashMap<>();
			if(response.getBody() != null) {
				for (SchoolAPIKV info : response.getBody()) {
					infoMap.put(info.getField(), info.getValue());
				}
			}
			return infoMap;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	private String getUrl() {
		return System.getenv("config_url");
	}

	private String getUsername() {
		return System.getenv("api_config_username");
	}

	private String getPassword() {
		return System.getenv("api_config_password");
	}
}
