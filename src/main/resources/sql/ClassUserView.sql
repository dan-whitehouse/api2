CREATE VIEW ClassUserView AS
(
	select 
		scs.StudentRefId as UserId,
		scs.StudentSchoolYear as UserSchoolYear,
		'student' as Role,
		scs.CourseSectionRefId as SourcedId,
		scs.CourseSectionSchoolYear as SourcedSchoolYear
	from studentcoursesection as scs
	join coursesection cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
)
union all
(
	select 
		scs.StaffRefId,
		scs.StaffSchoolYear,
		'staff',
		scs.CourseSectionRefId,
		scs.CourseSectionSchoolYear
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