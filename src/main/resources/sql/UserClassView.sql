CREATE VIEW UserClassView AS
(
	select 
		scs.CourseSectionRefId as ClassId,
		scs.CourseSectionSchoolYear as ClassSchoolYear,
        c.SchoolRefId as OrgId,
        c.SchoolSchoolYear as OrgSchoolYear,
		scs.StudentRefId as SourcedId,
		scs.StudentSchoolYear as SourcedSchoolYear,
		'student' as SourcedRole
	from studentcoursesection as scs
	join coursesection cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
)
union all
(
	select 
		scs.CourseSectionRefId,
		scs.CourseSectionSchoolYear,
        c.SchoolRefId,
        c.SchoolSchoolYear,
		scs.StaffRefId,
		scs.StaffSchoolYear,
		'staff'
	from staffcoursesection as scs
	join coursesection cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
)
order by SourcedId, SourcedSchoolYear;

select * from UserClassView;