CREATE VIEW EnrollmentView AS
(
	select 
		scs.StudentCourseSectionRefId as SourcedId,
        scs.StudentCourseSectionSchoolYear as SourcedSchoolYear,
        scs.StudentRefId as UserId,
		scs.StudentSchoolYear as UserSchoolYear,
		'student' as Role,
		scs.CourseSectionRefId as ClassId,
		scs.CourseSectionSchoolYear as ClassSchoolYear,
        null as `Primary`,
        null as BeginDate,
        null as EndDate,
        c.SchoolRefId as OrgId,
        c.SchoolSchoolYear as OrgSchoolYear
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
		scs.StaffCourseSectionRefId,
        scs.StaffCourseSectionSchoolYear,
        scs.StaffRefId,
		scs.StaffSchoolYear,
		'teacher',
		scs.CourseSectionRefId,
		scs.CourseSectionSchoolYear,
        scs.TeacherOfRecord,
        null, 
        null,
        c.SchoolRefId,
        c.SchoolSchoolYear
	from staffcoursesection as scs
	join coursesection cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
)
order by UserId, UserSchoolYear;

select * from EnrollmentView;