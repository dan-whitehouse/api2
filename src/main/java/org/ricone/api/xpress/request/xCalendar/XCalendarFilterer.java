package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.SessionList;
import org.ricone.api.xpress.model.XCalendar;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.XCalendarFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XCalendars:XCalendarFilterer")
public class XCalendarFilterer {
    private final AppService cacheService;

    public XCalendarFilterer(AppService cacheService) {
        this.cacheService = cacheService;
    }

    XCalendarsResponse apply(XCalendarsResponse response, ControllerData metadata) {
        //Filter All
        response.getXCalendars().getXCalendar().forEach(xCalendar -> {
            filter(xCalendar, cacheService.getXCalendarFilter(xCalendar.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXCalendars().getXCalendar().removeIf(XCalendar::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXCalendars().getXCalendar())) {
            return null;
        }
        return response;
    }

    XCalendarResponse apply(XCalendarResponse response, ControllerData metadata) {
        filter(response.getXCalendar(), cacheService.getXCalendarFilter(response.getXCalendar().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXCalendar().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XCalendar instance, XCalendarFilter filter) {
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
    }
}