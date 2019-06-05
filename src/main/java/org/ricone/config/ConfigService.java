package org.ricone.config;

import org.ricone.config.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class ConfigService {
    private static ConfigService instance = null;
    
    public static ConfigService getInstance() {
        if(instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    /*********** Login ***********/
    String getAccessToken() throws RestClientException {
        try {
            // If Null or Expired
            //if(getCredential() == null || (ISO8601toLong(this.getCredential().getCreated()) + (1000 * this.getCredential().getTtl())) <= System.currentTimeMillis()) {
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

    public App getApp(String appId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("Authorization", getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<App> response = rt.exchange((getUrl() + "app/" + appId), HttpMethod.GET, entity, App.class);
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DataXML getDataXMLByApp(String appId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("Authorization", getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<DataXML> response = rt.exchange((getUrl() + "app/" + appId + "/dataxml"), HttpMethod.GET, entity, DataXML.class);
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> getDistrictAPIKV(String districtId) throws RestClientException {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("Authorization", getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<DistrictKV[]> response = rt.exchange((getUrl() + "district/" + districtId + "/apikv"), HttpMethod.GET, entity, DistrictKV[].class);

            HashMap<String, String> infoMap = new HashMap<String, String>();

            for (DistrictKV info : response.getBody()) {
                infoMap.put(info.getField(), info.getValue());
            }
            return infoMap;
        }
        catch (Exception e) {
        }
        return null;
    }

    public List<District> getDistrictsByApp(String appId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<District>> response;
        try {
            headers.set("Authorization", getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            response = rt.exchange((getUrl() + "app/" + appId + "/district"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<District>>() {
            });
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* School */
    public List<School> getSchoolsByDistrict(String districtId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<List<School>> response;
        try {
            headers.set("Authorization", getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            response = rt.exchange((getUrl() + "/district/" + districtId + "/school"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<School>>() {
            });
            return response.getBody();
        }
        catch (Exception ignored) {

        }
        return null;
    }

    /*********** School API KV ***********/
    public HashMap<String, String> getSchoolAPIKV(String schoolId) throws RestClientException {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("Authorization", this.getAccessToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<SchoolAPIKV[]> response = rt.exchange((getUrl() + "/school/" + schoolId + "/apikv"), HttpMethod.GET, entity, SchoolAPIKV[].class);

            HashMap<String, String> infoMap = new HashMap<>();
            for (SchoolAPIKV info : response.getBody()) {
                infoMap.put(info.getField(), info.getValue());
            }
            return infoMap;
        }
        catch (Exception ignored) {
        }
        return null;
    }

    private static long ISO8601toLong(String iso) {
        ZonedDateTime date = ZonedDateTime.parse(iso); // works
        return date.toInstant().toEpochMilli();
    }

    @SuppressWarnings("unused")
    private static Date LongToISO8601(long t) {
        return new Date(t);
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
