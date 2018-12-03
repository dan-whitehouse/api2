package org.ricone.config;

import org.ricone.config.model.*;
import org.ricone.error.exception.ConfigException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FilterService extends AbstractService {
    //Singleton
    private static FilterService instance = null;

    public static FilterService getInstance() {
        if(instance == null) {
            instance = new FilterService();
        }
        return instance;
    }

    //Constants
    private final String APP = "/app/";
    private final String DISTRICT = "/district/";


    public XLeaFilter getXLeaFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XLeaFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xlea"), HttpMethod.GET, getEntity(), XLeaFilter.class);
        return response.getBody();
    }

    public XSchoolFilter getXSchoolFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XSchoolFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xschool"), HttpMethod.GET, getEntity(), XSchoolFilter.class);
        return response.getBody();
    }

    public XCalendarFilter getXCalendarFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XCalendarFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xcalendar"), HttpMethod.GET, getEntity(), XCalendarFilter.class);
        return response.getBody();
    }

    public XCourseFilter getXCourseFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XCourseFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xcourse"), HttpMethod.GET, getEntity(), XCourseFilter.class);
        return response.getBody();
    }

    public XRosterFilter getXRosterFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XRosterFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xroster"), HttpMethod.GET, getEntity(), XRosterFilter.class);
        return response.getBody();
    }

    public XStaffFilter getXStaffFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XStaffFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xstaff"), HttpMethod.GET, getEntity(), XStaffFilter.class);
        return response.getBody();
    }

    public XStudentFilter getXStudentFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XStudentFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xstaff"), HttpMethod.GET, getEntity(), XStudentFilter.class);
        return response.getBody();
    }

    public XContactFilter getXContactFilter(String districtId, String appId) throws ConfigException {
        ResponseEntity<XContactFilter> response = restTemplate().exchange((getUrl() + DISTRICT + districtId + APP + appId + "/xstaff"), HttpMethod.GET, getEntity(), XContactFilter.class);
        return response.getBody();
    }
}
