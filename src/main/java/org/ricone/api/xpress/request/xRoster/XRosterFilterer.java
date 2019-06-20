package org.ricone.api.xpress.request.xRoster;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.XRosterFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XRosters:XRosterFilterer")
public class XRosterFilterer {
    private final AppService cacheService;

    public XRosterFilterer(AppService cacheService) {
        this.cacheService = cacheService;
    }

    XRostersResponse apply(XRostersResponse response, ControllerData metadata) {
        //Filter All
        response.getXRosters().getXRoster().forEach(xRoster -> {
            filter(xRoster, cacheService.getXRosterFilter(xRoster.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXRosters().getXRoster().removeIf(XRoster::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXRosters().getXRoster())) {
            return null;
        }
        return response;
    }

    XRosterResponse apply(XRosterResponse response, ControllerData metadata) {
        filter(response.getXRoster(), cacheService.getXRosterFilter(response.getXRoster().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXRoster().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XRoster instance, XRosterFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if (!filter.getSectionRefId()) {
            instance.setSectionRefId(null);
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
            instance.getMeetingTimes().getMeetingTime().forEach(meetingTime -> {
                if(!filter.getMeetingTimesmeetingTimeroomNumber()) {
                    meetingTime.setRoomNumber(null);
                }
                if(!filter.getMeetingTimessessionCode()) {
                    meetingTime.setSessionCode(null);
                }
                if(!filter.getMeetingTimesmeetingTimeTableDay()) {
                    meetingTime.setTimeTableDay(null);
                }
                if(!filter.getMeetingTimesmeetingTimetimeTablePeriod()) {
                    meetingTime.setTimeTablePeriod(null);
                }
                if(!filter.getMeetingTimesmeetingTimeclassBeginningTime()) {
                    meetingTime.setClassBeginningTime(null);
                }
                if(!filter.getMeetingTimesmeetingTimeclassEndingTime()) {
                    meetingTime.setClassEndingTime(null);
                }

                //Class Meeting Days
                if(meetingTime.getClassMeetingDays() != null) {
                    if(!filter.getMeetingTimesmeetingTimeclassMeetingDaysbellScheduleDay()) {
                        meetingTime.getClassMeetingDays().setBellScheduleDay(null);
                    }

                    if (meetingTime.getClassMeetingDays().isEmptyObject()) {
                        meetingTime.setClassMeetingDays(null);
                    }
                }

                //Calendar
                if(!filter.getMeetingTimesschoolCalendarRefId()) {
                    meetingTime.setSchoolCalendarRefId(null);
                }
            });

            instance.getMeetingTimes().getMeetingTime().removeIf(MeetingTime::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getMeetingTimes().getMeetingTime())) {
                instance.setMeetingTimes(null);
            }
        }


        //Students
        if(instance.getStudents() != null) {
            instance.getStudents().getStudentReference().forEach(studentReference -> {
                if(!filter.getStudentsstudentReferencerefID()) {
                    studentReference.setRefId(null);
                }
                if(!filter.getStudentsstudentReferencelocalId()) {
                    studentReference.setLocalId(null);
                }
                if(!filter.getStudentsstudentReferencegivenName()) {
                    studentReference.setGivenName(null);
                }
                if(!filter.getStudentsstudentReferencefamilyName()) {
                    studentReference.setFamilyName(null);
                }
            });

            instance.getStudents().getStudentReference().removeIf(StudentReference::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getStudents().getStudentReference())) {
                instance.setStudents(null);
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

                // Remove object if empty
                if (instance.getPrimaryStaff().getStaffPersonReference().isEmptyObject()) {
                    instance.getPrimaryStaff().setStaffPersonReference(null);
                }
            }
            if(!filter.getPrimaryStaffpercentResponsible()) {
                instance.getPrimaryStaff().setPercentResponsible(null);
            }
            if(!filter.getPrimaryStaffteacherOfRecord()) {
                instance.getPrimaryStaff().setTeacherOfRecord(null);
            }

            // Remove object if empty
            if (instance.getPrimaryStaff().isEmptyObject()) {
                instance.setPrimaryStaff(null);
            }
        }

        //Other Staffs
        if(instance.getOtherStaffs() != null) {
            instance.getOtherStaffs().getOtherStaff().forEach(staff -> {
                if(staff.getStaffPersonReference() != null) {
                    if(!filter.getPrimaryStaffstaffPersonReferencerefId()) {
                        staff.getStaffPersonReference().setRefId(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencelocalId()) {
                        staff.getStaffPersonReference().setLocalId(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencegivenName()) {
                        staff.getStaffPersonReference().setGivenName(null);
                    }
                    if(!filter.getPrimaryStaffstaffPersonReferencefamilyName()) {
                        staff.getStaffPersonReference().setFamilyName(null);
                    }

                    // Make null so that the iterator can remove it if it is empty
                    if (staff.getStaffPersonReference().isEmptyObject()) {
                        staff.setStaffPersonReference(null);
                    }
                }
                if(!filter.getPrimaryStaffpercentResponsible()) {
                    staff.setPercentResponsible(null);
                }
                if(!filter.getPrimaryStaffteacherOfRecord()) {
                    staff.setTeacherOfRecord(null);
                }
            });

            instance.getOtherStaffs().getOtherStaff().removeIf(OtherStaff::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherStaffs().getOtherStaff())) {
                instance.setOtherStaffs(null);
            }
        }
    }
}