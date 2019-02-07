CREATE VIEW EnrollmentView AS
(
	select 
		scs.StudentCourseSectionRefId as SourcedId,
        scs.StudentCourseSectionSchoolYear as SourcedSchoolYear,
        l.leaId as DistrictId,
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
	join coursesection as cs 
		on scs.CourseSectionRefId = cs.CourseSectionRefId
        and scs.CourseSectionSchoolYear= cs.CourseSectionSchoolYear
	left join coursesectionschedule as css
		on cs.CourseSectionRefId = css.CourseSectionRefId
        and cs.CourseSectionSchoolYear = css.CourseSectionSchoolYear
	left join schoolcalendarsession as schcs 
		on css.SchoolCalendarSessionRefId = schcs.SchoolCalendarSessionRefId
        and css.SchoolCalendarSessionSchoolYear = schcs.SchoolCalendarSessionSchoolYear
	join course as c 
		on cs.CourseRefId = c.CourseRefId
        and cs.CourseSchoolYear = c.CourseSchoolYear
	join school as sch
		on sch.SchoolRefId = c.SchoolRefId
        and sch.SchoolSchoolYear = c.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear  
)
union all
(
	select 
		scs.StaffCourseSectionRefId,
        scs.StaffCourseSectionSchoolYear,
        l.leaId as DistrictId,
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
	join school as sch
		on sch.SchoolRefId = c.SchoolRefId
        and sch.SchoolSchoolYear = c.SchoolSchoolYear
	join lea as l 
		on l.LEARefId = sch.LEARefId 
		and l.LEASchoolYear = sch.LEASchoolYear  
)
order by UserId, UserSchoolYear;

#select * from EnrollmentView;