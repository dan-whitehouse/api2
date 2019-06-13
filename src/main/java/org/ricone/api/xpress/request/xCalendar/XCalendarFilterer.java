package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.model.XCalendarFilter;
import org.ricone.config.cache.CacheService;
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
        if(instance.getSessions() != null) {
            instance.getSessions().getSessionList().forEach(session -> {
                if(!filter.getSessionssessionListdescription()) {
                    session.setDescription(null);
                }
                if(!filter.getSessionssessionListsessionCode()) {
                    session.setSessionCode(null);
                }
                if(!filter.getSessionssessionListsessionType()) {
                    session.setSessionType(null);
                }
                if(!filter.getSessionssessionListlinkedSessionCode()) {
                    session.setLinkedSessionCode(null);
                }
                if(!filter.getSessionssessionListmarkingTerm()) {
                    session.setMarkingTerm(null);
                }
                if(!filter.getSessionssessionListschedulingTerm()) {
                    session.setSchedulingTerm(null);
                }
                if(!filter.getSessionssessionListstartDate()) {
                    session.setStartDate(null);
                }
                if(!filter.getSessionssessionListendDate()) {
                    session.setEndDate(null);
                }
            });
            instance.getSessions().getSessionList().removeIf(SessionList::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getSessions().getSessionList())) {
                instance.setSessions(null);
            }
        }
        return instance;
    }
}