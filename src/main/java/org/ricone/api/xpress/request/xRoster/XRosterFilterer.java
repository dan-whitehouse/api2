package org.ricone.api.xpress.request.xRoster;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XRosterFilter;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("XRosterFilterer")
public class XRosterFilterer {
    public XRosterFilterer() {
    }

    XRostersResponse apply(XRostersResponse response, ControllerData metaData) {
        Iterator<XRoster> iterator = response.getXRosters().getXRoster().iterator();
        while (iterator.hasNext()) {
            XRoster i = iterator.next();
            i = filter(i, FilterCache.getInstance().getXRosterFilter(i.getDistrictId(), metaData.getApplication().getApp()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXRosters().getXRoster())) {
            return null;
        }
        return response;
    }

    XRosterResponse apply(XRosterResponse response, ControllerData metaData) {
        response.setXRoster(filter(response.getXRoster(), FilterCache.getInstance().getXRosterFilter(response.getXRoster().getDistrictId(), metaData.getApplication().getApp())));
        if (response.getXRoster().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XRoster filter(XRoster instance, XRosterFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }

        //Course & School Data
        if(!filter.getCourseRefId()) {
            instance.setCourseRefId(null);
        }
        if(!filter.getCourseTitle()) {
            instance.setCourseTitle(null);
        }
        if(!filter.getSubject()) {
            instance.setSubject(null);
        }
        if(!filter.getSchoolRefId()) {
            instance.setSchoolRefId(null);
        }

        //Calendar
        if(!filter.getSchoolYear()) {
            instance.setSchoolYear(null);
        }
        if(!filter.getSchoolSectionId()) {
            instance.setSchoolSectionId(null);
        }

        //Meeting Times
        if(instance.getMeetingTimes() != null) {
            for (MeetingTime i : instance.getMeetingTimes().getMeetingTime()) {
                if(!filter.getMeetingTimesmeetingTimeroomNumber()) {
                    i.setRoomNumber(null);
                }
                if(!filter.getMeetingTimessessionCode()) {
                    i.setSessionCode(null);
                }
                if(!filter.getMeetingTimesmeetingTimeTableDay()) {
                    i.setTimeTableDay(null);
                }
                if(!filter.getMeetingTimesmeetingTimetimeTablePeriod()) {
                    i.setTimeTablePeriod(null);
                }
                if(!filter.getMeetingTimesmeetingTimeclassBeginningTime()) {
                    i.setClassBeginningTime(null);
                }
                if(!filter.getMeetingTimesmeetingTimeclassEndingTime()) {
                    i.setClassEndingTime(null);
                }

                //Class Meeting Days
                if(i.getClassMeetingDays() != null) {
                    if(!filter.getMeetingTimesmeetingTimeclassMeetingDaysbellScheduleDay()) {
                        i.getClassMeetingDays().setBellScheduleDay(null);
                    }
                }

                //Calendar
                if(!filter.getMeetingTimesschoolCalendarRefId()) {
                    i.setSchoolCalendarRefId(null);
                }
            }
        }


        //Students
        if(instance.getStudents() != null) {
            for (StudentReference i : instance.getStudents().getStudentReference()) {
                if(!filter.getStudentsstudentReferencerefID()) {
                    i.setRefId(null);
                }
                if(!filter.getStudentsstudentReferencelocalId()) {
                    i.setLocalId(null);
                }
                if(!filter.getStudentsstudentReferencegivenName()) {
                    i.setGivenName(null);
                }
                if(!filter.getStudentsstudentReferencefamilyName()) {
                    i.setFamilyName(null);
                }
            }
        }


        //Primary Staff
        if(instance.getPrimaryStaff() != null) {
            if(instance.getPrimaryStaff().getStaffPersonReference() != null) {
                if(!filter.getPrimaryStaffstaffPersonReferencerefId()) {
                    instance.getPrimaryStaff().getStaffPersonReference().setRefId(null);
                }
                if(!filter.getPrimaryStaffstaffPersonReferencelocalId()) {
                    instance.getPrimaryStaff().getStaffPersonReference().setLocalId(null);
                }
                if(!filter.getPrimaryStaffstaffPersonReferencegivenName()) {
                    instance.getPrimaryStaff().getStaffPersonReference().setGivenName(null);
                }
                if(!filter.getPrimaryStaffstaffPersonReferencefamilyName()) {
                    instance.getPrimaryStaff().getStaffPersonReference().setFamilyName(null);
                }

                if(!filter.getPrimaryStaffstaffPersonReferencefamilyName()) {
                    instance.getPrimaryStaff().getStaffPersonReference().setFamilyName(null);
                }
            }
            if(!filter.getPrimaryStaffpercentResponsible()) {
                instance.getPrimaryStaff().setPercentResponsible(null);
            }
            if(!filter.getPrimaryStaffteacherOfRecord()) {
                instance.getPrimaryStaff().setTeacherOfRecord(null);
            }
        }

        //Other Staffs
        if(instance.getOtherStaffs() != null) {
            for (OtherStaff i : instance.getOtherStaffs().getOtherStaff()) {
                if(i.getStaffPersonReference() != null) {
                    if(!filter.getPrimaryStaffstaffPersonReferencerefId()) {
                        i.getStaffPersonReference().setRefId(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencelocalId()) {
                        i.getStaffPersonReference().setLocalId(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencegivenName()) {
                        i.getStaffPersonReference().setGivenName(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencefamilyName()) {
                        i.getStaffPersonReference().setFamilyName(null);
                    }
                }
                if(!filter.getPrimaryStaffpercentResponsible()) {
                    i.setPercentResponsible(null);
                }
                if(!filter.getPrimaryStaffteacherOfRecord()) {
                    i.setTeacherOfRecord(null);
                }
            }
        }
        return instance;
    }
}