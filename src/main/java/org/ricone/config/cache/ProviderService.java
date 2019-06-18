package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static org.ricone.config.cache.CacheConfig.*;

@Service("PasswordService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProviderService {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final ProviderRepository repository;
	private final ProviderService self; /* !Important */

	public ProviderService(ProviderRepository repository, ProviderService cacheService) {
		this.repository = repository;
		this.self = cacheService;
	}

	@Cacheable(value = CACHE_TOKEN, key = "#providerId", unless = "#result != null")
	public String getAccessToken(String providerId) {
		return repository.getAccessToken(providerId);
	}

	/* Non Cached */

	public HashMap<String, String> getProviderKV(String providerId) {
		return repository.getProviderKV(providerId, self.getAccessToken(providerId));
	}

	public HashMap<String, ProviderPasswordKV> getProviderPasswordKV(String providerId) {
		return repository.getProviderPasswordKV(providerId, self.getAccessToken(providerId));
	}

	public ProviderPasswordKV getProviderPasswordKV(String providerId, String kvId, String secret_key) {
		return repository.getProviderPasswordKV(providerId, self.getAccessToken(providerId), kvId, secret_key);
	}
}
