package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.SchoolIdentifier;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.request.xSchool.XSchoolDAO;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.ricone.config.cache.CacheConfig.*;

@Service("CacheService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CacheService {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final CacheRepository cacheRepository;
	private final CacheService self; /* !Important */
	private final XSchoolDAO schoolDAO;
	private final static String key = "testKey";

	/*
		Spring Cache @Cacheable - not working while calling from another method of the same bean

		This is because of the way proxies are created for handling caching, transaction related functionality in Spring.
		This is a very good reference of how Spring handles it - Transactions, Caching and AOP: understanding proxy usage in Spring.

		In short, a self call bypasses the dynamic proxy and any cross cutting concern like caching,
		transaction etc which is part of the dynamic proxies logic is also bypassed.

		Solution: Self reference, through autowiring in the constructor

		See:
			https://stackoverflow.com/questions/12115996/spring-cache-cacheable-method-ignored-when-called-from-within-the-same-class
			https://stackoverflow.com/questions/16899604/spring-cache-cacheable-not-working-while-calling-from-another-method-of-the-s
			https://stackoverflow.com/a/34091265/2225183 <-- Solution Used
	 */


	public CacheService(CacheRepository cacheRepository, CacheService cacheService, XSchoolDAO schoolDAO) {
		logger.debug("Loading Service: " + "CacheService");
		this.cacheRepository = cacheRepository;
		this.self = cacheService;
		this.schoolDAO = schoolDAO;
	}

	@Cacheable(value = CACHE_TOKEN, key = "#providerId", unless = "#result != null")
	public String getAccessToken(String providerId) {
		return cacheRepository.getAccessToken(key);
	}

	@Cacheable(value = CACHE_APP, key = "#appId", unless = "#result != null")
	public App getAppById(String appId) {
		return cacheRepository.getAppByAppId(appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_ACL, key = "#appId", unless = "#result != null")
	public DataXML getDataXMLByAppId(String appId) {
		return cacheRepository.getDataXMLByAppId(appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_DISTRICT, key = "#appId", unless = "#result != null")
	public List<District> getDistrictsByAppId(String appId) {
		return cacheRepository.getDistrictsByAppId(appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_DISTRICT_KV, key = "#schoolRefId", unless = "#result != null")
	public HashMap<String, String> getDistrictKVsBySchoolRefId(String schoolRefId) {
		try {
			SchoolWrapper wrapper = schoolDAO.findByRefId(new ControllerData(new MockHttpServletRequest(), new MockHttpServletResponse()), schoolRefId);
			logger.debug("DistrictId: " + wrapper.getDistrictId());
			return cacheRepository.getDistrictKVsByDistrictId(schoolRefId, wrapper.getDistrictId(), self.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value = CACHE_DISTRICT_KV, key = "#districtId", unless = "#result != null")
	public HashMap<String, String> getDistrictKVsByDistrictId(String districtId) {
		try {
			return cacheRepository.getDistrictKVsByDistrictId(districtId, districtId, self.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Cacheable(value = CACHE_SCHOOL, key = "#districtId", unless = "#result != null")
	public List<School> getSchoolsByDistrictId(String districtId) {
		return cacheRepository.getSchoolsByDistrictId(districtId, self.getAccessToken(key));
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
				return cacheRepository.getSchoolKVsBySchoolId(schoolRefId, schoolIdentifier.get().getSchoolId(), self.getAccessToken(key));
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
			return cacheRepository.getSchoolKVsBySchoolId(schoolId, schoolId, self.getAccessToken(key));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Dynamic Filters */

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XLea'", unless = "#result != null")
	public XLeaFilter getXLeaFilter(String districtId, String appId) {
		return cacheRepository.getXLeaFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XSchool'", unless = "#result != null")
	public XSchoolFilter getXSchoolFilter(String districtId, String appId) {
		return cacheRepository.getXSchoolFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCalendar'", unless = "#result != null")
	public XCalendarFilter getXCalendarFilter(String districtId, String appId) {
		return cacheRepository.getXCalendarFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCourse'", unless = "#result != null")
	public XCourseFilter getXCourseFilter(String districtId, String appId) {
		return cacheRepository.getXCourseFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XRoster'", unless = "#result != null")
	public XRosterFilter getXRosterFilter(String districtId, String appId) {
		return cacheRepository.getXRosterFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStaff'", unless = "#result != null")
	public XStaffFilter getXStaffFilter(String districtId, String appId) {
		return cacheRepository.getXStaffFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStudent'", unless = "#result != null")
	public XStudentFilter getXStudentFilter(String districtId, String appId) {
		return cacheRepository.getXStudentFilter(districtId, appId, self.getAccessToken(key));
	}

	@Cacheable(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XContact'", unless = "#result != null")
	public XContactFilter getXContactFilter(String districtId, String appId) {
		return cacheRepository.getXContactFilter(districtId, appId, self.getAccessToken(key));
	}
}
