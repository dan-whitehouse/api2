package org.ricone.config;

import org.ricone.config.model.*;
import org.ricone.error.exception.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class FilterService extends AbstractService {
    private Logger logger = LoggerFactory.getLogger(FilterService.class);
    private final String SEPARATOR = "_";
    private final String XLEAS = "xLeas/";
    private final String XSCHOOLS = "xSchools/";
    private final String XCALENDARS = "xCalendars/";
    private final String XCOURSES = "xCourses/";
    private final String XROSTERS = "xRosters/";
    private final String XSTAFFS = "xStaffs/";
    private final String XSTUDENTS = "xStudents/";
    private final String XCONTACTS = "xContacts/";

    private static FilterService instance = null;
    public static FilterService getInstance() {
        if (instance == null) {
            instance = new FilterService();
        }
        return instance;
    }

    public XLeaFilter getXLeaFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XLeaFilter> response = restTemplate().exchange((getUrl() + XLEAS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XLeaFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XLeaFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XLea failed to load from Config");
            return new XLeaFilter();
        }
    }

    public XSchoolFilter getXSchoolFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XSchoolFilter> response = restTemplate().exchange((getUrl() + XSCHOOLS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XSchoolFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XSchoolFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XSchool failed to load from Config");
            return new XSchoolFilter();
        }
    }

    public XCalendarFilter getXCalendarFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XCalendarFilter> response = restTemplate().exchange((getUrl() + XCALENDARS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XCalendarFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XCalendarFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XCalendar failed to load from Config");
            return new XCalendarFilter();
        }
    }

    public XCourseFilter getXCourseFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XCourseFilter> response = restTemplate().exchange((getUrl() + XCOURSES + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XCourseFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XCourseFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XCourse failed to load from Config");
            return new XCourseFilter();
        }
    }

    public XRosterFilter getXRosterFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XRosterFilter> response = restTemplate().exchange((getUrl() + XROSTERS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XRosterFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XRosterFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XRoster failed to load from Config");
            return new XRosterFilter();
        }
    }

    public XStaffFilter getXStaffFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XStaffFilter> response = restTemplate().exchange((getUrl() + XSTAFFS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XStaffFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XStaffFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XStaff failed to load from Config");
            return new XStaffFilter();
        }
    }

    public XStudentFilter getXStudentFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XStudentFilter> response = restTemplate().exchange((getUrl() + XSTUDENTS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XStudentFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XStudentFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XStudent failed to load from Config");
            return new XStudentFilter();
        }
    }

    public XContactFilter getXContactFilter(String districtId, String appId) throws RestClientException {
        try {
            ResponseEntity<XContactFilter> response = restTemplate().exchange((getUrl() + XCONTACTS + districtId.toUpperCase() + SEPARATOR + appId.toUpperCase()), HttpMethod.GET, getEntity(), XContactFilter.class);
            return response.getBody();
        }
        catch (Exception e) {
            logger.debug("Loading Default XContactFilter, " + districtId + SEPARATOR + appId + SEPARATOR + "XContact failed to load from Config");
            return new XContactFilter();
        }
    }
}