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

@Service("Config:ProviderService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProviderService {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final ProviderRepository repository;
	private final TokenService service;

	public ProviderService(ProviderRepository repository, TokenService service) {
		this.repository = repository;
		this.service = service;
	}

	/* Non Cached */
	public HashMap<String, String> getProviderKV(String providerId) {
		return repository.getProviderKV(providerId, service.getAccessToken(providerId));
	}

	public HashMap<String, ProviderPasswordKV> getProviderPasswordKV(String providerId) {
		return repository.getProviderPasswordKV(providerId, service.getAccessToken(providerId));
	}

	public ProviderPasswordKV getProviderPasswordKV(String providerId, String kvId, String secret_key) {
		return repository.getProviderPasswordKV(providerId, service.getAccessToken(providerId), kvId, secret_key);
	}
}
