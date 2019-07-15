package org.ricone.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import com.splunk.logging.TcpAppender;
import org.ricone.config.model.Credential;
import org.ricone.config.model.Login;
import org.ricone.config.model.ProviderKV;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

public class LoggingInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        LoggerContext c = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = c.getLogger("splunk.logger");

        logger.info("Adding Splunk TCP Appender");

        PatternLayout layout = new PatternLayout();
        layout.setContext(c);
        layout.setPattern("%m%n");
        layout.start();

        //Get Splunk Host & Port From Config
        try {
            HashMap<String, String> pkv = getProviderKV(getAccessToken());

            TcpAppender appender = new TcpAppender();
            appender.setContext(c);
            appender.setLayout(layout);
            appender.setRemoteHost(Objects.requireNonNull(pkv).get("splunk.host"));
            appender.setPort(Integer.parseInt(Objects.requireNonNull(pkv).get("splunk.port")));
            appender.start();

            //Add Appender
            logger.addAppender(appender);
            logger.setLevel(Level.INFO);
        }
        catch(Exception e) {
            logger.error("Couldn't Configure Splunk... though this is only a console message so... no one will ever see this.");
        }
    }

    private HashMap<String, String> getProviderKV(String token) throws RestClientException {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set(HttpHeaders.AUTHORIZATION, token);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<ProviderKV[]> response = rt.exchange((getUrl() + "/provider/" + getProvider() + "/apikv"), HttpMethod.GET, entity, ProviderKV[].class);

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

    private String getAccessToken() throws RestClientException {
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