CREATE VIEW ClassSubjectView AS
select 
	cs.CourseSectionRefId as SubjectId,
    cs.CourseSectionSchoolYear as SubjectSchoolYear,
    c.SubjectCode as Subject,
    c.SCEDCourseCode as SubjectCode,
	cs.CourseSectionRefId as SourcedId, 
	cs.CourseSectionSchoolYear as SourcedSchoolYear
from coursesection as cs
join course as c
	on c.CourseRefId = cs.CourseRefId
    and c.CourseSchoolYear = cs.CourseSchoolYear
where c.SubjectCode is not null 
or c.SCEDCourseCode is not null;

