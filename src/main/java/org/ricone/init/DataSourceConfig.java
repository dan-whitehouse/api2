package org.ricone.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.cache.ProviderService;
import org.ricone.config.model.ProviderPasswordKV;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:security.properties")
public class DataSourceConfig {
    private final ProviderService service;
    private final Environment environment;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public DataSourceConfig(Environment environment, ProviderService service) {
        this.environment = environment;
        this.service = service;
    }

    @Bean
    public DataSource getDataSource() {
        HashMap<String, String> pkv = null;

        logger.debug("------------------------");
        ProviderPasswordKV credentials = getCredentials();
        if(credentials != null) {

            pkv = service.getProviderKV(environment.getProperty("security.auth.provider.id"));


            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.url(pkv.get("db.core.url"));
            dataSourceBuilder.username(credentials.getUsername());
            dataSourceBuilder.password(credentials.getPassword());
            return dataSourceBuilder.build();
        }
        return null;


        /*HashMap<String, String> pkv = null;
        HashMap<String, ProviderPasswordKV> ppkv = null;
        ProviderPasswordKV credentials = ConfigService.getInstance().getProviderPasswordKV(ConfigUtil.getInstance().getProviderId(), ppkv.get("db.core").getId(), ConfigUtil.getInstance().getProviderKey());

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(pkv.get("db.driver"));
        dataSourceBuilder.url(pkv.get("db.core.url"));
        dataSourceBuilder.username(credentials.getUsername());
        dataSourceBuilder.password(credentials.getPassword());
        return dataSourceBuilder.build();*/

    }


    private ProviderPasswordKV getCredentials() {
        HashMap<String, ProviderPasswordKV> ppkv = service.getProviderPasswordKV(environment.getProperty("security.auth.provider.id"));
        for(Map.Entry<String, ProviderPasswordKV> kv : ppkv.entrySet()) {
            if(kv.getKey().equalsIgnoreCase("db.core")) {
                return service.getProviderPasswordKV(environment.getProperty("security.auth.provider.id"), kv.getValue().getId(), environment.getProperty("security.auth.provider.key"));
            }
        }
        return null;
        /*ppkv.forEach((k, v) -> {
            logger.debug(k + " - " + v.getId() + " | " + v.getName() + " | " + v.getPassword());
            ProviderPasswordKV c = service.getProviderPasswordKV(environment.getProperty("security.auth.provider.id"), v.getId(), environment.getProperty("security.auth.provider.key"));
            logger.debug("\t \u21b3" + k + " - " + c.getId() + " | " + c.getUsername() + " | " + c.getPassword());
        });*/
    }
}