package org.ricone.config.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.ricone.config.FilterService;
import org.ricone.config.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class FilterCache {
	private Logger logger = LoggerFactory.getLogger(FilterCache.class);
	private static FilterCache instance = null;
	private final LoadingCache<String, IFilter<?>> cache;
	private static final int EXPIRE = 20;
	private final String SEPARATOR = "_";

	public static FilterCache getInstance() {
		if (instance == null) {
			instance = new FilterCache();
		}
		return instance;
	}

	public FilterCache() {
		cache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE, TimeUnit.MINUTES).build(new CacheLoader<String, IFilter<?>>() {
			@Override
			public IFilter<?> load(String cacheId) {
			//logger.debug("Loading Filter from Config: " + cacheId);
			return loadCache(cacheId);
			}
		});
	}

	public IFilter<?> get(String cacheId) {
		//logger.debug("Loading Filter from Cache: " + cacheId);
		return cache.getUnchecked(cacheId);
	}

	public void put(String cacheId, IFilter<?> filter) {
		cache.put(cacheId, filter);
	}

	public void clearCache() {
		cache.invalidateAll();
	}

	public void clearCache(String cacheId) {
		cache.invalidate(cacheId);
	}

	private IFilter<?> loadCache(String cacheId) {
		final String districtId = getDistrictIdFromKey(cacheId);
		final String appId = getAppIdFromKey(cacheId);
		final String filterType = getFilterTypeFromKey(cacheId);

		try {
			if ("XLea".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXLeaFilter(districtId, appId);
			}
			else if ("XSchool".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXSchoolFilter(districtId, appId);
			}
			else if ("XCalendar".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXCalendarFilter(districtId, appId);
			}
			else if ("XCourse".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXCourseFilter(districtId, appId);
			}
			else if ("XRoster".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXRosterFilter(districtId, appId);
			}
			else if ("XStaff".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXStaffFilter(districtId, appId);
			}
			else if ("XStudent".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXStudentFilter(districtId, appId);
			}
			else if ("XContact".equalsIgnoreCase(filterType)) {
				return FilterService.getInstance().getXContactFilter(districtId, appId);
			}
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}

	// Custom Getters
	public XLeaFilter getXLeaFilter(String districtId, App app) {
		return (XLeaFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XLea");
	}

	public XSchoolFilter getXSchoolFilter(String districtId, App app) {
		return (XSchoolFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XSchool");
	}

	public XCalendarFilter getXCalendarFilter(String districtId, App app) {
		return (XCalendarFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XCalendar");
	}

	public XCourseFilter getXCourseFilter(String districtId, App app) {
		return (XCourseFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XCourse");
	}

	public XRosterFilter getXRosterFilter(String districtId, App app) {
		return (XRosterFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XRoster");
	}

	public XStaffFilter getXStaffFilter(String districtId, App app) {
		return (XStaffFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XStaff");
	}

	public XStudentFilter getXStudentFilter(String districtId, App app) {
		return (XStudentFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XStudent");
	}

	public XContactFilter getXContactFilter(String districtId, App app) {
		return (XContactFilter) FilterCache.getInstance().get(districtId + SEPARATOR + StringUtils.replace(app.getId(),"_", "|") + SEPARATOR + "XContact");
	}

	// Splitters
	private String getDistrictIdFromKey(String cacheId) {
		String[] parts = StringUtils.split(cacheId, SEPARATOR);
		return parts[0];
	}

	private String getAppIdFromKey(String cacheId) {
		String[] parts = StringUtils.split(cacheId, SEPARATOR);
		return parts[1];
	}

	private String getFilterTypeFromKey(String cacheId) {
		String[] parts = StringUtils.split(cacheId, SEPARATOR);
		return parts[2];
	}

}