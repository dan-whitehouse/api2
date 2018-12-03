package org.ricone.config;

import org.ricone.error.exception.ConfigException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * @project: ricone
 * @author: Dan on 11/14/2017.
 */
public abstract class AbstractService {

    private RestTemplate rt;
    private HttpHeaders headers;
    private HttpEntity<?> entity;

    public AbstractService() {
        rt = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", ConfigService.getInstance().getAccessToken());
        entity = new HttpEntity<>(headers);
    }

    public RestTemplate restTemplate() {
        return rt;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public HttpEntity<?> getEntity() {
        return entity;
    }

    String getUrl() throws ConfigException {
        return System.getenv("config_url");
    }

    String getUsername() throws ConfigException {
        return System.getenv("api_config_username");
    }

    String getPassword() throws ConfigException {
        return System.getenv("api_config_password");
    }
}
