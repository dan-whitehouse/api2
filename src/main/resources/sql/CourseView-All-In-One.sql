/*
	DW: Due to One Roster Filtering, I am thinking that maybe these arrays need to be stored in a single field.
	-------------------------------------------------------------------------------------------------------------
	1R: 
	When filtering on objects that are arrays the application of the filter depends on the nature of the comparison. 

	So in the case of filtering on the 'subjects' field when the value of the field is "subject1,subject2,subject3" the following filters would return:
		• ?filter="subject=subject1" - record not returned;
		• ?filter="subject=subject1,subject2" - record not returned;
		• ?filter="subject=subject1,subject2,subject3" - record returned;
		• ?filter="subject~subject1" - record returned;
		• ?filter="subject~subject1,subject2" - record returned;
		• ?filter="subject~subject1,subject2,subject3" - record returned.
	 
	DW:
	In the first bullet, where it says subject = 'subject 1' returns no results. 
	If these subjects were stored in a sub table, the join would return a record... which is said to be incorrect.
	 
	select * 
	from CourseView as cv
	join CourseGradeView as cgv on cv.sourcedId = cgv.sourcedId
	where cgv.gradeLevel = 'subject 1'
	 
	However, if this was a single column in the CourseView view, then it would not return a record 
	because the field would actaully have a value of 'subject1,subject2,subject3'
*/

CREATE VIEW CourseView_GroupConcat AS
select distinct
	c.CourseRefId as SourcedId, 
	c.CourseSchoolYear as SourcedSchoolYear,
    l.leaId as DistrictId,
	c.Title as Title,
    ci.CourseId as CourseCode,
    (
		select GROUP_CONCAT(distinct replace(GradeLevelCode, ',', ''))
        from coursegrade as sub_cg 
		where sub_cg.CourseRefId = c.CourseRefId
        and sub_cg.CourseSchoolYear = c.CourseSchoolYear
        group by sub_cg.CourseRefId
    ) as Grades,
    (
		select GROUP_CONCAT(distinct replace(SubjectCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as Subjects,
     (
		select GROUP_CONCAT(distinct replace(SCEDCourseCode, ',', ''))
        from course as sub_c 
		where sub_c.CourseRefId = c.CourseRefId
        and sub_c.CourseSchoolYear = c.CourseSchoolYear
        group by sub_c.CourseRefId
    ) as SubjectCodes,
    
    schc.SchoolCalendarRefId as AcademicSessionId,
    schc.SchoolCalendarSchoolYear as AcademicSessionSchoolYear,
    sch.SchoolRefId as OrgId,
    sch.SchoolSchoolYear as OrgSchoolYear
from course as c

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
    
left join schoolcalendar schc
	on schc.SchoolRefId = sch.SchoolRefId
	and schc.SchoolSchoolYear = sch.SchoolSchoolYear
    #and now() between scs.BeginDate and scs.EndDate
where c.CourseSchoolYear = 2019
;