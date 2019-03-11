CREATE VIEW ClassView_GroupConcat AS
select distinct
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear,
	l.leaId as DistrictId,
	c.Title as Title,
    ci.CourseId as ClassCode,
    'scheduled' as ClassType,
    css.ClassroomIdentifier as Location,
    (
		select GROUP_CONCAT( replace(GradeLevelCode, ',', ''))
        from coursegrade as sub_cg 
		where sub_cg.CourseRefId = c.CourseRefId
        and sub_cg.CourseSchoolYear = c.CourseSchoolYear
        group by sub_cg.CourseRefId
    ) as Grades,
    (
		select GROUP_CONCAT( replace(SubjectCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as Subjects,
    c.CourseRefId as CourseId,
    c.CourseSchoolYear as CourseSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear,
    (
		select GROUP_CONCAT( replace(SCEDCourseCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as SubjectCodes,
    (
		select GROUP_CONCAT( replace(ClassPeriod, ',', ''))
        from coursesectionschedule as sub_css 
		where sub_css.CourseSectionRefId = css.CourseSectionRefId
        and sub_css.CourseSectionSchoolYear = css.CourseSectionSchoolYear
        group by sub_css.CourseSectionRefId
    ) as Periods
from coursesection as cs

join course as c 
	on c.CourseRefId = cs.CourseRefId
    and c.CourseSchoolYear = cs.CourseSchoolYear

join school as sch
	on sch.SchoolRefId = c.SchoolRefId
    and sch.SchoolSchoolYear = c.SchoolSchoolYear

join lea as l 
		on l.LEARefId = sch.LEARefId 
        and l.LEASchoolYear = sch.LEASchoolYear   

    
/*left join schoolcalendarsession scs
	on scs.SchoolCalendarSessionRefId = cs.SchoolCalendarSessionRefId
	and scs.SchoolCalendarSessionSchoolYear = cs.SchoolCalendarSessionSchoolYear
    and now() between scs.BeginDate and scs.EndDate*/


left join coursesectionschedule css
	on css.CourseSectionRefId = cs.CourseSectionRefId
    and css.CourseSectionSchoolYear = cs.CourseSectionSchoolYear
    
left join courseidentifier as ci 
	on c.CourseRefId = ci.CourseRefId
    and c.CourseSchoolYear = ci.CourseSchoolYear
    and ci.IdentificationSystemCode = 'School'
;

