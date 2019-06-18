package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.ricone.config.cache.CacheConfig.*;

@Repository("PasswordRepository")
public class ProviderRepository {
	private final static String AUTHORIZATION = "Authorization";
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Caching(put = {@CachePut(value = CACHE_TOKEN, key = "#providerId", condition = "#result != null")})
	public String getAccessToken(String providerId) throws RestClientException {
		try {
			RestTemplate rt = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Login> request = new HttpEntity<>(new Login(getUsername(), getPassword()), headers);

			Credential credential = rt.postForObject(getUrl() + "users/login", request, Credential.class);
			if(credential != null) {
				logger.debug("Getting Config Token From Config: " + credential.getId());
				return credential.getId();
			}
			//logger.debug("Getting Config Token From Config: " + null);
			return null;
		}
		catch (RestClientException e) {
			e.printStackTrace();
			//logger.debug("Getting Config Token From Config: catch....");
			return null;
		}
	}

	HashMap<String, String> getProviderKV(String providerId, String token) throws RestClientException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<ProviderKV[]> response = rt.exchange((getUrl() + "/provider/" + providerId + "/apikv"), HttpMethod.GET, entity, ProviderKV[].class);

			HashMap<String, String> map = null;
			if(response.getBody() != null) {
				map = new HashMap<>();
				for (ProviderKV info : response.getBody()) {
					map.put(info.getField(), info.getValue());
				}
			}
			return map;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	/* Non Cached */
	HashMap<String, ProviderPasswordKV> getProviderPasswordKV(String providerId, String token) throws RestClientException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<ProviderPasswordKV[]> response = rt.exchange((getUrl() + "/provider/" + providerId + "/passwordkv"), HttpMethod.GET, entity, ProviderPasswordKV[].class);

			HashMap<String, ProviderPasswordKV> map = null;
			if(response.getBody() != null) {
				map = new HashMap<>();
				for (ProviderPasswordKV p : response.getBody()) {
					map.put(p.getName(), p);
				}
			}
			return map;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	ProviderPasswordKV getProviderPasswordKV(String providerId, String token, String kvId, String secret_key) throws RestClientException {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			headers.set("secret_key", secret_key);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return rt.postForObject(getUrl() + "/provider/" + providerId + "/passwordkv/" + kvId, entity, ProviderPasswordKV.class);
		}
		catch (Exception ignored) {
		}
		return null;
	}

	/* Other */
	private String getProvider() {
		return System.getenv("provider_id");
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
