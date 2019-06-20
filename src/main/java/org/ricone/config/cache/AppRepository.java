package org.ricone.config.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.config.model.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.ricone.config.cache.CacheConfig.*;

@Repository("Config:AppRepository")
public class AppRepository {
	private final static String AUTHORIZATION = "Authorization";
	private final Logger logger = LogManager.getLogger(this.getClass());


	@Caching(put = {@CachePut(value = CACHE_APP, key = "#result.id", condition = "#result != null")})
	public App getAppByAppId(String appId, String token) {
		logger.debug("Getting App From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<App> response = rt.exchange((getUrl() + "app/" + appId), HttpMethod.GET, entity, App.class);
			return response.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_ACL, key = "#result.xml.appId", condition = "#result != null")})
	public DataXML getDataXMLByAppId(String appId, String token) {
		logger.debug("Getting DataXML From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<DataXML> response = rt.exchange((getUrl() + "app/" + appId + "/dataxml"), HttpMethod.GET, entity, DataXML.class);
			return response.getBody();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_DISTRICT, key = "#appId", condition = "#result != null")})
	public List<District> getDistrictsByAppId(String appId, String token) {
		logger.debug("Getting Districts From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<List<District>> response;
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			response = rt.exchange((getUrl() + "app/" + appId + "/district"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<District>>() {});
			return Objects.requireNonNull(response.getBody()).stream()
					.filter(district -> district.getEnabled() && district.getProviderId().equalsIgnoreCase(getProvider()))
					.collect(Collectors.toList());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Caching(put = {
		@CachePut(value = CACHE_DISTRICT_KV, key = "#schoolRefId", condition = "#result != null"),
		@CachePut(value = CACHE_DISTRICT_KV, key = "#districtId", condition = "#result != null")
	})
	public HashMap<String, String> getDistrictKVsByDistrictId(String schoolRefId, String districtId, String token) throws RestClientException {
		logger.debug("Getting DistrictKVs From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<DistrictKV[]> response = rt.exchange((getUrl() + "district/" + districtId + "/apikv"), HttpMethod.GET, entity, DistrictKV[].class);

			HashMap<String, String> infoMap = null;
			if(response.getBody() != null) {
				infoMap = new HashMap<>();
				for (DistrictKV info : response.getBody()) {
					infoMap.put(info.getField(), info.getValue());
				}
			}
			return infoMap;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	@Caching(put = {@CachePut(value = CACHE_SCHOOL, key = "#districtId", condition = "#result != null")})
	public List<School> getSchoolsByDistrictId(String districtId, String token) {
		logger.debug("Getting Schools From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<List<School>> response;
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			response = rt.exchange((getUrl() + "/district/" + districtId + "/school"), HttpMethod.GET, entity, new ParameterizedTypeReference<List<School>>() {});
			return response.getBody();
		}
		catch (Exception ignored) {
		}
		return null;
	}

	@Caching(put = {
		@CachePut(value = CACHE_SCHOOL_KV, key = "#schoolRefId", condition = "#result != null"),
		@CachePut(value = CACHE_SCHOOL_KV, key = "#schoolId", condition = "#result != null")
	})
	public HashMap<String, String> getSchoolKVsBySchoolId(String schoolRefId, String schoolId, String token) throws RestClientException {
		logger.debug("Getting SchoolKVs From Config: " + schoolRefId + " | " + schoolId);

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<SchoolKV[]> response = rt.exchange((getUrl() + "/school/" + schoolId + "/apikv"), HttpMethod.GET, entity, SchoolKV[].class);

			HashMap<String, String> infoMap = null;
			if(response.getBody() != null) {
				infoMap = new HashMap<>();
				for (SchoolKV info : response.getBody()) {
					infoMap.put(info.getField(), info.getValue());
				}
			}
			return infoMap;
		}
		catch (Exception ignored) {
		}
		return null;
	}

	/* Dynamic Filters */
	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XLea'", condition = "#result != null")})
	public XLeaFilter getXLeaFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XLea Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XLeaFilter> response = rt.exchange((getUrl() + "/xLeas/" + districtId + "_" + appId), HttpMethod.GET, entity, XLeaFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XLeaFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XLeaFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XSchool'", condition = "#result != null")})
	public XSchoolFilter getXSchoolFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XSchool Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XSchoolFilter> response = rt.exchange((getUrl() + "/xSchools/" + districtId + "_" + appId), HttpMethod.GET, entity, XSchoolFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XSchoolFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XSchoolFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCalendar'", condition = "#result != null")})
	public XCalendarFilter getXCalendarFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XCalendar Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XCalendarFilter> response = rt.exchange((getUrl() + "/xCalendars/" + districtId + "_" + appId), HttpMethod.GET, entity, XCalendarFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XCalendarFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XCalendarFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XCourse'", condition = "#result != null")})
	public XCourseFilter getXCourseFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XCourse Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XCourseFilter> response = rt.exchange((getUrl() + "/xCourses/" + districtId + "_" + appId), HttpMethod.GET, entity, XCourseFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XCourseFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XCourseFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XRoster'", condition = "#result != null")})
	public XRosterFilter getXRosterFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XRoster Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XRosterFilter> response = rt.exchange((getUrl() + "/xRosters/" + districtId + "_" + appId), HttpMethod.GET, entity, XRosterFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XRosterFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XRosterFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStaff'", condition = "#result != null")})
	public XStaffFilter getXStaffFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XStaff Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XStaffFilter> response = rt.exchange((getUrl() + "/xStaffs/" + districtId + "_" + appId), HttpMethod.GET, entity, XStaffFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XStaffFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XStaffFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XStudent'", condition = "#result != null")})
	public XStudentFilter getXStudentFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XStudent Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XStudentFilter> response = rt.exchange((getUrl() + "/xStudents/" + districtId + "_" + appId), HttpMethod.GET, entity, XStudentFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XStudentFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XStudentFilter();
		}
	}

	@Caching(put = {@CachePut(value = CACHE_FILTER, key = "#districtId + '_' + #appId + '_XContact'", condition = "#result != null")})
	public XContactFilter getXContactFilter(String districtId, String appId, String token) throws RestClientException {
		logger.debug("Getting XContact Filter: " + districtId + '_' + appId + " From Config");

		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.set(AUTHORIZATION, token);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			ResponseEntity<XContactFilter> response = rt.exchange((getUrl() + "/xContacts/" + districtId + "_" + appId), HttpMethod.GET, entity, XContactFilter.class);
			return response.getBody();
		}
		catch (Exception e) {
			logger.debug("\t \u21b3 Loading Default XContactFilter, " + districtId + "_" + appId + " failed to load from Config");
			return new XContactFilter();
		}
	}

	/* Other */
	private String getProvider() {
		return System.getenv("provider_id");
	}

	private String getUrl() {
		return System.getenv("config_url");
	}
}
