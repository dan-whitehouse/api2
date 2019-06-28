package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.model.ProviderKV;
import org.ricone.config.model.ProviderPasswordKV;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Repository("Config:ProviderRepository")
public class ProviderRepository {
	private final static String AUTHORIZATION = "Authorization";
	private final Logger logger = LogManager.getLogger(this.getClass());

	/* Non Cached */
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
