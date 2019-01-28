CREATE VIEW CourseView AS
select distinct
	c.CourseRefId as SourcedId, 
	c.CourseSchoolYear as SourcedSchoolYear,
	c.Title as Title,
    ci.CourseId as CourseCode,
    schc.SchoolCalendarRefId as AcademicSessionId,
    schc.SchoolCalendarSchoolYear as AcademicSessionSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from course as c

left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School'
    
left join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear
    
left join schoolcalendar schc
	on schc.SchoolRefId = sch.SchoolRefId
	and schc.SchoolSchoolYear = sch.SchoolSchoolYear
    #and now() between scs.BeginDate and scs.EndDate
;