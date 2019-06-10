package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.SessionList;
import org.ricone.api.xpress.model.XCalendar;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XCalendarFilter;
import org.ricone.init.CacheService;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("XPress:XCalendars:XCalendarFilterer")
public class XCalendarFilterer {
    private final CacheService cacheService;

    public XCalendarFilterer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    XCalendarsResponse apply(XCalendarsResponse response, ControllerData metadata) {
        Iterator<XCalendar> iterator = response.getXCalendars().getXCalendar().iterator();
        while (iterator.hasNext()) {
            XCalendar i = iterator.next();
            i = filter(i, cacheService.getXCalendarFilter(i.getDistrictId(), metadata.getApplication().getApp().getId()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXCalendars().getXCalendar())) {
            return null;
        }
        return response;
    }

    XCalendarResponse apply(XCalendarResponse response, ControllerData metadata) {
        response.setXCalendar(filter(response.getXCalendar(), cacheService.getXCalendarFilter(response.getXCalendar().getDistrictId(), metadata.getApplication().getApp().getId())));
        if (response.getXCalendar().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XCalendar filter(XCalendar instance, XCalendarFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if(!filter.getSchoolRefId()) {
            instance.setSchoolRefId(null);
        }
        if(!filter.getSchoolYear()) {
            instance.setSchoolYear(null);
        }

        //Sessions
        if (instance.getSessions() != null) {
            for (SessionList i : instance.getSessions().getSessionList()) {
                if(!filter.getSessionssessionListdescription()) {
                    i.setDescription(null);
                }
                if(!filter.getSessionssessionListsessionCode()) {
                    i.setSessionCode(null);
                }
                if(!filter.getSessionssessionListsessionType()) {
                    i.setSessionType(null);
                }
                if(!filter.getSessionssessionListlinkedSessionCode()) {
                    i.setLinkedSessionCode(null);
                }
                if(!filter.getSessionssessionListmarkingTerm()) {
                    i.setMarkingTerm(null);
                }
                if(!filter.getSessionssessionListschedulingTerm()) {
                    i.setSchedulingTerm(null);
                }
                if(!filter.getSessionssessionListstartDate()) {
                    i.setStartDate(null);
                }
                if(!filter.getSessionssessionListendDate()) {
                    i.setEndDate(null);
                }
            }
        }

        return instance;
    }
}