#CREATE VIEW ClassView AS

select 
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear,
	c.Title as Title,
    ci.CourseId as ClassCode,
    null as ClassType,
    c.CourseRefId as CourseId,
    c.CourseSchoolYear as CourseSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from coursesection as cs
join course as c 
	on c.CourseRefId = cs.CourseRefId
    and c.CourseSchoolYear = cs.CourseSchoolYear
left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School'
left join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear

