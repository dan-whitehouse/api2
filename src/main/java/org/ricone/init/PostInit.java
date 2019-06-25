package org.ricone.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class PostInit implements ApplicationListener<ApplicationReadyEvent> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
            DataSource dataSource = (DataSource)event.getApplicationContext().getBean("getDataSource");
            logger.debug(dataSource);
            logger.debug(dataSource.getConnection().getMetaData().getURL());


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}