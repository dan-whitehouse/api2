package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.SchoolIdentifier;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.request.xSchool.XSchoolDAO;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.ricone.config.cache.CacheConfig.*;

@Service("Config:AppService")
public class AppService {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final AppRepository repository;
	private final TokenService service;
	private final XSchoolDAO schoolDAO;
	private final static String key = "localhost";

	public AppService(AppRepository cacheRepository, TokenService service, XSchoolDAO schoolDAO) {
		this.repository = cacheRepository;
		this.service = service;
		this.schoolDAO = schoolDAO;
	}

	@Cacheable(value = CACHE_APP, key = "#appId", unless = "#result != null")
	public App getAppById(String appId) {
		return repository.getAppByAppId(appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_ACL, key = "#appId", unless = "#result != null")
	public DataXML getDataXMLByAppId(String appId) {
		return repository.getDataXMLByAppId(appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_DISTRICT, key = "#appId", unless = "#result != null")
	public List<District> getDistrictsByAppId(String appId) {
		return repository.getDistrictsByAppId(appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_DISTRICT_KV, key = "#schoolRefId", unless = "#result != null")
	public HashMap<String, String> getDistrictKVsBySchoolRefId(String schoolRefId) {
		try {
			SchoolWrapper wrapper = schoolDAO.findByRefId(new ControllerData(new MockHttpServletRequest(), new MockHttpServletResponse()), schoolRefId);
			logger.debug("DistrictId: " + wrapper.getDistrictId());
			return repository.getDistrictKVsByDistrictId(schoolRefId, wrapper.getDistrictId(), service.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value = CACHE_DISTRICT_KV, key = "#districtId", unless = "#result != null")
	public HashMap<String, String> getDistrictKVsByDistrictId(String districtId) {
		try {
			return repository.getDistrictKVsByDistrictId(districtId, districtId, service.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value = CACHE_SCHOOL, key = "#districtId", unless = "#result != null")
	public List<School> getSchoolsByDistrictId(String districtId) {
		return repository.getSchoolsByDistrictId(districtId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_SCHOOL_KV, key = "#schoolRefId", unless = "#result != null")
	public HashMap<String, String> getSchoolKVsBySchoolRefId(String schoolRefId) {
		try {
			logger.debug("SchoolRefId: " + schoolRefId);
			SchoolWrapper wrapper = schoolDAO.findByRefId(new ControllerData(new MockHttpServletRequest(), new MockHttpServletResponse()), schoolRefId);
			Optional<SchoolIdentifier> schoolIdentifier = wrapper.getSchool().getSchoolIdentifiers()
					.stream()
					.filter(id -> id.getIdentificationSystemCode().equalsIgnoreCase("SEA"))
					.findFirst();
			if(schoolIdentifier.isPresent()) {
				logger.debug("SchoolId: " + schoolIdentifier.get().getSchoolId());
				return repository.getSchoolKVsBySchoolId(schoolRefId, schoolIdentifier.get().getSchoolId(), service.getAccessToken(key));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value = CACHE_SCHOOL_KV, key = "#schoolId", unless = "#result != null")
	public HashMap<String, String> getSchoolKVsBySchoolId(String schoolId) {
		try {
			return repository.getSchoolKVsBySchoolId(schoolId, schoolId, service.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Dynamic Filters */

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XLea'", unless = "#result != null")
	public XLeaFilter getXLeaFilter(String districtId, String appId) {
		return repository.getXLeaFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XSchool'", unless = "#result != null")
	public XSchoolFilter getXSchoolFilter(String districtId, String appId) {
		return repository.getXSchoolFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCalendar'", unless = "#result != null")
	public XCalendarFilter getXCalendarFilter(String districtId, String appId) {
		return repository.getXCalendarFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCourse'", unless = "#result != null")
	public XCourseFilter getXCourseFilter(String districtId, String appId) {
		return repository.getXCourseFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XRoster'", unless = "#result != null")
	public XRosterFilter getXRosterFilter(String districtId, String appId) {
		return repository.getXRosterFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStaff'", unless = "#result != null")
	public XStaffFilter getXStaffFilter(String districtId, String appId) {
		return repository.getXStaffFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStudent'", unless = "#result != null")
	public XStudentFilter getXStudentFilter(String districtId, String appId) {
		return repository.getXStudentFilter(districtId, appId, service.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XContact'", unless = "#result != null")
	public XContactFilter getXContactFilter(String districtId, String appId) {
		return repository.getXContactFilter(districtId, appId, service.getAccessToken(key));
	}
}
