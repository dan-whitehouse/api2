package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import static org.ricone.config.cache.CacheConfig.CACHE_TOKEN;

@Service("Config:TokenService")
@Scope(proxyMode = ScopedProxyMode.DEFAULT)
@org.springframework.cache.annotation.CacheConfig
public class TokenService {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final TokenRepository repository;

	public TokenService(TokenRepository repository) {
		this.repository = repository;
	}

	@Cacheable(value = CACHE_TOKEN, key = "#providerId", unless = "#result != null")
	public String getAccessToken(String providerId) {
		return repository.getAccessToken("localhost");
	}
}
