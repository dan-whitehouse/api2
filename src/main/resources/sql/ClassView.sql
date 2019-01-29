CREATE VIEW ClassView AS
select distinct
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear,
	l.leaId as DistrictId,
	c.Title as Title,
    ci.CourseId as ClassCode,
    'scheduled' as ClassType,
    csc.ClassroomIdentifier as Location,
    c.CourseRefId as CourseId,
    c.CourseSchoolYear as CourseSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from coursesection as cs
left join coursesectionschedule csc
	on csc.CourseSectionRefId = cs.CourseSectionRefId
    and csc.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
    
left join schoolcalendarsession scs
	on scs.SchoolCalendarSessionRefId = cs.SchoolCalendarSessionRefId
	and scs.SchoolCalendarSessionSchoolYear = cs.SchoolCalendarSessionSchoolYear
    and now() between scs.BeginDate and scs.EndDate
    
join course as c 
	on c.CourseRefId = cs.CourseRefId
    and c.CourseSchoolYear = cs.CourseSchoolYear
    
left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School'
    
join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear

join lea as l 
		on l.LEARefId = sch.LEARefId 
        and l.LEASchoolYear = sch.LEASchoolYear    

;

